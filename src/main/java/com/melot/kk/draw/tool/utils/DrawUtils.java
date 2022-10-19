package com.melot.kk.draw.tool.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.melot.kk.draw.tool.constants.RewardTypeEnum;
import com.melot.kk.draw.tool.domain.DrawLevelDTO;
import com.melot.kk.draw.tool.domain.DrawOddsDTO;
import com.melot.kk.draw.tool.domain.DrawResultDTO;
import com.melot.kk.draw.tool.domain.RewardDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DrawUtils.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.utils-DrawUtils
 * date: 2022-3-7 15:26
 * version: 1.0.0
 */
@Slf4j
public class DrawUtils {

    public static DrawResultDTO getDrawGameResult(String configStr, int playCount, String joint) {
        DrawResultDTO result = new DrawResultDTO();

        List<DrawLevelDTO> allResultList = drawForConfig(configStr, playCount);
        if (CollectionUtil.isNotEmpty(allResultList)) {

            result.setAllResultList(allResultList);
            result.setFirstLevel(allResultList.stream()
                    .filter(level -> !level.getRewardList().get(0).getRewardType().equals(RewardTypeEnum.NO_REWARD))
                    .collect(Collectors.toList()).get(0).getLevel());
        }

        List<RewardDTO> sendRewardList = sumRewardListForSend(allResultList);
        if (CollectionUtil.isNotEmpty(sendRewardList)) {
            result.setRewardList(sendRewardList);
        }

        String rewardMsgContent = getRewardMsgContent(sendRewardList, joint);
        if (rewardMsgContent.length() > 0) {
            result.setRewardContent(rewardMsgContent);
        }

        return result;
    }

    /**
     * 根据配置出奖
     *
     * @param configStr 配置的json string
     * @param playCount 游玩次数
     * @return 全结果list
     */
    public static List<DrawLevelDTO> drawForConfig(String configStr, int playCount) {
        List<DrawLevelDTO> drawLevelConfigList = JSONUtil.parseArray(configStr).toList(DrawLevelDTO.class);
        List<DrawOddsDTO> oddsList = new ArrayList<>();
        int index = 1;
        double startRange = 1.0d;
        double endRange = 0;
        for (DrawLevelDTO rewardInfo : drawLevelConfigList) {
            DrawOddsDTO oddsDTO = new DrawOddsDTO();
            if (index == 1) {
                endRange = rewardInfo.getOdds() * 10;
            } else {
                startRange = endRange + 1;
                endRange += rewardInfo.getOdds() * 10;
            }
            oddsDTO.setStartRange(startRange);
            oddsDTO.setEndRange(endRange);
            oddsDTO.setDrawLevelDTO(rewardInfo);

            oddsList.add(oddsDTO);
            index++;
        }

        List<DrawLevelDTO> result = new ArrayList<>();
        for (int i = 0; i < playCount; i++) {
            //生成1-1000的随机正式
            double random = new Random().nextInt(1000) + 1;
            //判断落在哪个区域 返回抽奖数值
            for (DrawOddsDTO oddsDTO : oddsList) {
                if (random >= oddsDTO.getStartRange() && random <= oddsDTO.getEndRange()) {
                    result.add(oddsDTO.getDrawLevelDTO());
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 汇总奖励结果
     *
     * @param levelList 奖励list
     * @return 奖励结果
     */
    public static List<RewardDTO> sumRewardListForSend(List<DrawLevelDTO> levelList) {

        List<RewardDTO> rewardTogetherList = new ArrayList<>();
        //将所有奖励塞入待处理list
        levelList.forEach(list -> rewardTogetherList.addAll(list.getRewardList()));

        Map<String, RewardDTO> resultHashMap = new HashMap<>(100);
        List<RewardDTO> result;
        for (RewardDTO rewardConfig : rewardTogetherList) {
            //滤过未中奖结果
            String desc = rewardConfig.getDesc();
            if (rewardConfig.getRewardType().equals(RewardTypeEnum.NO_REWARD)) {
                continue;
            }

            int rewardId = rewardConfig.getRewardId();
            int rewardCount = rewardConfig.getRewardCount();
            RewardDTO cacheDTO = resultHashMap.get(desc);
            if (cacheDTO != null) {
                cacheDTO.setRewardCount(cacheDTO.getRewardCount() + rewardCount);
                resultHashMap.put(desc, cacheDTO);
            } else {
                RewardDTO newInsert = new RewardDTO();
                newInsert.setRewardId(rewardId);
                newInsert.setRewardType(rewardConfig.getRewardType());
                newInsert.setRewardCount(rewardCount);
                newInsert.setDesc(desc);

                if (rewardConfig.getExValue() != null) {
                    newInsert.setExValue(rewardConfig.getExValue());
                }
                resultHashMap.put(desc, newInsert);
            }
        }

        result = new ArrayList<>(resultHashMap.values());
        return result;
    }

    /**
     * 获取消息中奖励消息内容
     *
     * @param rewardList 奖励列表
     * @param joint      连接符号 默认为 +
     * @return 消息内容
     */
    public static String getRewardMsgContent(List<RewardDTO> rewardList, String joint) {
        StringBuilder content = new StringBuilder();
        for (RewardDTO resultRewardDTO : rewardList) {
            if (resultRewardDTO.getRewardType().equals(0) || "no reward".equals(resultRewardDTO.getDesc())) {
                continue;
            }

            joint = joint == null ? " + " : joint;
            if (content.length() == 0) {
                content.append(String.format(resultRewardDTO.getDesc(), resultRewardDTO.getRewardCount()));
            } else {
                content.append(joint).append(String.format(resultRewardDTO.getDesc(), resultRewardDTO.getRewardCount()));
            }
        }
        return content.toString();
    }
}

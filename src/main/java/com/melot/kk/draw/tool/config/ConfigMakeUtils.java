package com.melot.kk.draw.tool.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.gson.Gson;
import com.melot.kk.draw.tool.constants.ConfigException;
import com.melot.kk.draw.tool.utils.ResourceUtils;
import com.melot.kk.draw.tool.constants.RewardTypeEnumV2;
import com.melot.kk.draw.tool.domain.DrawDTO;
import com.melot.kk.draw.tool.domain.DrawExcelDTO;
import com.melot.kk.draw.tool.domain.DrawLevelDTO;
import com.melot.kk.draw.tool.domain.RewardDTO;
import com.melot.kk.draw.tool.utils.EnumUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ConfigMakeUtils.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.config-ConfigMakeUtils
 * date: 2022-3-8 16:08
 * version: 1.0.0
 */
@Slf4j
public class ConfigMakeUtils {

    private static final String DEFAULT_XML_PATH = "C:\\Users\\生如死劫\\Documents\\配置文件处理\\%s.xls";

    private static final String EVENT_DESC = "20210308_test";

    private static final Boolean TEST_SWITCH = Boolean.TRUE;

    public static void main(String[] args) {
        createXml();
    }

    private static void createXml() {
        try {
            List<DrawDTO> configResultList = new LinkedList<>();
//        String path = String.format(DEFAULT_XML_PATH, EVENT_DESC);
            String path = "C:\\Users\\生如死劫\\Documents\\配置文件处理\\工作簿1.xlsx";
            ExcelReader reader = ExcelUtil.getReader(path);


            List<DrawExcelDTO> excelList = reader.readAll(DrawExcelDTO.class);

            int maxIndex = getMaxIndexByList(excelList);
            for (int i = 1; i <= maxIndex; i++) {
                int processIndex = i;
                DrawDTO draw = new DrawDTO();


                List<DrawExcelDTO> collect = excelList.stream().filter(excelDTO -> excelDTO.getIndex().equals(processIndex)).collect(Collectors.toList());
                if (CollectionUtil.isEmpty(collect)) {
                    continue;
                }
                draw.setDrawLevelList(getDrawLevelList(collect));
                draw.setNeedPoint(collect.get(0).getPoint());
                draw.setDrawIndex(i);
                configResultList.add(draw);
            }

            System.out.println(new Gson().toJson(configResultList));
        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }

    private static List<DrawLevelDTO> getDrawLevelList(List<DrawExcelDTO> collect) throws ConfigException {
        int maxLevel = getMaxLevelByList(collect);
        List<DrawLevelDTO> result = new ArrayList<>();
        for (int i = 1; i <= maxLevel; i++) {
            int processLevel = i;
            DrawLevelDTO levelDTO = new DrawLevelDTO();
            List<DrawExcelDTO> levelList = collect.stream().filter(excelDTO -> excelDTO.getLevel().equals(processLevel)).collect(Collectors.toList());
            if (CollectionUtil.isEmpty(levelList)) {
                continue;
            }

            levelDTO.setLevel(i);
            levelDTO.setOdds(levelList.get(0).getOdds());
            levelDTO.setRewardList(getRewardDTOList(levelList));
            result.add(levelDTO);
        }
        return result;
    }

    private static List<RewardDTO> getRewardDTOList(List<DrawExcelDTO> levelList) {
        List<RewardDTO> result = new ArrayList<>();
        for (DrawExcelDTO drawExcelDTO : levelList) {
            RewardDTO rewardDTO = new RewardDTO();
            rewardDTO.setRewardCount(drawExcelDTO.getCount());
            RewardTypeEnumV2 rewardType = getTypeByStr(drawExcelDTO.getRewardType());
            String rewardName = drawExcelDTO.getRewardName();
            if (rewardType.equals(RewardTypeEnumV2.NO_REWARD)) {
                rewardDTO.setDesc(RewardTypeEnumV2.NO_REWARD.getDesc());
            } else {
                rewardDTO.setDesc(rewardName);
            }

            rewardDTO.setRewardType(rewardType.getType());
            rewardDTO.setRewardId(getIdByConfig(drawExcelDTO.getId(), rewardType));
            result.add(rewardDTO);
        }
        return result;
    }

    @SneakyThrows
    private static RewardTypeEnumV2 getTypeByStr(String rewardType) {
        Map<String, Object> typeMap = EnumUtils.getNameFieldMap(RewardTypeEnumV2.class, "desc");
        if (CollectionUtil.isEmpty(typeMap)) {
         throw new ConfigException("空配置");
        }
        RewardTypeEnumV2 enumV2 = (RewardTypeEnumV2) typeMap.get(rewardType);
        if (enumV2 == null) {
            throw new ConfigException("非法类型");
        }
        return enumV2;
    }

    @SneakyThrows
    private static Integer getIdByConfig(Integer excelId, RewardTypeEnumV2 rewardType) {
        Integer result = ResourceUtils.getIdForNoIdResource(rewardType);

        if (result == null) {
            if (TEST_SWITCH) {
                result = ResourceUtils.getRandomIdForTest(rewardType);
            } else {
                result = excelId;
            }
        }
        if (result == null) {
            throw new ConfigException("存在空ID单元格");
        }
        return result;
    }

    private static int getMaxIndexByList(List<DrawExcelDTO> excelList) {
        int defaultIndex = 0;
        for (DrawExcelDTO drawExcelDTO : excelList) {
            if (drawExcelDTO.getIndex() > defaultIndex) {
                defaultIndex = drawExcelDTO.getIndex();
            }
        }
        return defaultIndex;
    }

    private static int getMaxLevelByList(List<DrawExcelDTO> collect) {
        int maxLevel = 0;
        for (DrawExcelDTO drawExcelDTO : collect) {
            if (drawExcelDTO.getLevel() > maxLevel) {
                maxLevel = drawExcelDTO.getLevel();
            }
        }
        return maxLevel;
    }

    private static List<RewardDTO> getRewardConfigByStr(String reward, String id) {
        return null;
    }

}

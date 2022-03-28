package com.melot.kk.draw.tool.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.gson.Gson;
import com.melot.kk.draw.tool.constants.ConfigException;
import com.melot.kk.draw.tool.constants.RewardTypeEnumV2;
import com.melot.kk.draw.tool.domain.*;
import com.melot.kk.draw.tool.utils.EnumUtils;
import com.melot.kk.draw.tool.utils.ResourceUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
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
            SimpleDateFormat simpleDateFormat = DateUtil.newSimpleFormat("20212814");
            System.out.println(simpleDateFormat);

//        String path = String.format(DEFAULT_XML_PATH, EVENT_DESC);
            String path = "C:\\Users\\生如死劫\\Documents\\配置文件处理\\工作簿1.xlsx";
            ExcelReader reader = ExcelUtil.getReader(path, 1);

            List<ConfigExcelDTO> excelList = reader.readAll(ConfigExcelDTO.class);

            if (excelList.get(0).getOdds() == null) {
                showPointConfigListByExcel(excelList);
            } else {
                showDrawConfigListByExcel(excelList);
            }
        } catch (ConfigException e) {
            e.printStackTrace();
        }
    }

    private static void showPointConfigListByExcel(List<ConfigExcelDTO> excelList) throws ConfigException {
        List<PointDTO> configResultList = new LinkedList<>();
        int maxIndex = getMaxIndexByList(excelList);
        for (int i = 1; i <= maxIndex; i++) {
            int processIndex = i;
            PointDTO pointDTO = new PointDTO();


            List<ConfigExcelDTO> collect = excelList.stream().filter(excelDTO -> excelDTO.getIndex().equals(processIndex)).collect(Collectors.toList());
            if (CollectionUtil.isEmpty(collect)) {
                continue;
            }
            pointDTO.setRewardList(getRewardList(collect));
            pointDTO.setPoint(collect.get(0).getPoint());
            pointDTO.setIndex(i);
            configResultList.add(pointDTO);
        }
        System.out.println(new Gson().toJson(configResultList));
    }

    private static void showDrawConfigListByExcel(List<ConfigExcelDTO> excelList) throws ConfigException {
        List<DrawDTO> configResultList = new LinkedList<>();
        int maxIndex = getMaxIndexByList(excelList);
        for (int i = 1; i <= maxIndex; i++) {
            int processIndex = i;
            DrawDTO draw = new DrawDTO();
            List<ConfigExcelDTO> collect = excelList.stream().filter(excelDTO -> excelDTO.getIndex().equals(processIndex)).collect(Collectors.toList());
            if (CollectionUtil.isEmpty(collect)) {
                continue;
            }
            draw.setDrawLevelList(getDrawLevelList(collect));
            draw.setNeedPoint(collect.get(0).getPoint());
            draw.setDrawIndex(i);
            configResultList.add(draw);
        }
        System.out.println(new Gson().toJson(configResultList));
    }


    private static List<RewardDTO> getRewardList(List<ConfigExcelDTO> collect) {
        List<RewardDTO> result = new ArrayList<>();
        collect.forEach(excel -> processForReward(result, excel));
        return result;
    }

    private static void processForReward(List<RewardDTO> result, ConfigExcelDTO excel) {
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setRewardCount(excel.getCount());
        RewardTypeEnumV2 rewardType = getTypeByStr(excel.getRewardType());
        String rewardName = excel.getRewardName();
        if (rewardType.equals(RewardTypeEnumV2.NO_REWARD)) {
            rewardDTO.setDesc(RewardTypeEnumV2.NO_REWARD.getDesc());
        } else if (rewardType.equals(RewardTypeEnumV2.FOLLOW_CARD)) {
            rewardDTO.setDesc(RewardTypeEnumV2.FOLLOW_CARD.getDesc());
        } else if (rewardType.equals(RewardTypeEnumV2.SHOW_TAG)) {
            rewardDTO.setDesc(RewardTypeEnumV2.SHOW_TAG.getDesc());
        } else if (rewardType.equals(RewardTypeEnumV2.BEAN)) {
            rewardDTO.setDesc(RewardTypeEnumV2.BEAN.getDesc());
        } else if (rewardType.equals(RewardTypeEnumV2.GEM)) {
            rewardDTO.setDesc(RewardTypeEnumV2.GEM.getDesc());
        } else {
            rewardDTO.setDesc(rewardName);
        }

        rewardDTO.setRewardType(rewardType.getType());
        rewardDTO.setRewardId(getIdByConfig(excel.getId(), rewardType));
        result.add(rewardDTO);
    }


    private static List<DrawLevelDTO> getDrawLevelList(List<ConfigExcelDTO> collect) {
        int maxLevel = getMaxLevelByList(collect);
        List<DrawLevelDTO> result = new ArrayList<>();
        for (int i = 1; i <= maxLevel; i++) {
            int processLevel = i;
            DrawLevelDTO levelDTO = new DrawLevelDTO();
            List<ConfigExcelDTO> levelList = collect.stream().filter(excelDTO -> excelDTO.getLevel().equals(processLevel)).collect(Collectors.toList());
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

    private static List<RewardDTO> getRewardDTOList(List<ConfigExcelDTO> levelList) {
        List<RewardDTO> result = new ArrayList<>();
        levelList.forEach(excel -> processForReward(result, excel));
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

    private static int getMaxIndexByList(List<ConfigExcelDTO> excelList) {
        int defaultIndex = 0;
        for (ConfigExcelDTO configExcelDTO : excelList) {
            if (configExcelDTO.getIndex() > defaultIndex) {
                defaultIndex = configExcelDTO.getIndex();
            }
        }
        return defaultIndex;
    }

    private static int getMaxLevelByList(List<ConfigExcelDTO> collect) {
        int maxLevel = 0;
        for (ConfigExcelDTO configExcelDTO : collect) {
            if (configExcelDTO.getLevel() > maxLevel) {
                maxLevel = configExcelDTO.getLevel();
            }
        }
        return maxLevel;
    }

    private static List<RewardDTO> getRewardConfigByStr(String reward, String id) {
        return null;
    }

}

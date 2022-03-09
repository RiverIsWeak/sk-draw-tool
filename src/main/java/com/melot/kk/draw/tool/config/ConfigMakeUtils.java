package com.melot.kk.draw.tool.config;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.gson.Gson;
import com.melot.kk.draw.tool.domain.DrawExcelDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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

    private static final Boolean TEST_SWITCH = Boolean.FALSE;

    public static void main(String[] args) {
        createXml();
    }

    private static void createXml() {

        String path = String.format(DEFAULT_XML_PATH, EVENT_DESC);
        ExcelReader reader = ExcelUtil.getReader(path);
        List<DrawExcelDTO> all = reader.readAll(DrawExcelDTO.class);
        System.out.println(new Gson().toJson(all));
    }

}

package com.melot.kk.draw.tool.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.exception.NacosException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * ConfigMakeUtils.
 * 只适用于测试服（一键发布测试服的draw/point game配置）
 *
 * todo 1.配置对校验 2.新增配置 3.修改配置后删除旧配置 4.新增配置需要进行格式校验
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.config-ConfigMakeUtils
 * date: 2022-3-8 16:08
 * version: 1.0.0
 */
public class ConfigPushUtils {
    public static void main(String[] args) throws IOException {
        String dataId = "test.push";
        String group = "test.push";
        String oldConfig = getConfigFromNacos(dataId, group, null);
        assert oldConfig != null;
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(oldConfig.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));
        String str;
        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }
    }

    private static String getConfigFromNacos(String dataId, String group, String namespace) {
        try {
            String serverAddr = "nacos1.kktv2.com:8848,nacos2.kktv2.com:8848,nacos3.kktv2.com:8848";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            if (namespace != null) {
                properties.put("namespace", namespace);
            }
            ConfigService configService = NacosFactory.createConfigService(properties);
            return configService.getConfig(dataId, group, 5000);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private static void publishConfigToNacos() {
        try {
            // 初始化配置服务，控制台通过示例代码自动获取下面参数aaa
            String serverAddr = "nacos1.kktv2.com:8848,nacos2.kktv2.com:8848,nacos3.kktv2.com:8848";
            String dataId = "test.push";
            String group = "test.push";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String oldConfig = getConfigFromNacos(dataId, group, null);
            if (oldConfig != null) {
                oldConfig = oldConfig + "\n" + "baba=20";
            }
            boolean isPublishOk = configService.publishConfig(dataId, group, oldConfig, ConfigType.PROPERTIES.getType());
            System.out.println(isPublishOk);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

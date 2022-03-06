package com.melot.kk.draw.tool;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.melot.kk.draw.tool.domain.RewardDTO;

/**
 * Test.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool-Test
 * date: 2022-3-6 19:14
 * version: 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        String value = "{\"rewardId\":1,\"rewardCount\":10,\"rewardType\":1,\"desc\":\"hahahha\"}";
        RewardDTO rewardDTO = JSONUtil.parse(value).toBean(RewardDTO.class);
        System.out.println(new Gson().toJson(rewardDTO));
    }
}

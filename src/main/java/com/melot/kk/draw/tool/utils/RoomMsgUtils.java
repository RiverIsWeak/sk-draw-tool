package com.melot.kk.draw.tool.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * RoomMsgUtils.
 *
 * @author junjian.lan@melot.cn
 * description:
 * path:com.melot.kk.draw.tool.utils-RoomMsgUtils
 * date: 2022-3-7 15:27
 * version: 1.0.0
 */
@Slf4j
public class RoomMsgUtils {

    private static final String UTF_8 = "utf-8";

    public static void sendBigThing(int bigThingColor, String content, String eventUrl, String runWayUrl) {
        JsonObject msg = new JsonObject();
        msg.addProperty("MsgTag", 10010802);
        msg.addProperty("content", content);
        msg.addProperty("background", bigThingColor);
        msg.addProperty("url", eventUrl);
        sendMsgToRoom(1, 0, 0, 0, 1, msg.toString(), runWayUrl);
    }

    public static void sendRoomMsg(String content, String eventUrl, String runWayUrl) {
        JsonObject msgJson = new JsonObject();
        msgJson.addProperty("MsgTag", 10010368);
        msgJson.addProperty("type", 2);
        JsonArray contents = new JsonArray();
        JsonObject j = new JsonObject();

        j.addProperty("content", content);
        j.addProperty("type", 4);
        j.addProperty("mobileUrl", eventUrl);
        contents.add(j);
        msgJson.add("contents", contents);

        sendMsgToRoom(1, 0, 0, 0, 1, msgJson.toString(), runWayUrl);
    }

    public static void sendRoomMsgJumpRoom(String content, String roomUrl, String runWayUrl) {
        JsonObject msg;
        msg = new JsonObject();
        msg.addProperty("MsgTag", 10010368);
        msg.addProperty("type", 2);

        msg.addProperty("route", roomUrl);
        msg.addProperty("processLogicType", 1);

        JsonObject j = new JsonObject();
        JsonArray contents = new JsonArray();
        j.addProperty("content", content);
        contents.add(j);
        msg.add("contents", contents);

        sendMsgToRoom(1, 0, 0, 0, 1, msg.toString(), runWayUrl);
    }

    public void sendColorRoomMsg(String content, String eventUrl, String backgroundColor, String fontColor, String runWayUrl) {
        JsonObject msgJson = new JsonObject();
        msgJson.addProperty("MsgTag", 10010368);
        msgJson.addProperty("type", 2);
        JsonArray contents = new JsonArray();
        JsonObject j = new JsonObject();

        j.addProperty("content", content);
        j.addProperty("type", 4);
        j.addProperty("mobileUrl", eventUrl);
        j.addProperty("backgroundColor", backgroundColor);
        j.addProperty("color", fontColor);
        contents.add(j);
        msgJson.add("contents", contents);

        sendMsgToRoom(1, 0, 0, 0, 1, msgJson.toString(), runWayUrl);
    }

    private static void sendMsgToRoom(int type, int roomId, int userId, int platform, int appId, String msg, String runWayUrl) {
        HttpURLConnection urlCon;
        try {
            StringBuilder queryParamsBuffer = new StringBuilder("?type=" + type + "&msg=" + URLEncoder.encode(msg, UTF_8));
            if (roomId > 0) {
                queryParamsBuffer.append("&roomId=").append(roomId);
            }
            if (userId > 0) {
                queryParamsBuffer.append("&userId=").append(userId);
            }
            if (platform > 0) {
                queryParamsBuffer.append("&platform=").append(platform);
            }
            if (appId > 0) {
                queryParamsBuffer.append("&appId=").append(appId);
            }
            String queryParams = queryParamsBuffer.toString();
            log.info("SendMsgUtil sendRunwayMsg request: " + runWayUrl + queryParams);
            URL url = new URL(runWayUrl + queryParams);
            urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("GET");
            urlCon.setConnectTimeout(10000);
            urlCon.setReadTimeout(5000);
            urlCon.setDoInput(true);
        } catch (Exception e) {
            log.error("url.openConnection()", e);
            return;
        }
        try (
                InputStream in = urlCon.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(in, UTF_8));
        ) {
            StringBuilder tempStr = new StringBuilder();
            String tempLine;
            while ((tempLine = rd.readLine()) != null) {
                tempStr.append(tempLine);
            }
            log.info("SendMsgUtil sendRunwayMsg response: " + tempStr);
        } catch (Exception e) {
            log.error("房间跑道消息推送请求异常", e);
        } finally {
            urlCon.disconnect();
        }
    }
}

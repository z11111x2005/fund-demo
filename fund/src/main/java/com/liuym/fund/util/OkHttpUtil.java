package com.liuym.fund.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

/**
 * @author liuym
 * @date 2020/8/6 14:25
 * @description
 */
@Slf4j
public class OkHttpUtil {

    public static String get(String url) {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            log.error("GET REQUEST ERROR, URL={}", url);
        }
        return result;
    }

    public static String post(String url, String data) {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html;charset=utf-8"), data);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            log.error("POST REQUEST ERROR, URL={}, DATA={}", url, data);
        }
        return result;
    }
}

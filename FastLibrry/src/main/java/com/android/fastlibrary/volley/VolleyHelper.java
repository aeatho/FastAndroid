/*
 * Copyright (C) 2014 loQua.Xee <loquaciouser@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.fastlibrary.volley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.Volley;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie2;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.common.volley.Volley
 * @Description: TODO
 * @author: 席强    xiqiang@fugao.com
 * @date: 2014/7/28 9:28
 * @version: V1.0
 */

public class VolleyHelper {
    private String TAG = "";
    public static String BASE_URL = "";

    private static DefaultHttpClient httpClient;
    private static RequestQueue requestQueue;

    public VolleyHelper() {
    }

    public static void init(Context context) {
        if (requestQueue == null) {
            httpClient = new DefaultHttpClient();
            requestQueue = Volley.newRequestQueue(context, new HttpClientStack(httpClient));
        }
    }

    /**
     * @return Volley 查询队列
     */
    public static RequestQueue getRequestQueue() {
        if (requestQueue != null) {
            return requestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    /**
     * 添加Request到Volley队列中，使用自定义标记
     *
     * @param req
     * @param tag 标记request
     */
    public <T> void addRequest(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        VolleyLog.d("Adding request to queue: %s", req.getUrl());
        getRequestQueue().add(req);
    }

    public <T> void addRequest(Request<T> req) {
        addRequest(req, TAG);
    }

    /**
     * 根据tag停止request
     *
     * @param tag
     */
    public void cancelRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    /**
     * 设置cookie
     * <p/>
     * cookie必须在添加到队列之前设置好
     * setCookie();
     */
    public void setCookie() {
        CookieStore cs = httpClient.getCookieStore();
        cs.addCookie(new BasicClientCookie2("cookie", "spooky"));
    }
}
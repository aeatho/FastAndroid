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

import com.android.volley.Request;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.common.volley.NetworkUtil
 * @Description: TODO
 * @author: 席强    xiqiang@fugao.com
 * @date: 2014/7/31 0:38
 * @version: V1.0
 */

public class RequestManager<T> {
    private String TAG = "";
    private VolleyHelper helper;

    public RequestManager() {
        helper = new VolleyHelper();
    }

    public RequestManager(String tag) {
        this.TAG = tag;
        helper = new VolleyHelper();
    }

    public void cancelAll(Object tag) {
        helper.cancelRequests(tag);
    }

    public void get(String url, VolleyListener<String> listener) {
        StringRequest stringReq = new StringRequest(url, listener);
        helper.addRequest(stringReq, this.TAG);
    }

    public void post(String url, String jsonString, VolleyListener<String> listener) {
        StringRequest stringReq = new StringRequest(Request.Method.POST, url, jsonString, listener);
        helper.addRequest(stringReq, this.TAG);
    }

    public void put(String url, String jsonString, VolleyListener<String> listener) {
        StringRequest stringReq = new StringRequest(Request.Method.PUT, url, jsonString, listener);
        helper.addRequest(stringReq, this.TAG);
    }

    public void delete(String url, String jsonString, VolleyListener<String> listener) {
        StringRequest stringReq = new StringRequest(Request.Method.DELETE, url, jsonString,
                listener);
        helper.addRequest(stringReq, this.TAG);
    }
}

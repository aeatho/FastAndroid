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

import android.util.Log;

import com.android.volley.*;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;


/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.common.volley.GsonRequest
 * @Description: 封装好的GsonRequest请求类
 * @author: 席强    xiqiang@fugao.com
 * @date: 2014/7/28 0:31
 * @version: V1.0
 */

public class GsonRequest<T> extends Request<T> {
    //    private static String TAG = JacksonRequest.class.getSimpleName();
    private static final String TAG = "Fugao-Volley-GsonRequest";

    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; " +
            "charset=%s", PROTOCOL_CHARSET);

    private final Class<T> clazz;
    private final Map<String,String> headers;
    private final Listener<T> listener;
    private final static Gson gson = new Gson();

    private String postString = null;

    /**
     * 构建GsonRequest请求
     *
     * @param method       Volley.Method.Get/Post/Put/Delete
     * @param url          请求URL
     * @param clazz        请求或返回的类型
     * @param headers      HTTP头.默认为空,当不为get请求时,默认发application/json; charset=utf-8
     * @param requestParam 请求参数,,,参数类型可以为Map<String, Object>也可以为json字符串等
     * @param listener     返回成功监听
     */
    public GsonRequest(int method, String url, Class<T> clazz, Map<String,String> headers,
                       Object requestParam, VolleyListener<T> listener) {
        super(method, VolleyHelper.BASE_URL + url, listener);

        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;

        setRetryPolicy(new DefaultRetryPolicy(12 * 1000, 1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        if (method != Method.GET && requestParam != null) {
            postString = gson.toJson(requestParam);
            headers.put("Content-Type", PROTOCOL_CONTENT_TYPE);
        }
    }

    public GsonRequest(int method, String url, Class<T> clazz, Object obj,
                       VolleyListener<T> listener) {
        this(method, url, clazz, null, obj, listener);
    }

    public GsonRequest(String url, Class<T> clazz, VolleyListener<T> listener) {
        this(Method.GET, url, clazz, null, listener);
    }

    @Override
    public Map<String,String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public String getBodyContentType() {
        return postString != null ? PROTOCOL_CONTENT_TYPE : super.getBodyContentType();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return postString != null ? postString.getBytes(Charset.forName(PROTOCOL_CHARSET)) :
                super.getBody();
    }

    @Override
    protected Response<T> parseNetworkResponse(final NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response
                    .headers));
            try {
                Log.d(TAG, getUrl() + "   >>> response: " + (new JSONObject(json)).toString(4));
            } catch (JSONException e) {
                Log.d(TAG, getUrl() + "   >>> response(parse exception!): " + json);
            }
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser
                    .parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(final T response) {
        listener.onResponse(response);
    }
}

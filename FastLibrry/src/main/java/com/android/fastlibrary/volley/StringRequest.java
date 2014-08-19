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

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.common.volley.FugaoRequest
 * @Description: TODO
 * @author: 席强    xiqiang@fugao.com
 * @date: 2014/7/31 1:06
 * @version: V1.0
 */

public class StringRequest extends Request<String> {
    private static final String TAG = "Fugao-Volley-StringRequest";

    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; " +
            "charset=%s", PROTOCOL_CHARSET);

    private final Listener<String> mListener;
    private Map<String,String> headers = new HashMap<String,String>();
    private final static Gson gson = new Gson();
    private String postString = null;

    public StringRequest(int method, String url, Object requestParam,
                         VolleyListener<String> listener) {
        super(method, VolleyHelper.BASE_URL + url, listener);
        this.mListener = listener;
        setRetryPolicy(new DefaultRetryPolicy(12 * 1000, 1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        if (method != Method.GET && requestParam != null) {
            postString = gson.toJson(requestParam);
            headers.put("Content-Type", PROTOCOL_CONTENT_TYPE);
        }
    }

    public StringRequest(String url, VolleyListener<String> listener) {
        this(Method.GET, url, null, listener);
    }

    @Override
    protected VolleyError parseNetworkError(final VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
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
    protected Response<String> parseNetworkResponse(final NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        Log.d(TAG, getUrl() + " >>>> response >>>>" + parsed);

        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(final String response) {
        mListener.onResponse(response);
    }
}

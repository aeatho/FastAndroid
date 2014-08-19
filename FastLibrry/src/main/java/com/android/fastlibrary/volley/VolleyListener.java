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

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.http.RequestListener
 * @Description: TODO
 * @author: 席强    xiqiang@fugao.com
 * @date: 2014/7/30 22:27
 * @version: V1.0
 */

public interface VolleyListener<T> extends Listener<T>, ErrorListener {}

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

package com.android.fastlibrary.util.app;

import android.content.Context;

import com.android.fastlibrary.AppManager;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastlibrary.util.app.ExitDoubleClick
 * @Description: TODO
 * @author: loQua.Xee    loquaciouser@gmail.com
 * @date: 2014/8/19 18:59
 * @version: V1.0
 */

public class ExitDoubleClick extends DoubleClick {
    private static final String TAG = "FastAndroid-ExitDoubleClick";

    private static ExitDoubleClick exit;


    /**
     * 构造方法，初始化Context对象及开始的任务时间。
     *
     * @param context
     */
    public ExitDoubleClick(final Context context) {
        super(context);
        setDoubleClickListener(new DoubleClickListener() {
            @Override
            public void afteDoubleClick() {
                AppManager.getInstance().appExit(mContext);
                exit = null;
            }
        });
    }

    /**
     * 返回一个双击退出的实例。
     *
     * @param context
     * @return ExitDoubleClick
     */
    public static synchronized ExitDoubleClick getInstance(Context context) {
        if (exit == null) {
            exit = new ExitDoubleClick(context);
        }
        return exit;
    }

    /**
     * 双击退出Activity，如果msg为null，而默认显示的提示语为"再按一次退出"。
     */
    @Override
    public void doDoubleClick(int delayTime, String msg) {
        if (msg == null || msg.equals("")) {
            msg = "再按一次退出";
        }
        super.doDoubleClick(delayTime, msg);
    }

}

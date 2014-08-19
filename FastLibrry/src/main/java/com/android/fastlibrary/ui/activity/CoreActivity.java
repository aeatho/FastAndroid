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

package com.android.fastlibrary.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.fastlibrary.AppManager;

import butterknife.ButterKnife;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.androd.fastibrary.ui.activity.BaseActivity
 * @Description: TODO 基本核心模板....不建议读者更改
 * @author: loQua.Xee    loquaciouser@gmail.com
 * @date: 2014/8/16 14:00
 * @version: V1.0
 */

public abstract class CoreActivity extends FragmentActivity {
    private static final String TAG = "Fugao-CoreActivity";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 将当前activity加入到栈顶
         */
        AppManager.getInstance().addActivity(this);
        setRootView();
        ButterKnife.inject(this);
        initialize();
    }

    private void initialize() {
        initWidget();
        new Thread(new Runnable() {
            @Override
            public void run() {
                initAsyncData();
            }
        }).start();
        initData();
        registerBroadcast();
        initListener();
    }

    /**
     * 初始化界面根布局
     */
    protected abstract void setRootView();

    /**
     * 初始化布局控件
     */
    protected void initWidget() {}

    /**
     * 异步初始化数据,不能直接更改主线程中控件
     */
    protected void initAsyncData() {}

    /**
     * 同步初始化数据
     */
    protected void initData() {}

    /**
     * 初始化时间监听
     */
    protected void initListener(){}

    /**
     * 注册广播
     */
    protected void registerBroadcast() {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 取消注册广播,并将当前ativity移除堆栈并销毁
         */
        unRegisterBroadcast();
        AppManager.getInstance().finishActivity(this);
    }

    protected void unRegisterBroadcast() {}
}

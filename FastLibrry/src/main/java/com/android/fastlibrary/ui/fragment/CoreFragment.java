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

package com.android.fastlibrary.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fastlibrary.ui.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastlibrary.ui.fragment.CoreFragment
 * @Description: TODO
 * @author: loQua.Xee    loquaciouser@gmail.com
 * @date: 2014/8/16 15:54
 * @version: V1.0
 */

public abstract class CoreFragment extends Fragment {
    private static final String TAG = "Fugao-CoreFragment";

    /**
     * 当前activity
     */
    public BaseActivity fatherActivity;
    /**
     * 当前视图
     */
    public View currentView;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.fatherActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentView = setRootView(inflater, container, savedInstanceState);
        ButterKnife.inject(currentView);
        initWidget(currentView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                initAsyncData();
            }
        }).start();
        initData();
        return currentView;
    }

    protected abstract View setRootView(LayoutInflater inflater, ViewGroup container,
                                        Bundle bundle);

    /**
     * 初始化布局控件
     *
     * @param view
     */
    protected void initWidget(final View view) {}

    /**
     * 异步初始化数据,不能直接更改主线程中控件
     */
    protected void initAsyncData() {}

    /**
     * 同步初始化数据
     */
    protected void initData() {}
}
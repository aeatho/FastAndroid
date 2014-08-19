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

import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.fastlibrary.R;


/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastirary.ui.activity.FastLoadingActivity
 * @Description: 加载界面欢迎界面模板
 * @author: loQua.Xee    loquaciouser@gmail.com
 * @date: 2014/8/16 20:11
 * @version: V1.0
 */

public abstract class FastLoadingActivity extends BaseActivity {
    private static final String TAG = "Fugao-FastLoadingActivity";

    private View view;

    private ProgressBar progressBar;

    /**
     * 是否隐藏进度条
     */
    private boolean hiddenProgressBar = false;


    /**
     * 设置启动界面(有默认背景图片)
     */
    protected int getRootBackground() {
        return R.drawable.bg_loading;
    }

    /**
     * 默认设置为全屏、竖屏锁定显示
     */
    public FastLoadingActivity() {
        setHiddenActionBar(true);
        setAllowFullScreen(true);
        setScreenOrientation(ScreenOrientation.VERTICAL);
    }

    /**
     * 设置是否隐藏进度条
     *
     * @param hiddenProgressBar
     */
    protected void setHiddenProgressBar(boolean hiddenProgressBar) {
        this.hiddenProgressBar = hiddenProgressBar;
    }


    @Override
    protected void setRootView() {
        view = View.inflate(this, R.layout.activity_loading, null);
        RelativeLayout wellcome = (RelativeLayout) view.findViewById(R.id.app_start_view);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress);
        progressBar.setVisibility(hiddenProgressBar ? View.GONE : View.VISIBLE);
        wellcome.setBackgroundResource(getRootBackground());
        setContentView(view);
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        /**
         * 添加渐变动画
         */
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                onLoadingStart();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                onLoadingFinish();
            }
        });

        view.setAnimation(animation);
    }

    /**
     * 加载开始.里面可以执行一些初始化操作,版本检查更新操作等等
     */
    protected void onLoadingStart() {}

    /**
     * 加载完成,子类必须重载实现该方法,,,一般都是加载完毕跳入下一界面
     */
    protected abstract void onLoadingFinish();

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        return true;
    }
}

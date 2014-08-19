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

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.android.fastlibrary.AppManager;
import com.android.fastlibrary.R;
import com.android.fastlibrary.ui.UIHelper;
import com.android.fastlibrary.volley.RequestManager;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastlibrary.ui.activity.BaseActivity
 * @Description: TODO 模板基类
 * @author: loQua.Xee    loquaciouser@gmail.com
 * @date: 2014/8/16 14:21
 * @version: V1.0
 */

public abstract class BaseActivity extends CoreActivity {
    private static final String TAG = "Fugao-BaseActivity";

    /**
     * Activity显示方向
     */
    public static enum ScreenOrientation {
        HORIZONTAL, VERTICAL, AUTO
    }

    /**
     * 上下文
     */
    public Context context;

    /**
     * 是否允许全屏
     */
    private boolean allowFullScreen = false;

    /**
     * 是否隐藏ActionBar
     */
    private boolean hiddenActionBar = false;

    /**
     * 是否启用框架的退出界面
     */
    private boolean openBackListener = true;

    /**
     * 屏幕方向
     */
    private ScreenOrientation orientation = ScreenOrientation.VERTICAL;

    private DisplayMetrics displayMetrics = new DisplayMetrics();

    /**
     * 屏幕宽度,高度
     */
    public int windowWidth;
    public int windowHeight;

    /**
     * volley请求
     */
    private RequestManager requestManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        switch (orientation) {
            case HORIZONTAL:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case VERTICAL:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case AUTO:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
                break;
        }
        if (hiddenActionBar) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        } else {
            ActionBar a = getActionBar();
            if (a != null) a.show();
        }
        if (allowFullScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        windowWidth = displayMetrics.widthPixels;
        windowHeight = displayMetrics.heightPixels;

        context = this;
        super.onCreate(savedInstanceState);
    }

    /**
     * 是否全屏显示本Activity，全屏后将隐藏状态栏，默认不全屏
     *
     * @param allowFullScreen 是否全屏
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.allowFullScreen = allowFullScreen;
    }

    /**
     * 是否隐藏ActionBar，默认隐藏
     *
     * @param hiddenActionBar 是否隐藏ActionBar
     */
    public void setHiddenActionBar(boolean hiddenActionBar) {
        this.hiddenActionBar = hiddenActionBar;
    }

    /**
     * 修改屏幕显示方向，默认竖屏锁定
     *
     * @param orientation 屏幕方向
     */
    public void setScreenOrientation(ScreenOrientation orientation) {
        this.orientation = orientation;
    }

    /**
     * 是否启用返回键监听，若启用，则在显示最后一个Activity时将弹出退出对话框。默认启用（若修改必须在构造方法中调用）
     *
     * @param openBackListener
     */
    public void setBackListener(boolean openBackListener) {
        this.openBackListener = openBackListener;
    }

    /**
     * 含有Bundle通过Class跳转界面
     *
     * @param cls
     * @param bundle
     */
    public void openActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        /**
         * 跳转动画,从左进如
         */
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 通过Class跳转界面
     *
     * @param cls
     */
    public void openActivity(Class<?> cls) {
        openActivity(cls, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     *
     * @param action
     * @param bundle
     */
    public void openActivity(String action, Bundle bundle) {
        Intent intent = new Intent(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        /**
         * 跳转动画,从左进如
         */
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 通过Action跳转界面
     *
     * @param action
     */
    public void openActivity(String action) {
        openActivity(action, null);
    }

    /**
     * 销毁,附加动画(销毁的时候需要手动立即销毁堆栈中的activity)
     */
    public void closeActivityWithAnim() {
//        super.finish();
        AppManager.getInstance().finishActivity(this);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    /**
     * 销毁,不附加动画
     */
    public void closeActivity() {
//        super.finish();
        AppManager.getInstance().finishActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (openBackListener && keyCode == KeyEvent.KEYCODE_BACK && AppManager.getInstance()
                .getCount() < 2) {
            UIHelper.create().getExitDialog(this);
        } else {
            AppManager.getInstance().finishActivity(this);
        }
        return super.onKeyDown(keyCode, event);
    }

    /** >>>>>>>>>>>>>>>>>>>>>>>>>>>>封装volley开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>* */

    public RequestManager volley() {
        return initRequestManager();
    }

    public RequestManager volley(String tag) {
        return initRequestManager(tag);
    }

    private RequestManager initRequestManager() {
        if (requestManager == null) {
            requestManager = new RequestManager();
        }
        return requestManager;
    }

    private RequestManager initRequestManager(String tag) {
        if (requestManager == null) {
            requestManager = new RequestManager(tag);
        }
        return requestManager;
    }

    /**<<<<<<<<<<<<<<<<<<<<<<<<<<<<<封装volley结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<**/
}

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

package com.android.fastlibrary.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fastlibrary.AppManager;
import com.android.fastlibrary.ui.activity.FastBrowserActivity;
import com.android.fastlibrary.util.DensityUtils;
import com.android.fastlibrary.util.StringUtils;
import com.android.fastlibrary.util.SystemTool;


/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastlibrary.ui.ViewInject
 * @Description: TODO
 * @author: loQua.Xee    loquaciouser@gmail.com
 * @date: 2014/8/16 14:44
 * @version: V1.0
 */

public class UIHelper {
    private static final String TAG = "Fugao-ViewInject";

    private static UIHelper instance;

    private UIHelper() {}

    /**
     * 单一实例
     */
    public static UIHelper create() {
        if (instance == null) {
            instance = new UIHelper();
        }
        return instance;
    }

    /**
     * 显示一个toast
     *
     * @param msg
     */
    public static void toast(String msg) {
        try {
            toast(AppManager.getInstance().getTopActivity(), msg);
        } catch (Exception e) {
        }
    }

    /**
     * 长时间显示一个toast
     *
     * @param msg
     */
    public static void longToast(String msg) {
        try {
            longToast(AppManager.getInstance().getTopActivity(), msg);
        } catch (Exception e) {
        }
    }

    /**
     * 长时间显示一个toast
     *
     * @param context
     * @param msg
     */
    public static void longToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示一个toast
     *
     * @param context
     * @param msg
     */
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回一个退出确认对话框
     *
     * @param context 当前上下文
     */
    public void getExitDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定退出吗？");
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                AppManager.getInstance().appExit(context);
            }
        });
        builder.create();
        builder.show();
        builder = null;
    }

    /**
     * 用于创建PopupWindow封装一些公用属性
     */
    private PopupWindow createWindow(View view, int w, int h, int argb) {
        PopupWindow popupView = new PopupWindow(view, w, h);
        popupView.setFocusable(true);
        popupView.setBackgroundDrawable(new ColorDrawable(argb));
        popupView.setOutsideTouchable(true);
        return popupView;
    }

    /**
     * 返回一个日期对话框
     */
    public void getDateDialog(String title, final TextView textView) {
        final String[] time = SystemTool.getDataTime("yyyy-MM-dd").split("-");
        final int year = StringUtils.toInt(time[0], 0);
        final int month = StringUtils.toInt(time[1], 1);
        final int day = StringUtils.toInt(time[2], 0);
        DatePickerDialog dialog = new DatePickerDialog(textView.getContext(),
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }, year, month - 1, day);
        dialog.setTitle(title);
        dialog.show();
    }

    /**
     * 返回一个等待信息弹窗
     *
     * @param aty    要显示弹出窗的Activity
     * @param msg    弹出窗上要显示的文字
     * @param cancel dialog是否可以被取消
     */
    public static ProgressDialog getProgress(Activity aty, String msg, boolean cancel) {
        // 实例化一个ProgressBarDialog
        ProgressDialog progressDialog = new ProgressDialog(aty);
        progressDialog.setMessage(msg);
        progressDialog.getWindow().setLayout(DensityUtils.getScreenW(aty),
                DensityUtils.getScreenH(aty));
        progressDialog.setCancelable(cancel);
        // 设置ProgressBarDialog的显示样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        return progressDialog;
    }

    public static void showBrowser(String urlString){
        Activity currentActivity = AppManager.getInstance().getTopActivity();
        Intent intent = new Intent(currentActivity, FastBrowserActivity.class);
        intent.putExtra("url_string", urlString);
        currentActivity.startActivity(intent);
    }
}

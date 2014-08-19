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

package com.android.fastlibrary.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.fastlibrary.R;
import com.android.fastlibrary.util.DisplayUtils;


/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.common.view.CustomLoadingDialog
 * @Description: TODO
 * @author: ϯǿ    xiqiang@fugao.com
 * @date: 2014/7/26 22:03
 * @version: V1.0
 */

public class CustomLoadingDialog extends Dialog {
    private static final String TAG = "Fugao-CustomLoadingDialog";

    private View.OnClickListener listener;
    private String message;

    public CustomLoadingDialog(final Context context) {
        super(context);
    }

    public CustomLoadingDialog(final Context context, final int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View localView = LayoutInflater.from(getContext()).inflate(R.layout
                .layout_loading_dialog, null);
        Drawable localDrawable = getContext().getResources().getDrawable(R.drawable
                .bg_loading_dialog);
        setContentView(localView, new ViewGroup.LayoutParams(DisplayUtils.dip2px(getContext(),
                210), localDrawable.getIntrinsicHeight()));
        getWindow().setBackgroundDrawableResource(R.drawable.bg_loading_dialog);
        ((ImageButton) findViewById(R.id.id_cross_dissmis)).setOnClickListener(this.listener);
        ((TextView) findViewById(R.id.id_hint_msg)).setText(this.message);
    }

    public void setMessageListener(String paramString, View.OnClickListener paramOnClickListener) {
        this.message = paramString;
        this.listener = paramOnClickListener;
    }
}

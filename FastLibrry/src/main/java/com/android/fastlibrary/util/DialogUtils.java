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

package com.android.fastlibrary.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.fastlibrary.R;


/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.view.DialogUtil
 * @Description: TODO
 * @author: LoQua xiqiang@fugao.com
 * @date: 2014-6-26 15:56:12
 * @version: V1.0
 */

public class DialogUtils {
    private static final String TAG = "DialogUtil";

    private static AlertDialog alertDialog;

    public interface DialogListener {
        public void onLeftBtnClick();

        public void onRightBtnClick();

        public void onListItemClick(int position, String string);

        public void onListItemLongClick(int position, String string);

        public void onCancel();

    }


    /**
     * 加载进度对话框 相较于creatPorgressDialog 其不能设置标题
     *
     * @param context
     * @param message
     * @param canceledOnTouchOutside
     * @param cancelable
     * @param cancelListener
     * @return
     */
    public static Dialog creatLoadingDialog(Context context, CharSequence message,
                                            boolean canceledOnTouchOutside, boolean cancelable,
                                            boolean showClickButton,
                                            OnCancelListener cancelListener) {

        final Dialog dialog = new Dialog(context, R.style.theme_dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading_layout, null);
        TextView txtMsg = (TextView) view.findViewById(R.id.id_hint_msg);
        txtMsg.setText(message);
        ImageButton btnCancel = (ImageButton) view.findViewById(R.id.id_cross_dissmis);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                dialog.cancel();
            }
        });
        btnCancel.setVisibility(showClickButton ? View.VISIBLE : View.GONE);
        view.findViewById(R.id.view).setVisibility(showClickButton ? View.VISIBLE : View.GONE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        return dialog;
    }
}

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


import android.support.v4.app.FragmentTransaction;

import com.android.fastlibrary.R;
import com.android.fastlibrary.ui.fragment.BaseFragment;


/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastlirary.ui.activity.FastActivity
 * @Description: TODO
 * @author: loQua.Xee    loquaciouser@gmail.com
 * @date: 2014/8/16 15:43
 * @version: V1.0
 */

public abstract class FastFragmentActivity extends BaseActivity {
    private static final String TAG = "Fugao-FastActivity";

    /** 改变界面的fragment */
    protected void changeFragment(int resView, BaseFragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(resView, targetFragment, targetFragment.getClass()
                .getName());
        transaction.setCustomAnimations(R.anim.slide_left_in,
                R.anim.slide_left_out);
        transaction.commit();
    }

    /**
     * 你应该在这里调用changeFragment(R.id.content, addStack, targetFragment);
     *
     * @param targetFragment
     *            要改变的Activity
     */
    public abstract void changeFragment(BaseFragment targetFragment);
}

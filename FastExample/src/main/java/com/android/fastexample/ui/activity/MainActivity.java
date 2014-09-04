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

package com.android.fastexample.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.fastexample.R;
import com.android.fastexample.adapter.FragmentTabAdapter;
import com.android.fastexample.ui.fragment.*;
import com.android.fastlibrary.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastexample.ui.activity.MainActivity
 * @Description: TODO
 * @author: 席强    xiqiang@fugao.com
 * @date: 2014/9/3 11:57
 * @version: V1.0
 */

public class MainActivity extends BaseActivity {
    public List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void setRootView() {
        setContentView(R.layout.main);
    }

    @Override
    protected void initData() {
        fragments.add(new FragmentPage1());
        fragments.add(new FragmentPage2());
        fragments.add(new FragmentPage3());
        fragments.add(new FragmentPage4());
        fragments.add(new FragmentPage5());

        RadioGroup rgs = (RadioGroup) findViewById(R.id.tabs_rg);

        String[] moduldes = {"首页", "功能", "病人", "统计", "更多"};

        int[] imgs = {R.drawable.tab_home_btn, R.drawable.tab_message_btn,
                R.drawable.tab_more_btn, R.drawable.tab_selfinfo_btn, R.drawable.tab_square_btn};

        int width = windowWidth / moduldes.length;

        for (int i = 0; i < moduldes.length; i++) {
            String moduleName = moduldes[i];

            RadioButton radioButton = (RadioButton) LayoutInflater.from(this).inflate(R.layout
                    .home_radiobutton, null);

            radioButton.setLayoutParams(new ViewGroup.LayoutParams(width,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            radioButton.setText(moduleName);

            Drawable drawable = getResources().getDrawable(imgs[i]);

            radioButton.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);

            rgs.addView(radioButton);

        }

        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content,
                rgs);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter
                .OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                System.out.println("Extra---- " + index + " checked!!! ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("YYYYYYYYY____onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("YYYYYYYYY____onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("YYYYYYYYY____onStop");
    }
}

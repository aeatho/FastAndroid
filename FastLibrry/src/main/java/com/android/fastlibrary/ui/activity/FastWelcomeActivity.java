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

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.fastlibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastlibrary.ui.activity.FastWelcomeActivity
 * @Description: TODO
 * @author: loQua.Xee    loquaciouser@gmail.com
 * @date: 2014/8/19 11:28
 * @version: V1.0
 */

public abstract class FastWelcomeActivity extends BaseActivity implements View.OnClickListener,
                                                                          ViewPager
                                                                                  .OnPageChangeListener {
    private static final String TAG = "FastAndroid-FastWelcomeActivity";

    private Button startButton;
    private LinearLayout indicatorLayout;
    private ViewPager viewPager;
    private ImageView[] indicators;

    public FastWelcomeActivity() {
        setHiddenActionBar(true);
        setAllowFullScreen(true);
        setScreenOrientation(ScreenOrientation.VERTICAL);
    }

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initWidget() {
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        startButton = (Button) findViewById(R.id.start_Button);
        indicatorLayout = (LinearLayout) findViewById(R.id.indicator);
    }

    @Override
    protected void initData() {
        ArrayList<View> views = new ArrayList<View>();

        int[] images = getWelcomeImages();

        indicators = new ImageView[images.length];
        for (int i = 0; i < images.length; i++) {
            // 循环加入图片
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(images[i]);
            views.add(imageView);
            // 循环加入指示器
            indicators[i] = new ImageView(context);
            indicators[i].setBackgroundResource(R.drawable.indicators_default);
            if (i == 0) {
                indicators[i].setBackgroundResource(R.drawable.indicators_now);
            }
            indicatorLayout.addView(indicators[i]);
        }

        PagerAdapter pagerAdapter = new WelcomePagerAdapter(views);
        viewPager.setAdapter(pagerAdapter); // 设置适配器
        viewPager.setOnPageChangeListener(this);
    }

    private int[] getWelcomeImages() {
        return new int[]{R.drawable.welcome_01, R.drawable.welcome_02, R.drawable.welcome_03};
    }

    @Override
    protected void initListener() {
        startButton.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        return true;
    }

    @Override
    public void onClick(final View v) {
        if (v.getId() == R.id.start_Button) {
            openActivity(getRedirectActivity());
            closeActivity();
        }
    }

    protected abstract Class<?> getRedirectActivity();


    @Override
    public void onPageScrolled(final int i, final float v, final int i2) {

    }

    @Override
    public void onPageSelected(final int index) {
// 显示最后一个图片时显示按钮
        if (index == indicators.length - 1) {
            startButton.setVisibility(View.VISIBLE);
        } else {
            startButton.setVisibility(View.INVISIBLE);
        }
        // 更改指示器图片
        for (int i = 0; i < indicators.length; i++) {
            indicators[index].setBackgroundResource(R.drawable.indicators_now);
            if (index != i) {
                indicators[i].setBackgroundResource(R.drawable.indicators_default);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(final int i) {

    }

    private class WelcomePagerAdapter extends PagerAdapter {
        private List<View> views;

        public WelcomePagerAdapter(final ArrayList<View> views) {
            this.views = views;
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(final View view, final Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(final ViewGroup container, final int position,
                                final Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            container.addView(views.get(position));
            return views.get(position);
        }
    }
}

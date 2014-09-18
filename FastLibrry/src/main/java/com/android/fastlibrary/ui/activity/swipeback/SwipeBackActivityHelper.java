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

package com.android.fastlibrary.ui.activity.swipeback;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/**
 * @author Yrom
 */
public class SwipeBackActivityHelper {
  private Activity mActivity;

  private SwipeBackLayout mSwipeBackLayout;

  public SwipeBackActivityHelper(Activity activity) {
    mActivity = activity;
  }

  @SuppressWarnings("deprecation")
  public void onActivityCreate() {
    mActivity.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    mActivity.getWindow().getDecorView().setBackgroundDrawable(null);
    mSwipeBackLayout = new SwipeBackLayout(mActivity);
    mSwipeBackLayout.setLayoutParams(
        new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT));
    mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
      @Override
      public void onScrollStateChange(int state, float scrollPercent) {
      }

      @Override
      public void onEdgeTouch(int edgeFlag) {
        convertActivityToTranslucent(mActivity);
      }

      @Override
      public void onScrollOverThreshold() {

      }
    });
  }

  private void convertActivityToTranslucent(Activity mActivity) {
    try {
      Class<?>[] classes = Activity.class.getDeclaredClasses();
      Class<?> translucentConversionListenerClazz = null;
      for (Class clazz : classes) {
        if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
          translucentConversionListenerClazz = clazz;
        }
      }
      Method method = Activity.class.getDeclaredMethod("convertToTranslucent",
          translucentConversionListenerClazz);
      method.setAccessible(true);
      method.invoke(mActivity, new Object[] {
          null
      });
    } catch (Throwable t) {
    }
  }

  public void onPostCreate() {
    mSwipeBackLayout.attachToActivity(mActivity);
  }

  public View findViewById(int id) {
    if (mSwipeBackLayout != null) {
      return mSwipeBackLayout.findViewById(id);
    }
    return null;
  }

  public SwipeBackLayout getSwipeBackLayout() {
    return mSwipeBackLayout;
  }
}

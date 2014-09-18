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
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.android.fastlibrary.ui.activity.BaseActivity;
import java.lang.reflect.Method;

public abstract class SwipeBackActivity extends BaseActivity implements SwipeBackActivityBase {
  private SwipeBackActivityHelper mHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mHelper = new SwipeBackActivityHelper(this);
    mHelper.onActivityCreate();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    mHelper.onPostCreate();
  }

  @Override
  public View findViewById(int id) {
    View v = super.findViewById(id);
    if (v == null && mHelper != null) {
      return mHelper.findViewById(id);
    }
    return v;
  }

  @Override
  public SwipeBackLayout getSwipeBackLayout() {
    return mHelper.getSwipeBackLayout();
  }

  @Override
  public void setSwipeBackEnable(boolean enable) {
    getSwipeBackLayout().setEnableGesture(enable);
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      scrollToFinishActivity();
      return true;
    } else {
      return super.onKeyUp(keyCode, event);
    }
  }

  @Override
  public void scrollToFinishActivity() {
    convertActivityToTranslucent(this);
    getSwipeBackLayout().scrollToFinishActivity();
  }

  private void convertActivityToTranslucent(Activity activity) {
    try {
      Method method = Activity.class.getDeclaredMethod("convertFromTranslucent");
      method.setAccessible(true);
      method.invoke(activity);
    } catch (Throwable t) {
    }
  }
}

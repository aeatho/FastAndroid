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

package com.android.fastinfusion.ui.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import com.android.fastinfusion.R;
import com.android.fastinfusion.ui.fragment.FragmentPage1;
import com.android.fastinfusion.ui.fragment.FragmentPage2;
import com.android.fastinfusion.ui.fragment.NavigationDrawerFragment;
import com.android.fastlibrary.ui.activity.BaseActivity;

public class MyActivity extends BaseActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks {

  private NavigationDrawerFragment mNavigationDrawerFragment;

  private FragmentPage1 fragmentPage1;
  private FragmentPage2 fragmentPage2;

  private CharSequence mTitle;

  @Override
  protected void setRootView() {
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void initWidget() {
    getActionBar().setIcon(R.drawable.ic_launcher);
    mNavigationDrawerFragment =
        (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
    mTitle = getTitle();
    mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
        (DrawerLayout) findViewById(R.id.drawer_layout));
  }

  @Override
  protected void initData() {
  }

  @Override public void onNavigationDrawerItemSelected(int position) {
    // update the main content by replacing fragments
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    switch (position) {
      case 0:
        if (fragmentPage1 == null) {
          fragmentPage1 = new FragmentPage1();
        }
        fragmentTransaction.replace(R.id.container, fragmentPage1);
        break;
      case 1:
        if (fragmentPage2 == null) {
          fragmentPage2 = new FragmentPage2();
        }
        fragmentTransaction.replace(R.id.container, fragmentPage2);
        break;
      default:
        break;
    }
  }
}

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

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.android.fastexample.R;
import com.android.fastexample.ui.fragment.FragmentPage1;
import com.android.fastexample.ui.fragment.FragmentPage2;
import com.android.fastexample.ui.fragment.FragmentPage3;
import com.android.fastexample.ui.fragment.FragmentPage4;
import com.android.fastexample.ui.fragment.FragmentPage5;
import com.android.fastlibrary.view.fragmentswitcher.FragmentStateArrayPagerAdapter;
import com.android.fastlibrary.view.fragmentswitcher.FragmentSwitcher;
import java.util.ArrayList;
import java.util.List;

public class DrawerActivity extends FragmentActivity {
  private ListView mListView;
  private FragmentSwitcher mFragmentSwitcher;
  private ArrayAdapter<String> mListAdapter;
  private FragmentStateArrayPagerAdapter mFragmentAdapter;
  private ActionBarDrawerToggle mDrawerToggle;
  private DrawerLayout mDrawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drawer);
    initializeFragmentSwitcher();
    initializeList();
    initializeDrawer();
    fillAdapters();
  }

  /**
   * Initializes the fragment switcher.  It works just like a viewpager.
   */
  private void initializeFragmentSwitcher() {
    mFragmentSwitcher = (FragmentSwitcher) findViewById(R.id.fragment_switcher);
    mFragmentAdapter = new FragmentStateArrayPagerAdapter(getSupportFragmentManager());
    mFragmentSwitcher.setAdapter(mFragmentAdapter);
  }

  /**
   * Sets up the UI for the navigation drawer.
   */
  private void initializeDrawer() {
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer,
        R.string.show, R.string.hide);
    mDrawerLayout.setDrawerListener(mDrawerToggle);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    mDrawerToggle.syncState();
  }

  /**
   * Initializes the list that controls which fragment will be shown.
   */
  private void initializeList() {
    mListView = (ListView) findViewById(R.id.drawer_list);
    mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
    mListView.setAdapter(mListAdapter);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        mFragmentSwitcher.setCurrentItem(position);
        mDrawerLayout.closeDrawer(Gravity.START);
      }
    });
  }

  /**
   * Creates a list of fragments and list items and loads up the adapters.
   */
  private void fillAdapters() {
    List<Fragment> fragments = new ArrayList<Fragment>();
    List<String> listItems = new ArrayList<String>();

    fragments.add(new FragmentPage1());
    fragments.add(new FragmentPage2());
    fragments.add(new FragmentPage3());
    fragments.add(new FragmentPage4());
    fragments.add(new FragmentPage5());

    listItems.add("Fragment #" + 1);
    listItems.add("Fragment #" + 2);
    listItems.add("Fragment #" + 3);
    listItems.add("Fragment #" + 4);
    listItems.add("Fragment #" + 5);

    //for (int i = 0; i < 100; i++) {
    //  String title = "Fragment #" + i;
    //  fragments.add(new FragmentPage1());
    //  listItems.add(title);
    //}
    mFragmentAdapter.addAll(fragments);
    mListAdapter.addAll(listItems);
  }

  /**
   * Wires up the 'home' button to control the navigation drawer.
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}

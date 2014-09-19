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

package com.android.fastinfusion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.fastinfusion.R;

/**
 * Created by yugy on 14-3-15.
 */
public class DrawerAdapter extends BaseAdapter {

  private Context mContext;
  private String[] mTitles;

  public DrawerAdapter(Context context) {
    mContext = context;
    mTitles = new String[] {
        context.getResources().getString(R.string.title_section1),
        context.getResources().getString(R.string.title_section2),
        context.getResources().getString(R.string.title_section3),
        context.getResources().getString(R.string.title_section4),
        context.getResources().getString(R.string.title_section5),
    };
  }

  @Override
  public int getCount() {
    return mTitles.length;
  }

  @Override
  public String getItem(int position) {
    return mTitles[position];
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  public int getIconId(int position) {
    switch (position) {
      case 0:
        return R.drawable.ic_menu_today;
      case 1:
        return R.drawable.ic_menu_view;
      case 2:
        return R.drawable.ic_menu_star;
      case 3:
        return R.drawable.ic_menu_notifications;
      case 4:
        return R.drawable.ic_menu_preferences;
      default:
        return 0;
    }
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    TextView item = (TextView) convertView;
    if (item == null) {
      item = (TextView) LayoutInflater.from(mContext).inflate(R.layout.view_drawer_item, null);
    }
    item.setText(getItem(position));
    item.setCompoundDrawablesWithIntrinsicBounds(getIconId(position), 0, 0, 0);
    return item;
  }
}

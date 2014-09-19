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

package com.android.fastinfusion.ui.fragment;

import android.database.Cursor;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.fastinfusion.R;
import com.android.fastinfusion.adapter.BottleAdapter;
import com.android.fastinfusion.dao.datahelper.BottleDataHelper;
import com.android.fastlibrary.ui.fragment.BaseFragment;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastinfusion.ui.fragment.HomeFragment
 * @Description: TODO
 * @Author: LoQua.Xee    loquaciouser@gmail.com
 * @Create-date: 2014/9/19 23:34
 * @Modify-date: 修改日期
 * @Modify-description: TODO 修改说明
 * @Modify-author: 修改人
 * @version: V1.0
 */

public class HomeFragment extends BaseFragment
    implements OnRefreshListener, AdapterView.OnItemClickListener,
    android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {
  private static final String TAG = "Fast-HomeFragment";

  private PullToRefreshLayout mPullToRefreshLayout;
  private ListView mListView;
  private BottleDataHelper mBottleDataHelper;
  private BottleAdapter mBottleAdapter;

  @Override protected View setRootView(LayoutInflater inflater, ViewGroup container,
      Bundle bundle) {
    return inflater.inflate(R.layout.fragment_node, container, false);
  }

  @Override protected void initView(View view) {
    mPullToRefreshLayout = (PullToRefreshLayout) view;
    mListView = (ListView) mPullToRefreshLayout.findViewById(R.id.list_fragment_node);
    mListView.setEmptyView(mPullToRefreshLayout.findViewById(R.id.progress_fragment_node));
    mListView.setOnItemClickListener(this);
    ActionBarPullToRefresh.from(getActivity())
        .allChildrenArePullable()
        .listener(this)
        .setup(mPullToRefreshLayout);
  }

  @Override protected void initData() {
    mBottleDataHelper = new BottleDataHelper(getActivity());
    mBottleAdapter = new BottleAdapter(getActivity(), null, false);
    mListView.setAdapter(mBottleAdapter);
    getLoaderManager().initLoader(0, null, this);
  }

  @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

  }

  @Override public void onRefreshStarted(View view) {

  }

  @Override public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
    return mBottleDataHelper.getCursorLoader();
  }

  @Override public void onLoadFinished(Loader<Cursor> objectLoader, Cursor o) {
    mBottleAdapter.changeCursor(o);
  }

  @Override public void onLoaderReset(Loader<Cursor> objectLoader) {
    mBottleAdapter.changeCursor(null);
  }
}

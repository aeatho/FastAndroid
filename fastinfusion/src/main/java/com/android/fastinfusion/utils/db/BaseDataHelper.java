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

package com.android.fastinfusion.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastinfusion.utils.db.BaseDataHelper
 * @Description: TODO
 * @Author: LoQua.Xee    loquaciouser@gmail.com
 * @Create-date: 2014/9/18 14:51
 * @Modify-date: 修改日期
 * @Modify-description: TODO 修改说明
 * @Modify-author: 修改人
 * @version: V1.0
 */
public abstract class BaseDataHelper {
  private Context mContext;

  public BaseDataHelper(Context context) {
    mContext = context;
  }

  public Context getContext() {
    return mContext;
  }

  protected abstract Uri getContentUri();

  protected abstract String getTableName();

  public void notifyChange() {
    mContext.getContentResolver().notifyChange(getContentUri(), null);
  }

  protected final Cursor query(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    return mContext.getContentResolver().query(uri, projection, selection, selectionArgs,
        sortOrder);
  }

  protected final Cursor query(String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    return mContext.getContentResolver().query(getContentUri(), projection, selection,
        selectionArgs, sortOrder);
  }

  protected final Uri insert(ContentValues values) {
    return mContext.getContentResolver().insert(getContentUri(), values);
  }

  protected final int bulkInsert(ContentValues[] values) {
    return mContext.getContentResolver().bulkInsert(getContentUri(), values);
  }

  protected final int update(ContentValues values, String where, String[] whereArgs) {
    return mContext.getContentResolver().update(getContentUri(), values, where, whereArgs);
  }

  protected final int delete(String where, String[] selectionArgs) {
    return mContext.getContentResolver().delete(getContentUri(), where, selectionArgs);
  }

  public CursorLoader getCursorLoader() {
    return getCursorLoader(getContext());
  }

  public CursorLoader getCursorLoader(Context context) {
    return getCursorLoader(context, null, null, null, null);
  }

  protected final CursorLoader getCursorLoader(Context context, String[] projection,
      String selection, String[] selectionArgs, String sortOrder) {
    return new CursorLoader(context, getContentUri(), projection, selection, selectionArgs,
        sortOrder);
  }
}
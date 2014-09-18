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

package com.android.fastinfusion.dao;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import com.android.fastinfusion.InfusionConfig;
import com.android.fastlibrary.AppContext;

/**
 * Created by yugy on 14-3-6.
 */
public class DataProvider extends ContentProvider {

  public static final Object obj = new Object();

  private static final int BOTTLES = 0;
  private static final int BOTTLES_ITEM = 3;
  private static final int QUEUES = 1;
  private static final int QUEUES_ITEM = 4;
  private static final UriMatcher sUriMATCHER;

  static {
    sUriMATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    sUriMATCHER.addURI(InfusionConfig.AUTHORITY, InfusionConfig.TABLE_BOTTLES, BOTTLES);
    sUriMATCHER.addURI(InfusionConfig.AUTHORITY, InfusionConfig.TABLE_QUEUES, QUEUES);
    sUriMATCHER.addURI(InfusionConfig.AUTHORITY, InfusionConfig.TABLE_QUEUES + "/#", BOTTLES_ITEM);
    sUriMATCHER.addURI(InfusionConfig.AUTHORITY, InfusionConfig.TABLE_BOTTLES + "/#", QUEUES_ITEM);
  }

  private static DBHelper mDBHelper;

  public static DBHelper getDBHelper() {
    if (mDBHelper == null) {
      mDBHelper = new DBHelper(AppContext.getContext());
    }
    return mDBHelper;
  }

  @Override
  public boolean onCreate() {
    return true;
  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    synchronized (obj) {
      SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
      queryBuilder.setTables(matchTable(uri));

      SQLiteDatabase db = getDBHelper().getReadableDatabase();
      Cursor cursor = queryBuilder.query(db,
          projection,
          selection,
          selectionArgs,
          null,
          null,
          sortOrder);
      cursor.setNotificationUri(getContext().getContentResolver(), uri);
      return cursor;
    }
  }

  private String matchTable(Uri uri) {
    String table;
    switch (sUriMATCHER.match(uri)) {
      case BOTTLES:
      case BOTTLES_ITEM:
        table = InfusionConfig.TABLE_BOTTLES;
        break;
      case QUEUES:
      case QUEUES_ITEM:
        table = InfusionConfig.TABLE_QUEUES;
        break;
      default:
        throw new IllegalArgumentException("Unknown Uri" + uri);
    }
    return table;
  }

  @Override
  public String getType(Uri uri) {
    switch (sUriMATCHER.match(uri)) {
      case BOTTLES:
        return InfusionConfig.CONTENT_TYPE_BOTTLES;
      case QUEUES:
        return InfusionConfig.CONTENT_TYPE_QUEUES;
      default:
        throw new IllegalArgumentException("Unknown Uri" + uri);
    }
  }

  @Override
  public Uri insert(Uri uri, ContentValues values) {
    synchronized (obj) {
      SQLiteDatabase db = getDBHelper().getWritableDatabase();
      long rowId = 0;
      db.beginTransaction();
      try {
        rowId = db.insert(matchTable(uri), null, values);
        db.setTransactionSuccessful();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        db.endTransaction();
      }
      if (rowId > 0) {
        Uri returnUri = ContentUris.withAppendedId(uri, rowId);
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
      }
      throw new SQLException("Failed to insert row into " + uri);
    }
  }

  @Override
  public int bulkInsert(Uri uri, ContentValues[] values) {
    synchronized (obj) {
      SQLiteDatabase db = getDBHelper().getWritableDatabase();
      db.beginTransaction();
      try {
        for (ContentValues contentValues : values) {
          db.insertWithOnConflict(matchTable(uri), BaseColumns._ID, contentValues,
              SQLiteDatabase.CONFLICT_IGNORE);
        }
        db.setTransactionSuccessful();
        getContext().getContentResolver().notifyChange(uri, null);
        return values.length;
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        db.endTransaction();
      }
      throw new SQLException("Failed to insert row into " + uri);
    }
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    synchronized (obj) {
      SQLiteDatabase db = getDBHelper().getWritableDatabase();
      int count = 0;
      db.beginTransaction();
      try {
        count = db.delete(matchTable(uri), selection, selectionArgs);
        db.setTransactionSuccessful();
      } finally {
        db.endTransaction();
      }
      getContext().getContentResolver().notifyChange(uri, null);
      return count;
    }
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
    synchronized (obj) {
      SQLiteDatabase db = getDBHelper().getWritableDatabase();
      int count;
      db.beginTransaction();
      try {
        count = db.update(matchTable(uri), values, selection, selectionArgs);
        db.setTransactionSuccessful();
      } finally {
        db.endTransaction();
      }
      getContext().getContentResolver().notifyChange(uri, null);
      return count;
    }
  }
}

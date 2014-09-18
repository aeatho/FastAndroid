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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.android.fastinfusion.InfusionConfig;
import com.android.fastinfusion.dao.dbInfo.BottleDBInfo;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.infusion.fragment.InfusionFragment
 * @Description: TODO
 * @author: LoQua    xiqiang@fugao.com
 * @date: 2014-6-19 21:00:30
 * @version: V1.0
 */
public class DBHelper extends SQLiteOpenHelper {

  public DBHelper(Context context) {
    super(context, InfusionConfig.DB_NAME, null, InfusionConfig.DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    BottleDBInfo.TABLE.create(db);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    BottleDBInfo.TABLE.delete(db);
  }
}

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

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import java.util.ArrayList;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastinfusion.utils.db.SQLiteTable
 * @Description: TODO
 * @Author: LoQua.Xee    loquaciouser@gmail.com
 * @Create-date: 2014/9/18 14:28
 * @Modify-date: 修改日期
 * @Modify-description: TODO 修改说明
 * @Modify-author: 修改人
 * @version: V1.0
 */
public class SQLiteTable {

  private String mTableName;
  private ArrayList<Column> mColumnsDefinitions = new ArrayList<Column>();

  public String getTableName() {
    return mTableName;
  }

  /**
   * 会自动添加主键 BaseColumns._ID
   */
  public SQLiteTable(String tableName) {
    mTableName = tableName;
    mColumnsDefinitions.add(new Column(BaseColumns._ID,
        Column.Constraint.PRIMARY_KEY_AUTOINCREMENT, Column.DataType.INTEGER));
  }

  public SQLiteTable addColumn(Column columnsDefinition) {
    mColumnsDefinitions.add(columnsDefinition);
    return this;
  }

  public SQLiteTable addColumn(String columnName, Column.DataType dataType) {
    mColumnsDefinitions.add(new Column(columnName, null, dataType));
    return this;
  }

  public SQLiteTable addColumn(String columnName, Column.Constraint constraint,
      Column.DataType dataType) {
    mColumnsDefinitions.add(new Column(columnName, constraint, dataType));
    return this;
  }

  public void create(SQLiteDatabase db) {
    String formatter = " %s";
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
    stringBuilder.append(mTableName);
    stringBuilder.append("(");
    int columnCount = mColumnsDefinitions.size();
    int index = 0;
    for (Column columnDefinition : mColumnsDefinitions) {
      stringBuilder.append(columnDefinition.getColumnName()).append(String.format
          (formatter, columnDefinition.getDataType().name()));
      Column.Constraint constraint = columnDefinition.getConstraint();
      if (constraint != null) {
        stringBuilder.append(String.format(formatter, constraint.toString()));
      }
      if (index < columnCount - 1) {
        stringBuilder.append(",");
      }
      index++;
    }
    stringBuilder.append(");");
    db.execSQL(stringBuilder.toString());
  }

  public void delete(final SQLiteDatabase db) {
    db.execSQL("DROP TABLE IF EXISTS " + mTableName);
  }
}

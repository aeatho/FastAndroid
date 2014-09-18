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

package com.android.fastinfusion.dao.dbInfo;

import com.android.fastinfusion.dao.datahelper.BottleDataHelper;
import com.android.fastinfusion.utils.db.Column;
import com.android.fastinfusion.utils.db.SQLiteTable;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastinfusion.dao.dbInfo.BottleDBInfo
 * @Description: TODO 瓶贴表
 * @Author: LoQua.Xee    loquaciouser@gmail.com
 * @Create-date: 2014/9/18 14:49
 * @Modify-date: 修改日期
 * @Modify-description: TODO 修改说明
 * @Modify-author: 修改人
 * @version: V1.0
 */

public class BottleDBInfo extends BaseBottleDBInfo {

  public static final SQLiteTable TABLE = new SQLiteTable(BottleDataHelper.TABLE_NAME)
      .addColumn(BottleId, Column.DataType.INTEGER)
      .addColumn(InfusionId, Column.DataType.INTEGER)
      .addColumn(InfusionNo, Column.DataType.INTEGER)
      .addColumn(InvoicingId, Column.DataType.INTEGER)
      .addColumn(PrescriptionId, Column.DataType.INTEGER)
      .addColumn(DoctorId, Column.DataType.TEXT)
      .addColumn(DoctorCore, Column.DataType.TEXT)
      .addColumn(DoctorName, Column.DataType.TEXT)
      .addColumn(DiagnoseCore, Column.DataType.TEXT)
      .addColumn(DiagnoseName, Column.DataType.TEXT)
      .addColumn(PrescribeDate, Column.DataType.TEXT)
      .addColumn(PrescribeTime, Column.DataType.TEXT)
      .addColumn(GroupId, Column.DataType.INTEGER)
      .addColumn(BottleStatus, Column.DataType.INTEGER)
      .addColumn(Way, Column.DataType.TEXT)
      .addColumn(Frequency, Column.DataType.TEXT)
      .addColumn(TransfusionBulk, Column.DataType.INTEGER)
      .addColumn(TransfusionSpeed, Column.DataType.INTEGER)
      .addColumn(ExpectTime, Column.DataType.INTEGER)
      .addColumn(SubscribeDate, Column.DataType.TEXT)
      .addColumn(SubscribeTime, Column.DataType.TEXT)
      .addColumn(RegistrationDate, Column.DataType.TEXT)
      .addColumn(RegistrationTime, Column.DataType.TEXT)
      .addColumn(RegistrationId, Column.DataType.INTEGER)
      .addColumn(RegistrationCore, Column.DataType.TEXT)
      .addColumn(PillDate, Column.DataType.TEXT)
      .addColumn(PillTime, Column.DataType.TEXT)
      .addColumn(PillId, Column.DataType.INTEGER)
      .addColumn(PillCore, Column.DataType.TEXT)
      .addColumn(LiquorDate, Column.DataType.TEXT)
      .addColumn(LiquorTime, Column.DataType.TEXT)
      .addColumn(LiquorId, Column.DataType.INTEGER)
      .addColumn(LiquorCore, Column.DataType.TEXT)
      .addColumn(InfusionDate, Column.DataType.TEXT)
      .addColumn(InfusionTime, Column.DataType.TEXT)
      .addColumn(InfusionPeopleId, Column.DataType.INTEGER)
      .addColumn(InfusionCore, Column.DataType.TEXT)
      .addColumn(EndDate, Column.DataType.TEXT)
      .addColumn(EndTime, Column.DataType.TEXT)
      .addColumn(EndId, Column.DataType.INTEGER)
      .addColumn(EndCore, Column.DataType.TEXT)
      .addColumn(Remark, Column.DataType.TEXT)
      .addColumn(SeatNo, Column.DataType.TEXT)
      .addColumn(IsUpload, Column.DataType.TEXT)
      .addColumn(PatId, Column.DataType.TEXT)
      .addColumn(DrugDetails, Column.DataType.TEXT)
      .addColumn(PeopleInfo, Column.DataType.TEXT)
      .addColumn(AboutPatrols, Column.DataType.TEXT)
      .addColumn(IsUpload, Column.DataType.INTEGER);
}

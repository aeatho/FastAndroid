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

package com.android.fastinfusion.dao.datahelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.android.fastinfusion.InfusionConfig;
import com.android.fastinfusion.dao.DataProvider;
import com.android.fastinfusion.dao.dbInfo.BaseBottleDBInfo;
import com.android.fastinfusion.model.BottleInfoModel;
import com.android.fastinfusion.model.DrugDetailModel;
import com.android.fastinfusion.model.PatrolDetailModel;
import com.android.fastinfusion.model.PeopleInfoModel;
import com.android.fastinfusion.utils.JacksonHelper;
import com.android.fastinfusion.utils.db.BaseDataHelper;
import com.android.fastlibrary.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.type.TypeReference;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastinfusion.dao.datahelper.BottleDataHelper
 * @Description: TODO
 * @Author: LoQua.Xee    loquaciouser@gmail.com
 * @Create-date: 2014/9/18 14:51
 * @Modify-date: 修改日期
 * @Modify-description: TODO 修改说明
 * @Modify-author: 修改人
 * @version: V1.0
 */

public class BottleDataHelper extends BaseDataHelper {
  public static final String TABLE_NAME = "allBottles";

  public BottleDataHelper(Context context) {
    super(context);
  }

  @Override protected Uri getContentUri() {
    return InfusionConfig.CONTENT_URI_BOTTLES;
  }

  @Override protected String getTableName() {
    return TABLE_NAME;
  }

  /**
   * 根据瓶贴ID获取瓶贴
   *
   * @param bottleID 瓶贴ID
   * @return 返回瓶贴对象
   */
  public BottleInfoModel query(int bottleID) {
    BottleInfoModel retBottle = null;
    Cursor cursor = query(null, BaseBottleDBInfo.BottleId + "=" + bottleID, null, null);
    if (cursor.moveToFirst()) {
      retBottle = getBottleByCurosr(cursor);
    }
    return retBottle;
  }

  /**
   * 保存一组瓶贴
   */
  public void insert(BottleInfoModel info) {
    insert(getContentValues(info));
  }

  /**
   * 批量保存瓶签信息
   */
  public void bulkInsert(List<BottleInfoModel> infos) {
    ArrayList<ContentValues> contentValues = new ArrayList<ContentValues>();
    for (BottleInfoModel info : infos) {
      ContentValues values = getContentValues(info);
      contentValues.add(values);
    }
    ContentValues[] valueArray = new ContentValues[contentValues.size()];
    bulkInsert(contentValues.toArray(valueArray));
  }

  /**
   * 更新一组瓶贴信息
   *
   * @param info 瓶贴
   * @return 返回受影响行数
   */
  public int update(BottleInfoModel info) {
    ContentValues values = getContentValues(info);
    return update(values, BaseBottleDBInfo.BottleId + "=" + info.BottleId, null);
  }

  /**
   * 批量更新瓶贴信息
   *
   * @param infos 瓶贴集合
   * @return 返回受影响行数
   */

  public int bulkUpdate(List<BottleInfoModel> infos) {
    synchronized (DataProvider.obj) {
      SQLiteDatabase db = DataProvider.getDBHelper().getWritableDatabase();
      db.beginTransaction();
      try {
        for (BottleInfoModel info : infos) {
          ContentValues values = getContentValues(info);
          db.update(getTableName(), values, BaseBottleDBInfo.BottleId + "=" + info.BottleId, null);
        }
        db.setTransactionSuccessful();
        getContext().getContentResolver().notifyChange(getContentUri(), null);
        return infos.size();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        db.endTransaction();
      }
      throw new SQLException("Failed to update row into " + getContentUri());
    }
  }

  /**
   * 删除所有的瓶贴
   *
   * @return 返回受影响行数
   */
  public int delete() {
    return delete(null, null);
  }

  /**
   * 构建contentvalues
   */
  private ContentValues getContentValues(BottleInfoModel infos) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("BottleId", infos.BottleId);
    contentValues.put("InfusionId", infos.InfusionId);
    contentValues.put("InfusionNo", infos.InfusionNo);
    contentValues.put("InvoicingId", infos.InvoicingId);
    contentValues.put("PrescriptionId", infos.PrescriptionId);
    contentValues.put("DoctorId",
        StringUtils.getString(infos.DoctorId));
    contentValues.put("DoctorCore",
        StringUtils.getString(infos.DoctorCore));
    contentValues.put("DoctorName",
        StringUtils.getString(infos.DoctorName));
    contentValues.put("DiagnoseCore",
        StringUtils.getString(infos.DiagnoseCore));
    contentValues.put("DiagnoseName",
        StringUtils.getString(infos.DiagnoseName));
    contentValues.put("PrescribeDate",
        StringUtils.getString(infos.PrescribeDate));
    contentValues.put("PrescribeTime",
        StringUtils.getString(infos.PrescribeTime));
    contentValues.put("GroupId", infos.GroupId);
    contentValues.put("BottleStatus", infos.BottleStatus);
    contentValues.put("Way",
        StringUtils.getString(infos.Way));
    contentValues.put("Frequency",
        StringUtils.getString(infos.Frequency));
    contentValues.put("TransfusionBulk", infos.TransfusionBulk);
    contentValues.put("TransfusionSpeed", infos.TransfusionSpeed);
    contentValues.put("ExpectTime", infos.ExpectTime);
    contentValues.put("SubscribeDate",
        StringUtils.getString(infos.SubscribeDate));
    contentValues.put("SubscribeTime",
        StringUtils.getString(infos.SubscribeTime));
    contentValues.put("RegistrationDate",
        StringUtils.getString(infos.RegistrationDate));
    contentValues.put("RegistrationTime",
        StringUtils.getString(infos.RegistrationTime));
    contentValues.put("RegistrationId", infos.RegistrationId);
    contentValues.put("RegistrationCore",
        StringUtils.getString(infos.RegistrationCore));
    contentValues.put("PillDate",
        StringUtils.getString(infos.PillDate));
    contentValues.put("PillTime",
        StringUtils.getString(infos.PillTime));
    contentValues.put("PillId", infos.PillId);
    contentValues.put("PillCore",
        StringUtils.getString(infos.PillCore));
    contentValues.put("LiquorDate",
        StringUtils.getString(infos.LiquorDate));
    contentValues.put("LiquorTime",
        StringUtils.getString(infos.LiquorTime));
    contentValues.put("LiquorId", infos.LiquorId);
    contentValues.put("LiquorCore",
        StringUtils.getString(infos.LiquorCore));
    contentValues.put("InfusionDate",
        StringUtils.getString(infos.InfusionDate));
    contentValues.put("InfusionTime",
        StringUtils.getString(infos.InfusionTime));
    contentValues.put("InfusionPeopleId", infos.InfusionPeopleId);
    contentValues.put("InfusionCore",
        StringUtils.getString(infos.InfusionCore));
    contentValues.put("EndDate",
        StringUtils.getString(infos.EndDate));
    contentValues.put("EndTime",
        StringUtils.getString(infos.EndTime));
    contentValues.put("EndId", infos.EndId);
    contentValues.put("EndCore",
        StringUtils.getString(infos.EndCore));
    contentValues.put("Remark",
        StringUtils.getString(infos.Remark));
    contentValues.put("SeatNo",
        StringUtils.getString(infos.SeatNo));
    contentValues.put("DrugDetails",
        StringUtils.getString(JacksonHelper.model2String(infos.DrugDetails)));
    contentValues.put("PeopleInfo",
        StringUtils.getString(JacksonHelper.model2String(infos.PeopleInfo)));
    contentValues.put("AboutPatrols",
        StringUtils.getString(JacksonHelper.model2String(infos.AboutPatrols)));
    contentValues.put("PatId", infos.PeopleInfo == null ? -1 + "" : infos.PeopleInfo.PatId);
    return contentValues;
  }

  /**
   * 返回瓶贴对象
   */
  private BottleInfoModel getBottleByCurosr(Cursor cursor) {
    BottleInfoModel info = null;
    if (cursor.moveToFirst()) {
      info = new BottleInfoModel();
      info.BottleId = cursor.getInt(cursor
          .getColumnIndex("BottleId"));
      info.InfusionId = cursor.getInt(cursor
          .getColumnIndex("InfusionId"));
      info.InfusionNo = cursor.getInt(cursor
          .getColumnIndex("InfusionNo"));
      info.InvoicingId = cursor.getInt(cursor
          .getColumnIndex("InvoicingId"));
      info.PrescriptionId = cursor.getInt(cursor
          .getColumnIndex("PrescriptionId"));
      info.DoctorId = cursor.getString(cursor
          .getColumnIndex("DoctorId"));
      info.DoctorCore = cursor.getString(cursor
          .getColumnIndex("DoctorCore"));
      info.DoctorName = cursor.getString(cursor
          .getColumnIndex("DoctorName"));
      info.DiagnoseCore = cursor.getString(cursor
          .getColumnIndex("DiagnoseCore"));
      info.DiagnoseName = cursor.getString(cursor
          .getColumnIndex("DiagnoseName"));
      info.PrescribeDate = cursor.getString(cursor
          .getColumnIndex("PrescribeDate"));
      info.PrescribeTime = cursor.getString(cursor
          .getColumnIndex("PrescribeTime"));
      info.GroupId = cursor.getInt(cursor
          .getColumnIndex("GroupId"));
      info.BottleStatus = cursor.getInt(cursor
          .getColumnIndex("BottleStatus"));
      info.Way = cursor.getString(cursor
          .getColumnIndex("Way"));
      info.Frequency = cursor.getString(cursor
          .getColumnIndex("Frequency"));
      info.TransfusionBulk = cursor.getInt(cursor
          .getColumnIndex("TransfusionBulk"));
      info.TransfusionSpeed = cursor.getInt(cursor
          .getColumnIndex("TransfusionSpeed"));
      info.ExpectTime = cursor.getInt(cursor
          .getColumnIndex("ExpectTime"));
      info.SubscribeDate = cursor.getString(cursor
          .getColumnIndex("SubscribeDate"));
      info.SubscribeTime = cursor.getString(cursor
          .getColumnIndex("SubscribeTime"));
      info.RegistrationDate = cursor.getString(cursor
          .getColumnIndex("RegistrationDate"));
      info.RegistrationTime = cursor.getString(cursor
          .getColumnIndex("RegistrationTime"));
      info.RegistrationId = cursor.getInt(cursor
          .getColumnIndex("RegistrationId"));
      info.RegistrationCore = cursor.getString(cursor
          .getColumnIndex("RegistrationCore"));
      info.PillDate = cursor.getString(cursor
          .getColumnIndex("PillDate"));
      info.PillTime = cursor.getString(cursor
          .getColumnIndex("PillTime"));
      info.PillId = cursor.getInt(cursor
          .getColumnIndex("PillId"));
      info.PillCore = cursor.getString(cursor
          .getColumnIndex("PillCore"));
      info.LiquorDate = cursor.getString(cursor
          .getColumnIndex("LiquorDate"));
      info.LiquorTime = cursor.getString(cursor
          .getColumnIndex("LiquorTime"));
      info.LiquorId = cursor.getInt(cursor
          .getColumnIndex("LiquorId"));
      info.LiquorCore = cursor.getString(cursor
          .getColumnIndex("LiquorCore"));
      info.InfusionDate = cursor.getString(cursor
          .getColumnIndex("InfusionDate"));
      info.InfusionTime = cursor.getString(cursor
          .getColumnIndex("InfusionTime"));
      info.InfusionPeopleId = cursor.getInt(cursor
          .getColumnIndex("InfusionPeopleId"));
      info.InfusionCore = cursor.getString(cursor
          .getColumnIndex("InfusionCore"));
      info.EndDate = cursor.getString(cursor
          .getColumnIndex("EndDate"));
      info.EndTime = cursor.getString(cursor
          .getColumnIndex("EndTime"));
      info.EndId = cursor.getInt(cursor
          .getColumnIndex("EndId"));
      info.EndCore = cursor.getString(cursor
          .getColumnIndex("EndCore"));
      info.Remark = cursor.getString(cursor
          .getColumnIndex("Remark"));
      info.SeatNo = cursor.getString(cursor
          .getColumnIndex("SeatNo"));

      info.DrugDetails = JacksonHelper.getObjects(cursor.getString(cursor
          .getColumnIndex("DrugDetails")), new TypeReference<List<DrugDetailModel>>() {
      });
      info.PeopleInfo = JacksonHelper.getObject(cursor.getString(cursor
          .getColumnIndex("PeopleInfo")), new TypeReference<PeopleInfoModel>() {
      });
      info.AboutPatrols = JacksonHelper.getObject(cursor.getString(cursor
          .getColumnIndex("AboutPatrols")), new TypeReference<List<PatrolDetailModel>>() {
      });
    }
    cursor.close();
    return info;
  }
}

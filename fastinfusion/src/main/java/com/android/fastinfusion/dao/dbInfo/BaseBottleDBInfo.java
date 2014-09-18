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

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastinfusion.dao.dbInfo.BaseBottleDBInfo
 * @Description: TODO 瓶贴表字段
 * @Author: LoQua.Xee    loquaciouser@gmail.com
 * @Create-date: 2014/9/18 14:41
 * @Modify-date: 修改日期
 * @Modify-description: TODO 修改说明
 * @Modify-author: 修改人
 * @version: V1.0
 */

public abstract class BaseBottleDBInfo implements BaseColumns {

  public static final String BottleId = "BottleId";//瓶贴ID
  public static final String InfusionId = "InfusionId";//输液ID
  public static final String InfusionNo = "InfusionNo";//输液编号
  public static final String InvoicingId = "InvoicingId";//结账ID
  public static final String PrescriptionId = "PrescriptionId";//处方ID
  public static final String DoctorId = "DoctorId";//医生ID
  public static final String DoctorCore = "DoctorCore";//医生工号
  public static final String DoctorName = "DoctorName";//医生姓名
  public static final String DiagnoseCore = "DiagnoseCore";//诊断编码
  public static final String DiagnoseName = "DiagnoseName";//诊断名称
  public static final String PrescribeDate = "PrescribeDate";//开方日期
  public static final String PrescribeTime = "PrescribeTime";//开方时间
  public static final String GroupId = "GroupId";//组号
  public static final String BottleStatus = "BottleStatus";//瓶贴状态
  public static final String Way = "Way";//途径
  public static final String Frequency = "Frequency";//频次
  public static final String TransfusionBulk = "TransfusionBulk";//输液体积
  public static final String TransfusionSpeed = "TransfusionSpeed";//输液滴速
  public static final String ExpectTime = "ExpectTime";//预计时长
  public static final String SubscribeDate = "SubscribeDate";//预约日期
  public static final String SubscribeTime = "SubscribeTime";//预约时间
  public static final String RegistrationDate = "RegistrationDate";//登记日期
  public static final String RegistrationTime = "RegistrationTime";//登记时间
  public static final String RegistrationId = "RegistrationId";//登记人ID
  public static final String RegistrationCore = "RegistrationCore";//登记工号
  public static final String PillDate = "PillDate";//排药日期
  public static final String PillTime = "PillTime";//排药时间
  public static final String PillId = "PillId";//排药人ID
  public static final String PillCore = "PillCore";//排药工号
  public static final String LiquorDate = "LiquorDate";//配液日期
  public static final String LiquorTime = "LiquorTime";//配液时间
  public static final String LiquorId = "LiquorId";//配液人ID
  public static final String LiquorCore = "LiquorCore";//配液工号
  public static final String InfusionDate = "InfusionDate";//输液日期
  public static final String InfusionTime = "InfusionTime";//输液时间
  public static final String InfusionPeopleId = "InfusionPeopleId";//输液人ID
  public static final String InfusionCore = "InfusionCore";//输液工号
  public static final String EndDate = "EndDate";//结束日期
  public static final String EndTime = "EndTime";//结束时间
  public static final String EndId = "EndId";//结束人ID
  public static final String EndCore = "EndCore";//结束工号
  public static final String Remark = "Remark";//备注
  public static final String SeatNo = "SeatNo";//座位号
  public static final String IsUpload = "IsUpload";//是否上传
  public static final String PatId = "PatId";//病人id
  public static final String DrugDetails = "DrugDetails";//药品明细,为jsonarray
  public static final String PeopleInfo = "PeopleInfo";//病人信息,为jsonobject
  public static final String AboutPatrols = "AboutPatrols";//巡视信息,为jsonobject

  public static final String TABLE_NAME = "bottls";
  public static final String AUTOHORITY = "com.fugao.infusion";
  public static final int ITEM = 1;
  public static final int ITEM_ID = 2;
  public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.fugao.infusion";
  public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.fugao.infusion";
  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTOHORITY + "/" + TABLE_NAME);
}

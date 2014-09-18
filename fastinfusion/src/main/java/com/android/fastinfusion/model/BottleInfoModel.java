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

package com.android.fastinfusion.model;

import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.infusion.entity.InfusionEntity
 * @Description: TODO
 * @author: LoQua    xiqiang@fugao.com
 * @date: 2014/6/5 10:25
 * @version: V1.0
 */

public class BottleInfoModel implements Serializable {

  /**
   * 瓶贴ID
   */
  @JsonProperty
  public int BottleId;
  /**
   * 输液ID
   */
  @JsonProperty
  public int InfusionId;
  /**
   * 输液编号
   */
  @JsonProperty
  public int InfusionNo;
  /**
   * 结账ID
   */
  @JsonProperty
  public int InvoicingId;
  /**
   * 处方ID
   */
  @JsonProperty
  public int PrescriptionId;
  /**
   * 医生ID
   */
  @JsonProperty
  public String DoctorId;
  /**
   * 医生工号
   */
  @JsonProperty
  public String DoctorCore;
  /**
   * 医生姓名
   */
  @JsonProperty
  public String DoctorName;
  /**
   * 诊断编码
   */
  @JsonProperty
  public String DiagnoseCore;
  /**
   * 诊断名称
   */
  @JsonProperty
  public String DiagnoseName;
  /**
   * 开方日期
   */
  @JsonProperty
  public String PrescribeDate;
  /**
   * 开方时间
   */
  @JsonProperty
  public String PrescribeTime;
  /**
   * 组号
   */
  @JsonProperty
  public int GroupId;
  /**
   * 瓶贴状态
   */
  @JsonProperty
  public int BottleStatus;
  /**
   * 途径
   */
  @JsonProperty
  public String Way;
  /**
   * 频次
   */
  @JsonProperty
  public String Frequency;
  /**
   * 输液体积
   */
  @JsonProperty
  public int TransfusionBulk;
  /**
   * 输液滴速
   */
  @JsonProperty
  public int TransfusionSpeed;
  /**
   * 预计时长
   */
  @JsonProperty
  public int ExpectTime;
  /**
   * 预约日期
   */
  @JsonProperty
  public String SubscribeDate;
  /**
   * 预约时间
   */
  @JsonProperty
  public String SubscribeTime;
  /**
   * 登记日期
   */
  @JsonProperty
  public String RegistrationDate;
  /**
   * 登记时间
   */
  @JsonProperty
  public String RegistrationTime;
  /**
   * 登记人ID
   */
  @JsonProperty
  public int RegistrationId;
  /**
   * 登记工号
   */
  @JsonProperty
  public String RegistrationCore;
  /**
   * 排药日期
   */
  @JsonProperty
  public String PillDate;
  /**
   * 排药时间
   */
  @JsonProperty
  public String PillTime;
  /**
   * 排药人ID
   */
  @JsonProperty
  public int PillId;
  /**
   * 排药工号
   */
  @JsonProperty
  public String PillCore;
  /**
   * 配液日期
   */
  @JsonProperty
  public String LiquorDate;
  /**
   * 配液时间
   */
  @JsonProperty
  public String LiquorTime;
  /**
   * 配液人ID
   */
  @JsonProperty
  public int LiquorId;
  /**
   * 配液工号
   */
  @JsonProperty
  public String LiquorCore;
  /**
   * 输液日期
   */
  @JsonProperty
  public String InfusionDate;
  /**
   * 输液时间
   */
  @JsonProperty
  public String InfusionTime;
  /**
   * 输液人ID
   */
  @JsonProperty
  public int InfusionPeopleId;
  /**
   * 输液工号
   */
  @JsonProperty
  public String InfusionCore;
  /**
   * 结束日期
   */
  @JsonProperty
  public String EndDate;
  /**
   * 结束时间
   */
  @JsonProperty
  public String EndTime;
  /**
   * 结束人ID
   */
  @JsonProperty
  public int EndId;
  /**
   * 结束工号
   */
  @JsonProperty
  public String EndCore;
  /**
   * 备注
   */
  @JsonProperty
  public String Remark;
  /**
   * 座位号
   */
  @JsonProperty
  public String SeatNo;
  /**
   * 滴速单位
   */
  @JsonProperty
  public String SpeedUnit;
  /**
   * 药品集合
   */
  @JsonProperty
  public List<DrugDetailModel> DrugDetails;
  /**
   * 病人信息
   */
  @JsonProperty
  public PeopleInfoModel PeopleInfo;

  /**
   * 是否上传
   */
  @JsonProperty
  public int IsUpload;

  /**
   * 门诊号
   */
  @JsonProperty
  public String Pid;

  /**
   * 进度条进度
   */
  @JsonProperty
  public int Progress;
  /**
   * 巡视详情
   */
  @JsonProperty
  public List<PatrolDetailModel> AboutPatrols;
}

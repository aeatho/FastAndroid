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
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.infusion.entity.PeopleInfoEntity
 * @Description: TODO
 * @author: LoQua xiqiang@fugao.com
 * @date: 2014/6/5 10:55
 * @version: V1.0
 */

public class PeopleInfoModel implements Serializable {
  /**
   * 输液ID
   */
  @JsonProperty
  public int InfusionId;

  /**
   * 输液编号
   */
  @JsonProperty
  public String InfusionNo;

  /**
   * 门诊号
   */
  @JsonProperty
  public String PatId;

  /**
   * 病人姓名
   */
  @JsonProperty
  public String Name;

  /**
   * 性别
   */
  @JsonProperty
  public int Sex;

  /**
   * 年龄
   */
  @JsonProperty
  public String Age;

  /**
   * 出生日期
   */
  @JsonProperty
  public String BirthDay;

  /**
   * 儿童标志
   */
  @JsonProperty
  public int ChildFlag;

  /**
   * 药物过敏
   */
  @JsonProperty
  public String DrugAllergy;

  /**
   * 组数
   */
  @JsonProperty
  public String DrugAmount;

  /**
   * 输液次数
   */
  @JsonProperty
  public int InfusionNumber;

  /**
   * 完成次数
   */
  @JsonProperty
  public String FinishTime;

  /**
   * 病人状态
   */
  @JsonProperty
  public int Status;

  /**
   * 瓶贴ID
   */
  @JsonProperty
  public int BottleID;

  /**
   * 开始时间
   */
  @JsonProperty
  public String StartTime;

  /**
   * 预计时长
   */
  @JsonProperty
  public int ExpectTime;

  /**
   * 瓶贴状态
   */
  @JsonProperty
  public String BottleStatus;

  /**
   * 科室号
   */
  @JsonProperty
  public int DepartmentId;

  /**
   * 卡信息ID
   */
  @JsonProperty
  public String CardID;

  /**
   * 卡号
   */
  @JsonProperty
  public String CardNumber;
}

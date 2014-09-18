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
 * 巡视任务明细
 *
 * @ClassName: PatrolDetailBean
 * @Description: TODO
 * @author: 陈亮 chenliang@fugao.com
 * @date: 2014年2月15日 上午9:59:11
 */
public class PatrolDetailModel implements Serializable {
  /**
   * 明细编号
   */
  @JsonProperty
  public String Id;
  /**
   * 巡视编号
   */
  @JsonProperty
  public int BottleId;
  /**
   * 巡视人
   */
  @JsonProperty
  public String PatrolerNo;
  /**
   * 巡视人姓名
   */
  @JsonProperty
  public String PatrolerName;

  /**
   * 巡视时间
   */
  @JsonProperty
  public String PatrolTime;
  /**
   * 巡视内容
   */
  @JsonProperty
  public String Content;
  /**
   * 目标内容
   */
  @JsonProperty
  public String TargetContent;
  /**
   * 扩展字段
   */
  @JsonProperty
  public String Expand;
  /**
   * 巡视状态
   */
  @JsonProperty
  public int Status;
  /**
   * 相关号,医嘱编号,为了标识配液的功能，医嘱组号
   */
  @JsonProperty
  public String AboutNo;

  /**
   * 操作类型（1：扫描；2：手动）
   */
  @JsonProperty
  public int OperationType;
  /**
   * （1：输液巡视；2：病房巡视；3：巡床
   */
  @JsonProperty
  public int Type;
  /**
   * 当前滴速
   */
  @JsonProperty
  public String DrippingSpeed;
  /**
   * 当前滴速单位
   */
  @JsonProperty
  public String SpeedUnit;
}

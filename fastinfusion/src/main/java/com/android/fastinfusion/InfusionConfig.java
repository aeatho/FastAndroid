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

package com.android.fastinfusion;

import android.net.Uri;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FastAndroid
 * @Location: com.android.fastinfusion.AppConfig
 * @Description: TODO 程序配置文件
 * @Author: LoQua.Xee    loquaciouser@gmail.com
 * @Create-date: 2014/9/18 18:32
 * @Modify-date: 修改日期
 * @Modify-description: TODO 修改说明
 * @Modify-author: 修改人
 * @version: V1.0
 */

public class InfusionConfig {

  /**
   * 默认log标志
   */
  public static final String TAG = "Fast-Infusion";

  /**
   * 数据库名称
   */
  public static final String DB_NAME = "infusion.db";
  /**
   * 数据库版本
   */
  public static final int DB_VERSION = 1;
  /**
   * 瓶贴表名
   */
  public static final String TABLE_BOTTLES = "bottles";
  /**
   * 队列表名
   */
  public static final String TABLE_QUEUES = "queues";
  /**
   * 主机名
   */
  public static final String AUTHORITY = "com.fugao.infusion";

  /**
   * 返回瓶贴集合MIME类型字符串
   */
  public static final String CONTENT_TYPE_BOTTLES = "vnd.android.cursor.dir/bottles";
  /**
   * 返回单个瓶贴MIME类型字符串
   */
  public static final String CONTENT_TYPE_BOTTLES_ITEM = "vnd.android.cursor.item/bottles";
  /**
   * 返回队列MIME类型字符串
   */
  public static final String CONTENT_TYPE_QUEUES = "vnd.android.cursor.dir/queues";
  /**
   * 返回一个人MIME类型字符串
   */
  public static final String CONTENT_TYPE_QUEUES_ITEM = "vnd.android.cursor.item/queues";

  /**
   * 输液uri
   */
  public static final Uri CONTENT_URI_BOTTLES =
      Uri.parse("content://" + AUTHORITY + "/" + TABLE_BOTTLES);

  /**
   * 队列uri
   */
  public static final Uri CONTENT_URI_QUEUES =
      Uri.parse("content://" + AUTHORITY + "/" + TABLE_QUEUES);
}

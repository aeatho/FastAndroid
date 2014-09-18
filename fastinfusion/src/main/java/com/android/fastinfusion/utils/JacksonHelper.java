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

package com.android.fastinfusion.utils;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.infusion.util.String2Model
 * @Description: TODO
 * @author: LoQua    xiqiang@fugao.com
 * @date: 2014/6/13 13:02
 * @version: V1.0
 */

public class JacksonHelper {
  private static final String TAG = "String2Model";

  public static String ITEMS = "Items";
  public static String COUNTS = "Count";
  public static ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper
        .configure(
            DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);

    objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
  }

  public static <T> List<T> getObjects(String jsonString, Class<T> clazz) {
    List<T> list = null;
    try {
      list = objectMapper.readValue(jsonString,
          new TypeReference<List<T>>() {
          }
      );
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static <T> List<T> getObjects(String jsonString, TypeReference reference) {
    List<T> list = null;
    try {
      list = objectMapper.readValue(jsonString, reference);
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }

  public static <T> T getObject(String jsonString, TypeReference reference) {
    T t = null;
    try {
      t = objectMapper.readValue(jsonString, reference);
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return t;
  }

  public static <T> T getObject(String jsonString, Class<T> clazz) {
    T t = null;
    try {
      t = objectMapper.readValue(jsonString, clazz);
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return t;
  }

  /**
   * JAVA 对象转为JSON
   */
  public static String model2String(Object obj) {
    String jsonString = null;
    try {
      jsonString = objectMapper.writeValueAsString(obj);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonString;
  }
}

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

package com.android.fastlibrary.util;

/**
 * Do one thing at a time, and do well!
 *
 * @Prject: FugaoApps
 * @Location: com.fugao.common.util.Log
 * @Description: TODO
 * @author: 席强    xiqiang@fugao.com
 * @date: 2014/8/18 13:41
 * @version: V1.0
 */

import android.text.TextUtils;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Logger {
    private static String TAG = "Logger";

    /** 不记录日志* */
    public static final int LOG_NONE_TO_FILE = 0;
    /** 记录警告日志* */
    public static final int LOG_WARN_TO_FILE = 1;
    /** 记录出错日志* */
    public static final int LOG_ERROR_TO_FILE = 2;
    /** 记录所有类型日志* */
    public static final int LOG_ALL_TO_FILE = 3;

    /** 是否启用日志* */
    protected static boolean isEnable = true;
    /** 日志文件默认路径* */
    protected static String logDirPath = "/mnt/sdcard/loQua/android/log";
    /** 日志文件名称前缀* */
    protected static String logFilePrefix = "log";
    /** 日志文件名后缀* */
    protected static String logFileSuffix = "log";
    /** 日志文件默认路径* */
    protected static String path = "";
    /** 将制定类型日志记录到文件中* */
    protected static int policy = LOG_NONE_TO_FILE;


    private enum TYPE {
        INFO, DEBUG, VERBOSE, WARN, ERROR
    }

    private static ExecutorService executor = null;

    /**
     * 打印debug日志信息
     *
     * @param tag
     * @param msg
     * @param thr
     */
    public static void d(String tag, String msg, Throwable thr) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                d(msg, thr);
            } else {
                android.util.Log.d(TAG, buildMessage(TYPE.DEBUG, TAG, msg, thr), thr);
            }
        }
    }

    public static void d(String msg, Throwable thr) {
        if (isEnable) {
            android.util.Log.d(TAG, buildMessage(TYPE.DEBUG, TAG, msg, thr), thr);
        }
    }

    public static void d(String tag, String msg) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                d(msg);
            } else {
                android.util.Log.d(TAG, buildMessage(TYPE.DEBUG, TAG, msg, null));
            }
        }
    }

    public static void d(String msg) {
        if (isEnable) {
            android.util.Log.d(TAG, buildMessage(TYPE.DEBUG, TAG, msg, null));
        }
    }

    /**
     * 打印error日志信息
     *
     * @param tag
     * @param msg
     * @param thr
     */
    public static void e(String tag, String msg, Throwable thr) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                e(msg, thr);
            } else {
                android.util.Log.e(TAG, buildMessage(TYPE.ERROR, TAG, msg, thr), thr);
            }
        }
    }

    public static void e(String msg, Throwable thr) {
        if (isEnable) {
            android.util.Log.e(TAG, buildMessage(TYPE.ERROR, TAG, msg, thr), thr);
        }
    }

    public static void e(String tag, String msg) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                e(msg);
            } else {
                android.util.Log.e(TAG, buildMessage(TYPE.ERROR, TAG, msg, null));
            }
        }
    }

    public static void e(String msg) {
        if (isEnable) {
            android.util.Log.e(TAG, buildMessage(TYPE.ERROR, TAG, msg, null));
        }
    }

    /**
     * 打印日志信息
     *
     * @param tag
     * @param msg
     * @param thr
     */
    public static void i(String tag, String msg, Throwable thr) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                i(msg, thr);
            } else {
                android.util.Log.i(TAG, buildMessage(TYPE.INFO, TAG, msg, thr), thr);
            }
        }
    }

    public static void i(String msg, Throwable thr) {
        if (isEnable) {
            android.util.Log.i(TAG, buildMessage(TYPE.INFO, TAG, msg, thr), thr);
        }
    }

    public static void i(String tag, String msg) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                i(msg);
            } else {
                android.util.Log.i(TAG, buildMessage(TYPE.INFO, TAG, msg, null));
            }
        }
    }

    public static void i(String msg) {
        if (isEnable) {
            android.util.Log.i(TAG, buildMessage(TYPE.INFO, TAG, msg, null));
        }
    }

    /**
     * 打印变量日志信息
     *
     * @param tag
     * @param msg
     * @param thr
     */
    public static void v(String tag, String msg, Throwable thr) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                v(msg, thr);
            } else {
                android.util.Log.v(TAG, buildMessage(TYPE.VERBOSE, TAG, msg, thr), thr);
            }
        }
    }

    public static void v(String msg, Throwable thr) {
        if (isEnable) {
            android.util.Log.v(TAG, buildMessage(TYPE.VERBOSE, TAG, msg, thr), thr);
        }
    }

    public static void v(String tag, String msg) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                v(msg);
            } else {
                android.util.Log.v(TAG, buildMessage(TYPE.VERBOSE, TAG, msg, null));
            }
        }
    }

    public static void v(String msg) {
        if (isEnable) {
            android.util.Log.v(TAG, buildMessage(TYPE.VERBOSE, TAG, msg, null));
        }
    }

    /**
     * 打印警告日志信息
     *
     * @param tag
     * @param msg
     * @param thr
     */
    public static void w(String tag, String msg, Throwable thr) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                w(msg, thr);
            } else {
                android.util.Log.w(TAG, buildMessage(TYPE.WARN, TAG, msg, thr), thr);
            }
        }
    }

    public static void w(String msg, Throwable thr) {
        if (isEnable) {
            android.util.Log.w(TAG, buildMessage(TYPE.WARN, TAG, "", thr), thr);
        }
    }

    public static void w(String tag, String msg) {
        if (isEnable) {
            if (tag == null || tag.equals("")) {
                w(msg);
            } else {
                android.util.Log.w(TAG, buildMessage(TYPE.WARN, TAG, msg, null));
            }
        }
    }

    public static void w(String msg) {
        if (isEnable) {
            android.util.Log.w(TAG, buildMessage(TYPE.WARN, TAG, msg, null));
        }
    }

    /**
     * 创建日志
     *
     * @param type
     * @param tag
     * @param msg
     * @param thr
     * @return
     */
    protected static String buildMessage(TYPE type, String tag, String msg, Throwable thr) {
        if (TextUtils.isEmpty(path)) {
            setPath(logDirPath, logFilePrefix, logFileSuffix);
        }
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];

        boolean isLog2File = false;

        switch (policy) {
            case LOG_NONE_TO_FILE:
                isLog2File = false;
                break;
            case LOG_WARN_TO_FILE:
                isLog2File = type == TYPE.WARN;
                break;
            case LOG_ERROR_TO_FILE:
                isLog2File = type == TYPE.ERROR;
                break;
            case LOG_ALL_TO_FILE:
                isLog2File = true;
                break;
            default:
                break;
        }

        /**显示在logcat中的日志信息**/
        StringBuffer bufferLog = new StringBuffer();
        bufferLog.append(caller.getClassName());
        bufferLog.append(".");
        bufferLog.append(caller.getMethodName());
        bufferLog.append("( ");
        bufferLog.append(caller.getFileName());
        bufferLog.append(": ");
        bufferLog.append(caller.getLineNumber());
        bufferLog.append(")");
        bufferLog.append(" : ");
        bufferLog.append(msg);
        if (thr != null) {
            bufferLog.append(System.getProperty("line.separator"));
            bufferLog.append(android.util.Log.getStackTraceString(thr));
        }
        /**打印日志信息到文件中**/
        if (isLog2File) {
            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            log2file(path, "[" + currentTime + "] " + "[" + type.name() + "] " + "[" + tag + "] " +
                    ">>>>>>>>" + bufferLog);
        }
        return bufferLog.toString();
    }


    /**
     * 设置是否将日志记录到文件中
     *
     * @param policy
     */
    public static void setPolicy(int policy) {
        Logger.policy = policy;
    }

    /**
     * 设置日志文件名称前缀
     *
     * @param prefix
     */
    public static void setFilePrefix(String prefix) {
        Logger.logFilePrefix = prefix;
    }

    /**
     * 设置日志文件名称后缀  .log/.txt etc
     *
     * @param suffix
     */
    public static void setFileSuffix(String suffix) {
        Logger.logFileSuffix = suffix;
    }

    /**
     * 设置默认标记
     *
     * @param tag
     */
    public static void setTag(String tag) {
        TAG = tag;
    }

    /**
     * 设置是否启用日志
     *
     * @param enabled
     */
    public static void setEnabled(boolean enabled) {
        isEnable = enabled;
    }

    /**
     * 设置日志路径
     *
     * @param logDirPath
     * @param logFileBaseName
     * @param logFileSuffix
     */
    public static void setPath(String logDirPath, String logFileBaseName, String logFileSuffix) {
        if (!TextUtils.isEmpty(logDirPath)) {
            Logger.logDirPath = logDirPath;
        }
        if (!TextUtils.isEmpty(logFileBaseName)) {
            Logger.logFilePrefix = logFileBaseName;
        }
        if (!TextUtils.isEmpty(logFileSuffix)) {
            Logger.logFileSuffix = logFileSuffix;
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String myDateString = df.format(new Date());
        StringBuilder buffer = new StringBuilder();
        buffer.append(logDirPath);
        if (!logDirPath.endsWith("/")) {
            buffer.append("/");
        }
        if (!TextUtils.isEmpty(logFileBaseName)) {
            buffer.append(logFileBaseName);
            buffer.append("-");
        }
        buffer.append(myDateString);
        buffer.append(".");
        buffer.append(logFileSuffix);

        setPath(buffer.toString());
    }

    public static void setPath(String path) {
        Logger.path = path;
        createLogDir(path);
    }

    /**
     * 创建日志根目录
     *
     * @param path
     */
    private static void createLogDir(String path) {
        if (TextUtils.isEmpty(path)) {
            android.util.Log.e("Error", "The path is not valid.");
            return;
        }

        File file = new File(path);

        boolean ret;
        boolean exist;

        exist = file.getParentFile().exists();
        if (!exist) {
            ret = file.getParentFile().mkdirs();

            if (!ret) {
                android.util.Log.e("Error", "The Log Dir can not be created!");
                return;
            }
            android.util.Log.i("Success", "The Log Dir was successfully created! -" + file
                    .getParent());
        }
    }

    private static void log2file(final String path, final String str) {
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                PrintWriter out;

                File file = GetFileFromPath(path);

                try {
                    out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                    out.println(str);
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 返回日志文件,如果没有就新建
     *
     * @param path
     * @return
     */
    private static File GetFileFromPath(String path) {
        boolean ret;
        boolean isExist;
        boolean isWritable;

        if (TextUtils.isEmpty(path)) {
            Logger.e("Error", "The path of Log file is Null.");
            return null;
        }
        File file = new File(path);

        isExist = file.exists();
        isWritable = file.canWrite();

        if (isExist) {
            if (!isWritable) {
                Logger.e("Error", "The Log file can not be written.");
            }
        } else {
            try {
                ret = file.createNewFile();
                if (ret) {
                    Logger.i("Success", "The Log file was successfully created! -" + file
                            .getAbsolutePath());
                } else {
                    Logger.i("Success", "The Log file exist! -" + file.getAbsolutePath());
                }

                isWritable = file.canWrite();
                if (!isWritable) {
                    Logger.e("Error", "The Log file can not be written.");
                }
            } catch (IOException e) {
                Logger.e("Error", "Failed to create The Log file.");
                e.printStackTrace();
            }
        }

        return file;
    }
}

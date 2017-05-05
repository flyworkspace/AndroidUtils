package com.flyworkspace.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/**
 * Created by jinpengfei on 17-5-5.
 */

public class FileUtils {
    public static void cleanupOldLogs(String filename, final long deadline) {
        try {
            if (TextUtils.isEmpty(filename)) {
                LogUtils.i("CleanupOldLogs: Warming ! the file path is null !");
                return;
            }
            // Delete recent logs that is older than 3 days.
            File recentLogs = new File(filename);
            if (recentLogs.exists()) {
                long curTime = System.currentTimeMillis();
                if (recentLogs.isDirectory()) {
                    File recentLog[] = recentLogs.listFiles();
                    if (recentLog != null && recentLog.length > 0) {
                        for (File aRecentLog : recentLog) {
                            long logTime = aRecentLog.lastModified();
                            if ((curTime - logTime) > deadline) {
                                if (aRecentLog.isDirectory()) {
                                    cleanupOldLogs(aRecentLog.getAbsolutePath(), deadline);
                                } else {
                                    try {
                                        LogUtils.i("CleanupOldLogs: " + deleteDir(aRecentLog, null));
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    if ((curTime - recentLogs.lastModified()) > deadline) {
                        LogUtils.i("CleanupOldLogs: " + deleteDir(recentLogs, null));
                    }
                } else {
                    long logTime = recentLogs.lastModified();
                    if ((curTime - logTime) > deadline) {
                        LogUtils.i("CleanupOldLogs: " + deleteDir(recentLogs, null));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDir(File dir, String fileName) {
        if ((dir == null) || (!dir.exists())) {
            return false;
        }
        boolean deleteAll = true;
        boolean deleteStatus = false;
        if (fileName != null && !fileName.equals("")) {
            deleteAll = false;
        }
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    if (!deleteAll) {
                        if (children[i].contains(fileName)) {
                            File deleteFile = new File(dir, children[i]);
                            deleteStatus = deleteFile.delete();
                            LogUtils.i("deleteDir : the delete file is " + deleteFile.toString() + ", and the delete status is " + deleteStatus);
                        }
                    } else {
                        boolean success = deleteDir(new File(dir, children[i]), null);
                        if (!success) {
                            return false;
                        }
                    }
                }
            }
        }
        if (deleteAll) {
            LogUtils.i("deleteDir : the all file will be delete : " + dir.toString());
            return dir.delete();
        } else {
            return deleteStatus;
        }
    }

}

package com.flyworkspace.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Storage tools utils
 */
public class SDCardUtils {
    private SDCardUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * Is external storage is usable?
     *
     * @return
     */
    public static boolean isExternalStorageEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * Get external storage absolute path
     *
     * @return
     */
    public static String getExternalStorageAbsolutePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * Get external storage available space(byte)
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getSDCardAllSize() {
        if (isExternalStorageEnable()) {
            StatFs stat = new StatFs(getExternalStorageAbsolutePath());
            // 获取空闲的数据块的数量
            long availableBlocks;
            int sdk = android.os.Build.VERSION.SDK_INT;
            long freeBlocks;
            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                availableBlocks = (long) stat.getAvailableBlocks() - 4;
                freeBlocks = stat.getAvailableBlocks();
            } else {
                availableBlocks = stat.getAvailableBlocksLong() - 4;
                freeBlocks = stat.getAvailableBlocksLong();
            }
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * Get available space(byte) of designated path
     *
     * @param filePath
     * @return
     */
    public static long getFreeBytes(String filePath) {
        if (filePath.startsWith(getExternalStorageAbsolutePath())) {
            filePath = getExternalStorageAbsolutePath();
        } else {
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * Get system absolute path
     *
     * @return path
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }


}

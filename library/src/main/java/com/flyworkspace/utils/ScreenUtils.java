package com.flyworkspace.utils;

import android.content.Context;

/**
 *
 * @author jinpengfei
 */

public class ScreenUtils {
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}

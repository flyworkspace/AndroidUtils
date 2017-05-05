package com.flyworkspace.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinpengfei on 16-12-5.
 */

public class PackageUtils {

    public static String getPackageName(Context context){
        return context.getPackageName();
    }

    /**
     * Get application version code
     * @param context context
     * @return
     */
    public static int getVersionCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    getPackageName(context), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verCode;
    }

    /**
     * Get application version name
     * @param context context
     * @return ep:1.2.1
     */
    public static String getVersionName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    getPackageName(context), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verName;
    }

    /**
     * Get application is installed
     * @param context context
     * @param packageName target package name of application
     * @return  true:the application is installed  ,else false;
     */
    public static boolean isApplicationAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }
}

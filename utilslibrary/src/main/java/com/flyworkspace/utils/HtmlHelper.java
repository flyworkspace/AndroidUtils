package com.flyworkspace.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Html creator helper
 */
public class HtmlHelper {
    public HtmlHelper() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static String mHtmlHead = "";

    public static String getHtmlString(Context context, String content) {
        if(mHtmlHead.isEmpty()) {
            mHtmlHead = PublicFunction.getTextFromAsset(context,"news/header.html");
        }

        StringBuffer sb = new StringBuffer();
        sb.append(mHtmlHead);
        sb.append("<body>");
        sb.append(content);
        sb.append("</body>");
        return sb.toString();
    }

    public static String getHtmlString(Context context, String content, String htmlhead) {
        if (!TextUtils.isEmpty(htmlhead)){
            htmlhead = PublicFunction.getTextFromAsset(context, htmlhead);
        }

        if (TextUtils.isEmpty(htmlhead)) {
            if (mHtmlHead.isEmpty()) {
                mHtmlHead = PublicFunction.getTextFromAsset(context, "news/header.html");
            }
            htmlhead = mHtmlHead;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(htmlhead);
        sb.append("<body>");
        sb.append(content);
        sb.append("</body>");
        return sb.toString();
    }
}

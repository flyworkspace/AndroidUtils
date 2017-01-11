package com.flyworkspace.utils;

/**
 * Html creator helper
 */
public class HtmlHelper {
    public HtmlHelper() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    public static String getHtmlString(String content) {
        return getHtmlString(content, null);
    }

    public static String getHtmlString(String content, String htmlhead) {
        StringBuffer sb = new StringBuffer();
        sb.append(htmlhead);
        sb.append("<body>");
        sb.append(content);
        sb.append("</body>");
        return sb.toString();
    }
}

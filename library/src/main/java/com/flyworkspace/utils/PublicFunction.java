package com.flyworkspace.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Calendar;

/**
 * Public function
 *
 * @author jpf
 */
public class PublicFunction {
    /**
     * show a short toast
     * @param activity
     * @param str
     */
    public static void showToast(Activity activity, String str) {
        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
    }

    public static Bitmap buildTabBitmap(Context ctx, int drawableId,
                                        Boolean isTabSelect) {
        ColorFilter tab_colorColorFilter;
        if (isTabSelect) {
            tab_colorColorFilter = new LightingColorFilter(Color.rgb(255, 126,
                    0), Color.rgb(255, 90, 0));
        } else {
            tab_colorColorFilter = new LightingColorFilter(Color.WHITE,
                    Color.WHITE);
            // tab_colorColorFilter=new LightingColorFilter(Color.BLACK,
            // Color.BLACK);
        }
        Bitmap d = BitmapFactory.decodeResource(ctx.getResources(), drawableId);
        Bitmap output = Bitmap.createBitmap(d.getWidth(), d.getHeight(),
                Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, d.getWidth(), d.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 0;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        paint.setColorFilter(tab_colorColorFilter);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(d, rect, rect, paint);
        return output;
    }

    /**
     * Get text from asset file
     * @param context
     * @param filename
     * @return
     */
    public static String getTextFromAsset(Context context ,String filename) {
        InputStream mInputStream = null;
        String resultString = "";
        try {
            mInputStream = context.getAssets().open(filename);
            if (mInputStream == null){
                return null;
            }
            byte[] buffer = new byte[mInputStream.available()];
            if (buffer == null) {
                return null;
            }
            mInputStream.read(buffer);
            resultString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (mInputStream != null) {
                    mInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * set view background
     * @param v
     * @param d
     */
    @SuppressLint("NewApi")
    public static void setBackground(View v, Drawable d) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackgroundDrawable(d);
        } else {
            v.setBackground(d);
        }
    }

    /**
     * Get text from a file
     * @param filePath
     * @return
     */
    public static String readTextFromFilePath(String filePath) {
        if (TextUtils.isEmpty(filePath)){
            LogUtils.e("readTextFromFilePath  filePath is null");
            return null;
        }
        return readTextFromFilePath(new File(filePath));
    }

    /**
     * Get text from a file
     * @param file
     * @return
     */
    public static String readTextFromFilePath(File file) {
        if (file == null){
            LogUtils.e("readTextFromFilePath  file is null");
            return null;
        } else if (!file.exists()){
            LogUtils.e("readTextFromFilePath  file isn't exists");
            return null;
        } else if (file.isDirectory()){
            LogUtils.e("readTextFromFilePath  file is a directory");
            return null;
        }
        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(file);
            int c;
            while ((c = fis.read()) != -1) {
                sb.append((char) c);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Write content to a file
     * @param filePath
     * @param content
     */
    public static void writeToFile(String filePath ,String content) {
        try {
            File targetFile = new File(filePath);
            RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
            raf.seek(targetFile.length());
            raf.write(content.getBytes());
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy a text to clip.
     * @param context
     * @param charSequence
     */
    public static void copyText(Context context,CharSequence charSequence){
        ClipboardManager copy = (ClipboardManager) context.
                getSystemService(Context.CLIPBOARD_SERVICE);
        copy.setPrimaryClip(ClipData.newPlainText(null, charSequence));
    }

    /**
     * Check a color is dark color
     * @param color
     * @return true: dark color ;   false: light color
     */
    public boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }
}

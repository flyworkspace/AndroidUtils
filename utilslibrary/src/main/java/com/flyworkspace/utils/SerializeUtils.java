package com.flyworkspace.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by flyworkspace on 2016/4/9.
 */
public class SerializeUtils {
    public static String writeObject(Object o) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(o);
        oos.flush();
        oos.close();
        bos.close();
        return new String(bos.toByteArray(), "ISO-8859-1");
    }

    //反序列化String字符串为对象

    public static Object readObject(String object) throws Exception{
        ByteArrayInputStream bis = new ByteArrayInputStream(object.getBytes("ISO-8859-1"));
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object o = null;
        try {
            o = ois.readObject();
        } catch(EOFException e) {
            e.printStackTrace();
            LogUtils.e("read finished");
        }
        bis.close();
        ois.close();
        return o;
    }
}

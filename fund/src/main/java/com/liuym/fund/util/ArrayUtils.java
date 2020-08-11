package com.liuym.fund.util;

import java.util.Arrays;

/**
 * @ClassName ArrayUtils
 * @Description ArrayUtils
 * @Author LIUYQ
 * @Date 2019/10/25 16:19
 * @Version 1.0
 */
public class ArrayUtils {
    public static boolean isArray(Object obj) {
        return null == obj ? false : obj.getClass().isArray();
    }

    public static String toString(Object obj) {
        if (null == obj) {
            return null;
        } else if (obj instanceof long[]) {
            return Arrays.toString((long[]) ((long[]) obj));
        } else if (obj instanceof int[]) {
            return Arrays.toString((int[]) ((int[]) obj));
        } else if (obj instanceof short[]) {
            return Arrays.toString((short[]) ((short[]) obj));
        } else if (obj instanceof char[]) {
            return Arrays.toString((char[]) ((char[]) obj));
        } else if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) ((byte[]) obj));
        } else if (obj instanceof boolean[]) {
            return Arrays.toString((boolean[]) ((boolean[]) obj));
        } else if (obj instanceof float[]) {
            return Arrays.toString((float[]) ((float[]) obj));
        } else if (obj instanceof double[]) {
            return Arrays.toString((double[]) ((double[]) obj));
        } else {
            if (isArray(obj)) {
                try {
                    return Arrays.deepToString((Object[]) ((Object[]) obj));
                } catch (Exception var2) {
                }
            }

            return obj.toString();
        }
    }
}

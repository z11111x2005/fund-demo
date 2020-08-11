package com.liuym.fund.util;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

/**
 * @ClassName CollectionUtils
 * @Description CollectionUtils
 * @Author LIUYQ
 * @Date 2019/10/28 15:36
 * @Version 1.0
 */
public class CollectionUtils {
    /**
     * 使用Function接口转换对象，返回转换后对象集合。
     *
     * @param list     源数据
     * @param function 转换函数
     * @param <T>      目的类型
     * @param <O>      原对象类型
     * @return list为null或者空时，返回空集合。
     */
    public static <T, O> List<T> transferFrom(List<O> list, Function<O, T> function) {
        if (isNotEmpty(list)) {
            List<T> out = new ArrayList<>();
            for (O o : list) {
                out.add(function.apply(o));
            }
            return out;
        }
        return emptyList();
    }

    /**
     * 取集合第一个元素，空集合时，返回null
     *
     * @param coll
     * @param <T>
     * @return
     */
    public static <T> T firstOrNull(Collection<T> coll) {
        // 不管你是list还是set，如果不为空，我只要第一个
        return isEmpty(coll) ? null : coll.iterator().next();
    }

    /**
     * 空或者只有一个元素的集合
     *
     * @param coll
     * @return
     */
    public static <T> boolean isUniqueOrEmpty(Collection<T> coll) {
        return isEmpty(coll) || coll.size() == 1;
    }

    /**
     * 集合不为空则取第一个元素返回，如果有多个元素，抛出IllegalStateException异常。
     *
     * @param coll
     * @param <T>
     * @return
     * @throws IllegalStateException
     */
    public static <T> T uniqueOrNull(Collection<T> coll) throws IllegalStateException {
        if (isEmpty(coll)) return null;

        if (coll.size() > 1) {
            throw new IllegalStateException("multi elements collection was rejected, want 1 but was " + coll.size());
        }

        return coll.iterator().next();
    }

    /**
     * 取唯一结果，传入的集合size集大于1时，抛出异常
     *
     * @param coll
     * @param errorObject 异常说明信息
     * @param <T>
     * @return
     * @throws IllegalStateException
     */
    public static <T> T uniqueOrNull(Collection<T> coll, String errorObject) throws IllegalStateException {

        if (isEmpty(coll)) return null;

        if (coll.size() > 1) {
            throw new IllegalStateException(errorObject);
        }

        return coll.iterator().next();
    }

    /**
     * java.util.Collections.emptyList()
     *
     * @return java.util.Collections.emptyList()
     * @see Collections#emptyList()
     */
    public static <T> List<T> emptyList() {

        return Collections.emptyList();
    }

    /**
     * 为null时，返回 Collections.EMPTY_LIST；不为空，返回原值
     *
     * @param coll
     * @return
     */
    public static <T> List<T> emptyIfNull(List<T> coll) {

        if (coll == null) return Collections.emptyList();

        return coll;
    }

    /**
     * 如果集合为 null ，返回一个新的ArrayList
     *
     * @param coll
     * @param <T>
     * @return
     */
    public static <T> List<T> newArrayListIfNull(List<T> coll) {
        if (coll == null) {
            return new ArrayList<>();
        }
        return coll;
    }

    /**
     * 如果集合为 null ，返回一个默认数组
     *
     * @param coll
     * @param defaultList
     * @param <T>
     * @return
     */
    public static <T> List<T> defaultIfNull(List<T> coll, List<T> defaultList) {
        if (coll == null) {
            return defaultList;
        }
        return coll;
    }

    public static <T> Object[] toArray(Collection<T> coll) {
        return isEmpty(coll) ? new Object[0] : coll.toArray();
    }

    /**
     * 集合转数组
     *
     * @param coll
     * @param type
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(Collection<T> coll, Class<T> type) {
        // 由于泛型擦除机制，需要指定数组类型
        if (isEmpty(coll)) {
            return (T[]) Array.newInstance(type, 0);
        }
        T[] array = (T[]) Array.newInstance(type, coll.size());
        int i = 0;
        for (T t : coll) {
            array[i++] = t;
        }
        return array;
    }

    /**
     * 集合转成String数组
     *
     * @param coll
     * @param <T>
     * @return
     */
    public static <T> String[] toStringArray(Collection<T> coll) {
        if (isEmpty(coll)) {
            return new String[0];
        }
        String[] array = new String[coll.size()];
        int i = 0;
        for (T t : coll) {
            array[i++] = Objects.toString(t, null);
        }
        return array;
    }

//    /**
//     * <pre>{@code
//     *   List<String> users = new ArrayList<>("s1", "s2", "s3");
//     *   String[] strings = CollectionUtils.toArray(users, String[]::new)
//     * }</pre>
//     *
//     * @param coll
//     * @param function
//     * @param <T>
//     * @return
//     */
//    @Deprecated // 效率不及 Array.newInstance
//    public static <T> T[] toArray(Collection<T> coll, IntFunction<T[]> function) {
//        // 由于java的泛型擦除机智，在方法内部获取不到T的类型。
//        if (isEmpty(coll)) {
//            return function.apply(0);
//        }
//        T[] array = function.apply(coll.size());
//        int i = 0;
//        for (T t : coll) {
//            array[i++] = t;
//        }
//        return array;
//    }

    /**
     * 一个元素一行打印
     * <pre>
     *     [
     *      1
     *      2
     *      3
     *     ]
     * </pre>
     *
     * @param coll
     * @return
     */
    public static <T> String toStringln(Collection<T> coll) {
        StringBuilder sb = new StringBuilder();
        if (isNotEmpty(coll)) {
            sb.append("[\n");
            for (T o : coll) {
                sb.append("  ");
                sb.append(o);
                sb.append("\n");
            }
            sb.append("]");
        }
        return sb.toString();
    }

    /**
     * 一个元素一行打印
     * <pre>
     *     {
     *       k1=v1
     *       k2=v2
     *       k3=v3
     *     }
     * </pre>
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> String toStringln(Map<K, V> map) {
        StringBuilder sb = new StringBuilder();
        if (isNotEmpty(map)) {
            sb.append("{\n");
            for (Map.Entry<K, V> entry : map.entrySet()) {
                sb.append("  ");
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                sb.append("\n");
            }
            sb.append("}");
        }
        return sb.toString();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(Collection coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection coll) {
        return !isEmpty(coll);
    }

    /**
     * @param array
     * @param toFind
     * @param findNull 是否查找null值
     * @return
     */
    public static int indexOf(Object[] array, Object toFind, boolean findNull) {
        if (!isEmpty(array)) {
            if (toFind == null) {

                if (findNull) {
                    for (int i = 0; i < array.length; i++) {
                        if (array[i] == null) return i;
                    }
                }

                return -1;
            }

            for (int i = 0; i < array.length; i++) {
                if (array[i] != null && array[i].equals(toFind)) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 不处理null值
     *
     * @param array
     * @param toFind
     * @return
     */
    public static boolean contains(Object[] array, Object toFind) {
        return indexOf(array, toFind, false) >= 0;
    }


    /**
     * 数组转成ArrayList
     *
     * @param ts
     * @return
     */
    @SafeVarargs
    public static <T> ArrayList<T> newArrayList(T... ts) {
        return new ArrayList<>(Arrays.asList(ts));
    }

    /**
     * new ArrayList
     *
     * @return
     */
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * {@code
     * // 要求key必须是String类型的。
     * Object[]{"key1",obj1,"key2",obj2} => Map<String,Object>
     * }
     *
     * @param kvs
     * @return
     */
    public static Map<String, Object> newStringKeyMapOfObjects(Object... kvs) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < kvs.length; i += 2) {
            map.put((String) kvs[i], kvs[i + 1]);
        }
        return map;
    }

    /**
     * {@code
     * String[] => Map<String,Object>
     * }
     *
     * @param kvs
     * @return
     */
    public static Map<String, Object> newStringKeyMap(String... kvs) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < kvs.length; i += 2) {
            map.put(kvs[i], kvs[i + 1]);
        }
        return map;
    }

    /**
     * {@code
     * String[] => Map<String,String>
     * }
     *
     * @param kvs
     * @return
     */
    public static Map<String, String> newStringMap(String... kvs) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < kvs.length; i += 2) {
            map.put(kvs[i], kvs[i + 1]);
        }
        return map;
    }

    public static Map<String, Object> newSingleStringKeyMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

    /**
     * <pre>
     * 合并数组
     * concat(new Object[]{1,2,3,4}, new Object[]{5,6}) => [1, 2, 3, 4, 5, 6]
     * </pre>
     *
     * @param one
     * @param two
     * @return
     */
    public static Object[] concat(Object[] one, Object[] two) {
        return concat(one, -1, two);
    }

    /**
     * <pre>
     * 合并（插入）数组：position 为 main 数组的下标位置
     * concat(new Object[]{1,2,3,4}, 1, new Object[]{5,6}) => [1, 5, 6, 2, 3, 4]
     * concat(new Object[]{1,2,3,4}, -1, new Object[]{5,6}) => [1, 2, 3, 4, 5, 6]
     * </pre>
     *
     * @param main
     * @param append
     * @param position base 0，-1 表示从最后插入
     * @return
     */
    public static Object[] concat(Object[] main, int position, Object... append) {
        Object[] newAry = new Object[main.length + append.length];
        if (position >= main.length || position == -1) {
            position = main.length;
        }
        System.arraycopy(main, 0, newAry, 0, position);
        System.arraycopy(append, 0, newAry, position, append.length);
        System.arraycopy(main, position, newAry, position + append.length, main.length - position);
        return newAry;
    }
}

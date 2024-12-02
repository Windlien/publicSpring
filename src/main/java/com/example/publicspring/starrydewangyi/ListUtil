package com.example.publicspring.windLien.util;

import cn.hutool.core.util.ArrayUtil;

import java.util.*;

public class ListUtil {

    @SuppressWarnings("unchecked")
    public static <T> List<T> listAnything(T... ms) {
        List<T> l = new ArrayList<T>();
        for (int i = 0; i < ms.length; i++) {
            l.add(ms[i]);
        }
        return l;
    }

//    @SuppressWarnings("unchecked")
//    public static List<String> toLowerCase(Collection<String> collection) {
//        List<String> low = new ArrayList<>();
//        for (String col : collection) {
//            low.add(col.toLowerCase());
//        }
//        return low;
//    }

    @SuppressWarnings("unchecked")
    public static List<String> toUpperCase(Collection<String> collection) {
        List<String> up = new ArrayList<>();
        for (String col : collection) {
            up.add(col.toUpperCase());
        }
        return up;
    }

    /**
     * 针对sql查询列表过滤目标列表，同时去掉目标列
     */
    public static List<Map<String, Object>> filterList(List<Map<String, Object>> source, String fieldName, String value) {
        List<Map<String, Object>> filtered = new ArrayList<Map<String, Object>>();
        source.stream().filter(map -> {
            return value.equals(map.get(fieldName));
        }).forEach(map -> {
            map.remove(fieldName);
            filtered.add(map);
        });
        return filtered;
    }

    /**
     * 针对sql查询列表过滤目标列表，同时去掉目标列
     */
    public static List<Map<String, Object>> filterList2(List<Map<String, Object>> source, String fieldName, String value) {
        List<Map<String, Object>> filtered = new ArrayList<Map<String, Object>>();
        source.stream().filter(map -> {
            return value.equals(map.get(fieldName));
        }).forEach(map -> {
            map.remove(fieldName);
            filtered.add(map);
        });
        return filtered;
    }
    public static <T> List<T> list(boolean isLinked, T... values) {
        if (ArrayUtil.isEmpty(values)) {
            return list(isLinked);
        }
        final List<T> arrayList = isLinked ? new LinkedList<>() : new ArrayList<>(values.length);
        Collections.addAll(arrayList, values);
        return arrayList;
    }

    /**
     * 新建一个List
     *
     * @param <T>        集合元素类型
     * @param isLinked   是否新建LinkedList
     * @param collection 集合
     * @return List对象
     * @since 4.1.2
     */
    public static <T> List<T> list(boolean isLinked, Collection<T> collection) {
        if (null == collection) {
            return list(isLinked);
        }
        return isLinked ? new LinkedList<>(collection) : new ArrayList<>(collection);
    }

    /**
     * 新建一个List<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T>      集合元素类型
     * @param isLinked 是否新建LinkedList
     * @param iterable {@link Iterable}
     * @return List对象
     * @since 4.1.2
     */
    public static <T> List<T> list(boolean isLinked, Iterable<T> iterable) {
        if (null == iterable) {
            return list(isLinked);
        }
        return list(isLinked, iterable.iterator());
    }

    /**
     * 新建一个List<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T>      集合元素类型
     * @param isLinked 是否新建LinkedList
     * @param iter     {@link Iterator}
     * @return ArrayList对象
     * @since 4.1.2
     */
    public static <T> List<T> list(boolean isLinked, Iterator<T> iter) {
        final List<T> list = list(isLinked);
        if (null != iter) {
            while (iter.hasNext()) {
                list.add(iter.next());
            }
        }
        return list;
    }

}

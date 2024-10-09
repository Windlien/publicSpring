package com.example.publicspring.windLien.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
}

package com.example.publicspring.util;

import cn.hutool.core.bean.PropDesc;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanUtil extends cn.hutool.core.bean.BeanUtil {

    public static Set<String> getFieldNames(Class<?> clazz, boolean ignoreCase) {
        return getBeanDesc(clazz).getPropMap(ignoreCase).keySet();
    }

    public static Set<String> getFieldNames4UnderLine(Class<?> clazz) {
        Set<String> fields = getBeanDesc(clazz).getPropMap(true).keySet();
        fields.forEach(s -> StringUtil.toUnderlineCase(s));
        return fields;
    }

    public static <T> Set<String> filterField(Class<T> aClass, Function<Field, Boolean> filterFunc) {
        Map<String, PropDesc> propMap = getBeanDesc(aClass).getPropMap(false);
        return propMap.values().stream().map(PropDesc::getField).filter(filterFunc::apply).map(Field::getName).collect(Collectors.toSet());
    }

}

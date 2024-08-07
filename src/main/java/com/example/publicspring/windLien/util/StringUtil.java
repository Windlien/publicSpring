package com.example.publicspring.windLien.util;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.NamingCase;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.Collection;

@Slf4j
public class StringUtil extends StrUtil {
    private StringUtil() {
        JButton jButton = new JButton();
        jButton.doClick();
    }

    /**
     * 将驼峰式命名的字符串转换为下划线方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。<br>
     * 例如：
     *
     * <pre>
     * HelloWorld=》hello_world
     * Hello_World=》hello_world
     * HelloWorld_test=》hello_world_test
     * </pre>
     *
     * @param str 转换前的驼峰式命名的字符串，也可以为下划线形式
     * @return 转换后下划线方式命名的字符串
     * @see NamingCase#toUnderlineCase(CharSequence)
     */
    public static String toUnderlineCase(CharSequence str) {
        return NamingCase.toUnderlineCase(str);
    }
     public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    /**
     * return (s == null) || "".equals(s.trim())
     */
    public static boolean isBlank(String s) {
        return (s == null) || "".equals(s.trim());
    }

    public static boolean isNotBlank(Object str) {
        return !isBlank(str);
    }

    public static boolean isBlank(Object str) {
        return (str == null || "".equals(str.toString().trim()));
    }
    public static boolean isY(String value) {
        return "Y".equals(value);
    }
    public static boolean isN(String value) {
        return "N".equals(value);
    }

    public static String join(@SuppressWarnings("rawtypes") Collection values, String split, String quotes) {
        if (CollectionUtil.isEmpty(values)) {
            log.warn("Empty collection.", new Exception("Stack for coder."));
            return "";
        }
        StringBuilder sb = new StringBuilder();
        values.forEach((Object o) ->
                sb.append(quotes).append(o == null ? "<null>" : o.toString()).append(quotes).append(split));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    public static boolean isInteger(String text) {
        return NumberUtil.isInteger(text);
    }
}

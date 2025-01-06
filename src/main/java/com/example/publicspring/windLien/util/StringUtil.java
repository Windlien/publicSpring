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
    private static final int PAD_LIMIT = 8192;

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

    /**
     * <p>Right pad a String with a specified String.</p>
     *
     * <p>The String is padded to the size of {@code size}.</p>
     *
     * <pre>
     * StringUtil.rightPad(null, *, *)      = null
     * StringUtil.rightPad("", 3, "z")      = "zzz"
     * StringUtil.rightPad("bat", 3, "yz")  = "bat"
     * StringUtil.rightPad("bat", 5, "yz")  = "batyz"
     * StringUtil.rightPad("bat", 8, "yz")  = "batyzyzy"
     * StringUtil.rightPad("bat", 1, "yz")  = "bat"
     * StringUtil.rightPad("bat", -1, "yz") = "bat"
     * StringUtil.rightPad("bat", 5, null)  = "bat  "
     * StringUtil.rightPad("bat", 5, "")    = "bat  "
     * </pre>
     *
     * @param str    the String to pad out, may be null
     * @param size   the size to pad to
     * @param padStr the String to pad with, null or empty treated as single space
     * @return right padded String or original String if no padding is necessary,
     * {@code null} if null String input
     */
    public static String rightPad(final String str, final int size, String padStr) {
        if (str == null) {
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE;
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) {
            return str; // returns original String when possible
        }
        if (padLen == 1 && pads <= PAD_LIMIT) {
            return rightPad(str, size, String.valueOf(padStr.charAt(0)));
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.concat(new String(padding));
        }
    }

}

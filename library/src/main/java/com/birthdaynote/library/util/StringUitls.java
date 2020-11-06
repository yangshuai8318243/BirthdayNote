package com.birthdaynote.library.util;

import android.util.Patterns;

import com.birthdaynote.library.log.AppLog;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/8/27
 * Time: 20:09
 */
public class StringUitls {
    /**
     * 判断字符串是否为null或者空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(final CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断去除空格后的字符串是否为null或者空
     *
     * @param s
     * @return
     */
    public static boolean isTrimEmpty(final String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 判断字符串是否为null或者空
     *
     * @param s
     * @return
     */
    public static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断忽略大小写情况下，字符串是否equals
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equalsIgnoreCase(final String s1, final String s2) {
        return s1 == null ? s2 == null : s1.equalsIgnoreCase(s2);
    }

    /**
     * 避免字符串null时，空指针问题
     *
     * @param s
     * @return
     */
    public static String null2Length0(final String s) {
        return s == null ? "" : s;
    }

    /**
     * 判断字符串长度
     *
     * @param s
     * @return
     */
    public static int length(final CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 字符串首字母大写
     *
     * @param s
     * @return
     */
    public static String upperFirstLetter(final String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s
     * @return
     */
    public static String lowerFirstLetter(final String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 判断字符串是否纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumericzidai(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是@某个的字符串
     *
     * @param str
     * @return
     */
    public static boolean isAtString(String str) {
        Pattern pattern = Pattern.compile("\\@(.*?)(\\s)");
        return pattern.matcher(str).matches();
    }

    /**
     * 获取@的内容
     *
     * @param str
     * @return
     */
    public static List<String> getAtString(String str) {
        Pattern p = Pattern.compile("\\@(.*?)(\\s)");
        Matcher m = p.matcher(str);
        ArrayList<String> strings = new ArrayList<>();
        while (m.find()) {
            int i = 1;
            strings.add(m.group(i));
            i++;
        }
        return strings;
    }

    /**
     * 获取url的中的文件名
     *
     * @param urlPath
     * @param suffixes
     * @return
     */
    public static String getUrlFileName(String urlPath, String suffixes) {

        Pattern pat = Pattern.compile("[\\w]+[\\.](" + suffixes + ")");//正则判断
        Matcher mc = pat.matcher(urlPath);//条件匹配
        if (mc.find()) {
            return mc.group();//截取文件名后缀名
        }
        return "";
    }

    //使用正则，（推荐）
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否纯字母
     *
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {
        Pattern pattern = Pattern.compile("[a-zA-Z] + ");
        Matcher isLetter = pattern.matcher(str);
        if (!isLetter.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobilePhone(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 判断提取字符串中手机号码
     *
     * @param num
     * @return
     */
    private static String checkNum(String num) {
        if (num == null || num.length() == 0) {
            return "";
        }
        Pattern pattern = Pattern.compile("(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)");
        Matcher matcher = pattern.matcher(num);
        StringBuffer bf = new StringBuffer(64);
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }

    /**
     * 判断字符串，手机号码隐藏中间四位
     *
     * @param phone
     * @return
     */
    public static String getSafePhone(String phone) {
        if (phone == null || phone.length() == 0) return "";
        String phoneNumber = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return phoneNumber;
    }

    /**
     * 判断是否是正常url
     *
     * @param URL
     * @return
     */
    public static boolean isUrl(String URL) {
        return Patterns.WEB_URL.matcher(URL).matches();
    }


}

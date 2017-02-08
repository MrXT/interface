package com.karakal.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class StringUtil {

    /**
     * 获取num随机数
     * @param num
     * @return
     */
    public static String getRandrom(int num) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }

    /**
     * @Title: checkIsNull
     * @Description: TODO(验证对象是否为空)
     * @param @param obj
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean checkIsNull(Object obj) {
        return obj == null || obj.toString().isEmpty() || obj.toString().equals("null");
    }

    /**
     * @Title: checkIsNotNull
     * @Description: TODO(验证对象是否为空)
     * @param @param obj
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     */
    public static boolean checkIsNotNull(Object obj) {
        return !checkIsNull(obj);
    }

    /**
     * 排序,id
     */
    public static List<Integer> sortSingerIds(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        Collections.sort(list, new Comparator<Integer>() {

            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        return list;
    }

    /**
     * 排序
     */
    public static String sortSingersStr(List<String> list) {
        Collections.sort(list, new Comparator<String>() {

            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        // 规则化歌手字符串
        StringBuffer sb = new StringBuffer();
        for (String artist : list) {
            sb.append(artist + ";");
        }
        return sb.substring(0, sb.lastIndexOf(";"));
    }

    /**
     * 排序
     */
    public static List<String> sortSingersList(List<String> list) {
        Collections.sort(list, new Comparator<String>() {

            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        return list;
    }

    public static List<Integer> toList(int[] arrys) {
        if (arrys == null || arrys.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int arry : arrys) {
            list.add(arry);
        }
        return list;
    }

    public static List<Integer> toList(Integer[] arrys) {
        if (arrys == null || arrys.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int arry : arrys) {
            list.add(arry);
        }
        return list;
    }

    static Map<String, String> specilChar = new HashMap<String, String>();
    static {
        specilChar.put("Ç", "C");
        specilChar.put("ç", "c");
        specilChar.put("Ë", "E");
        specilChar.put("ë", "e");
        specilChar.put("Ć", "c");
        specilChar.put("ć", "c");
        specilChar.put("Č", "C");
        specilChar.put("č", "c");
        specilChar.put("Đ", "D");
        specilChar.put("đ", "d");
        specilChar.put("Š", "S");
        specilChar.put("š", "s");
        specilChar.put("Ž", "Z");
        specilChar.put("ž", "z");
        specilChar.put("À", "A");
        specilChar.put("à", "a");
        specilChar.put("È", "E");
        specilChar.put("è", "e");
        specilChar.put("É", "E");
        specilChar.put("é", "e");
        specilChar.put("Í", "I");
        specilChar.put("í", "i");
        specilChar.put("Ï", "I");
        specilChar.put("ï", "i");
        specilChar.put("Ò", "O");
        specilChar.put("ò", "o");
        specilChar.put("Ó", "O");
        specilChar.put("ó", "o");
        specilChar.put("Ú", "U");
        specilChar.put("ú", "u");
        specilChar.put("Ü", "U");
        specilChar.put("ü", "u");
        specilChar.put("Á", "A");
        specilChar.put("á", "a");
        specilChar.put("Ď", "D");
        specilChar.put("ď", "d");
        specilChar.put("Ě", "E");
        specilChar.put("ě", "e");
        specilChar.put("Ň", "N");
        specilChar.put("ň", "n");
        specilChar.put("Ř", "R");
        specilChar.put("ř", "r");
        specilChar.put("Ť", "T");
        specilChar.put("ť", "t");
        specilChar.put("Ů", "U");
        specilChar.put("ů", "u");
        specilChar.put("Ý", "Y");
        specilChar.put("ý", "y");
        specilChar.put("Æ", "AE");
        specilChar.put("æ", "ae");
        specilChar.put("Ø", "O");
        specilChar.put("ø", "o");
        specilChar.put("Å", "A");
        specilChar.put("å", "a");
        specilChar.put("Ĉ", "C");
        specilChar.put("ĉ", "c");
        specilChar.put("Ĝ", "G");
        specilChar.put("ĝ", "g");
        specilChar.put("Ĥ", "H");
        specilChar.put("ĥ", "h");
        specilChar.put("Ĵ", "J");
        specilChar.put("ĵ", "j");
        specilChar.put("Ŝ", "S");
        specilChar.put("ŝ", "s");
        specilChar.put("Ŭ", "U");
        specilChar.put("ŭ", "u");
        specilChar.put("Ä", "A");
        specilChar.put("ä", "a");
        specilChar.put("Ö", "O");
        specilChar.put("Õ", "O");
        specilChar.put("õ", "o");
        specilChar.put("Ð", "D");
        specilChar.put("ð", "e");
        specilChar.put("Â", "A");
        specilChar.put("â", "a");
        specilChar.put("Ê", "E");
        specilChar.put("ê", "e");
        specilChar.put("Î", "I");
        specilChar.put("î", "i");
        specilChar.put("Ô", "O");
        specilChar.put("ô", "o");
        specilChar.put("ö", "o");
        specilChar.put("Œ", "CE");
        specilChar.put("œ", "oe");
        specilChar.put("Ù", "U");
        specilChar.put("ù", "u");
        specilChar.put("Û", "U");
        specilChar.put("û", "u");
        specilChar.put("Ÿ", "Y");
        specilChar.put("ÿ", "y");
        specilChar.put("ß", "B");
        specilChar.put("Ã", "A");
        specilChar.put("ã", "a");
        specilChar.put("Ĩ", "I");
        specilChar.put("ĩ", "i");
        specilChar.put("Ũ", "U");
        specilChar.put("ũ", "u");
        specilChar.put("ĸ", "k");
        specilChar.put("Ő", "O");
        specilChar.put("ő", "o");
        specilChar.put("Ű", "U");
        specilChar.put("ű", "u");
        specilChar.put("þ", "p");
        specilChar.put("Ì", "I");
        specilChar.put("ì", "i");
        specilChar.put("Ā", "A");
        specilChar.put("ā", "a");
        specilChar.put("Ē", "E");
        specilChar.put("ē", "e");
        specilChar.put("Ģ", "G");
        specilChar.put("ģ", "g");
        specilChar.put("Ī", "I");
        specilChar.put("ī", "i");
        specilChar.put("Ķ", "K");
        specilChar.put("ķ", "k");
        specilChar.put("Ļ", "L");
        specilChar.put("ļ", "l");
        specilChar.put("Ņ", "N");
        specilChar.put("ņ", "n");
        specilChar.put("Ŗ", "R");
        specilChar.put("ŗ", "r");
        specilChar.put("Ū", "U");
        specilChar.put("ū", "u");
        specilChar.put("Ą", "A");
        specilChar.put("ą", "a");
        specilChar.put("Ę", "E");
        specilChar.put("ę", "e");
        specilChar.put("Ł", "L");
        specilChar.put("ł", "l");
        specilChar.put("Ń", "N");
        specilChar.put("ń", "n");
        specilChar.put("Ś", "S");
        specilChar.put("ś", "s");
        specilChar.put("Ź", "Z");
        specilChar.put("ź", "z");
        specilChar.put("Ż", "Z");
        specilChar.put("ª", "a");
        specilChar.put("º", "o");
        specilChar.put("Ă", "A");
        specilChar.put("ă", "a");
        specilChar.put("Ş", "S");
        specilChar.put("ş", "s");
        specilChar.put("Ţ", "T");
        specilChar.put("ţ", "t");
        specilChar.put("Ŋ", "N");
        specilChar.put("ŋ", "n");
        specilChar.put("Ŧ", "T");
        specilChar.put("ŧ", "t");
        specilChar.put("Ž", "Z");
        specilChar.put("ž", "z");
        specilChar.put("Ĺ", "L");
        specilChar.put("ĺ", "I");
        specilChar.put("Ľ", "L");
        specilChar.put("ľ", "l");
        specilChar.put("Ŕ", "R");
        specilChar.put("ŕ", "r");
        specilChar.put("Ñ", "N");
        specilChar.put("ñ", "n");
        specilChar.put("¡", "i");
        specilChar.put("¿", "");
        specilChar.put("Ğ", "G");
        specilChar.put("ğ", "g");
        specilChar.put("İ", "I");
        specilChar.put("ı", "i");
        specilChar.put("&amp;", "&");
    };

    static Map<String, Boolean> frence_hash = new HashMap<String, Boolean>() {

        private static final long serialVersionUID = 3289000454509558835L;

        {
            String[] arr = { "é", "è", "ê", "ë", "à", "â", "â", "å", "ä", "á", "ô", "ö", "ò", "û", "ù", "ú", "¡", "î", "ï", "í", "ì", "ô", "ö", "ò",
                "û", "ù", "ú", "¡", "ü", "ß", "ç", "œ" };
            for (String str : arr)
                this.put(str, true);
        }
    };

    public static boolean isFrence(String str) {
        String ch = null;
        boolean r = false;
        for (int i = 0; i < str.length(); i++) {
            ch = str.substring(i, i + 1);
            if (frence_hash.get(ch) != null) {
                r = true;
            }
        }

        return r;
    }

    public static String replaceSpecialSongName(String name) {
        String c = replaceSpecial(name);
        return c.replaceAll(" & ", " and ");
    }

    public static String replacSpecialSinger(String name) {
        String c = replaceSpecial(name);
        return c.replaceAll("&", ",");
    }

    public static String replaceSpecial(String src) {
        if (StringUtils.isEmpty(src))
            return "";
        Set<String> en = specilChar.keySet();
        String sct = StringEscapeUtils.unescapeHtml(src);
        List<String> keys = new ArrayList<String>();
        List<String> values = new ArrayList<String>();
        for (String key : en) {
            keys.add(key);
            values.add(specilChar.get(key));
        }
        sct = StringUtils.replaceEach(sct, keys.toArray(new String[1]), values.toArray(new String[1]));
        sct = sct.replaceAll(" {2,}", " ").replaceAll("\r\n|\n", " ").trim();
        return sct.trim();
    }

    /**
     * 字符串是否全英文
     * @param word
     * @return
     */
    static boolean isEnglish(String word) {
        word = word.replace(" ", "");
        String p = "[a-zA-Z0-9\\+\\.\\,\\-()]+";
        return word.matches(p);
    }

    /**
     * 是否含有中文
     * @param word
     * @return
     */
    public static boolean containChinaeseWords(String word) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]+");
        Matcher m = pattern.matcher(word);
        boolean find = false;
        if (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                String c = m.group(i);
                if (null == c) {
                    continue;
                } else {
                    find = true;
                    break;
                }
            }
        }

        return find;
    }

    /**
     * jsonarry 转 List
     */
    public static Integer[] toList(JSONArray arr) {
        if (arr == null || arr.size() == 0) {
            return null;
        }
        Integer[] list = new Integer[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            list[i] = arr.getInteger(i);
        }
        return list;

    }

    /**
     * 集合是否相等List<Interger>,true=不为空
     */
    public static boolean CheckIsNotNullIntegerList(List<Integer> list) {
        if (list != null && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除所有特殊字符
     * @param name 原始字符串
     * @param extra 指定不删除字符串
     * @return
     */
    public static String trimSpecialChar(String name, String extra) {
        if (name == null) {
            return null;
        }
        String regEx = "[^\\p{L}\\p{Nd}" + extra + "]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name.toLowerCase());
        String re = m.replaceAll("").trim();
        if (re.equals("")) {
            return name;
        }
        return re;
    }

    @SuppressWarnings("rawtypes")
    public static void getFields(Class c, List<Field> fl) {
        Field[] fs = c.getDeclaredFields();
        for (Field f : fs) {
            fl.add(f);
        }
        Class sc = c.getSuperclass();
        if (sc != null) {
            getFields(sc, fl);
        }
    }

    /**
     * 字符串做并集
     */
    public static String toBingJi(String name, String name2) {
        String returnV = "";
        if (StringUtil.checkIsNull(name)) {
            return name2;
        }
        if (StringUtil.checkIsNull(name2)) {
            return name;
        }
        Set<String> values = new HashSet<String>();
        values.addAll(Arrays.asList(name.trim().split("、")));
        values.addAll(Arrays.asList(name2.trim().split("、")));
        returnV = values.toString();
        returnV = returnV.substring(1, returnV.length() - 1).replace(" ", "");
        returnV = returnV.replaceAll(",", "、");
        return returnV;
    }

    /**
     * List 转换 jsonarry
     */
    public static JSONArray toJsonArrty(Integer[] list) {
        JSONArray arry = new JSONArray();
        if (list == null || list.length == 0) {
            return null;
        }
        for (Integer i : list) {
            arry.add(i);
        }
        return arry;
    }

    /**
     * List 转换 jsonarry
     */
    public static JSONArray toJsonArrty(List<Integer> list, String type) {
        JSONArray arry = new JSONArray();
        if (list == null || list.size() == 0) {
            return null;
        }
        for (Integer i : list) {
            JSONObject one = new JSONObject();
            one.put(type, i);
            arry.add(one);
        }
        return arry;
    }

    /**
     * List 转换 jsonarry
     */
    public static JSONArray toJsonArrty2(List<String> list, String type) {
        JSONArray arry = new JSONArray();
        if (list == null || list.size() == 0) {
            return null;
        }
        for (String i : list) {
            JSONObject one = new JSONObject();
            one.put(type, i);
            arry.add(one);
        }
        return arry;
    }

    /**
     * 合并spilt
     */
    public static String megreSpilt(String a, String b) {
        if (StringUtil.checkIsNull(b)) {
            return a;
        }
        if (StringUtil.checkIsNull(a)) {
            return b;
        }
        JSONObject jso = new JSONObject();
        JSONObject a1 = JSONObject.parseObject(a);
        JSONObject b1 = JSONObject.parseObject(b);
        Set<String> colums = new HashSet<String>();
        Set<String> a_key = a1.keySet();
        Set<String> b_key = b1.keySet();
        for (String key : a_key) {
            colums.add(key);
        }
        for (String key : b_key) {
            colums.add(key);
        }

        for (String colum : colums) {
            JSONArray tmp = new JSONArray();
            JSONArray v1 = a1.getJSONArray(colum);
            JSONArray v2 = b1.getJSONArray(colum);
            if (StringUtil.checkIsNull(v1)) {
                jso.put(colum, v2);
            } else if (StringUtil.checkIsNull(v2)) {
                jso.put(colum, v1);
            } else if (StringUtil.checkIsNotNull(v1) && StringUtil.checkIsNotNull(v2)) {
                if (v1.equals(v2)) {
                    jso.put(colum, v1);
                } else {
                    Set<Integer> v_merge = new HashSet<Integer>();
                    for (Object i : v1) {
                        v_merge.add(Integer.parseInt(i.toString()));
                    }
                    for (Object i : v2) {
                        v_merge.add(Integer.parseInt(i.toString()));
                    }
                    for (Integer s : v_merge) {
                        tmp.add(s);
                    }
                    System.out.println(tmp);
                    jso.put(colum, tmp);
                }

            }
        }
        return jso.toJSONString();

    }

    public static int[] toArry(List<Integer> list) {

        if (list == null || list.size() == 0) {
            return null;
        }
        int[] a = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }
        return a;
    }

    public static String songGuizhe2(String songName) {
        // 规则二，#匹配括号及括号中的内容
        Pattern pattern = Pattern.compile("\\([^\\(\\)]*\\)");
        Matcher matcher = pattern.matcher(songName);
        songName = matcher.replaceAll("");

        // 规则二，#匹配括号及括号中的内容
        Pattern pattern_zh = Pattern.compile("\\（[^\\(\\)]*\\）");
        Matcher matcher_zh = pattern_zh.matcher(songName);
        songName = matcher_zh.replaceAll("");
        songName = trimSpecialChar(songName, "、");
        return songName;
    }

    /**
     * @Description: 去掉特殊字符后构造后like查询参数
     * @param str
     * @return
     * @throws
     * @author xiaolong.li@karakal.com.cn (李小龙)
     * @date 2015年11月27日 上午11:04:17
     * @version 1.0
     */
    public static String toTrimSpecialSearchLikeParam(String str) {
        String s = trimSpecialChar(str, "");
        if (!StringUtils.isBlank(s)) {
            return s.trim() + "%";
        }
        return null;
    }

    /**
     * list 转字符串,为数据库使用
     * @param list
     * @param patten
     * @return
     */
    public static <T> String join(List<T> list, String patten) {
        if (list == null || list.size() == 0) {
            return "";
        } else {
            T t = list.get(0);
            if (t instanceof String) {
                StringBuilder sb = new StringBuilder();
                for (T s : list) {
                    sb.append("'");
                    sb.append(s);
                    sb.append("'");
                    sb.append(patten);
                }
                sb.deleteCharAt(sb.length() - 1);
                return sb.toString();
            } else {
                StringBuilder sb = new StringBuilder();
                for (T s : list) {
                    sb.append(s);
                    sb.append(patten);
                }
                sb.deleteCharAt(sb.length() - 1);
                return sb.toString();
            }
        }
    }

    /**
     * 判断输入字符串是否满足手机号格式<br>
     * 手机号格式为：13开头的11位纯数字
     * @return 满足返回true反之false
     */
    public static boolean isMobileNo(String str) {
        return isMatch(str, "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$");
    }

    /**
     * 利用正则表达式检查是否完整匹配
     * @param str 校验字符
     * @param reg 正表表达式
     * @return
     */
    public static boolean isMatch(String str, String reg) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}

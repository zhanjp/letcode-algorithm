package top.hrzgj.string.n28;

import java.util.Objects;

/**
 * https://leetcode-cn.com/problems/implement-strstr/
 *
 * @author zhan jp
 * @date 2021-04-20 00:36
 */
public class StrIndex {

    /**
     * 相当于实现String.indexOf("xxx");
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        // 判断非法
        if (Objects.isNull(haystack) || Objects.isNull(needle) || haystack.length() < needle.length()) {
            return -1;
        }
        if (haystack.equals(needle) || needle.length() == 0) {
            return 0;
        }

        // 从主字符串的每一位开始
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            // 判断每一位匹配字符串
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i+j) != needle.charAt(j)) {
                    break;
                } else if (j == needle.length() - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        StrIndex strIndex = new StrIndex();
        System.out.println(strIndex.strStr("mississippi", "issipi"));
    }
}

package top.hrzgj.string.n3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * description
 *
 * @author zhan jp
 * @date 2021-04-11 23:24
 */
public class LongestSubString {

    public int lengthOfLongestSubstring(String s) {
        int len = 0;
        if (Objects.isNull(s) || (len = s.length()) == 0) {
            return 0;
        }

        // 利用map存字符索引，用于判断重复字符的出现
        Map<Character, Integer> charIndexMap = new HashMap<>(256);
        int maxLen = 0;
        // 滑动窗口，左指针
        int start = 0;
        // i: 滑动窗口右指针
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (!charIndexMap.containsKey(c) || charIndexMap.get(c) < start) {
                // 如果不重复则写入map
                charIndexMap.put(c, i);
            } else {
                // 重复则把左指针，移到重复值的右侧
                start = charIndexMap.get(c) + 1;
                charIndexMap.put(c, i);
                continue;
            }
            maxLen = Math.max(i - start + 1, maxLen);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubString().lengthOfLongestSubstring("tmmzuxt"));
    }
}

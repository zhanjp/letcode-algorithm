package top.hrzgj.string.n6;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/zigzag-conversion
 * @author zhan jp
 * @date 2021-04-12 00:18
 */
public class ZString {
    public String convert(String s, int numRows) {
        if (Objects.isNull(s) || s.length() <= 1 || numRows <= 1) {
            return s;
        }
        List<Integer>[] rows = new List[numRows];
        int x = 0;
        int direction = -1;
        for (int i = 0; i < s.length(); i++) {
            if (x == 0 || x == numRows - 1) {
                direction *= -1;
            }

            if (Objects.isNull(rows[x])) {
                rows[x] = new ArrayList<>(s.length() / numRows);
            }
            rows[x].add(i);
            x += direction;
        }

        char[] chars = new char[s.length()];
        int n = 0;
        Integer size = Math.min(s.length(), numRows);
        for (int i = 0; i < size; i++) {
            for (Integer index : rows[i]) {
                chars[n++] = s.charAt(index);
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(new ZString().convert("PAYPALISHIRING", 3));
    }
}

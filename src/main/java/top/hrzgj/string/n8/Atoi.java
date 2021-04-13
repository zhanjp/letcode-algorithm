package top.hrzgj.string.n8;

import java.util.Objects;

/**
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 * 把字符串读成整数
 * @author zhan jp
 * @date 2021-04-12 23:24
 */
public class Atoi {

    public int myAtoi(String s) {
        if (Objects.nonNull(s)) {
            s = s.trim();
        }
        if (Objects.isNull(s) || s.length() == 0) {
            return 0;
        }
        int syb = 0;
        int start = 0;

        if (s.charAt(0) == '-' ) {
            syb = -1;
            start ++;
        } else if (s.charAt(0) == '+' ) {
            syb = 1;
            start ++;
        } else {
            syb = 1;
        }

        int res = 0;
        for (; start < s.length(); start++) {
            int digit = s.charAt(start) - '0';
            if (digit < 0 || digit > 9) {
                break;
            }
            // 判断是否越界
            // 1. 用最大值除以10来判断当前值是否会越界
            // 2. 判断尾数是否会越界
            if (res > Integer.MAX_VALUE / 10 || (Integer.MAX_VALUE - res * 10) < digit) {
                return syb == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            } else {
                res = res * 10 + digit;
            }
        }
        return res * syb;
    }

    public static void main(String[] args) {
        System.out.println((byte)'0');
        System.out.println((byte)'9');
        System.out.println((byte)'+');
        System.out.println((byte)'-');
        System.out.println((byte)' ');
        System.out.println(Integer.MIN_VALUE+1);
        System.out.println(new Atoi().myAtoi(String.valueOf(Integer.MIN_VALUE)));
    }
}

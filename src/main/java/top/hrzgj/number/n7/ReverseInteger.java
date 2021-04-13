package top.hrzgj.number.n7;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 * 整数反转
 * @author zhan jp
 * @date 2021-04-13 23:37
 */
public class ReverseInteger {

    public int reverse(int x) {
        // 记录数字符号
        boolean positive = true;
        if (x == 0 || x == Integer.MIN_VALUE) {
            return 0;
        } else if (x < 0){
            positive = false;
            // 负数取绝对值进行计算
            x = Math.abs(x);
        }
        // 判断数字的位数
        int b = 0;
        int n = 1;
        // 用一个数组记录数字
        int[] digits = new int[10];
        while (x != 0) {
            // 算出最大位数的单位
            n = (int) Math.pow(10, b);
            // 取出，每一个数字记到数组中
            digits[b++] = x % 10;
            x /= 10;
        }
        // 循环位数，累加
        int res = 0;
        for (int i = 0; i < b; i++) {
            // 判断数字是否越界
            if ((Integer.MAX_VALUE - res) / n >= digits[i]) {
                res += digits[i] * n;
                n = n / 10;
            } else {
                return 0;
            }
        }
        return positive ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse(1563847412));
    }
}

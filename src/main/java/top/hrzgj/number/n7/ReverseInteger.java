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
        int res = 0;
        while (x != 0) {
            // 取出每一个数字
            int b = x % 10;
            x /= 10;
            // 判断溢出
            if ((Integer.MAX_VALUE - b) / 10 >= res) {
                res = res * 10 + b;
            } else {
                return 0;
            }
        }
        return positive ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new ReverseInteger().reverse(1463847412));
    }
}

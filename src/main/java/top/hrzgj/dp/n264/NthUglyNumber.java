package top.hrzgj.dp.n264;

/**
 * https://leetcode-cn.com/problems/ugly-number-ii/
 *
 * @author zhan jp
 * @date 2021-04-11 22:06
 */
public class NthUglyNumber {

    /**
     * 动态规划解法
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        if (n < 1 ) {
            return 0;
        }
        // 初始化n个丑数的数组
        int[] init = new int[n];
        // 第一个丑数=1
        init[0] = 1;

        // 第n个丑数，是之前的丑数，乘以因子(2/3/5)得到的
        // 3个指针，对应3个最基础的丑数因子
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        int index = 0;
        while (index < n-1) {
            // 选出下一个丑数
            int min = Math.min(Math.min(init[p2] * 2, init[p3] * 3), init[p5] * 5);
            // 选出的丑数也是当前已存在丑数数组中的最大值
            init[++index] = min;
            // 小于等于当前最大值的位置可以跳过
            while (init[p2] * 2 <= min) {
                p2 ++;
            }
            while (init[p3] * 3 <= min) {
                p3 ++;
            }
            while (init[p5] * 5 <= min) {
                p5 ++;
            }
        }
        return init[n -1];
    }

    public static void main(String[] args) {
        System.out.println(new NthUglyNumber().nthUglyNumber(10));
    }
}

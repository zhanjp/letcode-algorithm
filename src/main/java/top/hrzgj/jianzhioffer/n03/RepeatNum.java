package top.hrzgj.jianzhioffer.n03;

import java.util.BitSet;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 *
 * @author zhan jp
 * @date 2021-04-19 23:15
 */
public class RepeatNum {

    /**
     * 解法1，利用bitset,遇到相同位置的数字即返回
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        BitSet bitSet = new BitSet();
        for (int num : nums) {
            if (bitSet.get(num)) {
                return num;
            } else {
                bitSet.set(num);
            }
        }
        return 0;
    }

    /**
     * 解法2，由于题目中，长度为n的数组，包含了[0 - n-1]中的数字，如果不重复，则数组中可以正好索引和数字对上，如果对应索引上已有该数字，则表示重复
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            } else {
                if (nums[nums[i]] == nums[i]) {
                    return nums[i];
                } else {
                    int tmp = nums[i];
                    nums[i] = nums[tmp];
                    nums[tmp] = tmp;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int nums[] = {1,2,3,4,4,5,6,7};
        System.out.println(new RepeatNum().findRepeatNumber2(nums));
    }
}

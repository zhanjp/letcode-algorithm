package top.hrzgj.jianzhioffer.n04;

import java.sql.SQLOutput;

/**
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 *
 * @author zhan jp
 * @date 2021-04-19 23:57
 */
public class FindNumberIn2DArray {

    /**
     * n 行 m列的二维数组
     * 时间复杂度O(n + m)
     * 空间复杂度O(1)
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        final int rowNum = matrix.length;
        final int colNum = matrix[0].length;
        // 从每一行的末端开始对比，如果target > 该值，则判断下一行的末端值，如果target<该值，则说明target可能存在于该行
        // 如果对行内查找使用二分查找法，可有优化到O(n + logm)
        for (int i = colNum - 1; i >= 0; i--) {
            for (int j = 0; j < rowNum; j++) {
                if (matrix[j][i] == target) {
                    return true;
                } else if(matrix[j][i] < target) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] nums = {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        FindNumberIn2DArray finder = new FindNumberIn2DArray();
        System.out.println(finder.findNumberIn2DArray(nums, 5));
        System.out.println(finder.findNumberIn2DArray(nums, 20));


        int nums2[][] = {
                {-5}
        };

        System.out.println(finder.findNumberIn2DArray(nums2, -5));

    }
}

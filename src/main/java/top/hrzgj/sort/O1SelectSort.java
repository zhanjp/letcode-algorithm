package top.hrzgj.sort;

/**
 * 选择排序
 *   稳定排序算法，时间复杂度为O(n^2), 空间复杂度为O(1)
 *   适合数据量较小的排序
 *   核心就是每次选出一个最大/最小的数字放到已排序数组的最后面
 * 步骤
 *   1. 在未排序的序列中选择最大/最小的数，存放到未排序序列的起始位置
 *   2. 所有元素遍历完成，排序完成
 *
 * @author zhan jp
 * @date 2021-05-04 14:06
 */
public class O1SelectSort {

    public void sort(int[] arr, int start) {
        if (arr == null || arr.length <= 1 || arr.length <= start) {
            return;
        }

        int i = start;
        int max = start;
        while (++i < arr.length) {
            if (arr[max] < arr[i]) {
                max = i;
            }
        }
        int tmp = arr[start];
        arr[start] = arr[max];
        arr[max] = tmp;
        sort(arr, start+1);
    }

    public static void main(String[] args) {
        int[] arr = {2,1,7,3,2,7,9};
        new O1SelectSort().sort(arr, 0);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

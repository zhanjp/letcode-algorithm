package top.hrzgj.sort;

/**
 * 插入排序
 *   从未排序的数组中获取到一个数字，插入到已排序数组的对应位置中
 *   时间复杂度O(n^2)
 *   空间复杂度O(1)
 *   不稳定的排序算法，如果已经排过序的数组，则时间复杂度为O(n)
 * @author zhan jp
 * @date 2021-05-04 14:38
 */
public class O3InsertSort {

    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int n = arr[i];
            for (int j = i-1; j > 0; j--) {
                if (arr[j] > arr[j+1]) {
                    arr[j+1] = arr[j];
                } else {
                    arr[j+1] = n;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,1,7,3,2,7,9};
        new O2BubbleSort().sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

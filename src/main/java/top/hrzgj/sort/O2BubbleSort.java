package top.hrzgj.sort;

/**
 * 冒泡排序
 *   遍历未排序元素，每次比较相邻的两个元素，把较大者替换到右边
 *   时间复杂度O(n^2) 空间复杂度O(1)
 *   和选择排序时间、空间复杂度一样
 * @author zhan jp
 * @date 2021-05-04 14:20
 */
public class O2BubbleSort {

    public void sort(int[] arr) {
        if (arr == null || arr.length <= 1 ) {
            return;
        }

        for (int i = 0; i < arr.length-i; i++) {
            for (int j = i; j < arr.length-i-1; j++) {
                if (arr[j] > arr[j+1] ) {
                    int nj = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = nj;
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

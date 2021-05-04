package top.hrzgj.sort;

/**
 * 希尔排序
 *
 * @author zhan jp
 * @date 2021-05-04 21:12
 */
public class O6ShellSort {

    public void sort(int[] arr) {
        int length = arr.length;

        for (int step = length / 2; step >= 1; step >>= 1) {
            for (int i = step; i < length; i++) {
                int n = arr[i];
                int pm = i - step;
                while (pm >= 0 && arr[pm] > n) {
                    arr[pm + step] = arr[pm];
                    pm -= step;
                }
                arr[pm + step] = n;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,1,7,3,2,7,9, 4, 13};
        new O6ShellSort().sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

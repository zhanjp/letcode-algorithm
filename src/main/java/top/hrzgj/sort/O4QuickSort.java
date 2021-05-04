package top.hrzgj.sort;

/**
 * 快速排序
 *   在待排序的数组中选择一个数字，数字左边的子数组元素全部小于这个数字，右边的子数组元素全部大于这个元素，
 *   再对左右两个子数组执行相同的操作
 *   时间复杂度：数组length=n，每次遍历n个数字，需要遍历log(n)次，即nlog(n)
 *   空间复杂度：O(1)
 *
 *   非稳定排序算法，如果每次都选到的是最大的数字，则将退化为选择排序
 *
 * @author zhan jp
 * @date 2021-05-04 14:48
 */
public class O4QuickSort {

    public void sort(int[] arr, int start, int end) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        if (start >= end) {
            return;
        } else if (start +1 == end) {
            if (arr[start] > arr[end]) {
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
            }
        } else {
            // 把随机出来的数放到待排序数组尾部，是为了方便遍历
            int poivt = start + (int) Math.round (Math.random() * (end - start));
            int last = arr[end];
            arr[end] = arr[poivt];
            arr[poivt] = last;

            // 定义一个指针，指向左边数组的右边界
            int split = start;
            boolean rightNotEmpty = false;
            for (int i = start; i < end; i++) {
                // 如果数字小于poivt
                if (arr[i] < arr[end]) {
                    if (rightNotEmpty) {
                        int tmp = arr[split];
                        arr[split] = arr[i];
                        arr[i] = tmp;
                    }
                    split++;
                } else {
                    rightNotEmpty = true;
                }
            }
            int tmp = arr[end];
            arr[end] = arr[split];
            arr[split] = tmp;
            sort(arr, start, split - 1);
            sort(arr, split + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,1,7,3,2,7,9, 4, 13};
        new O4QuickSort().sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

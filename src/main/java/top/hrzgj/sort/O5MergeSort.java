package top.hrzgj.sort;

/**
 * 并归排序
 * 利用分而治之的思想，把数组的排序变成子数组排序后，再聚合起来即为并归排序
 * 时间复杂度=每次扫描数量n * 扫描次数 log(n) = nlog(n)
 * 空间复杂度=log(n)
 *
 * @author zhan jp
 * @date 2021-05-04 16:51
 */
public class O5MergeSort {

    public int[] sort(int[] arr, int start , int end) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        if (start == end) {
            return new int[] {arr[start]};
        } else if (start == end - 1) {
            return arr[start] < arr[end] ? new int[]{arr[start], arr[end]} : new int[]{arr[end], arr[start]};
        } else {
            int mid = start + end >> 1;
            int[] left = sort(arr, start, mid);
            int[] right = sort(arr, mid + 1, end);
            int[] res = new int[end - start + 1];
            int p1 = 0, p2 = 0, i = 0;
            while (p1 < left.length && p2 < right.length) {
                if (left[p1] < right[p2]) {
                    res[i++] = left[p1++];
                } else {
                    res[i++] = right[p2++];
                }
            }
            while (p1 < left.length) {
                res[i++] = left[p1++];
            }
            while (p2 < right.length) {
                res[i++] = right[p2++];
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,1,7,3,2,7,9, 4, 13};
        arr = new O5MergeSort().sort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

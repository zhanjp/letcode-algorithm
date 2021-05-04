package top.hrzgj.topn;

/**
 * 大顶堆求TopN 小的值
 * 小顶堆求TopN 大的值
 *
 * @author zhan jp
 * @date 2021-04-28 22:13
 */
public class TopN2 {

    /**
     * 构建大顶堆
     * @param arr
     * @param n
     */
    public void buildHeap(int[] arr, int n) {
        for (int i = n - 1; i > 0; i--) {
            int m = i;
            while (m > 0) {
                int child = arr[m];
                int p = (m + 1 >> 1) - 1;
                if (child > arr[p]) {
                    arr[m] = arr[p];
                    arr[p] = child;
                }
                m = p;
            }
        }
    }

    public void adjustHeap(int[] arr, int m, int n) {
        for (int index = n; index < arr.length; index++) {
            int num = arr[index];
            if (num >= arr[0]) {
                return;
            }
            arr[index] = arr[0];
            arr[0] = num;
            int i = 0;

            while (i < m-1) {
                int left = (i << 1) + 1;
                int right = (i << 1) + 2;
                if (left < m -1 && arr[i] < arr[left] ) {
                    int temp = arr[i];
                    arr[i] = arr[left];
                    arr[left] = temp;
                    i = left;
                } else if (right < m - 1 && arr[i] < arr[right] ) {
                    int temp = arr[i];
                    arr[i] = arr[right];
                    arr[right] = temp;
                    i = right;
                } else {
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {3,2,9,2,31,12,3,1,4,5};
        final TopN2 topN2 = new TopN2();
        topN2.buildHeap(arr, 5);
        print(arr);
        System.out.println("-----");
        topN2.adjustHeap(arr, 5, 5);
        print(arr);

        rootLastTraverse(arr, 5, 0);

    }

    // 打印数组
    public static void print(int[] data) {
        for(int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    /**
     * 后序遍历，打印整个数组中最小的5个数
     * @param arr
     * @param n
     * @param i
     */
    public static void rootLastTraverse(int[] arr, int n, int i) {
        int left = (i << 1) + 1;
        int right = (i << 1) + 2;
        if (left < n) {
            rootLastTraverse(arr, n, left);
        }
        if (right < n) {
            rootLastTraverse(arr, n, right);
        }
        System.out.print(arr[i] + " ");
    }

}

package template;

public class SortTemplate {

    /**
     * 快速搜索
     */
    public void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;  // 递归终止条件

        int pivot = partition(nums, start, end);  // 划分数组
        quickSort(nums, start, pivot - 1);        // 递归排序左半部分
        quickSort(nums, pivot + 1, end);          // 递归排序右半部分
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left + 1, j = right;

        while (i <= j) {
            if (nums[i] > pivot && nums[j] < pivot) {
                swap(nums, i, j);
            }
            if (nums[i] <= pivot) i++;
            if (nums[j] >= pivot) j--;
        }

        swap(nums, left, j);  // 将 pivot 放置到正确位置
        return j;
    }

    /**
     * 归并排序
     */
    public void mergeSort(int[] nums, int start, int end) {
        if (start >= end) return;  // 递归终止条件

        int mid = start + (end - start) / 2;  // 计算中间点
        mergeSort(nums, start, mid);          // 递归排序左半部分
        mergeSort(nums, mid + 1, end);        // 递归排序右半部分
        merge(nums, start, mid, end);         // 合并两个已排序部分
    }

    private void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];  // 临时数组存放合并结果
        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        // 把剩余的元素复制到临时数组
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= end) temp[k++] = nums[j++];

        // 将排序后的数组复制回原数组
        for (int p = 0; p < temp.length; p++) {
            nums[start + p] = temp[p];
        }
    }

    /**
     * 堆排序
     */
    public void heapSort(int[] nums) {
        int n = nums.length;

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, n, i);
        }

        // 逐个取出堆顶元素并调整堆
        for (int i = n - 1; i > 0; i--) {
            swap(nums, 0, i);     // 将堆顶元素与最后一个元素交换
            heapify(nums, i, 0);  // 对剩余元素重新调整为最大堆
        }
    }

    private void heapify(int[] nums, int n, int i) {
        int largest = i;        // 当前父节点
        int left = 2 * i + 1;   // 左子节点
        int right = 2 * i + 2;  // 右子节点

        // 找到子节点中较大的那个
        if (left < n && nums[left] > nums[largest]) largest = left;
        if (right < n && nums[right] > nums[largest]) largest = right;

        // 如果父节点不是最大值，交换并继续调整
        if (largest != i) {
            swap(nums, i, largest);
            heapify(nums, n, largest);  // 递归调整
        }
    }

    /**
     * 三向切分快速排序
     */
    public void threeWayQuickSort(int[] array, int low, int high) {
        if (low < high) {
            int[] pivotIndices = partition2(array, low, high);

            threeWayQuickSort(array, low, pivotIndices[0] - 1);

            threeWayQuickSort(array, pivotIndices[1] + 1, high);
        }
    }

    private int[] partition2(int[] array, int low, int high) {
        int pivot = array[low];
        int lt = low;
        int gt = high;
        int i = low + 1;

        while (i <= gt) {
            if (array[i] < pivot) {
                swap(array, lt++, i++);
                continue;
            }

            if (array[i] > pivot) {
                swap(array, i, gt--);
                continue;
            }

            i++;
        }

        return new int[]{lt, gt};
    }

    /* ------------------------------------------- common ------------------------------------------------------ */

    /**
     * 交换
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

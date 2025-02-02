package template;

public class QuickSelectTemplate {

    /**
     * 查找第K大的元素
     */
    public int findKthLargest(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        int target = nums.length - k;

        while (low <= high) {
            int pivot = partition(nums, low, high);
            if (pivot == target) return nums[pivot];
            else if (pivot < target) low = pivot + 1;
            else high = pivot - 1;
        }

        return -1;
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        int left = low + 1, right = high;

        while (left <= right) {
            if (nums[left] > pivot && nums[right] < pivot) swap(nums, left, right);

            if (nums[left] <= pivot) left++;
            if (nums[right] >= pivot) right--;
        }

        swap(nums, low, right);
        return right;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
package template;

public class SearchTemplate {

    /**
     * 二分搜索
     */
    public int search(int[] nums, int target) {
        // 1、初始化left、right
        int left = 0, right = nums.length - 1;

        // 2、处理for循环
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            // 3、比较nums[mid]和target值
            if (nums[mid] == target) return mid;

            if (nums[mid] < target) {
                left = mid;
                continue;
            }

            right = mid;
        }

        // 4、最后剩下两个元素，手动判断
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

}

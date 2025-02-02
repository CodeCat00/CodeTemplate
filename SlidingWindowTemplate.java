package template;

public class SlidingWindowTemplate {

    /**
     * 模板
     */
    public int slidingWindow(int[] nums, int k) {
        int left = 0, right = 0; // 定义左右指针
        int sum = 0;             // 用来跟踪窗口内的数值和
        int result = 0;          // 存储结果

        while (right < nums.length) {
            // 扩大窗口，加入右边元素
            sum += nums[right];

            // 判断窗口是否满足条件，如：窗口大小为 k
            while (right - left + 1 > k) {
                sum -= nums[left];  // 缩小窗口，移除左边元素
                left++;
            }

            // 当窗口大小为 k 时，更新结果
            if (right - left + 1 == k) {
                result = Math.max(result, sum);  // 根据题意更新结果（这里假设是最大和）
            }

            right++;  // 扩大窗口
        }

        return result;  // 返回最终结果
    }

}

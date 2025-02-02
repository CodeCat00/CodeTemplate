package template;

import java.util.List;

public class DynamicProgrammingTemplate {

    /**
     * DFS
     */
    private int dfs(int x, int y, List<List<Integer>> triangle, int[][] saves) {
        if (x == triangle.size() - 1) {
            return triangle.get(x).get(y);
        }
        // 如果已经被计算过则直接返回
        if (saves[x][y] != 0) {
            return saves[x][y];
        }
        int minLeft = dfs(x + 1, y, triangle, saves);
        int minRight = dfs(x + 1, y + 1, triangle, saves);
        // 缓存已经被计算的值
        saves[x][y] = Math.min(minLeft, minRight) + triangle.get(x).get(y);
        return saves[x][y];
    }

    /**
     * 动态规划 - 底向上
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 1、状态定义：f[i][j] 表示从i,j出发，到达最后一层的最短路径
        int[][] dp = new int[triangle.size()][triangle.size()];
        // 2、初始化
        for (int i = 0; i < triangle.size(); i++) {
            dp[triangle.size() - 1][i] = triangle.getLast().get(i);
        }
        // 3、递推求解
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        // 4、结果
        return dp[0][0];
    }

    /**
     * 动态规划 - 顶向下
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        // 1、状态定义：dp[i][j] 表示从0,0出发，到达i,j的最短路径
        int[][] dp = new int[triangle.size()][triangle.size()];
        // 2、初始化
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // 这里分为三种情况：
                // 1、上一层没有左边值
                // 2、上一层没有右边值
                // 3、其他
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                } else if (j == triangle.get(i).size() - 1) {
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
                }
            }
        }
        // 从最后一层中查找最小值
        int minValue = dp[triangle.size() - 1][0];
        for (int i = 0; i < triangle.size(); i++) {
            minValue = Math.min(minValue, dp[triangle.size() - 1][i]);
        }
        return minValue;
    }

    /**
     * 动态规划 - 空间优化
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()];
        for (int i = 0; i < triangle.size(); i++) {
            dp[i] = triangle.getLast().get(i);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

}

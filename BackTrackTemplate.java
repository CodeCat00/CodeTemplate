package template;

import java.util.ArrayList;
import java.util.List;

/*

以下是一些其他经典的回溯问题：
    1. N 皇后问题（N-Queens Problem）
        问题描述：在 N x N 的棋盘上摆放 N 个皇后，使得它们彼此不能攻击（即任意两个皇后不能在同一行、同一列或同一斜线上）。
        解法：使用回溯法，每次在棋盘的某一行尝试放置一个皇后，递归检查下一个皇后是否可以合法放置。
    2. 解数独（Sudoku Solver）
        问题描述：填充数独的空白格，使得每行、每列和每个 3x3 宫内都包含 1-9 的数字，且没有重复数字。
        解法：使用回溯法逐步填充空格，并在每次填充后验证当前数独是否符合规则。如果不符合规则则回溯重新选择。
    3. 组合总和（Combination Sum）
        问题描述：给定一个候选数集合和一个目标数，找出所有能够使候选数之和等于目标数的组合，候选数可以无限制重复使用。
        解法：回溯过程中选择一个数，可以重复使用该数，但递归时需要注意控制候选数的选择顺序以避免重复组合。
    4. 分割回文串（Palindrome Partitioning）
        问题描述：将一个字符串分割成若干子串，要求每个子串都是回文。
        解法：使用回溯法尝试所有可能的分割位置，检查每个子串是否是回文，然后递归处理剩下的字符串。
    5. 单词搜索（Word Search）
        问题描述：给定一个二维字符网格和一个单词，判断该单词是否存在于网格中，单词可以通过连续的上下左右相邻字符构成，但不能重复使用同一格子。
        解法：从网格的每一个位置开始，使用回溯法检查单词能否通过相邻的字符构成。
    6. 划分问题（Partition Problem）
        问题描述：给定一个数组，将其划分为两个子集，使得这两个子集的和相等。
        解法：回溯法尝试将元素放入两个子集，递归检查是否可以达到目标。
    7. 找零钱问题（Coin Change Problem）
        问题描述：给定一组不同面额的硬币和一个总金额，找出所有可能的硬币组合，使得这些组合的金额等于目标值。
        解法：使用回溯法，在每一步选择一个硬币，递归寻找剩下的金额的解决方案。
    8. 矩阵中的路径问题（Path in Matrix）
        问题描述：给定一个二维矩阵，寻找一条从起点到终点的路径，使得路径经过的格子的值满足特定的要求（如最小路径和、特定字符构成的路径等）。
        解法：通过回溯探索矩阵中的每一个格子，递归地寻找满足条件的路径，并在必要时回溯。
    9. 全排列 II（Permutation II, with Duplicates）
        问题描述：给定一个可能包含重复数字的数组，返回所有不重复的全排列。
        解法：使用回溯法生成全排列时，需要跳过重复元素以避免生成重复的排列结果。
    10. 括号生成（Generate Parentheses）
        问题描述：给定一个整数 n，生成所有由 n 对括号组成的合法括号组合。
        解法：通过回溯法递归地生成左括号和右括号，并确保生成的括号组合合法。
    11. 路径总和问题（Path Sum）
        问题描述：在二叉树中寻找所有从根节点到叶节点的路径，使得路径上的节点值之和等于给定的目标值。
        解法：使用回溯法从根节点开始递归搜索每一条路径，累加节点值并判断路径是否符合目标。
    12. 分割等和子集问题（Partition Equal Subset Sum）
        问题描述：给定一个正整数数组，判断是否可以将数组划分为两个子集，使得两个子集的和相等。
        解法：回溯法递归地尝试将数组的元素分配到两个子集中，检查是否可以满足和相等的条件。
    13. 字母大小写全排列（Letter Case Permutation）
        问题描述：给定一个字母和数字组成的字符串，返回所有可能的字母大小写全排列。
        解法：通过回溯法处理每个字符，如果是字母则生成大写和小写两种选择，递归生成结果。
    14. K-Sum 问题
        问题描述：在给定数组中找到 k 个数字，使得它们的和等于目标值。
        解法：使用回溯法进行递归，在每一步选择一个数字并递归查找其余部分的解。
总结：
    回溯法适用于解决需要枚举所有可能解，并从中找出满足特定条件解的场景。常见的应用场景包括排列、组合、子集、路径查找、棋盘类问题等。

 */

public class BackTrackTemplate {

    /**
     * 子集
     */
    private void backtrack(int[] nums, int start, List<Integer> currentSubset, List<List<Integer>> result) {
        // 每个递归调用都将当前的子集加入结果中
        result.add(new ArrayList<>(currentSubset));

        // 遍历数组中从当前位置开始的每个元素
        for (int i = start; i < nums.length; i++) {
            // 做出选择：把当前元素添加到子集中
            currentSubset.add(nums[i]);

            // 递归地尝试选择后续的元素
            backtrack(nums, i + 1, currentSubset, result);

            // 撤销选择：从子集中移除最后一个元素，进行回溯
            currentSubset.removeLast();
        }
    }

    /**
     * 全排列
     */
    private void backtrack(int[] nums, boolean[] used, List<Integer> currentPermutation, List<List<Integer>> result) {
        // 如果当前排列的长度等于数组的长度，则找到一个有效的排列
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
            return; // 返回，继续查找其他排列
        }

        // 遍历数组，尝试每个元素
        for (int i = 0; i < nums.length; i++) {
            // 如果元素已被使用，则跳过
            if (used[i]) continue;

            // 做出选择：将当前元素添加到排列中
            used[i] = true;
            currentPermutation.add(nums[i]);

            // 递归调用，继续生成后续元素的排列
            backtrack(nums, used, currentPermutation, result);

            // 撤销选择：从排列中移除最后添加的元素，进行回溯
            currentPermutation.removeLast();
            used[i] = false; // 标记当前元素为未使用
        }
    }

    /**
     * 组合
     */
    private void backtrack(int n, int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        // 如果当前组合的长度等于 k，则找到一个有效组合
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination)); // 将其添加到结果中
            return;
        }

        // 从 start 到 n 遍历，尝试每个可能的选择
        for (int i = start; i <= n; i++) {
            // 剪枝：后续元素比目标大，直接break
            if (currentCombination.get(i) > target) break;

            // 做出选择：将当前元素 i 加入到组合中
            currentCombination.add(i);

            // 递归调用，继续选择下一个元素
            backtrack(n, target, i + 1, currentCombination, result);

            // 撤销选择：从组合中移除最后一个元素，进行回溯
            currentCombination.removeLast();
        }
    }

}

package template;

import java.util.*;

public class TreeNodeTemplate {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
        }
    }

    /**
     * 递归遍历
     */
    public void traverse(TreeNode p, List<Integer> result) {
        if (p == null) {
            return;
        }
        // 其他遍历调整这里的语句顺序即可
        result.add(p.val);
        traverse(p.left, result);
        traverse(p.right, result);
    }

    /**
     * 前序非递归
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);  // 将根节点入栈

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();  // 访问当前节点
            result.add(node.val);          // 记录当前节点的值

            // 先入栈右子节点，再入栈左子节点
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }

    /**
     * 中序非递归
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        // 遍历整个树
        while (current != null || !stack.isEmpty()) {
            // 先遍历到最左边的节点
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // 访问栈顶节点
            current = stack.pop();
            result.add(current.val);  // 记录当前节点的值

            // 继续遍历右子树
            current = current.right;
        }

        return result;
    }

    /**
     * 后序非递归
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>();
        stack.push(root);

        // 进行标准的前序遍历，节点顺序被反转
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.push(node); // 先将根节点压入输出栈

            // 压入左右子节点
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        // 从输出栈中提取结果
        while (!output.isEmpty()) {
            result.add(output.pop().val);
        }

        return result;
    }

    /**
     * DFS
     */
    public void dfs(TreeNode p, StringBuilder path, List<String> paths) {
        if (null == p) return;

        path.append(p.val);

        if (p.left == null && p.right == null) {
            paths.add(path.toString());
            return;
        }

        path.append("->");
        dfs(p.left, new StringBuilder(path), paths);
        dfs(p.right, new StringBuilder(path), paths);
    }

    /**
     * BFS
     */
    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            result.add(p.val);
            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);
            }
        }
        return result;
    }

}

package template;

public class UnionSetTemplate {

    private int[] parent; // 存储每个节点的父节点
    private int[] rank;   // 存储每个节点的秩（树的深度）

    public UnionSetTemplate(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i; // 初始化每个节点的父节点为其本身
            rank[i] = 1;    // 初始化每个节点的秩为 1
        }
    }

    // 查找节点的根节点，并进行路径压缩
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 路径压缩
        }
        return parent[x];
    }

    // 合并两个节点的集合
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            // 按秩合并，保证树的高度尽量小
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // 判断两个节点是否在同一个集合
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }

}

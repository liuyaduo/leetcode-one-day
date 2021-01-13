class Solution {
    int nodeCount;
    int[] parent;
    int[] size;
    
    public int[] findRedundantConnection(int[][] edges) {
        //此时边数等于节点数
        nodeCount = edges.length;
        //并查集初始化
        parent = new int[nodeCount+1];
        size = new int[nodeCount+1];
        for(int i = 1; i <= nodeCount; i++){
            parent[i] = i;
            size[i] = 1;
        }
        for(int i = 0; i < nodeCount; i++) {
            //取出第i条边
            int[] edge = edges[i];
            if(union(edge[0], edge[1]))
                return edge;
        }
        return new int[0];
    }
    
    //并查集查找操作
    private int find(int x) {
        if(x != parent[x]){
            //路径压缩
            parent[x] = find(parent[x]);
            return parent[x];
        }
        return x;
    }
    
    //并查集并操作
    private boolean union(int x, int y){
        //连通则返回，有环
        if(connected(x, y))
            return true;
        //将一个节点挂到另一个节点的祖先上，这样两者祖先就相同了
        //将小的树挂到大的树上,使树尽量平衡
        if(size[x] > size[y]) {
            parent[find(x)] = find(y);
            size[x] += size[y];
        }
        if(size[y] >= size[x]) {
            parent[find(y)] = find(x);
            size[y] += size[x];
        }
        return false;
    }
    
    //是否连通
    private boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
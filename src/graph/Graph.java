package graph;

import java.util.LinkedList;

public class Graph {
    private int vertexSize;
    private int[] vertexs;
    private int[][] matrix;
    private boolean[] isVisited;
    private static final int MAX_WEIGHT = 65535;

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getVertexSize() {
        return vertexSize;
    }

    public Graph(int vertexSize) {
        this.vertexSize = vertexSize;
        matrix = new int[vertexSize][vertexSize];
        isVisited = new boolean[vertexSize];
        vertexs = new int[vertexSize];
        for (int i = 0; i < vertexSize; i++) {
            vertexs[i] = i;
        }
    }

    public int getOutDegree(int index) {
        int N = matrix[index].length;
        int degree = 0;
        for (int i = 0; i < N; i++) {
            if (matrix[index][i] != MAX_WEIGHT && matrix[index][i] != 0) {
                degree++;
            }
        }
        return degree;
    }

    public int getInDegree(int index) {
        int N = matrix[index].length;
        int degree = 0;
        for (int i = 0; i < N; i++) {
            if (matrix[i][index] != MAX_WEIGHT && matrix[i][index] != 0) {
                degree++;
            }
        }
        return degree;
    }

    public int getWeight(int v1, int v2) {
        return matrix[v1][v2] != 0 && matrix[v1][v2] != MAX_WEIGHT ? matrix[v1][v2] : -1;
    }

    /**
        找到一个顶点的第一个邻接点
     */
    public int getFristNeighbor(int index) {
        for (int i = 0; i < vertexSize; i++) {
            if (matrix[index][i] != 0 && matrix[index][i] < MAX_WEIGHT) {
                return i;
            }
        }
        return -1;
    }

    /**
        根据前一个邻接点的下标来取得下一个邻接点
        @param v 代表要找的点
        @param index 表示该顶点(v)相对于哪个邻接点去获取下一个邻接点
     */
    public int getNextNeighbor(int v, int index) {
        for (int i = index+1; i < vertexSize; i++) {
            if (matrix[v][i] != 0 && matrix[v][i] < MAX_WEIGHT) {
                return i;
            }
        }
        return -1;
    }

    private void depthFristSearch(int i) {
        isVisited[i] = true;
        int w = getFristNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                //需要遍历该顶点
                System.out.println("item is "+w);
                depthFristSearch(w);
            }
            w = getNextNeighbor(i, w); //第一个相对于w的邻接点
        }
    }

    /**
     * 对外公开的深度优先遍历
     * 解决了有向图和非连通图的问题
     */
    public void depthFristSearch(){
        isVisited = new boolean[vertexSize];
        for (int i = 0; i < vertexSize; i++)
        {
            if (!isVisited[i]) {
                System.out.println("item is "+i);
                depthFristSearch(i);
            }
        }
        isVisited = new boolean[vertexSize];
    }


    /**
     * 广度优先遍历
     */
    private void broadFirstSearch(int i) {
        int u,w;
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.println("item is "+i);
        isVisited[i] = true;
        queue.offer(i); //第一次把v0加入队列
        while (!queue.isEmpty()) {
            u = queue.poll();
            w = getFristNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.println("item is " + w);
                    isVisited[w] = true;
                    queue.offer(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    /**
     * 对外公开的
     * 图的广度优先遍历算法
     */
    public void broadFirstSearch(){
        isVisited = new boolean[vertexSize];
        for (int i = 0; i < vertexSize; i++) {
            if (!isVisited[i]) {
                broadFirstSearch(i);
            }
        }
    }


    /**
     * Prim算法（最小生成树）
     * @param
     */

    public void prim() {
        int[] lowcost = new int[vertexSize]; //最小代价顶点权值的数组
        int[] adjvex = new int[vertexSize]; //放顶点权值
        int min, minId, sum = 0;
        for (int i = 1; i < vertexSize; i++) {
            lowcost[i] = matrix[0][i];
        }
        for (int i = 1; i < vertexSize; i++) {
            min = MAX_WEIGHT;
            minId = 0;
            for (int j = 1; j < vertexSize; j++) {
                if (lowcost[j] < min && lowcost[j] > 0) {
                    min = lowcost[j];
                    minId = j;
                }
            }
            System.out.println("顶点："+adjvex[minId]+"权值："+min);
            sum += min;
            lowcost[minId] = 0;
            for (int j = 1; j < vertexSize; j++) {
                if (lowcost[j] != 0 && matrix[minId][j] < lowcost[j]) {
                    lowcost[j] = matrix[minId][j];
                    adjvex[j] = minId;
                }
            }
        }
        System.out.println("最小生成树权值和："+sum);
    }

    public void createGraph(){
        int [] a1 = new int[]{0,1,5,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
        int [] a2 = new int[]{1,0,3,7,5,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
        int [] a3 = new int[]{5,3,0,MAX_WEIGHT,1,7,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
        int [] a4 = new int[]{MAX_WEIGHT,7,MAX_WEIGHT,0,2,MAX_WEIGHT,3,MAX_WEIGHT,MAX_WEIGHT};
        int [] a5 = new int[]{MAX_WEIGHT,5,1,2,0,3,6,9,MAX_WEIGHT};
        int [] a6 = new int[]{MAX_WEIGHT,MAX_WEIGHT,7,MAX_WEIGHT,3,0,MAX_WEIGHT,5,MAX_WEIGHT};
        int [] a7 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,3,6,MAX_WEIGHT,0,2,7};
        int [] a8 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,9,5,2,0,4};
        int [] a9 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,7,4,0};

        matrix[0] = a1;
        matrix[1] = a2;
        matrix[2] = a3;
        matrix[3] = a4;
        matrix[4] = a5;
        matrix[5] = a6;
        matrix[6] = a7;
        matrix[7] = a8;
        matrix[8] = a9;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(9);

        int [] a1 = new int[]{0,10,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
        int [] a2 = new int[]{10,0,18,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,MAX_WEIGHT,12};
        int [] a3 = new int[]{MAX_WEIGHT,MAX_WEIGHT,0,22,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,8};
        int [] a4 = new int[]{MAX_WEIGHT,MAX_WEIGHT,22,0,20,MAX_WEIGHT,MAX_WEIGHT,16,21};
        int [] a5 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,20,0,26,MAX_WEIGHT,7,MAX_WEIGHT};
        int [] a6 = new int[]{11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,26,0,17,MAX_WEIGHT,MAX_WEIGHT};
        int [] a7 = new int[]{MAX_WEIGHT,16,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,17,0,19,MAX_WEIGHT};
        int [] a8 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,7,MAX_WEIGHT,19,0,MAX_WEIGHT};
        int [] a9 = new int[]{MAX_WEIGHT,12,8,21,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0};

        graph.matrix[0] = a1;
        graph.matrix[1] = a2;
        graph.matrix[2] = a3;
        graph.matrix[3] = a4;
        graph.matrix[4] = a5;
        graph.matrix[5] = a6;
        graph.matrix[6] = a7;
        graph.matrix[7] = a8;
        graph.matrix[8] = a9;

//        System.out.println(graph.getOutDegree(4));
//        System.out.println(graph.getInDegree(4));
//        System.out.println(graph.getWeight(3,2));
        graph.depthFristSearch();
        System.out.println(" ");
        graph.broadFirstSearch();

//        for (int i = 0; i < graph.vertexSize; i++) {
//            for (int j = 0; j < graph.vertexSize; j++) {
//                System.out.print(graph.matrix[i][j]+" ");
//            }
//            System.out.println();
//        }

        graph.prim();


    }


}

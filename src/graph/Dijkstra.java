package graph;

public class Dijkstra {
    private static final int MAXVEX = 9;
    private static final int MAXWEIGHT = 65535;
    private int[] shortTablePath = new int[MAXVEX]; //记录的是v0到某顶点的最短路径合

    public void shortestPathDijkstra(Graph graph) {
        int min;
        int k = 0;
        boolean[] isGetPath = new boolean[MAXVEX];   //是否被访问到，避免重复判断
        for (int v = 0; v < graph.getVertexSize(); v++) {
            shortTablePath[v] = graph.getMatrix()[0][v];
        }
        shortTablePath[0] = 0;
        isGetPath[0] = true;
        for (int v = 1; v < graph.getVertexSize(); v++) {
            min = MAXWEIGHT;
            for (int w = 0; w < graph.getVertexSize(); w++) {
                if (!isGetPath[w] && shortTablePath[w] < min) {
                    k = w;
                    min = shortTablePath[w];
                }
            }
            isGetPath[k] = true;
            for (int j = 0; j < graph.getVertexSize(); j++) {
                if (!isGetPath[j] && (min + graph.getMatrix()[k][j] < shortTablePath[j])) {
                    shortTablePath[j] = min + graph.getMatrix()[k][j];
                }
            }
        }
        for (int i = 0; i < shortTablePath.length; i++) {
            System.out.println("v0 --> v" + i + " 最短路径为：" + shortTablePath[i]);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(MAXVEX);
        graph.createGraph();
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.shortestPathDijkstra(graph);
    }

}


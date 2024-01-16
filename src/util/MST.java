package util;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MST {

    public static class Edge {
        int sourceVertex;
        int targetVertex;
        double weight;

        public Edge(int sourceVertex, int targetVertex, double weight) {
            this.sourceVertex = sourceVertex;
            this.targetVertex = targetVertex;
            this.weight = weight;
        }
    }

    private int numOfVertices;
    private Graph.VertexNode[] vertices;
    private int[][] classification;
    private ArrayList<ArrayList<Edge>> allMSTs;
    private int cols, rows;

    public int[][] getClassification() {
        return classification;
    }

    public MST(Graph graph) {
        this.numOfVertices = graph.getNumOfVertices();
        this.vertices = graph.getVertices();
        this.cols = graph.getCols();
        this.rows = graph.getRows();
        this.allMSTs = findAllMSTs();
        this.classification = classify();
    }


    private ArrayList<ArrayList<Edge>> findAllMSTs() {
        boolean[] visited = new boolean[numOfVertices];
        ArrayList<ArrayList<Edge>> allMSTs = new ArrayList<>();

        for (int startVertex = 0; startVertex < numOfVertices; startVertex++) {
            if (!visited[startVertex]) {
                ArrayList<Edge> mstEdges = primMST(startVertex, visited);
                allMSTs.add(mstEdges);
            }
        }

        return allMSTs;
    }

    private ArrayList<Edge> primMST(int startVertex, boolean[] visited) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((a, b) -> Double.compare(a.weight, b.weight));

        visited[startVertex] = true;


        addEdgesToPriorityQueue(startVertex, priorityQueue);

        ArrayList<Edge> mstEdges = new ArrayList<>();
        double totalWeight = 0;


        while (!priorityQueue.isEmpty()) {
            Edge minEdge = priorityQueue.poll();

            int nextVertex = minEdge.targetVertex;

            if (visited[nextVertex]) {
                continue;
            }

            visited[nextVertex] = true;

            mstEdges.add(minEdge);
            totalWeight += minEdge.weight;

            addEdgesToPriorityQueue(nextVertex, priorityQueue);
        }

//        System.out.println("该棵最小生成树的总权重：" + totalWeight + "\n" + "整颗生成图如下:");
//        displayMST(mstEdges);
        return mstEdges;
    }

    private void addEdgesToPriorityQueue(int vertexIndex, PriorityQueue<Edge> priorityQueue) {
        Graph.ArcNode currentArc = vertices[vertexIndex].firstArc;


        while (currentArc != null) {
            int targetVertex = currentArc.targetVertex;
            double weight = currentArc.getWeight();

            priorityQueue.add(new Edge(vertexIndex, targetVertex, weight));

            currentArc = currentArc.nextArc;
        }

    }

    // 显示每颗最小生成树的信息
    private void displayMST(ArrayList<Edge> mstEdges) {
        System.out.println("该棵最小生成树的路径：");
        for (Edge edge : mstEdges) {
            System.out.println(edge.sourceVertex + " - " + edge.targetVertex + " : " + edge.weight);
        }
        System.out.println();
    }

    public void displayAllMSTs(ArrayList<ArrayList<Edge>> allMSTs) {
        System.out.println("所有最小生成树的信息：");
        for (ArrayList<Edge> mstEdges : allMSTs) {
            displayMST(mstEdges);
        }
    }


    //    public void displayAllMSTs() {
//        ArrayList<ArrayList<Edge>> allMSTs = findAllMSTs();
//        System.out.println("所有最小生成树的信息：");
//        for (ArrayList<Edge> mstEdges : allMSTs) {
//            displayMST(mstEdges);
//        }
//    }
//
//    public int[][] classification(int height, int width) {
//        //将每颗最小生成树中的定点信息存入二维数组中
//        //同一颗最小生成树中的点的值相同
//        //不同最小生成树中的点的值不同
//        //从0开始
//        int label = 0;
//        ArrayList<ArrayList<Edge>> allMSTs = findAllMSTs();
//        int[][] classification = new int[height][width];
//        for (ArrayList<Edge> mstEdges : allMSTs) {
//            for (Edge edge : mstEdges) {
//                int targetVertex = edge.targetVertex;
//                //计算sourceVertex的坐标
//                int x = targetVertex % width;
//                int y = targetVertex / width;
//                classification[y][x] = label;
//            }
//            label++;
//        }
//        return classification;
//    }
    private int getI(int index) {
        return index / cols;
    }

    private int getJ(int index) {
        return index % cols;
    }

    private int[][] classify() {
        int[][] classification = new int[rows][cols];
        int label = 0;
        for (ArrayList<Edge> mstEdges : allMSTs) {
            if(mstEdges.size() == 0) {
                continue;
            }
            //将每颗最小生成树的第一个结点的sourceVertex对应的坐标作为该颗最小生成树的标签
            //以及所有结点的targetVertex对应的坐标都是该颗最小生成树的标签
            Edge firstEdge = mstEdges.get(0);
            int firstVertex = firstEdge.sourceVertex;
            int i = getI(firstVertex);
            int j = getJ(firstVertex);
            classification[i][j] = label;
            for (Edge edge : mstEdges) {
                int targetVertex = edge.targetVertex;
                int x = getI(targetVertex);
                int y = getJ(targetVertex);
                classification[x][y] = label;
            }
            label++;
        }
        return classification;
    }

}

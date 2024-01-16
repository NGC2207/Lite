package util;

public class Graph {
    class ArcNode {
        int targetVertex; //弧所指向的顶点的位置
        ArcNode nextArc; //指向下一条弧的指针
        double weight; //弧的权重

        public double getWeight() {
            return weight;
        }
    }

    class VertexNode {
        int vertex; //顶点的信息
        ArcNode firstArc; //指向第一条依附该顶点的弧的指针
    }

    private int numOfVertices; //顶点个数
    private VertexNode[] vertices; //顶点数组
    private int cols;
    private int rows;

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getNumOfVertices() {
        return numOfVertices;
    }

    public VertexNode[] getVertices() {
        return vertices;
    }

    public enum Direction {
        UP, LEFT, RIGHT, BOTTOM
    }

    private boolean isValidVisit(int width, int height, int y, int x, Direction direction) {
        switch (direction) {
            case UP -> {
                return y >= 2;
            }
            case LEFT -> {
                return x >= 2;
            }
            case RIGHT -> {
                return x < width - 2;
            }
            case BOTTOM -> {
                return y < height - 2;
            }
            default -> {
                return false;
            }
        }
    }

    public Graph(double[][] weights, double threshold) {
        createGraph(weights, threshold);
    }

    private void initVertices(int size) {
        vertices = new VertexNode[size];
        for (int i = 0; i < size; i++) {
            vertices[i] = new VertexNode();
            vertices[i].vertex = i;

        }
    }

    private void addEdge(int sourceIndex, int targetIndex, double weight) {
        ArcNode newArc = new ArcNode();
        newArc.targetVertex = targetIndex;
        newArc.weight = weight;
        if (vertices[sourceIndex].firstArc == null) {
            vertices[sourceIndex].firstArc = newArc;
        } else {
            ArcNode tmp = vertices[sourceIndex].firstArc;
            while (tmp.nextArc != null) {
                tmp = tmp.nextArc;
            }
            tmp.nextArc = newArc;
        }
    }

    private void createGraph(double[][] weights, double threshold) {
        int width = weights[0].length;
        int height = weights.length;
        cols = (width + 1) / 2;
        rows = (height + 1) / 2;
        this.numOfVertices = cols * rows;
        initVertices(numOfVertices);
        for (int y = 0; y < height; y = y + 2) {
            for (int x = 0; x < width; x = x + 2) {
                int index_vertex = y / 2 * cols + x / 2;
                if (isValidVisit(width, height, y, x, Direction.UP) && weights[y - 1][x] > threshold) {
                    addEdge(index_vertex, (y - 2) / 2 * cols + x / 2, weights[y - 1][x]);
                }
                if (isValidVisit(width, height, y, x, Direction.LEFT) && weights[y][x - 1] > threshold) {
                    addEdge(index_vertex, y / 2 * cols + (x - 2) / 2, weights[y][x - 1]);
                }
                if (isValidVisit(width, height, y, x, Direction.RIGHT) && weights[y][x + 1] > threshold) {
                    addEdge(index_vertex, y / 2 * cols + (x + 2) / 2, weights[y][x + 1]);
                }
                if (isValidVisit(width, height, y, x, Direction.BOTTOM) && weights[y + 1][x] > threshold) {
                    addEdge(index_vertex, (y + 2) / 2 * cols + x / 2, weights[y + 1][x]);
                }
            }
        }
    }

    public void display() {
        for (int i = 0; i < vertices.length; i++) {
            System.out.print("顶点" + vertices[i].vertex + "的邻接点有：");
            ArcNode tmp = vertices[i].firstArc;
            while (tmp != null) {
                System.out.print(tmp.targetVertex + " ");
                tmp = tmp.nextArc;
            }
            System.out.println();
        }
    }

}

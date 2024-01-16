import util.Graph;
import util.MST;
import util.Weights;
import util.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {
    public static void displayInt(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.printf("%8d ", anInt);
            }
            System.out.println();
        }
    }
    public static void displayDouble(double[][] array) {
        for (double[] ints : array) {
            for (double anInt : ints) {
                System.out.printf("%8.2f ", anInt);
            }
            System.out.println();
        }
    }

    public static Graph test() {
        Path originImagePath = Path.of("E:\\Demo\\5.png");
        BufferedImage originImage = Image.readImage(originImagePath);
        Image.displayImage(originImage);
        int[][] gray2dArray = Image.convertImageToGray2dArray(originImage);
        displayInt(gray2dArray);
        BufferedImage grayImage = Image.convertGray2dArrayToImage(gray2dArray);
        Image.displayImage(grayImage);
        Weights weights = new Weights(gray2dArray, 0.5);
        Graph graph = new Graph(weights.getVertexWithEdge2dArray(), weights.getThreshold());
        return graph;
    }

//    public static void test2(Graph graph) {
//        MST mst = new MST(graph);
//        //得到所有的最小生成树,用allMSTs表示
//        int[][] classification = mst.getClassification();
//        //新建一个BufferedImage,width和height为classification的宽和高
//        BufferedImage image = new BufferedImage(classification[0].length, classification.length, BufferedImage.TYPE_INT_RGB);
//        //遍历classification,根据里面的label设置image像素的彩色颜色,相同label的像素颜色相同
//        //label值可以用于生成随机颜色
//
//        // Create a mapping between label and color
//        Map<Integer, Color> labelColorMap = new HashMap<>();
//
//        // Assign predefined colors to each unique label
//        for (int i = 0; i < classification.length; i++) {
//            for (int j = 0; j < classification[i].length; j++) {
//                int label = classification[i][j];
//                if (!labelColorMap.containsKey(label)) {
//                    // Use modulo to cycle through predefined colors
//                    Color color = predefinedColors[label % predefinedColors.length];
//                    labelColorMap.put(label, color);
//                }
//
//                // Set the color of the corresponding pixel in the image
//                image.setRGB(j, i, labelColorMap.get(label).getRGB());
//            }
//        }
//
//        // Display or save the resulting image as needed
//        Image.displayImage(image);
//
//    }

    private static final Color[] predefinedColors = {
            Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
            Color.MAGENTA, Color.CYAN, Color.ORANGE, Color.PINK,
            Color.LIGHT_GRAY, Color.DARK_GRAY
    };

    public static void main(String[] args) {
//        // 原图
//        Path originImagePath = Path.of("E:\\Demo\\4.png");
//        BufferedImage originImage = Image.readImage(originImagePath);
//
//        // 灰度图
//        int[][] gray2dArray = Image.convertImageToGray2dArray(originImage);
//
//        // 以50%为阈值的邻域分割
//        Weights weights = new Weights(gray2dArray, 0.5);
//        double[][] neighborClassify = weights.getVertexWithEdge2dArray();
//
//        // 根据邻域分割的结果构建图,所构建的图已经将边权重小于阈值的边删除,达成了预先分割的效果
//        Graph graph = new Graph(neighborClassify, weights.getThreshold());
//        graph.display();
        test();
//        test2(test());


//        MST mst = new MST(graph);
//        mst.displayAllMSTs();
//        int[][] classification = mst.classification(gray2dArray.length, gray2dArray[0].length);
//        for (int i = 0; i < classification.length; i++) {
//            for (int j = 0; j < classification[i].length; j++) {
//                System.out.printf("%8d ", classification[i][j]);
//            }
//            System.out.println();
//        }
    }
}

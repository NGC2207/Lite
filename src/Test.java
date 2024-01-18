import util.Graph;
import util.MST;
import util.Weights;
import util.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
        Path originImagePath = Path.of("E:\\Demo\\9.jpeg");
        BufferedImage originImage = Image.readImage(originImagePath);
        Image.displayImage(originImage);
        int[][] gray2dArray = Image.convertImageToGray2dArray(originImage);
//        displayInt(gray2dArray);
        BufferedImage grayImage = Image.convertGray2dArrayToImage(gray2dArray);
        Image.displayImage(grayImage);
        Weights weights = new Weights(gray2dArray, 10);
        Graph graph = new Graph(weights.getVertexWithEdge2dArray(), weights.getThreshold());
        return graph;
    }

    public static void test3(Graph graph) {
        MST mst = new MST(graph);
        int[][] classification = mst.getClassification();
        //建立一张全白的BufferedImage
        BufferedImage image = new BufferedImage(classification[0].length, classification.length, BufferedImage.TYPE_INT_RGB);
        // 获取 Graphics 对象，用于绘制
        Graphics graphics = image.getGraphics();
        // 将整个图片填充为白色
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());

        // 释放资源
        graphics.dispose();
        for (int i = 0; i < classification.length; i++) {
            for (int j = 0; j < classification[i].length; j++) {
                int label = classification[i][j];
                if (i - 1 >= 0) {
                    if (classification[i - 1][j] != label) {
                        image.setRGB(j, i, Color.BLACK.getRGB());
                    }
                }
                if (i + 1 < classification.length) {
                    if (classification[i + 1][j] != label) {
                        image.setRGB(j, i, Color.BLACK.getRGB());
                    }
                }
                if (j - 1 >= 0) {
                    if (classification[i][j - 1] != label) {
                        image.setRGB(j, i, Color.BLACK.getRGB());
                    }
                }
                if (j + 1 < classification[i].length) {
                    if (classification[i][j + 1] != label) {
                        image.setRGB(j, i, Color.BLACK.getRGB());
                    }
                }
            }
        }
        Image.displayImage(image);
        //保存BufferedImage
        try{
            ImageIO.write(image, "png", Path.of("E:\\Demo\\9.jpeg").toFile());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void test2(Graph graph) {
        MST mst = new MST(graph);
        //得到所有的最小生成树,用allMSTs表示
        int[][] classification = mst.getClassification();
        //新建一个BufferedImage,width和height为classification的宽和高
        BufferedImage image = new BufferedImage(classification[0].length, classification.length, BufferedImage.TYPE_INT_RGB);
        //遍历classification,根据里面的label设置image像素的彩色颜色,相同label的像素颜色相同
        //label值可以用于生成随机颜色

        // Create a mapping between label and color
        Map<Integer, Color> labelColorMap = new HashMap<>();

        // Assign predefined colors to each unique label
        for (int i = 0; i < classification.length; i++) {
            for (int j = 0; j < classification[i].length; j++) {
                int label = classification[i][j];
                if (!labelColorMap.containsKey(label)) {
                    // Use modulo to cycle through predefined colors
                    Color color = predefinedColors[label % predefinedColors.length];
                    labelColorMap.put(label, color);
                }

                // Set the color of the corresponding pixel in the image
                image.setRGB(j, i, labelColorMap.get(label).getRGB());
            }
        }

        // Display or save the resulting image as needed
        Image.displayImage(image);

    }

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
//        test();

//        test2(test());
        test3(test());

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

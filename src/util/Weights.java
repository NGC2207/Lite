package util;

public class Weights {
    private int[][] gray2dArray;
    private double[][] vertexWithEdge2dArray;
    private double mean;
    private double variance;
    private double MAX_GRAY_VALUE;
    private double MIN_GRAY_VALUE;
    private double threshold;

    public double getThreshold() {
        return threshold;
    }

    public double[][] getVertexWithEdge2dArray() {
        return vertexWithEdge2dArray;
    }

    private double getMean() {
        double sum = 0;
        double maxGrayValue = Integer.MIN_VALUE;
        double minGrayValue = Integer.MAX_VALUE;
        for (int y = 0; y < gray2dArray.length; y++) {
            for (int x = 0; x < gray2dArray[0].length; x++) {
                int grayValue = gray2dArray[y][x];
                sum += gray2dArray[y][x];
                if (grayValue > maxGrayValue) {
                    maxGrayValue = grayValue;
                } else if (grayValue < minGrayValue) {
                    minGrayValue = grayValue;
                }
            }
        }
        MAX_GRAY_VALUE = maxGrayValue;
        MIN_GRAY_VALUE = minGrayValue;
        return sum / (gray2dArray.length * gray2dArray[0].length);
    }

    private double getVariance() {

        double sum = 0;
        for (int y = 0; y < gray2dArray.length; y++) {
            for (int x = 0; x < gray2dArray[0].length; x++) {
                sum += Math.pow(gray2dArray[y][x] - mean, 2);
            }
        }
        return sum / (gray2dArray.length * gray2dArray[0].length);
    }


    public Weights(int[][] gray2dArray, double percentage) {
        this.gray2dArray = gray2dArray;
        this.mean = getMean();
        this.variance = getVariance();
        this.vertexWithEdge2dArray = convertGray2dArrayToVertexWithEdge2dArray();
        double min = Math.exp(-Math.pow(MAX_GRAY_VALUE - MIN_GRAY_VALUE, 2) / variance);
        double range = 1 - min;
        this.threshold = min + range * percentage;
//        neighborClassify(threshold);
    }

    private double getWeightLeftAndRight(double[][] vertexWithEdge2dArray, int y_edge, int x_edge) {
        double grayValue_leftVertex = vertexWithEdge2dArray[y_edge][x_edge - 1];
        double grayValue_rightVertex = vertexWithEdge2dArray[y_edge][x_edge + 1];
        return Math.exp(-Math.pow(grayValue_leftVertex - grayValue_rightVertex, 2) / variance);
    }

    private double getWeightUpAndDown(double[][] vertexWithEdge2dArray, int y_edge, int x_edge) {
        double grayValue_upVertex = vertexWithEdge2dArray[y_edge - 1][x_edge];
        double grayValue_downVertex = vertexWithEdge2dArray[y_edge + 1][x_edge];
        return Math.exp(-Math.pow(grayValue_upVertex - grayValue_downVertex, 2) / variance);
    }

    private double[][] convertGray2dArrayToVertexWithEdge2dArray() {
        int height = gray2dArray.length;
        int width = gray2dArray[0].length;
        double[][] vertexWithEdge2dArray = new double[2 * height - 1][2 * width - 1];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                vertexWithEdge2dArray[2 * y][2 * x] = gray2dArray[y][x];
            }
        }
        for (int y = 0; y < vertexWithEdge2dArray.length; y++) {
            for (int x = 0; x < vertexWithEdge2dArray[0].length; x++) {
                if (y % 2 == 0 && x % 2 == 1) { //为横边时
                    vertexWithEdge2dArray[y][x] = getWeightLeftAndRight(vertexWithEdge2dArray, y, x);
                } else if (y % 2 == 1 && x % 2 == 0) { //为竖边时
                    vertexWithEdge2dArray[y][x] = getWeightUpAndDown(vertexWithEdge2dArray, y, x);
                }
            }
        }

        return vertexWithEdge2dArray;
    }

//    public void neighborClassify(double threshold) {
//        for (int y = 0; y < vertexWithEdge2dArray.length; y++) {
//            for (int x = 0; x < vertexWithEdge2dArray[0].length; x++) {
//                if (y % 2 == 0 && x % 2 == 1) {
//                    if (vertexWithEdge2dArray[y][x] < threshold) {
//                        vertexWithEdge2dArray[y][x] = 0;
//                    } else {
//                        vertexWithEdge2dArray[y][x] = 1;
//                    }
//                } else if (y % 2 == 1 && x % 2 == 0) {
//                    if (vertexWithEdge2dArray[y][x] < threshold) {
//                        vertexWithEdge2dArray[y][x] = 0;
//                    } else {
//                        vertexWithEdge2dArray[y][x] = 1;
//                    }
//                }
//            }
//        }
//    }

}

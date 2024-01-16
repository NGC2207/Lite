package util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class Image {
    public static BufferedImage readImage(Path imagePath) {
        try {
            return ImageIO.read(imagePath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage convertGray2dArrayToImage(int[][] array) {
        int height = array.length;
        int width = array[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int gray = array[y][x];
                int rgb = (gray << 16) | (gray << 8) | gray;
                image.setRGB(x, y, rgb);
            }
        }
        return image;
    }

    public static int[][] convertImageToGray2dArray(BufferedImage originalImage) {
        int height = originalImage.getHeight();
        int width = originalImage.getWidth();
        int[][] gray2dArray = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);
                int gray = (int) (0.2989 * ((rgb & 0xff0000) >> 16) + 0.5870 * ((rgb & 0xff00) >> 8) + 0.114 * (rgb & 0xff));
                gray2dArray[y][x] = gray;
            }
        }
        return gray2dArray;
    }

    public static void displayImage(BufferedImage image) {
        if (image == null) {
            throw new NullPointerException("图像不能为空!");
        }
        JFrame frame = createFrame(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static JFrame createFrame(BufferedImage image) {
        JFrame frame = new JFrame("Image Display");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        double imageWidth = image.getWidth();
        double imageHeight = image.getHeight();
        double scaleWidth = screenWidth / imageWidth;
        double scaleHeight = screenHeight / imageHeight;
        double scale = Math.min(scaleWidth, scaleHeight);
        int scaledWidth = (int) (imageWidth * scale);
        int scaledHeight = (int) (imageHeight * scale);
        java.awt.Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        frame.getContentPane().add(new JLabel(icon));
        return frame;
    }
}

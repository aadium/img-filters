import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ImgEnc {
    public static void main(String[] args) throws IOException {
        try (Scanner sc = new Scanner(System.in)) {
            String inputImagePath = "input.jpg";

            BufferedImage originalImage = ImageIO.read(new File(inputImagePath));

            int length = originalImage.getWidth();
            int breadth = originalImage.getHeight();

            Color[][] colorGrid = new Color[length][breadth];

            System.out.println("Enter the percentage of red: ");
            float redPercent = Float.parseFloat(sc.nextLine()) / 100;
            System.out.println("Enter the percentage of green: ");
            float greenPercent = Float.parseFloat(sc.nextLine()) / 100;
            System.out.println("Enter the percentage of blue: ");
            float bluePercent = Float.parseFloat(sc.nextLine()) / 100;

            for (int i = 0; i < length; i++) {
                for (int j = 0; j < breadth; j++) {
                    int rgb = originalImage.getRGB(i, j);

                    int redValue = (rgb >> 16) & 0xFF;
                    int greenValue = (rgb >> 8) & 0xFF;
                    int blueValue = rgb & 0xFF;

                    // Scale the RGB values back to the valid range [0, 255]
                    redValue = (int) (redValue * redPercent);
                    greenValue = (int) (greenValue * greenPercent);
                    blueValue = (int) (blueValue * bluePercent);

                    // Ensure values are within the valid range
                    redValue = Math.min(255, Math.max(0, redValue));
                    greenValue = Math.min(255, Math.max(0, greenValue));
                    blueValue = Math.min(255, Math.max(0, blueValue));

                    Color color = new Color(redValue, greenValue, blueValue);
                    colorGrid[i][j] = color;
                }
            }

            BufferedImage recreatedImage = new BufferedImage(length, breadth, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < length; i++) {
                for (int j = 0; j < breadth; j++) {
                    Color color = colorGrid[i][j];
                    recreatedImage.setRGB(i, j, color.getRGB());
                }
            }

            File output = new File("output.png");
            ImageIO.write(recreatedImage, "png", output);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

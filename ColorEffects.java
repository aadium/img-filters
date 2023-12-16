import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorEffects {

    public static void applyGrayscaleEffect(BufferedImage originalImage, BufferedImage recreatedImage) {
        int length = originalImage.getWidth();
        int breadth = originalImage.getHeight();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                int rgb = originalImage.getRGB(i, j);

                int grayValue = (int) (0.21 * ((rgb >> 16) & 0xFF) + 0.72 * ((rgb >> 8) & 0xFF) + 0.07 * (rgb & 0xFF));

                Color color = new Color(grayValue, grayValue, grayValue);
                recreatedImage.setRGB(i, j, color.getRGB());
            }
        }
    }

    public static void applySepiaEffect(BufferedImage originalImage, BufferedImage recreatedImage) {
        int length = originalImage.getWidth();
        int breadth = originalImage.getHeight();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                int rgb = originalImage.getRGB(i, j);

                int redValue = (int) (0.393 * ((rgb >> 16) & 0xFF) + 0.769 * ((rgb >> 8) & 0xFF) + 0.189 * (rgb & 0xFF));
                int greenValue = (int) (0.349 * ((rgb >> 16) & 0xFF) + 0.686 * ((rgb >> 8) & 0xFF) + 0.168 * (rgb & 0xFF));
                int blueValue = (int) (0.272 * ((rgb >> 16) & 0xFF) + 0.534 * ((rgb >> 8) & 0xFF) + 0.131 * (rgb & 0xFF));

                // Ensure values are within the valid range
                redValue = Math.min(255, Math.max(0, redValue));
                greenValue = Math.min(255, Math.max(0, greenValue));
                blueValue = Math.min(255, Math.max(0, blueValue));

                Color color = new Color(redValue, greenValue, blueValue);
                recreatedImage.setRGB(i, j, color.getRGB());
            }
        }
    }

    public static void applyInvertedEffect(BufferedImage originalImage, BufferedImage recreatedImage) {
        int length = originalImage.getWidth();
        int breadth = originalImage.getHeight();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < breadth; j++) {
                int rgb = originalImage.getRGB(i, j);

                int redValue = 255 - ((rgb >> 16) & 0xFF);
                int greenValue = 255 - ((rgb >> 8) & 0xFF);
                int blueValue = 255 - (rgb & 0xFF);

                Color color = new Color(redValue, greenValue, blueValue);
                recreatedImage.setRGB(i, j, color.getRGB());
            }
        }
    }
}

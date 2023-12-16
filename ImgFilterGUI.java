import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ImgFilterGUI {
    private static BufferedImage originalImage;
    private static BufferedImage recreatedImage;
    private static JComboBox<String> colorEffectComboBox;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Image Encoder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        JButton fileChooserButton = new JButton("Choose Image");
        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseImage();
            }
        });
        panel.add(fileChooserButton);

        colorEffectComboBox = new JComboBox<>(new String[]{"Grayscale", "Sepia", "Inverted"});
        panel.add(new JLabel("Select Color Effect:"));
        panel.add(colorEffectComboBox);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyColorEffect();
            }
        });
        panel.add(applyButton);

        frame.setSize(400, 150);
        frame.setVisible(true);
    }

    private static void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                originalImage = ImageIO.read(selectedFile);
                JOptionPane.showMessageDialog(null, "Image loaded successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error loading image.");
            }
        }
    }

    private static void applyColorEffect() {
        if (originalImage == null) {
            JOptionPane.showMessageDialog(null, "Please choose an image first.");
            return;
        }
    
        String selectedEffect = (String) colorEffectComboBox.getSelectedItem();
    
        recreatedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());

        switch (selectedEffect) {
            case ("Grayscale"):
                ColorEffects.applyGrayscaleEffect(originalImage,recreatedImage);
                break;
            case ("Sepia"):
                ColorEffects.applySepiaEffect(originalImage, recreatedImage);
                break;
            case ("Inverted"):
                ColorEffects.applyInvertedEffect(originalImage, recreatedImage);
                break;
        
            default:
                break;
        }
    
        showResultImage();
    }

    private static void showResultImage() {
        if (recreatedImage != null) {
            JFrame resultFrame = new JFrame("Result");
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            resultFrame.getContentPane().add(new JLabel(new ImageIcon(recreatedImage)));
            resultFrame.pack();
            resultFrame.setVisible(true);
        }
    }
}

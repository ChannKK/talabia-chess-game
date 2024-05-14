//  to invert image needed

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageInverter {
    private String filenaming;

    // Tai Qi Tong
    // to invert image of Point
    public String invertImage(String originalPic) {
        try {
            ImageIcon originalIcon = new ImageIcon(originalPic);
            Image originalImage = originalIcon.getImage();

            BufferedImage bufferedImage = new BufferedImage(
                    originalImage.getWidth(null),
                    originalImage.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);

            // inverted image
            BufferedImage invertedImage = new BufferedImage(
                    originalImage.getWidth(null),
                    originalImage.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);

            // graphics context from original image
            bufferedImage.getGraphics().drawImage(originalImage, 0, 0, null);

            // Invert image
            for (int y = 0; y < originalImage.getHeight(null); y++) {
                for (int x = 0; x < originalImage.getWidth(null); x++) {
                    invertedImage.setRGB(x, originalImage.getHeight(null) - y - 1, bufferedImage.getRGB(x, y));
                }
            }

            if (originalPic.substring(0, 9).equals("inverted_")) {
                filenaming = originalPic.substring(9);
            } else {
                filenaming = "inverted_" + originalPic;
            }

            File invertedFile = new File(filenaming);
            ImageIO.write(invertedImage, "png", invertedFile);
            return invertedFile.getPath();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

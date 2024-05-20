package in.precisiontestautomation.scriptlessautomation.core.utils;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * <p>ImageUtils class.</p>
 *
 * @author PTA-dev
 * @version $Id: $Id
 * @since 2024-05-02
 */
public class ImageUtils {


    /**
     * <p>base64ToImage.</p>
     *
     * @param base64String a {@link String} object
     * @return a {@link String} object
     */
    public static String base64ToImage(String base64String) {

        // Convert base64 string to byte array
        byte[] byteArray = Base64.getDecoder().decode(base64String);

        // Create BufferedImage object from byte array
        BufferedImage image = null;
        try {
            image = ImageIO.read(new ByteArrayInputStream(byteArray));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String screenShotPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "screenshots" + File.separator;

        File file = new File(screenShotPath);

        if (!file.isDirectory()) {
            file.mkdirs();
        }

        // Write BufferedImage object to file
        File output = new File(screenShotPath + "Image" + System.currentTimeMillis() + ".png");
        try {
            ImageIO.write(image, "png", output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return output.getAbsolutePath();
    }

    /**
     * <p>convertImageToBase64Url.</p>
     *
     * @param imagePath a {@link String} object
     * @return a {@link String} object
     */
    public static String convertImageToBase64Url(String imagePath) {
        String base64Url = "";
        try {
            File file = new File(imagePath);
            FileInputStream imageInFile = new FileInputStream(file);
            byte[] imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Url = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData);
            imageInFile.close();
        } catch (IOException e) {
            System.out.println("Error while converting image to base64 URL: " + e.getMessage());
        }
        return base64Url;
    }
}

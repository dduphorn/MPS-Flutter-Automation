package AutomationCode;

import org.testng.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageValidator {
    public static double compareImages(File fileA, File fileB) {
        BufferedImage imgA = null;
        BufferedImage imgB = null;

        try {
            imgA = ImageIO.read(fileA);
            imgB = ImageIO.read(fileB);
        } catch (IOException e) {
            System.out.println(e);
        }

        assert imgA != null;
        int width1 = imgA.getWidth();
        int height1 = imgA.getHeight();

        assert imgB != null;
        if (width1 != imgB.getWidth() || height1 != imgB.getHeight()) {
            throw new IllegalArgumentException("Error: Images dimensions mismatch");
        }

        long difference = 0;

        for (int y = 0; y < height1; y++) {
            for (int x = 0; x < width1; x++) {
                int rgbA = imgA.getRGB(x, y);
                int rgbB = imgB.getRGB(x, y);

                difference += Math.abs(((rgbA >> 16) & 0xff) - ((rgbB >> 16) & 0xff));
                difference += Math.abs(((rgbA >> 8) & 0xff) - ((rgbB >> 8) & 0xff));
                difference += Math.abs((rgbA & 0xff) - (rgbB & 0xff));
            }
        }

        double total_pixels = width1 * height1 * 3;
        double percentage = (double) difference / total_pixels / 255 * 100;

        System.out.println("Image Difference Percentage ---> " + percentage);

        return percentage;
    }

    public static boolean areImagesIdentical(File fileA, File fileB) {
        double percentDifference = compareImages(fileA, fileB);

        if (percentDifference > 0) {
            System.out.println("Test failed: Images are not Identical.");
            return false;
        } else {
            System.out.println("Test passed: Images are Identical.");
            return true;
        }
    }

    public static void imageCompareResult(File fileA, File fileB) {
        boolean areImagesEqual = ImageValidator.areImagesIdentical(fileA, fileB);

        try {
            Assert.assertTrue(areImagesEqual);
        } catch (AssertionError e) {
            System.out.println("The test failed because the difference in  percentage is greater than 0.");
            throw e; // rethrow the exception to fail the test
        }
    }
}
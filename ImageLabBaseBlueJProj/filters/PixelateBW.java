package filters;
import imagelab.*;
import java.util.Scanner;


/**
 * Shaw Kagawa
 * Period 3
 * 5/15/17
 * Pixelate filter: Pixelates the image with a radius of x.
 */
public class PixelateBW implements ImageFilter {
    private Scanner input;
    
    // Attribute to store the modified image
    ImgProvider filteredImage;
    
    public void filter (ImgProvider ip) {
        input = new Scanner(System.in);
        int numPixels = 5;
        
        // Grab the pixel information and put it into a 2D array
        short[][] im = ip.getBWImage();

        // Make variables for image height and width
        int height = im.length;
        int width  = im[0].length;

        // Create a new array to store the modified image
        short[][] newImage = new short[height][width];

        // Loop through the original image and store the modified
        // version in the newImage array
        for (int row = (int)Math.floor(numPixels / 2.0); row < height; row += numPixels) {
            for (int col = (int)Math.floor(numPixels / 2.0); col < width; col += numPixels) {
                int sum = 0;
                int numOk = 0;
                for (int i = -(int)Math.floor(numPixels / 2.0); i < Math.ceil(numPixels / 2.0); i++) {
                    for (int j = -(int)Math.floor(numPixels / 2.0); j < Math.ceil(numPixels / 2.0); j++) {
                        if (row + i >= 0 && row + i < height && col + j >= 0 && col + j < width) {
                            sum += im[row + i][col + j];
                            numOk++;
                        }
                    }
                }
                short average = (short)(numOk != 0 ? (sum / numOk) : 0);
                for (int i = -(int)Math.floor(numPixels / 2.0); i < Math.ceil(numPixels / 2.0); i++) {
                    for (int j = -(int)Math.floor(numPixels / 2.0); j < Math.ceil(numPixels / 2.0); j++) {
                        if (row + i >= 0 && row + i < height && col + j >= 0 && col + j < width) {
                            newImage[row + i][col + j] = average;
                        }
                    }
                }
            }
        }

        // Create a new ImgProvider and set the filtered image to our new image
        filteredImage = new ImgProvider();
        filteredImage.setBWImage(newImage);

        // Show the new image in a new window with title "Flipped Horizontally"
        filteredImage.showPix(numPixels + "-Pixelated");
    }

    public ImgProvider getImgProvider() {
        return filteredImage;
    }

    // This is what users see in the Filter menu
    public String getMenuLabel() {
        return "Pixelate (BW)";
    }

}
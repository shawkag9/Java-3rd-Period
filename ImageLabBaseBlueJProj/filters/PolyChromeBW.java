package filters;
import imagelab.*;
import java.util.Scanner;


/**
 * Shaw Kagawa
 * Period 3
 * 5/15/17
 * Polychrome filter: Alters the image so that there are only x amount of colors in BW.
 */
public class PolyChromeBW implements ImageFilter {
    private Scanner input;
    
    // Attribute to store the modified image
    ImgProvider filteredImage;
    
    public void filter (ImgProvider ip) {
        input = new Scanner(System.in);
        // System.out.println("Enter a number between 0 and 255");
        int numColors = 5;
        
        // Grab the pixel information and put it into a 2D array
        short[][] im = ip.getBWImage();

        // Make variables for image height and width
        int height = im.length;
        int width  = im[0].length;

        // Create a new array to store the modified image
        short[][] newImage = new short[height][width];

        // Loop through the original image and store the modified
        // version in the newImage array
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                newImage[row][col] = (short)(Math.round(im[row][col] / (255.0 / (double)(numColors - 1))) * (255.0 / (double)(numColors - 1)));
            }
        }

        // Create a new ImgProvider and set the filtered image to our new image
        filteredImage = new ImgProvider();
        filteredImage.setBWImage(newImage);

        // Show the new image in a new window with title "Flipped Horizontally"
        filteredImage.showPix(numColors + "-color");
    }

    public ImgProvider getImgProvider() {
        return filteredImage;
    }

    // This is what users see in the Filter menu
    public String getMenuLabel() {
        return "Polychrome (BW)";
    }

}
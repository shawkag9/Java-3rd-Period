package filters;
import imagelab.*;
import java.util.Scanner;

/**
 * Shaw Kagawa
 * Period 3
 * 5/15/17
 * Rotate filter: Rotates the image x degrees to the RIGHT.
 */

public class RotateBW implements ImageFilter {
    private Scanner input;
    
    // Attribute to store the modified image
    ImgProvider filteredImage;
    
    public void filter (ImgProvider ip) {
        input = new Scanner(System.in);
        int numDeg = input.nextInt();
        double numRad = -(numDeg / 180.0) * Math.PI;
        
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
                int[] toCenter = new int[]{
                    width / 2 - col,
                    height / 2 - row
                };
                double dist = Math.sqrt(toCenter[0] * toCenter[0] + toCenter[1] * toCenter[1]);
                double angle = Math.atan2(toCenter[1], toCenter[0]);
                int newX = (int)(width / 2.0 + dist * Math.cos(-angle - numRad));
                int newY = (int)(height / 2.0 + dist * Math.sin(-angle - numRad));
                System.out.println(newX + ", " + newY);
                if (newY >= 0 && newY < height && newX >= 0 && newX < width) {
                    newImage[newY][newX] = im[row][col];
                }
            }
        }

        // Create a new ImgProvider and set the filtered image to our new image
        filteredImage = new ImgProvider();
        filteredImage.setBWImage(newImage);

        // Show the new image in a new window with title "Flipped Horizontally"
        filteredImage.showPix("Rotated " + numDeg + " degrees");
    }

    public ImgProvider getImgProvider() {
        return filteredImage;
    }

    // This is what users see in the Filter menu
    public String getMenuLabel() {
        return "Rotate (BW)";
    }

}
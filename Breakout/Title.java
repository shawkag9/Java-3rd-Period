import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Title here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Title extends Actor
{
    private static final int width = 600;
    private static final int height = 400;
    /**
     * Act - do whatever the Title wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }
    public Title() {
        GreenfootImage image = new GreenfootImage(width * 3 / 4, height * 3 / 4);

        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(0, 0, width * 3 / 4, height * 3 / 4);
        image.setColor(new Color(0, 0, 0, 128));
        image.fillRect(5, 5, width * 3 / 4 -10, height * 3 / 4 -10);
        Font font = image.getFont();
        font = font.deriveFont(12);
        image.setFont(font);
        image.setColor(Color.WHITE);
        image.drawString("Welcome!", 60, 100);
        image.drawString("Press Space to start.", 60, 120);
        setImage(image);
    }
}

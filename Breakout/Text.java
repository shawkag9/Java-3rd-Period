import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
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
    public Text(String text, int size) {
        GreenfootImage image = new GreenfootImage(text, size, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(image);
    }
}

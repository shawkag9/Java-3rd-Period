import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    private String text;
    private int size;
    private Color foreground;
    private Color background;
    
    public Text(String text, int size) {
        this.text = text;
        this.size = size;
        this.foreground = Color.WHITE;
        this.background = new Color(0, 0, 0, 0);
        updateImage();
    }
    
    public void updateImage() {
        setImage(new GreenfootImage(this.text, this.size, this.foreground, this.background));
    }
    
    public void setText(String text) {
        this.text = text;
        updateImage();
    }
    public String getText() {
        return text;
    }
    
    public int getSize() {
        return size;
    }
    
    public Color getForeground() {
        return foreground;
    }
    
    public Color getBackground() {
        return background;
    }
}

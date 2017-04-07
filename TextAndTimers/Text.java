import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Text extends Actor {
    private String text;
    private int size;
    private Timer timer;
    
    public Text() {
        text = "0";
        size = 40;
        setImage(new GreenfootImage(text, size, Color.BLACK, new Color(0, 0, 0, 0)));
    }
}

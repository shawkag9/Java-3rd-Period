import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Timer extends Text
{
    private static boolean running;
    private int counter;
    private int delay;
    private int time;
    private int size;
    
    public Timer(int size, int delay) {
        // super("0", 40);
        running = false;
        counter = 0;
        this.delay = delay;
        this.size = size;
        time = 0;
    }
    
    public void act() {
        counter++;
        if (counter > delay) {
            setString(String.valueOf(time));
            counter = 0;
        }
    }
    
    public void setString(String t) {
        GreenfootImage img = new GreenfootImage(t, size, Color.BLACK, new Color(0, 0, 0, 0));
        setImage(img);
    }
}

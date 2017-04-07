import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Timer extends Text {
    private int time;
    private int counter;
    private int delay;
    private int increment;
    private int size;
    
    public Timer(int size, int delay, int increment) {
        time = 0;
        this.size = size;
        this.delay = delay;
        this.increment = increment;
    }
    
    public void act() {
        counter++;
        if (counter > delay) {
            time += increment;
            setImage(new GreenfootImage(String.valueOf(time), size, Color.BLACK, new Color(0, 0, 0, 0)));
            counter = 0;
        }
    }
    
    public int getTime() {
        return time;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Timer extends Text {
    private int time;
    private int counter;
    private int delay;
    private int increment;
    private int size;
    public static boolean paused;
    
    /**
     * Creates a new timer on screen with a specified int size, int delay, and int increment.
     */
    public Timer(int size, int delay, int increment) {
        super(String.valueOf(0), size);
        paused = false;
        time = 0;
        this.size = size;
        this.delay = delay;
        this.increment = increment;
    }
    
    public void act() {
        if (!paused) {
            counter++;
            if (counter > delay) {
                time += increment;
                setText(String.valueOf(time));
                counter = 0;
            }
        }
    }
    
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    
    public static void setPause(boolean p) {
        paused = p;
    }
    public static boolean getPause() {
        return paused;
    }
}

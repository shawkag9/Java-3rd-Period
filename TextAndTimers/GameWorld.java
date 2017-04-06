import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameWorld extends World {
    private Timer timer;
    private int time;
    
    public GameWorld() {
        super(600, 400, 1); 
        time = 0;
        
        timer = new Timer(40, 10);
        addObject(timer, getWidth()/2, getHeight()/2);
    }
}

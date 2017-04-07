import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameWorld extends World {
    private int time;
    
    public GameWorld() {
        super(600, 400, 1);
        addObject(new Timer(40, 20, 1), getWidth()/2, getHeight()/2);
        addObject(new Timer(30, 40, 3), getWidth()/4, getHeight()/4);
        addObject(new Timer(60, 10, 5), getWidth()/3, getHeight()/3);
    }
}

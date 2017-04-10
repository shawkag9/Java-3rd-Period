import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class GameWorld extends World {
    private int time;
    List<Timer> timers;
    
    public GameWorld() {
        super(600, 400, 1);
        timers = new ArrayList<Timer>(
            Arrays.asList(
                new Timer(40, 15, 2),
                new Timer(50, 20, 1),
                new Timer(70, 5, 3)
            )
        );
        for (Timer timer : timers) {
            addObject(timer, 
                (int)(Math.random() * getWidth() / 2 + getWidth() / 4),
                (int)(Math.random() * getHeight() / 2 + getHeight() / 4)
            );
        }
    }
    
    private boolean spaceUp;
    private boolean wasSpaceDown;
    
    public void act() {
        if (spaceUp) {
            Timer.setPause(!Timer.getPause());
            
            spaceUp = false;
            wasSpaceDown = false;
        } else {
            spaceUp = wasSpaceDown && !Greenfoot.isKeyDown("space");
            wasSpaceDown = Greenfoot.isKeyDown("space");
        }
    }
}

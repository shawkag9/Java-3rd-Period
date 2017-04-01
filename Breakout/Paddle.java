import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Paddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paddle extends Actor
{
    int dx = 3;
    private boolean gUp = false;
    private boolean wasKeyDown = false;
    private boolean godMode = false;
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (godMode) {
            if (!getWorld().getObjects(Ball.class).isEmpty()) {
                setLocation(getWorld().getObjects(Ball.class).get(0).getX(), getY());
            }
        } else {
            if (Greenfoot.isKeyDown("left") && (getX() > getImage().getWidth()/2)) {
                setLocation(getX() - dx, getY());
            } else if (Greenfoot.isKeyDown("right") && (getX() < getWorld().getWidth() - getImage().getWidth()/2)) {
                setLocation(getX() + dx, getY());
            }
        }
        if (gUp) {
            godMode = !godMode;
            
            gUp = false;
            wasKeyDown = false;
        } else {
            gUp = wasKeyDown && !Greenfoot.isKeyDown("g");
            wasKeyDown = Greenfoot.isKeyDown("g");
        }
    }    
}
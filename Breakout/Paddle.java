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
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (!getWorld().getObjects(Ball.class).isEmpty()) {
            setLocation(getWorld().getObjects(Ball.class).get(0).getX(), getY());
        }
        // if (Greenfoot.isKeyDown("left") && (getX() > getImage().getWidth()/2)) {
            // setLocation(getX() - dx, getY());
        // } else if (Greenfoot.isKeyDown("right") && (getX() < getWorld().getWidth() - getImage().getWidth()/2)) {
            // setLocation(getX() + dx, getY());
        // }
    }    
}
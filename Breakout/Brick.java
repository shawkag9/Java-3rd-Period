import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Brick here.
 * 
 * @author (Shaw Kagawa) 
 * @version (3/17/17)
 */
public class Brick extends Actor
{
    protected int level;
    protected String color;
    public int dx = 0;
    public int dy = 0;
    public static boolean acting = false;
    public Brick(int level, String color) {
        this.level = level;
        this.color = color;
        this.setImage("\\bricks\\" + this.color + "\\brick" + this.level + this.color + ".png");
    }
    
    public void onHit() {
        getWorld().removeObject(this);
    }
    
    public void act() {
        if (acting) {
            if (getX() >= getWorld().getWidth() - getImage().getWidth()/2 + 2) {
                setLocation(getImage().getWidth()/2, getY());
            }
            if (getX() <= getImage().getWidth()/2 - 2) {
                setLocation(getWorld().getWidth() - getImage().getWidth()/2, getY());
            }
            
            if (getY() <= getImage().getHeight()/2 - 2) {
                setLocation(getX(), getWorld().getHeight() - getImage().getHeight()/2);
            }
            if (getY() >= getWorld().getHeight() - getImage().getHeight()/2 + 2) {
                setLocation(getX(), getImage().getHeight()/2);
            }
            
            setLocation(getX() + dx, getY() + dy);
        }
    }
}

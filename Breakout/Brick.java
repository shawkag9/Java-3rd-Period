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
    private int dx = 0;
    private int dy = 0;
    public static boolean acting;
    public boolean wiggle;
    public Brick(int level, String color) {
        acting = false;
        wiggle = false;
        this.level = level;
        this.color = color;
        this.setImage("\\bricks\\" + this.color + "\\brick" + this.level + this.color + ".png");
    }
    // public Brick(int level, String color, boolean acting, boolean wiggle) {
        // this.acting = acting;
        // this.wiggle = wiggle;
        // this.level = level;
        // this.color = color;
        // this.setImage("\\bricks\\" + this.color + "\\brick" + this.level + this.color + ".png");
    // }
    
    public void setDx(int dx) {
        this.dx = dx;
    }
    public void setDy(int dy) {
        this.dy = dy;
    }
    
    public void onHit() {
        getWorld().removeObject(this);
    }
    
    public void act() {
        if (acting) {
            if (wiggle) {
                dx = (int)(Math.random() * 2 - 1);
                dy = (int)(Math.random() * 2 - 1);
            }
            
            
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

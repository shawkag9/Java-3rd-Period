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
    public Brick(int level, String color) {
        this.level = level;
        this.color = color;
        this.setImage("\\bricks\\" + this.color + "\\brick" + this.level + this.color + ".png");
    }
    
    public void onHit() {
        getWorld().removeObject(this);
        
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Brick here.
 * 
 * @author (Shaw Kagawa) 
 * @version (3/17/17)
 */
public class Brick extends Actor
{
    public Brick() {
        this.level = 0;
        this.color = "";
    }
    
    private int level;
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int l) {
        this.level = l;
    }
    
    private String color;
    public String getColor() {
        return this.color;
    }
    public void setColor(String c) {
        this.color = c;
    }
}

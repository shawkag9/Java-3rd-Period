import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StrongBrick here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StrongBrick extends Brick 
{
    public StrongBrick(String color) {
        super(3, color);
    }
    
    @Override
    public void onHit() {
        if (this.level == 1) {
            getWorld().removeObject(this);
        } else {
            this.level--;
            this.setImage("\\bricks\\" + this.color + "\\brick" + this.level + this.color + ".png");
        }
    }
}

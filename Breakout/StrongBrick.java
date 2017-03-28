import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StrongBrick here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StrongBrick extends Brick 
{
    public StrongBrick(int level, String color) {
        super(level, color);
    }
    
    @Override
    public void onHit() {
        if (this.level == 1) {
            super.onHit();
        } else {
            this.level--;
            this.setImage("\\bricks\\" + this.color + "\\brick" + this.level + this.color + ".png");
        }
    }
}

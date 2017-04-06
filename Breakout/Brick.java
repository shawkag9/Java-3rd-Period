import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
        if (this.level <= 1) {
            getWorld().removeObject(this);
        } else {
            this.level--;
            this.setImage("\\bricks\\" + this.color + "\\brick" + this.level + this.color + ".png");
        }
    }
}

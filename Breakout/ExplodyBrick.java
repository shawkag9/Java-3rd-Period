import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class ExplodyBrick here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExplodyBrick extends Brick
{
    public ExplodyBrick(String color) {
        super(1, color);
    }
    
    @Override
    public void onHit() {
        List<Brick> bricks = getObjectsInRange(getImage().getWidth() * 4, Brick.class);
        for (Brick brick: bricks) {
            if (brick.level == 1) {
                getWorld().removeObject(brick);
            } else {
                brick.onHit();
            }
        }
        getWorld().removeObject(this);
    }
}

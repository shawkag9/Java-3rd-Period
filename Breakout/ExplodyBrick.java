import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class ExplodyBrick extends Brick
{
    public ExplodyBrick() {
        super(1, "brown");
    }
    
    @Override
    public void onHit() {
        if (getWorld() != null) {
            MyWorld world = (MyWorld)getWorld();
            world.addPoints(100);
            List<Brick> bricks = getObjectsInRange(getImage().getWidth() * 4, Brick.class);
            getWorld().removeObject(this);
            for (Brick brick: bricks) {
                brick.onHit();
            }
        }
    }
}

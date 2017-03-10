import greenfoot.*;

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{
    int dx = 3;
    int dy = 3;
    
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if ((getX() > getWorld().getWidth() - getImage().getWidth()/2) || (getX() < getImage().getWidth()/2)) {
            dx = -dx;
        }
        
        if (getY() < getImage().getHeight()/2) {
            dy = -dy;
        }
        if (getY() > getWorld().getHeight() - getImage().getHeight()/2) {
            // game over
            Greenfoot.stop();
        }
        
        if (isTouching(Brick.class)) {
            dx = (int) (3 * Math.cos(Math.atan2(getY() - getOneIntersectingObject(Brick.class).getY(), getX() - getOneIntersectingObject(Brick.class).getX())));
            dy = (int) (3 * Math.sin(Math.atan2(getY() - getOneIntersectingObject(Brick.class).getY(), getX() - getOneIntersectingObject(Brick.class).getX())));
            if (Math.abs(dx) < 1) {
                if (dx < 0) {
                    dx = -1;
                } else {
                    dx = 1;
                }
            }
            if (Math.abs(dy) < 1) {
                if (dy < 0) {
                    dy = -1;
                } else {
                    dy = 1;
                }
            }
            
            removeTouching(Brick.class);
        }
        
        if (isTouching(Paddle.class)) {
            dx = (int) (3 * Math.cos(Math.atan2(getY() - getOneIntersectingObject(Paddle.class).getY(), getX() - getOneIntersectingObject(Paddle.class).getX())));
            dy = (int) (3 * Math.sin(Math.atan2(getY() - getOneIntersectingObject(Paddle.class).getY(), getX() - getOneIntersectingObject(Paddle.class).getX())));
            if (Math.abs(dx) < 1) {
                if (dx < 0) {
                    dx = -1;
                } else {
                    dx = 1;
                }
            }
            if (Math.abs(dy) < 1) {
                if (dy < 0) {
                    dy = -1;
                } else {
                    dy = 1;
                }
            }
        }
        
        setLocation(getX() + dx, getY() + dy);
    }
}

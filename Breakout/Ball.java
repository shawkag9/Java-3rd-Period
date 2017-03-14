import greenfoot.*;

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{
    int dx = 0;
    int dy = 3;
    
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if ((getX() > getWorld().getWidth() - getImage().getWidth()/2)) {
            dx = -dx;
            setLocation(getWorld().getWidth() - getImage().getWidth()/2, getY());
        }
        if (getX() < getImage().getWidth()/2) {
            dx = -dx;
            setLocation(getImage().getWidth()/2, getY());
        }
        
        if (getY() < getImage().getHeight()/2) {
            dy = -dy;
        }
        if (getY() > getWorld().getHeight() - getImage().getHeight()/2) {
            // game over
            Greenfoot.stop();
        }
        
        if (isTouching(Brick.class)) {
            double theta = Math.atan2(getY() - getOneIntersectingObject(Brick.class).getY(), getX() - getOneIntersectingObject(Brick.class).getX());
            int r = 3;
            dx = (int) (r * Math.cos(theta));
            dy = (int) (r * Math.sin(theta));
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
            double theta = Math.atan2(getY() - getOneIntersectingObject(Paddle.class).getY(), getX() - getOneIntersectingObject(Paddle.class).getX());
            int r = 3;
            dx = (int) (r * Math.cos(theta));
            dy = (int) (r * Math.sin(theta));
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

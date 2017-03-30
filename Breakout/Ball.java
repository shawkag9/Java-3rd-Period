import greenfoot.*;

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{
    int dx = 1;
    int dy = 2;
    public static boolean playing = false;
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (playing) {
            bounceOffWalls();
            bounceOffStuff();
            
            setLocation(getX() + dx, getY() + dy);
        }
    }
    
    private void bounceOffWalls() {
        if (getX() >= getWorld().getWidth() - getImage().getWidth()/2 + 2) {
            dx = -dx;
            setLocation(getWorld().getWidth() - getImage().getWidth()/2, getY());
        }
        if (getX() <= getImage().getWidth()/2 - 2) {
            dx = -dx;
            setLocation(getImage().getWidth()/2, getY());
        }
        
        if (getY() <= getImage().getHeight()/2 - 2) {
            dy = -dy;
            setLocation(getX(), getImage().getHeight()/2 - 2);
        }
        if (getY() >= getWorld().getHeight() - getImage().getHeight()/2 + 1) {
            // game over
            Greenfoot.stop();
        }
    }
    
    private void bounceOffStuff() {
        if (isTouching(Brick.class)) {
            Brick brick = getIntersectingObjects(Brick.class).get(0);
            if (intersects(brick)) {bounce(brick);}
            brick.onHit();
        }
        if (isTouching(Paddle.class)) {
            bounce(getIntersectingObjects(Paddle.class).get(0));
        }
    }
    
    private void bounce(Actor thing) {
        boolean x_edge = getX() - thing.getX() < -thing.getImage().getWidth()/2 || getX() - thing.getX() > thing.getImage().getWidth()/2;
        boolean y_edge = getY() - thing.getY() < -thing.getImage().getHeight()/2 || getY() - thing.getY() > thing.getImage().getHeight()/2;
        if (x_edge && y_edge) {
            double theta = Math.atan2(getY() - thing.getY(), getX() - thing.getX());
            int speed = 3;
            dx = (int) (speed * Math.cos(theta));
            dy = (int) (speed * Math.sin(theta));
            
        } else {
            if (!x_edge && y_edge) {
                dy = -dy;
            } else if (!y_edge && x_edge) {
                dx = -dx;
            }
        }
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
}

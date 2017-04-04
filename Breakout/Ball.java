import greenfoot.*;

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{
    private int speed;
    private int dx;
    private int dy;
    private static boolean playing;
    
    public Ball() {
        speed = 3;
        dy = 1 * speed;
        dx = 0 * speed;
        playing = false;
    }
    
    public void setPlaying(boolean p) {
        playing = p;
    }
    
    public boolean getPlaying() {
        return playing;
    }
    
    public void act() {
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
    }
    
    private void bounceOffStuff() {
        if (isTouching(Brick.class)) {
            Brick brick = (Brick)getOneIntersectingObject(Brick.class);
            if (intersects(brick)) bounce(brick);
            brick.onHit();
        }
        if (isTouching(Paddle.class)) {
            bounce((Paddle)getOneIntersectingObject(Paddle.class));
        }
    }
    
    private void bounce(Actor thing) {
        boolean x_edge = getX() - thing.getX() < -thing.getImage().getWidth()/2 || getX() - thing.getX() > thing.getImage().getWidth()/2;
        boolean y_edge = getY() - thing.getY() < -thing.getImage().getHeight()/2 || getY() - thing.getY() > thing.getImage().getHeight()/2;
        // if (x_edge && y_edge) {
            // double theta = Math.atan2(getY() - thing.getY(), getX() - thing.getX());
            // dx = (int) (speed * Math.cos(theta));
            // dy = (int) (speed * Math.sin(theta));
        // } else {
            // if (!x_edge && y_edge) {
                // dy = -dy;
            // } else if (!y_edge && x_edge) {
                // dx = -dx;
            // }
        // }
        if (x_edge) dx = -dx;
        if (y_edge) dy = -dy;
        dx = (Math.abs(dx) < 1 ? dx < 0 ? -1 : 1 : dx);
        dy = (Math.abs(dy) < 1 ? dy < 0 ? -1 : 1 : dy);
    }
}

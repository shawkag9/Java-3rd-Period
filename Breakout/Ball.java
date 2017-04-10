import greenfoot.*;

public class Ball extends Actor
{
    private int speed;
    private int dx;
    private int dy;
    private static boolean playing;
    private boolean bulldozer;
    private int powerupTimer;
    private boolean explode;
    
    public Ball() {
        speed = 3;
        dy = 1 * speed;
        dx = 0 * speed;
        playing = false;
        bulldozer = false;
        explode = false;
        powerupTimer = 500;
    }
    
    public void setPlaying(boolean p) {
        playing = p;
    }
    
    public boolean getPlaying() {
        return playing;
    }
    
    public void act() {
        if (playing) {
            checkForPowerup();
            bounceOffWalls();
            bounceOffStuff();
            
            setLocation(getX() + dx, getY() + dy);
        }
    }
    
    private void checkForPowerup() {
        if (bulldozer || explode) powerupTimer--;
        if (powerupTimer < 0) {
            setImage("ball.png");
            bulldozer = false;
            explode = false;
        }
        if (isTouching(Powerup.class)) {
            powerupTimer = 500;
            Powerup powerup = (Powerup)getOneIntersectingObject(Powerup.class);
            if (powerup.getType().equals("bulldozer")) {
                setImage("BiggerBall001.png");
                bulldozer = true;
                getWorld().removeObject(powerup);
            } else if (powerup.getType().equals("explode")) {
                setImage("BiggerBall002.png");
                explode = true;
                getWorld().removeObject(powerup);
            }
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
            if (bulldozer) {
                getWorld().removeObject(brick);
                MyWorld world = (MyWorld)getWorld();
                world.addPoints(100);
            } else {
                if (intersects(brick)) bounce(brick);
                if (explode) {
                    if (brick.getClass() != ExplodyBrick.class) {
                        getWorld().addObject(new ExplodyBrick(), brick.getX(), brick.getY());
                        getWorld().removeObject(brick);
                        ExplodyBrick newBrick = (ExplodyBrick)getOneIntersectingObject(ExplodyBrick.class);
                        newBrick.onHit();
                    } else {
                        brick.onHit();
                    }
                } else {
                    brick.onHit();
                }
            }
        }
        if (isTouching(Paddle.class)) {
            bounce((Paddle)getOneIntersectingObject(Paddle.class));
        }
    }
    
    private void bounce(Actor thing) {
        boolean x_edge = getX() - thing.getX() <= -thing.getImage().getWidth()/2 - 2|| getX() - thing.getX() >= thing.getImage().getWidth()/2 + 2;
        boolean y_edge = getY() - thing.getY() <= -thing.getImage().getHeight()/2 - 2|| getY() - thing.getY() >= thing.getImage().getHeight()/2 + 2;
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

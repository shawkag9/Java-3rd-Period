import greenfoot.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public Paddle paddle;
    public Ball ball;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        setPaintOrder(Ball.class, Paddle.class, Brick.class);
        
        ball = new Ball();
        addObject(ball, getWidth()/2, getHeight()/2);
        paddle = new Paddle();
        addObject(paddle, getWidth()/2, getHeight() - paddle.getImage().getHeight()/2);
        for (int i = 0; i < 10; i++) {
            Brick brick = new Brick();
            addObject(brick, i * getWidth()/10 + 10, Greenfoot.getRandomNumber(getHeight() - 50));
        }
        
    }
}

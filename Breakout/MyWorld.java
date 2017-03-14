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
    public Brick brick;
    public int brickW;
    public int brickH;
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
        
        brick = new Brick();
        brickW = brick.getImage().getWidth()*2;
        brickH = brick.getImage().getHeight();
        
        drawLevel(3);
    }
    
    public void drawLevel(int level) {
        switch (level) {
            case 1:
                for (int i = 0; i < 5; i++) {
                    brickLine((int)(brickW * 3 / 2), (int)(brickH * 3 / 2) + i * brickH, (int)(getWidth() / brickW) - 2, "red");
                }
                break;
            case 2:
                for (int i = 0; i < 5; i++) {
                    brickLine((int)(brickW * 3 / 2) + i * brickW, (int)(brickH * 3 / 2) + 2 * i * brickH, (int)(getWidth() / brickW) - 2 * (i + 1), "purple");
                }
                break;
            case 3:
                int[][] boxLocations = {
                    {(int)(getWidth() * 0.25), (int)(getHeight() * 0.25)},
                    {(int)(getWidth() * 0.50), (int)(getHeight() * 0.25)},
                    {(int)(getWidth() * 0.75), (int)(getHeight() * 0.25)},
                    {(int)(getWidth() * 0.33), (int)(getHeight() * 0.50)},
                    {(int)(getWidth() * 0.66), (int)(getHeight() * 0.50)},
                    {(int)(getWidth() * 0.25), (int)(getHeight() * 0.75)},
                    {(int)(getWidth() * 0.50), (int)(getHeight() * 0.75)},
                    {(int)(getWidth() * 0.75), (int)(getHeight() * 0.75)}
                };
                for (int i = 0; i < boxLocations.length; i++) {
                    brickBox(boxLocations[i][0], boxLocations[i][1], 3, "green");
                }
                System.out.println((getWidth() * 0.25) + ", " + (getHeight() * 0.25));
                break;
            default:
                break;
        }
    }
    
    public void brickBox(int x, int y, int sideLength, String color) {
        for (int i = (int)(-sideLength/2); i <= (int)(sideLength/2); i++) {
            brickLine(x - (int)(sideLength/2 * brickW), y + i * brickH, sideLength, color);
        }
    }
    
    public void brickLine(int x, int y, int length, String color) {
        for (int i = 0; i < length; i++) {
            Brick b = new Brick();
            b.setImage("brick" + color + ".png");
            addObject(b, x + i * brickW, y);
        }
    }
}
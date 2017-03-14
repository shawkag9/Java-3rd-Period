import greenfoot.*;
import java.util.Random;

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
    public String[] colors;
    public Random random;
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
        
        random = new Random();
        colors = new String[] {
            "red",
            "purple",
            "orange",
            "green",
            "gray",
            "brown",
            "blue"
        };
        
        drawLevel(2);
    }
    
    public void drawLevel(int level) {
        switch (level) {
            case 1:
                for (int i = 0; i < 5; i++) {
                    brickLine(
                        (int)(brickW * 3 / 2), 
                        (int)(brickH * 3 / 2) + i * brickH, 
                        (int)(getWidth() / brickW) - 2, 
                        new String[] {"red", "purple", "blue"}
                    );
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    brickLine(
                        (int)(brickW * 3 / 2) + i * brickW, 
                        (int)(brickH * 3 / 2) + 2 * i * brickH, 
                        (int)(getWidth() / brickW) - 1 - i * 2, 
                        new String[] {"purple", "blue", "orange", "red"}
                    );
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
                    brickBox(
                        boxLocations[i][0], 
                        boxLocations[i][1], 
                        4, 4,
                        new String[] {"green", "purple", "orange"}
                    );
                }
                break;
            default:
                break;
        }
    }
    
    public void brickBox(int x, int y, int width, int height, String[] color) {
        for (int i = 0; i < height; i++) {
            brickLine(
                x - (int)(width * brickW / 2), 
                y - (int)(height * brickH / 2) + i * brickH, 
                width, 
                color
            );
        }
    }
    
    public void brickLine(int x, int y, int length, String[] color) {
        for (int i = 0; i < length; i++) {
            placeBrick(
                x + i * brickW, 
                y, 
                color[i % color.length]
            );
        }
    }
    
    public void placeBrick(int x, int y, String color) {
        Brick brick = new Brick();
        brick.setImage("brick" + color + ".png");
        switch (color.substring(0, 7)) {
            case "stripe":
                brick.setLevel(2);
            default:
                brick.setLevel(2);
        }
        addObject(brick, x, y);
    }
}
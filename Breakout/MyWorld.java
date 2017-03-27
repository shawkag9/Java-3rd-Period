import greenfoot.*;
import java.util.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Paddle paddle;
    private Ball ball;
    private Brick brick;
    private int brickW;
    private int brickH;
    public static String[] colors;
    private int width;
    private int height;
    private String state;
    private String currState;
    private boolean stateChanged;
    private boolean wasKeyDown;
    private boolean spaceUp;
    private int level;
    private String[] flow;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        setPaintOrder(Text.class, Ball.class, Paddle.class, Brick.class);
        
        width = getWidth();
        height = getHeight();
        stateChanged = false;
        spaceUp = false;
        flow = new String[] {
            "menu", 
            "playing",
            "playing",
            "playing",
            "win"
        };
        
        level = 0;
        state = "menu";
        currState = state;
        loadState(state);
    }
    public void act() {
        switch(state) {
            case "menu":
                if (spaceUp) {
                    state = "playing";
                    level = 1;
                    loadState(state);
                    
                    spaceUp = false;
                    wasKeyDown = false;
                } else {
                    spaceUp = wasKeyDown && !Greenfoot.isKeyDown("space");
                    wasKeyDown = Greenfoot.isKeyDown("space");
                }
                break;
            case "playing":
                if (spaceUp) {
                    Ball ball = getObjects(Ball.class).get(0);
                    if (!ball.playing) {
                        ball.playing = true;
                    }
                    
                    spaceUp = false;
                    wasKeyDown = false;
                } else {
                    spaceUp = wasKeyDown && !Greenfoot.isKeyDown("space");
                    wasKeyDown = Greenfoot.isKeyDown("space");
                }
                if (getObjects(Brick.class).isEmpty()) {
                    if (level < 3) {
                        level++;
                        loadState(state);
                    } else {
                        state = "win";
                        loadState(state);
                    }
                }
                if (!getObjects(Ball.class).isEmpty() && getObjects(Ball.class).get(0).getY() > getHeight() - getObjects(Ball.class).get(0).getImage().getHeight()/2) {
                    state = "game over";
                    loadState(state);
                }
                break;
            case "win":
                if (spaceUp) {
                    
                    state = "menu";
                    loadState(state);
                    
                    spaceUp = false;
                    wasKeyDown = false;
                } else {
                    spaceUp = wasKeyDown && !Greenfoot.isKeyDown("space");
                    wasKeyDown = Greenfoot.isKeyDown("space");
                }
                break;
            case "game over":
                if (spaceUp) {
                    state = "menu";
                    loadState(state);
                    
                    spaceUp = false;
                    wasKeyDown = false;
                } else {
                    spaceUp = wasKeyDown && !Greenfoot.isKeyDown("space");
                    wasKeyDown = Greenfoot.isKeyDown("space");
                }
                break;
        }
    }
    
    public void loadState(String state) {
        removeObjects(getObjects(null));
        switch(state) {
            case "menu":
                addObject(new Text("Welcome!\nPress space to start"), getWidth()/2, getHeight()/2);
                
                ball = new Ball();
                paddle = new Paddle();
                paddle.setImage("\\paddles\\metalPaddle007.png");
                brick = new Brick();
                break;
            case "playing":
                addObject(ball, width/2, height / 2);
                getObjects(Ball.class).get(0).playing = false;
                addObject(paddle, width/2, height - paddle.getImage().getHeight()/2);
                brickW = brick.getImage().getWidth()*2;
                brickH = brick.getImage().getHeight();
                
                colors = new String[] {
                    "brown",  
                    "gray", 
                    "green", 
                    "orange",  
                    "purple", 
                    "red",
                };
        
                drawLevel(level);
                break;
            case "win":
                addObject(new Text("You Win!"), getWidth()/2, getHeight()/2);
                break;
            case "game over":
                addObject(new Text("Game Over!"), getWidth()/2, getHeight()/2);
                break;
        }
    }

    public void drawLevel(int level) {
        switch (level) {
            case 1:
                for (int i = 0; i < 5; i++) {
                    int[] levels = {
                        Greenfoot.getRandomNumber(2),
                        Greenfoot.getRandomNumber(2),
                        Greenfoot.getRandomNumber(2)
                    };
                    brickLine(
                        (int)(brickW * 3 / 2), 
                        (int)(brickH * 3 / 2) + i * brickH, 
                        (int)(width / brickW) - 2 + 1, 
                        new int[] {3, 2},
                        new String[] {
                            colors[Greenfoot.getRandomNumber(colors.length)],
                            colors[Greenfoot.getRandomNumber(colors.length)]
                        }
                    );
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    brickLine(
                        (int)(brickW * 3 / 2) + i * brickW, 
                        (int)(brickH * 3 / 2) + 2 * i * brickH, 
                        (int)(width / brickW) - 1 - i * 2, 
                        new int[] {3, 2},
                        new String[] {
                            colors[Greenfoot.getRandomNumber(colors.length)],
                            colors[Greenfoot.getRandomNumber(colors.length)]
                        }
                    );
                }
                break;
            case 3:
                int[][] boxLocations = {
                    {(int)(width * 0.25), (int)(height * 0.25)},
                    {(int)(width * 0.50), (int)(height * 0.25)},
                    {(int)(width * 0.75), (int)(height * 0.25)},
                    {(int)(width * 0.33), (int)(height * 0.50)},
                    {(int)(width * 0.66), (int)(height * 0.50)},
                    {(int)(width * 0.25), (int)(height * 0.75)},
                    {(int)(width * 0.50), (int)(height * 0.75)},
                    {(int)(width * 0.75), (int)(height * 0.75)}
                };
                int[] levels = {
                    Greenfoot.getRandomNumber(2),
                    Greenfoot.getRandomNumber(2),
                    Greenfoot.getRandomNumber(2)
                };
                for (int i = 0; i < boxLocations.length; i++) {
                    brickBox(
                        boxLocations[i][0], 
                        boxLocations[i][1], 
                        4, 4,
                        new String[] {
                            colors[Greenfoot.getRandomNumber(colors.length)],
                            colors[Greenfoot.getRandomNumber(colors.length)]
                        }
                    );
                }
                break;
            default:
                System.out.println("invalid level");
                break;
        }
    }

    public void brickBox(int x, int y, int width, int height, String[] color) {
        for (int i = 0; i < height; i++) {
            brickLine(
                x - (int)(width * brickW / 2) + 1, 
                y - (int)(height * brickH / 2) + 1 + i * brickH,
                width, 
                new int[] {3, 2},
                color
            );
        }
    }

    public void brickLine(int x, int y, int length, int[] levels, String[] color) {
        for (int i = 0; i < length; i++) {
            placeBrick(
                x + i * brickW, 
                y,
                levels[i % levels.length],
                color[i % color.length]
            );
        }
    }

    public void placeBrick(int x, int y, int level, String color) {
        Brick brick = new Brick();
        brick.setColor(color);
        brick.setLevel(level);
        
        addObject(brick, x, y);
    }
}


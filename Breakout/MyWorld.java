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
    private static String[] colors;
    private int width;
    private int height;
    private String state;
    private String currState;
    private boolean stateChanged;
    private boolean wasKeyDown;
    private boolean spaceUp;
    private int level;
    private String[] flow;
    private int numLevels;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        setPaintOrder(Text.class, Ball.class, Paddle.class, Brick.class);
        setActOrder(Brick.class, Ball.class);
        
        width = getWidth();
        height = getHeight();
        stateChanged = false;
        spaceUp = false;
        
        numLevels = 6;
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
                    if (level < numLevels) {
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
    
    private void loadState(String state) {
        removeObjects(getObjects(null));
        switch(state) {
            case "menu":
                addObject(new Text("Welcome!\nPress space to start"), getWidth()/2, getHeight()/2);
                
                ball = new Ball();
                paddle = new Paddle();
                paddle.setImage("\\paddles\\metalPaddle007.png");
                brick = new Brick(1, "red");
                break;
            case "playing":
                addObject(ball, width/2, height / 2);
                getObjects(Ball.class).get(0).playing = false;
                addObject(paddle, width/2, height - paddle.getImage().getHeight()/2);
                brickW = brick.getImage().getWidth();
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

    private void drawLevel(int level) {
        String[] level_colors = new String[] {
                                  colors[Greenfoot.getRandomNumber(colors.length)],
                                  colors[Greenfoot.getRandomNumber(colors.length)]
                                };
        switch (level) {
            case 1:
                for (int i = 0; i < 5; i++) {
                    brickLine(
                        (int)(brickW * 3 / 2) + brickW, 
                        (int)(brickH * 3 / 2) + (i + 3) * brickH, 
                        (int)(width / brickW) - 2 - 1, 
                        level_colors
                    );
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    brickLine(
                        (int)(brickW * 3 / 2) + i * brickW, 
                        (int)(brickH * 3 / 2) + 2 * i * brickH, 
                        (int)(width / brickW) - 1 - i * 2, 
                        level_colors
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
                for (int i = 0; i < boxLocations.length; i++) {
                    brickBox(
                        boxLocations[i][0], 
                        boxLocations[i][1], 
                        4, 4,
                        level_colors
                    );
                }
                break;
            case 4:
                int startX = brickW * 2;
                int startY = brickH * 2;
                int length = (int)((getWidth() - startX) / brickW);
                int height = (int)((getHeight() - startY) / brickH);
                brickColumn(startX, startY + brickH, height - 5, level_colors);
                brickColumn(startX + (length - 1) * brickW, startY + brickH, height - 5, level_colors);
                brickLine(startX, startY, length, level_colors);
                brickLine(startX, startY + ((height - 4) * brickH), length, level_colors);
                break;
            case 5:
                
                break;
            case 6:
                
                break;
            default:
                System.out.println("invalid level");
                break;
        }
    }

    private void brickBox(int x, int y, int width, int height, String[] color) {
        for (int i = 0; i < height; i++) {
            brickLine(
                x - (int)(width * brickW / 2) + 1, 
                y - (int)(height * brickH / 2) + 1 + i * brickH,
                width, 
                color
            );
        }
    }

    private void brickLine(int x, int y, int length, String[] color) {
        for (int i = 0; i < length; i++) {
            placeBrick(
                x + i * brickW, 
                y,
                color[i % color.length]
            );
        }
    }
    
    private void brickColumn(int x, int y, int height, String[] color) {
        for (int i = 0; i < height; i++) {
            placeBrick(
                x, 
                y + i * brickH,
                color[i % color.length]
            );
        }
    }

    private void placeBrick(int x, int y, String color) {
        Brick brick;
        if (Greenfoot.getRandomNumber(100) < 20) {
            brick = new ExplodyBrick(color);
        } else {
            brick = new StrongBrick(color);
        }
        
        addObject(brick, x, y);
    }
}


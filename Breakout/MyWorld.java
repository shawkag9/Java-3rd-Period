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
        
        numLevels = 7;
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
                    if (!ball.getPlaying()) {
                        ball.setPlaying(true);
                    }
                    for (Brick brick : getObjects(Brick.class)) {
                        brick.acting = true;
                        if (level == 7) {
                            brick.wiggle = true;
                        }
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
                        for (Brick brick : getObjects(Brick.class)) {
                            brick.acting = false;
                        }
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
                addObject(new Text("Welcome!\nPress space to start", 50), getWidth()/2, getHeight()/2);
                
                ball = new Ball();
                paddle = new Paddle();
                paddle.setImage("\\paddles\\metalPaddle007.png");
                brick = new Brick(1, "red");
                break;
            case "playing":
                addObject(ball, width/2, height / 2);
                getObjects(Ball.class).get(0).setPlaying(false);
                addObject(paddle, width/2, height - paddle.getImage().getHeight()/2);
                brickW = brick.getImage().getWidth();
                brickH = brick.getImage().getHeight();
                
                colors = new String[] {
                    "red",
                    "orange",
                    "green",
                    "blue",
                    "purple",
                    "gray"
                };
        
                drawLevel(level);
                break;
            case "win":
                addObject(new Text("You Win!", 50), getWidth()/2, getHeight()/2);
                break;
            case "game over":
                addObject(new Text("Game Over!", 50), getWidth()/2, getHeight()/2);
                break;
        }
    }

    private void drawLevel(int level) {
        String[] level_colors;
        int[][] boxLocations;
        int world_width = getWidth();
        int world_height = getHeight();
        switch (level) {
            case 1:
                level_colors = colors;
                for (int i = 0; i < 5; i++) {
                    brickLine(
                        (int)(brickW * 3 / 2) + brickW, 
                        (int)(brickH * 3 / 2) + (i + 3) * brickH, 
                        (int)(world_width / brickW) - 2 - 1, 
                        new String[]{level_colors[i % level_colors.length]},
                        new int[][] {{0, 0}}
                    );
                }
                break;
            case 2:
                level_colors = colors;
                for (int i = 0; i < 10; i++) {
                    brickLine(
                        (int)(brickW * 3 / 2) + i * brickW, 
                        (int)(brickH * 3 / 2) + 2 * i * brickH, 
                        (int)(world_width / brickW) - 1 - i * 2, 
                        new String[]{level_colors[i % level_colors.length]},
                        new int[][] {{0, 0}}
                    );
                }
                break;
            case 3:
                level_colors = colors;
                boxLocations = new int[][] {
                    {(int)(world_width * 0.25), (int)(world_height * 0.25)},
                    {(int)(world_width * 0.50), (int)(world_height * 0.25)},
                    {(int)(world_width * 0.75), (int)(world_height * 0.25)},
                    {(int)(world_width * 0.33), (int)(world_height * 0.50)},
                    {(int)(world_width * 0.66), (int)(world_height * 0.50)},
                    {(int)(world_width * 0.25), (int)(world_height * 0.75)},
                    {(int)(world_width * 0.50), (int)(world_height * 0.75)},
                    {(int)(world_width * 0.75), (int)(world_height * 0.75)}
                };
                for (int i = 0; i < boxLocations.length; i++) {
                    brickBox(
                        boxLocations[i][0], 
                        boxLocations[i][1], 
                        4, 4,
                        new String[]{level_colors[i % level_colors.length]},
                        new int[][] {{0, 0}}
                    );
                }
                break;
            case 4:
                level_colors = colors;
                int startX = brickW * 2;
                int startY = brickH * 2;
                int length = (int)((getWidth() - startX) / brickW);
                int height = (int)((getHeight() - startY) / brickH);
                brickColumn(startX, startY + brickH, height - 5, level_colors, new int[][] {{0, 0}});
                brickColumn(startX + (length - 1) * brickW, startY + brickH, height - 5, level_colors, new int[][] {{0, 0}});
                brickLine(startX, startY, length, level_colors, new int[][] {{0, 0}});
                brickLine(startX, startY + ((height - 4) * brickH), length, level_colors, new int[][] {{0, 0}});
                break;
            case 5:
                level_colors = colors;
                int[][] boxLocations_eyes = new int[][] {
                    {(int)(world_width * 0.275), (int)(world_height * 0.15)},
                    {(int)(world_width * 0.775), (int)(world_height * 0.15)}
                };
                for (int i = 0; i < boxLocations_eyes.length; i++) {
                    brickBox(
                        boxLocations_eyes[i][0], 
                        boxLocations_eyes[i][1], 
                        4, 4,
                        new String[]{level_colors[i % level_colors.length]},
                        new int[][] {{0, 0}}
                    );
                }
                
                for (int i = 0; i < (int)((world_width * 0.75) / brickW) + 1; i++) {
                    placeBrick(
                        i * brickW + (int)(world_width * 0.11), 
                        (int)(70 * Math.sin((i * brickW) / (world_width * 0.75) * Math.PI) + world_height * 0.60),
                        level_colors[i % level_colors.length],
                        new int[] {0, 0}
                    );
                }
                break;
            case 6:
                level_colors = colors;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        placeBrick(
                            (int)(Math.random() * world_width * 0.75 + world_width * 0.125),
                            i * brickH + brickH * 3,
                            level_colors[i % level_colors.length],
                            new int[] {(int)(Math.random() < 0.5 ? Math.random() + 1 : -Math.random() - 1), (int)(Math.random() < 0.5 ? Math.random() * 2 + 1 : -Math.random() * 2 - 1)}
                        );
                    }
                }
                break;
            case 7:
                placeBrick(
                    (int)(Math.random() * world_width / 2 + world_width * 0.25),
                    (int)(Math.random() * world_width / 2 + world_width * 0.25),
                    colors[(int)(Math.random() * colors.length)],
                    new int[] {(int)(Math.random() * 2), (int)(Math.random() * 2)}
                );
                break;
            default:
                System.out.println("invalid level");
                break;
        }
    }

    private void brickBox(int x, int y, int width, int height, String[] color, int[][] dir) {
        for (int i = 0; i < height; i++) {
            brickLine(
                x - (int)(width * brickW / 2) + 1, 
                y - (int)(height * brickH / 2) + 1 + i * brickH,
                width, 
                color,
                dir
            );
        }
    }

    private void brickLine(int x, int y, int length, String[] color, int[][] dir) {
        for (int i = 0; i < length; i++) {
            placeBrick(
                x + i * brickW, 
                y,
                color[i % color.length],
                dir[i % dir.length]
            );
        }
    }
    
    private void brickColumn(int x, int y, int height, String[] color, int[][] dir) {
        for (int i = 0; i < height; i++) {
            placeBrick(
                x, 
                y + i * brickH,
                color[i % color.length],
                dir[i % dir.length]
            );
        }
    }

    private void placeBrick(int x, int y, String color, int[] dir) {
        Brick brick;
        if (Greenfoot.getRandomNumber(100) < 95) brick = new StrongBrick(color);
        else brick = new ExplodyBrick();
        brick.setDx(dir[0]);
        brick.setDy(dir[1]);
        
        addObject(brick, x, y);
    }
}


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
    public String[][] colors;
    public int width;
    public int height;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); 
        setPaintOrder(Ball.class, Paddle.class, Brick.class);
        
        width = getWidth();
        height = getHeight();
        
        ball = new Ball();
        addObject(ball, width/2, height/2);
        paddle = new Paddle();
        addObject(paddle, width/2, height - paddle.getImage().getHeight()/2);

        brick = new Brick();
        brickW = brick.getImage().getWidth()*2;
        brickH = brick.getImage().getHeight();
        
        colors = new String[2][7];
        colors = new String[][] {
            {
                "blue", 
                "brown",  
                "gray", 
                "green", 
                "orange",  
                "purple", 
                "red",
            },
            {
                "stripebluedarkgreen", 
                "stripebluedarkorange", 
                "stripebluegreen",
                "stripeblueorange", 
                "stripebluepurple",
                "stripebluered", 
                "stripedarkorangepurple", 
                // "stripegrayorange", 
                // "stripegraypurple", 
                // "stripegreendarkorange",
                // "stripegreengray", 
                // "stripegreenorange", 
                // "stripegreenpurple",
                // "stripegreenred", 
                // "stripeorangepurple",
                // "stripepurpleorange", 
                // "stripereddarkorange", 
                // "striperedgray", 
                // "striperedorange", 
                // "striperedpurple"
            }
        };

        drawLevel(Greenfoot.getRandomNumber(3) + 1);
    }
    
    public void reset() {
        Ball ball = new Ball();
        addObject(ball, width/2, height/2);
        Paddle paddle = new Paddle();
        addObject(paddle, width/2, height - paddle.getImage().getHeight()/2);
        
        drawLevel(Greenfoot.getRandomNumber(3) + 1);
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
                    String test = colors[levels[0]][Greenfoot.getRandomNumber(colors[levels[0]].length)];
                    brickLine(
                        (int)(brickW * 3 / 2), 
                        (int)(brickH * 3 / 2) + i * brickH, 
                        (int)(width / brickW) - 2, 
                        new String[] {
                            colors[levels[0]][Greenfoot.getRandomNumber(colors[levels[0]].length)],
                            colors[levels[1]][Greenfoot.getRandomNumber(colors[levels[1]].length)],
                            colors[levels[2]][Greenfoot.getRandomNumber(colors[levels[2]].length)]
                        }
                    );
                }
                break;
            case 2:
                for (int i = 0; i < 10; i++) {
                    int[] levels = {
                            Greenfoot.getRandomNumber(2),
                            Greenfoot.getRandomNumber(2),
                            Greenfoot.getRandomNumber(2)
                        };
                    brickLine(
                        (int)(brickW * 3 / 2) + i * brickW, 
                        (int)(brickH * 3 / 2) + 2 * i * brickH, 
                        (int)(width / brickW) - 1 - i * 2, 
                        new String[] {
                            colors[levels[0]][Greenfoot.getRandomNumber(colors[levels[0]].length)],
                            colors[levels[1]][Greenfoot.getRandomNumber(colors[levels[1]].length)],
                            colors[levels[2]][Greenfoot.getRandomNumber(colors[levels[2]].length)]
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
                            colors[levels[0]][Greenfoot.getRandomNumber(colors[levels[0]].length)],
                            colors[levels[1]][Greenfoot.getRandomNumber(colors[levels[1]].length)],
                            colors[levels[2]][Greenfoot.getRandomNumber(colors[levels[2]].length)]
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
        if (color.length() < 7) {
            brick.setLevel(1);
        } else if (color.substring(0,6).equals("stripe")) {
            brick.setLevel(2);
        }
        addObject(brick, x, y);
    }
}


import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)



/**
 * This is Earth. Or at least some remote, uninhabited part of Earth. Here, Greeps can
 * land and look for piles of tomatoes...
 * 
 * @author Michael Kolling
 * @version 1.0
 */
public class Earth extends World
{
    public static final int RESOLUTION = 1;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private int count = 0;
    private boolean isMapB = false;

    public static final int SCORE_DISPLAY_TIME = 2400;

    private GreenfootImage map;
    private Ship ship;
    private int currentMap;
    
    // Data defining map layout. For each map, the first 3-tuple defines the space ship
    // location: { target-y, start-x, start-y } The ship moves from start-y to target-y.
    //
    // then follow an arbitrary number of additional 3-tuples, one for each tomato pile,
    // in the format: { number-tomatos-in-pile, x, y }
    //
    private int[][][] mapData = {
        { {480, 100, 0}, {40, 721, 532}, {12, 400, 560}, {40, 615, 400},    // map 1
          {40, 642, 192}, {16, 128, 113}, {30, 400, 40} },
 
        { {496, 709, 0}, {10, 322, 422}, {40, 700, 241}, {40, 681, 49},     // map 2
          {10, 317, 54}, {50, 90, 174}, {40, 60, 339} },
          
        { {272, 394, 0}, {10, 39, 30}, {30, 71, 476}, {50, 398, 520},       // map 3
          {40, 655, 492} },
          { {518, 72, 0}, {40,760,29}, {25, 758, 562}, {25, 21, 23}, 
          {50,392, 270},{30,431,30} },
       { {500, 350, 0}, {30, 700, 550}, {20, 500, 100}, {25, 172, 292}, {35, 41, 177}, {18, 767, 26} },
       { {75, 700, 0}, {100, 100, 575} },
        { {300, 200, 0}, {10, 500, 100}, {30, 525, 250}, {20, 600, 400} },
       { {500, 100, 0}, {20, 700, 550}, {5,38,258}, {45, 714, 152}, {24, 108, 28} },
       { {550, 750, 0}, {50, 50, 50}, {5, 100, 500} },
       { {350, 400, 0}, {40, 100, 450}, {25, 642, 171}, {30, 75, 25}, {25, 513, 27}, {8, 768, 337} },
       { {234, 566, 0}, {15, 307, 199}, {5, 713, 495}, {35, 267, 327}, {18, 479, 373} },
       { {500, 100, 0}, {10, 61, 63}, {50, 723, 274}, {25, 700, 520}},
       { {383, 423, 0}, {1, 1, 1}, {25, 710, 530}, {25, 55, 409}, {25, 260, 69}},
       { {119, 337, 0}, {25, 488, 233}, {25, 221, 109}},
       { {338, 253, 0}, {50, 700, 300}, {100, 426, 77}},
       { {400, 400, 0}, {50, 700, 500}, {15, 92, 522}, {20, 372, 175}},
       { {400, 400, 0}, {25, 73, 194}, {25, 171, 544}},
       { {300, 400, 0}, {15, 432, 41}, {25, 32, 291}, {100, 656, 493}},
       { {300, 400, 0}, {25, 700, 300}, {5, 482, 565}},
       { {301, 692, 0}, {1, 1, 1}, {100, 358, 227}},
       { {395, 107, 0}, {100, 703, 185}},
       { {300, 400, 0}, {2, 367, 127}, {2, 154, 242}, {2, 221, 429}, {2, 476, 487}, {2, 539, 230}},
       { {500, 100, -1}, {50, 397, 113} },
       { {500, 450, -1}, {50, 196, 42}, {50, 496, 42} }, 
       { {500, 150, -1}, {75,641, 448} },
       { {500, 100, 240}, {100, 726, 320} },
       { {300, 400, 480}, {4, 327, 520}, {45, 757, 391}, {20, 254, 21}, {50, 33, 520} },
       { {300, 400, 40}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)},
     {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)},
     {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)},
     {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)},
     {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, {4, Greenfoot.getRandomNumber(800),Greenfoot.getRandomNumber(600)}, 
    },
      { {200, 250, 0}, {25, 575, 475}, {1,1,1}, {30, 375, 375} },
       
       
    };

    private int[] scores;
    
    /**
     * Create a new world. 
     */
    public Earth()
    {
        super( WIDTH / RESOLUTION,  HEIGHT / RESOLUTION, RESOLUTION);
        currentMap = 0;
        scores = new int[mapData.length];    // one score for each map
        showMap(currentMap);
    }
    public void act(){
        if(mapData[currentMap][0][2]<0){
            count++;
            if (count==250){
                count = 0;
                isMapB = !isMapB;
                
            }
            if (isMapB){
                    
                    setBackground("map" + currentMap + "b.jpg");
                }
                else{
                    if (!isMapB){
                        
                        setBackground("map" + currentMap + ".jpg");
                    }
                
                }
        
            
        }
        if(mapData[currentMap][0][2]>0){
            Timer.gametime = mapData[currentMap][0][2]*3;
        }
        else{
            Timer.gametime = 120;
        }
    }
    
    /**
     * Return true, if the specified coordinate shows water.
     * (Water is defined as a predominantly blueish color.)
     */
    public boolean isWater(int x, int y)
    {
        if(mapData[currentMap][0][2]<0){
            GreenfootImage trollmap;
            if(isMapB){
                trollmap = new GreenfootImage("map" + currentMap +"b.jpg");
                
            }
            else{
                trollmap = new GreenfootImage("map" + currentMap + ".jpg");
            }
            Color col = trollmap.getColorAt(x, y);
            return col.getBlue() > (col.getRed() * 2);
        }
        
       
        else{
            
            Color col = map.getColorAt(x, y);
            return col.getBlue() > (col.getRed() * 2);
        }
    
    }


    
    /**
     * Jump to the given map number (1..n).
     */
    public void jumpToMap(int map)
    {
        isMapB = false;
        count = 0;
        clearWorld();
        currentMap = map-1;
        showMap(currentMap);
    }
    
    /**
     * Set up the start scene.
     */
    private void showMap(int mapNo)
    {
        map = new GreenfootImage("map" + mapNo + ".jpg");
        setBackground(map);
        Counter mapTitle = new Counter(Greep.getAuthorName() + " - Map ", mapNo+1);
        addObject(mapTitle, 200, 20);
        int[][] thisMap = mapData[mapNo];
        for(int i = 1; i < thisMap.length; i++) {
            int[] data = thisMap[i];
            addObject(new TomatoPile(data[0]), data[1], data[2]);
        }
        int[] shipData = thisMap[0];
        ship = new Ship(shipData[0]);
        addObject(ship, shipData[1], 0);
    }
    
    /**
     * Game is over. Stop running, display score.
     */
    public void mapFinished(int time)
    {
        displayScore(time);
        Greenfoot.delay(SCORE_DISPLAY_TIME);
        clearWorld();
        currentMap++;
        if(currentMap < mapData.length) {
            showMap(currentMap);
        }
        else {
            
            displayFinalScore();
            Greenfoot.stop();
            
        }
    }

    private void displayScore(int time)
    {
        int points = ship.getTomatoCount() + time;
        scores[currentMap] = points;
        ScoreBoard board = new ScoreBoard(Greep.getAuthorName(), "Map " + (currentMap+1), "Score: ", currentMap, scores);
        addObject(board, getWidth() / 2, getHeight() / 2);
    }
    
    private void displayFinalScore()
    {
        clearWorld();
        ScoreBoard board = new ScoreBoard(Greep.getAuthorName(), "Final score", "Total: ", scores);
        addObject(board, getWidth() / 2, getHeight() / 2);
    }
    
    private void clearWorld()
    {
        removeObjects(getObjects(null));
    }
}

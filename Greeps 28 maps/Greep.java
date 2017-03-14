import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A Greep is an alien creature that likes to collect tomatoes.
 *
 * @author (shaw kagawa)
 * @version 0.1
 */
public class Greep extends Creature
{
    // Remember: you cannot extend the Greep's memory. So:
    // no additional fields (other than final fields) allowed in this class!

    /**
     * Default constructor for testing purposes.
     */
    public Greep()
    {
        this(null);
    }


    /**
     * Create a Greep with its home space ship.
     */
    public Greep(Ship ship)
    {
        super(ship);
    }


    /*
     * Plan:
        Greeps wander until they meet a pile
        Greep returns home and tells others to go to the pile
        Greeps that are returning from the pile tell others to go to the pile
        Double load at pile
        Return home
     */
    /**
     * Move.
     */
    public void act()
    {
        super.act();   // do not delete! leave as first statement in act().

        final int turnHome_chance = 18;

        turnAtWaterEdge();
        switch (getMemory()) {
            case 0:
                if (isTouching(TomatoPile.class)) {
                    setMemory(1);
                    if (!isTouching(Greep.class)) {
                        setMemory(2);
                    }
                }
                if ((seePaint("red") || seePaint("orange")) && randomChance(10)) {
                    turnHome();
                    turn(180);
                }

                move();
                break;
            case 1:
                if (randomChance(turnHome_chance)) {
                    turnHome();
                }
                move();
                spit("purple");
                if ((seePaint("red") || seePaint("orange")) && randomChance(10)) {
                    setMemory(0);
                }
                if (atShip()) {
                    setMemory(0);
                    turn(180);
                }
                break;
            case 2:
                final Actor tomatoes = getOneIntersectingObject(TomatoPile.class);
                if (tomatoes != null) {
                    turnTowards(tomatoes.getX(), tomatoes.getY());
                }
                move();
                break;
            case 3:
                if (randomChance(turnHome_chance)) {
                    turnHome();
                }
                if (atShip()) {
                    dropTomato();
                    turn(180);
                    setMemory(0);
                }

                move();
                if (getFlag(1)) {
                    spit("red");
                }
                if (!getFlag(1)) {
                    spit("orange");
                }
                break;
        }

        loadTomato();
        if (carryingTomato()) {
            setMemory(3);
        }
    }

    public void turnAtWaterEdge()
    {
        if (atWater() || atWorldEdge()) {
            if (getFlag(1)) {
                if (seePaint("orange") && !carryingTomato()) {
                    turn(-(Greenfoot.getRandomNumber(70) + 10));
                } else {
                    turn(Greenfoot.getRandomNumber(70) + 10);
                }
            } else {
                if (seePaint("orange") && !carryingTomato()) {
                    turn(Greenfoot.getRandomNumber(70) + 10);
                } else {
                    turn(-(Greenfoot.getRandomNumber(70) + 10));
                }
            }
            if (randomChance(2)) {
                if (!getFlag(1)) {
                    setFlag(1, true);
                    if (seePaint("orange") && randomChance(60)) {
                        setFlag(1, false);
                    }
                } else {
                    setFlag(1, false);
                    if (seePaint("red") && randomChance(60)) {
                        setFlag(1, true);
                    }
                }
            }
        }
    }

    /**
     * This method specifies the name of the author (for display on the result board).
     */
    public static String getAuthorName()
    {
        return "Shaw Kagawa";  // write your name here!
    }


    /**
     * This method specifies the image we want displayed at any time. (No need
     * to change this for the competition.)
     */
    public String getCurrentImage()
    {
        if(carryingTomato())
            return "greep-with-food.png";
        else
            return "greep.png";
    }
}

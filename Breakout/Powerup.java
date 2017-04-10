import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Powerup extends Actor {
    private String type;
    public Powerup(String type) {
        super();
        this.type = type;
        if (type.equals("bulldozer")) setImage("blue-draught.png");
        if (type.equals("explode")) setImage("yellow-draught.png");
    }
    
    public void spawn() {
        boolean intersects = true;
        while (intersects) {
            intersects = false;
            setLocation(
                (int)(Math.random() * getWorld().getWidth() * 0.8 + getWorld().getWidth() * 0.1),
                (int)(Math.random() * getWorld().getHeight() * 0.8 + getWorld().getHeight() * 0.1)
            );
            if (isTouching(null)) intersects = true;
        }
    }
    
    public String getType() {
        return type;
    }
}

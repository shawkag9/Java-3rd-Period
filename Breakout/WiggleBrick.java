import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class WiggleBrick extends MovingBrick {
    private static boolean wiggle;
    private int level;
    
    public WiggleBrick(int level, String color, int dx, int dy) {
        super(level, color, dx, dy);
        wiggle = false;
    }
    
    public void act() {
        super.act();
        if (wiggle && Math.random() * 100 < 1) {
            dx = (int)(Math.random() < 0.5 ? Math.random() + 1 : -Math.random() - 1);
            dy = (int)(Math.random() < 0.5 ? Math.random() + 1 : -Math.random() - 1);
            System.out.println(dx + ", " + dy);
        }
        
    }
    
    public void setWiggle(boolean w) {
        wiggle = w;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MovingBrick extends Brick {
    protected static boolean acting;
    protected int dx;
    protected int dy;
    private int level;
    
    public MovingBrick(int level, String color, int dx, int dy) {
        super(level, color);
        acting = false;
        this.dx = dx;
        this.dy = dy;
    }
    
    public void act() {
        if (acting) {
            if (getX() > getWorld().getWidth() - getImage().getWidth()/2) {
                setLocation(getImage().getWidth()/2, getY());
            }
            if (getX() < getImage().getWidth()/2) {
                setLocation(getWorld().getWidth() - getImage().getWidth()/2, getY());
            }
            
            if (getY() < getImage().getHeight()/2) {
                setLocation(getX(), getWorld().getHeight() - getImage().getHeight()/2);
            }
            if (getY() > getWorld().getHeight() - getImage().getHeight()/2) {
                setLocation(getX(), getImage().getHeight()/2);
            }
            
            setLocation(getX() + dx, getY() + dy);
        }
    }
    
    public void setDx(int dx) {
        this.dx = dx;
    }
    public void setDy(int dy) {
        this.dy = dy;
    }
    
    public void setActing(boolean a) {
        acting = a;
    }
    public boolean getActing() {
        return acting;
    }
}

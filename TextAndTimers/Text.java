import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Text extends Actor {
    private String text;
    private int size;
    private Timer timer;
    private Color foreground;
    private Color background;
    
    /**
     * Creates a new text actor with String text, int size.
     */
    public Text(String text, int size) {
        this.text = text;
        this.size = size;
        this.foreground = Color.BLACK;
        this.background = new Color(0, 0, 0, 0);
        updateImage(text, size);
    }
    /**
     * Creates a new text actor with String text, int size, Color foreground, Color background.
     */
    public Text(String text, int size, Color foreground, Color background) {
        this.text = text;
        this.size = size;
        this.foreground = foreground;
        this.background = background;
        updateImage(text, size, foreground, background);
    }
    
    public void updateImage(String text, int size) {
        setImage(new GreenfootImage(text, size, this.foreground, this.background));
    }
    public void updateImage(String text, int size, Color foreground, Color background) {
        setImage(new GreenfootImage(text, size, foreground, background));
    }
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
        updateImage(this.text, this.size);
    }
    
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
        updateImage(this.text, this.size);
    }
}

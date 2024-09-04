import java.awt.Color;
import java.awt.Graphics;

// Code that implements a projectile
public class Projectile {

    // Instance variables
    private int x, y;
    private int width, height;
    private Color red;
    private boolean fired; // Checks if projectile is fired

    // Constructor
    public Projectile(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.width = 10;
        this.height = 10;

        this.red = new Color(255, 0, 0);
        fired = false;
        
    }

    // Draws the projectile only if fired is true.
    public void draw(Graphics g) {
        if (fired) {
            g.setColor(red);
            g.fillOval(x, y, width, height);
        }
    }

    // Move the projectile up
    public void moveUp() {
        y -= 25;
    }

    // Move the projectile down
    public void moveDown() {
        y += 25;
    }

    // Move the projectile to the right
    public void move() {
        if (fired) {
            x += 20;
        }
    }

    // Reset the projectile's location and visibility to prevent enemies hitting the unshot projectile and disappearing.
    public void reset(int x, int y) {
        fired = false;
        this.x = x;
        this.y = y;
    }

    // Return the x coordinate
    public int getX() {
        return x;
    }
    
    // Return y
    public int getY() {
        return y;
    }

    // Return width
    public int getWidth() {
        return width;
    }

    // Return height
    public int getHeight() {
        return height;
    }

    // Returns fired
    public boolean ifFired() {
        return fired;
    }

    // Sets fired boolean
    public void setFired(boolean fired) {
        this.fired = fired;
    }
    
}

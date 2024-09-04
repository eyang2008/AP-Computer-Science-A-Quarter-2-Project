import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

// Code that implements an enemy
public class Enemy {
    // Private instance variables
    private int x, y;
    private int resetX, resetY;
    private int width, height;
	private BufferedImage myEnemy;
    // Is the enemy visible
    private boolean visible;
    // Used later to move the enemy up and down at specific times.
    int timing = 0;

    // Constructor that initializes instance variables, input parameters x and y.
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;

        this.resetX = x;
        this.resetY = y;

        this.width = 30;
        this.height = 30;

        try {
			myEnemy = ImageIO.read(new File("dustsprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        visible = true;
    }

    // Draws an enemy
    public void draw(Graphics g) {
        if (visible) {
            g.drawImage(myEnemy, x, y, width, height, null);
        }
        
    }

    // Return the x-coordinate
    public int getX() {
        return x;
    }

    // Return the y-coordinate
    public int getY() {
        return y;
    }

    // Return the width
    public int getWidth() {
        return width;
    }

    // Return the height
    public int getHeight() {
        return height;
    }

    // Set visible to false
    public void disappear() {
        visible = false;
    }

    // Return visible
    public boolean getVisible() {
        return visible;
    }
    // Move the enemies
    public void move(int level) {
        int direction;  // Used to determine if the enemy will move up or down -- 1 = up, 2 = down.
        int speed = 1;
        if (level == 2) {
            speed = 2;
        } else if (level == 3) {
            speed = 3;
        }
        x-=speed;
        timing++;
        if (timing == 40) {
            direction = (int)(Math.random()*2+1);
            if (direction == 1) {
                if (getY() < 0) {
                    y+= 10;
                } else {
                    y -= 10;
                }
            } else if (direction == 2) {
                if (y > 570) {
                    y -= 10;
                } else {
                    y += 10;
                }
            }
            timing = 0;
        }
    }

    // Resets the variables of the enemy.
    public void reset() {
        visible = true;
        x = resetX;
        y = resetY;
    }
}
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class FinalEnemy {
    // private instance variables
    private int x, y;
    private int width, height;
	private BufferedImage noFace;
    // is the enemy visible
    private boolean visible;
    // used later to move the enemy up and down at specific times
    int timing = 0;

    public FinalEnemy(int x, int y) {
        this.x = x;
		this.y = y;

        width = 75;
		height = 100;
        visible = true;
		try {
			noFace = ImageIO.read(new File("noface.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    // draws the final enemy
    public void draw(Graphics g) {
		if (visible) {
			g.drawImage(noFace, x, y, width, height, null);
		}
	}

    // return the x-coordinate
    public int getX() {
        return x;
    }

    // return the y-coordinate
    public int getY() {
        return y;
    }

    // return the width
    public int getWidth() {
        return width;
    }

    // return the height
    public int getHeight() {
        return height;
    }

    // set visible to false
    public void disappear() {
        visible = false;
    }

    // return visible
    public boolean getVisible() {
        return visible;
    }

    // move the enemy
    public void move() {
        int direction;  // used to determine if the enemy will move up or down -- 1 = up, 2 = down
        int speed = 4;
        x-=speed;
        timing++;
        if (timing == 60) {
            direction = (int)(Math.random()*2+1);
            if (direction == 1) {
                if (getY() < 10) {
                    y += 75;
                } else {
                    y -= 75;
                }
            } else if (direction == 2) {
                if (getY() > 480) {
                    y -= 75;
                } else {
                    y += 75;
                }
            }
            timing = 0;
        }
    }

    // resets the variables of the enemy
    public void reset(int x, int y) {
        // sets the location of the final enemy to a random spot in the screen
        this.x = x;
        this.y = y;
        visible = true;
    }
}
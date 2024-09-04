import java.awt.Color;
import java.awt.Graphics;

public class Cloud {
    // Instance variables
    private int x, y, width, height, level;
    Color cloudColor1, cloudColor2, cloudColor3;

    public Cloud(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;     // Used to change the color of the clouds each level.

        this.width = 19;
        this.height = 13;
        cloudColor1 = new Color(247, 210, 223);
        cloudColor2 = new Color(204, 191, 219);
        cloudColor3 = new Color(92, 108, 156);
    }

    // Draws the cloud with its corresponding color depending on the level.
    public void drawCloud(Graphics g, int level) {
        this.level = level;
        if (level == 1) {
            g.setColor(cloudColor1);
        } else if (level == 2) {
            g.setColor(cloudColor2);
        } else if (level == 3) {
            g.setColor(cloudColor3);
        }
        g.fillOval(x, y, width, height);
        g.fillOval(x+8, y-8, width, height);
        g.fillOval(x+16, y, width, height);
    }

    public void moveCloud() {
        /* If the cloud is about to fall off the right side
           of the screen, reset x to it's starting position. */

        /* Move the clouds individually so that the first cloud to move off screen doesn't have to wait
           until all the clouds have moved off to respawn. */
        for (int i = 0; i <= 3; i++) {
            if (x < -100) {
                x = 800;
            } else {
                x--;        // Move the cloud one to the left, since the ship is moving to the right.
            }
        }
    }
}
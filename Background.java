import java.awt.Color;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Background {
    // Instance variables
    private Color skyDark1, skyLight1, skyDark2, skyLight2, skyDark3, skyLight3;    // Used for creating the gradient
    private Graphics2D g2d;
    private GradientPaint firstSky, secondSky, thirdSky;
    private int level;

    public Background(int level) {
        this.level = level;
        skyDark1 = new Color(247, 171, 178);
        skyLight1 = new Color(245, 218, 220);
        skyDark2 = new Color(159, 120, 191);
        skyLight2 = new Color(224, 209, 237);
        skyDark3 = new Color(48, 56, 117);
        skyLight3 = new Color(128, 136, 196);
        firstSky = new GradientPaint(0, 0, skyDark1, 0, 500, skyLight1);
        secondSky = new GradientPaint(0, 0, skyDark2, 0, 500, skyLight2);
        thirdSky = new GradientPaint(0, 0, skyDark3, 0, 500, skyLight3);
    }

    // Draws the background with its corresponding color theme.
    public void drawBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        if (level == 1) {
            g2d.setPaint(firstSky);
            g2d.fillRect(0, 0, 800, 600);
        } else if (level == 2) {
            g2d.setPaint(secondSky);
            g2d.fillRect(0, 0, 800, 600);
        } else if (level == 3) {
            g2d.setPaint(thirdSky);
            g2d.fillRect(0, 0, 800, 600);
        }
    }
    
}

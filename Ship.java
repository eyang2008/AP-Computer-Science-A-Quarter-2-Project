import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;


public class Ship {
	// Instance variables
	private int x, y, width, height, resetX, resetY;
	private BufferedImage myShip;
	private Projectile pObj;
	private boolean isVisible;

	// Constructor that initializes instance variables
	public Ship(int x, int y) {
		this.x = x;
		this.y = y;
		this.resetX = x;
		this.resetY = y;

		width = 100;
		height = 60;
		isVisible = true;
		pObj = new Projectile(x, y);
		pObj.setFired(false);
		try {
			myShip = ImageIO.read(new File("spaceship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Draw the ship
	public void draw(Graphics g) {
		// Check to see if projectile should be drawn
		if (pObj.ifFired()) {
			pObj.draw(g);
		}
		if (isVisible) {
			g.drawImage(myShip, x, y, width, height, null);
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

	// Move the ship up
	public void moveUp() {
		y -= 25;
		if (pObj.ifFired() == false) {
			pObj.reset(x+50,y+24);
		}
	}

	// Move the ship down
	public void moveDown() {
		y += 25;
		if (pObj.ifFired() == false) {
			pObj.reset(x+50,y+24);
		}
	}

	public void fire() {
		pObj.setFired(true);
	}

	public void disappear() {
		isVisible = false;
	}

	public void reset() {
		x = resetX;
		y = resetY;
		pObj.reset(resetX, resetY);
		isVisible = true;
	}

	public void moveProjectile(int limit) {
        if (pObj.ifFired()) {
            pObj.move();
        }
        // If the projectile went to the end of the JPanel.
		if (pObj.getX() >= limit) {
			pObj.reset(x+50,y+24);
			pObj.setFired(false);
		}
    }

	// Check to see if the projectile has hit an enemy
	public boolean checkProjectileCollision(Enemy eObj){
		if( eObj.getVisible() && pObj.ifFired()) { // Only calls checkCollision when the enemy and projectile are visible.
			// Declare variables
			int pX = pObj.getX();
			int pY = pObj.getY();
			int pWidth = pObj.getWidth();
			int pHeight = pObj.getHeight();

			int eX = eObj.getX();
			int eY = eObj.getY();
			int eWidth = eObj.getWidth();
			int eHeight = eObj.getHeight();
			
			// The following conditions were changed from the ones shown in the class demo.
				// I found that the class demo version led to some bugs, so I changed a few things.
			if (((eX >= pX && eX-pX <= pWidth) || 
				(pX >= eX) && (pX-eX <= eWidth)) &&
				((eY >= pY && eY-pY <= pHeight) ||
				(pY >= eY && pY-eY <= eHeight))) {
					eObj.disappear();
					pObj.reset(x+50, y+24);
					pObj.setFired(false);
					return true;
			}
		}
		return false;
	}

	// Check to see if the projectile has hit the final enemy.
		// A separate method is needed for this since the final enemy is not of the same class as the regular enemies.
	public boolean checkProjectileCollisionFinal(FinalEnemy eObj){
		if( eObj.getVisible() && pObj.ifFired()) { // Only calls checkCollision when the enemy and projectile are visible.
			// Declare variables
			int pX = pObj.getX();
			int pY = pObj.getY();
			int pWidth = pObj.getWidth();
			int pHeight = pObj.getHeight();

			int eX = eObj.getX();
			int eY = eObj.getY();
			int eWidth = eObj.getWidth();
			int eHeight = eObj.getHeight();

			// The following conditions were changed from the ones shown in the class demo.
				// I found that the class demo version led to some bugs, so I changed a few things.
			if (((eX >= pX && eX-pX <= pWidth) || 
				(pX >= eX) && (pX-eX <= eWidth)) &&
				((eY >= pY && eY-pY <= pHeight) ||
				(pY >= eY && pY-eY <= eHeight))) {
					eObj.disappear();
					pObj.reset(x+50, y+24);
					pObj.setFired(false);
					return true;
			}
		}
		return false;
	}

	// Checks for a collision with an enemy.
	public boolean hasHitShip(Enemy enemyObj, Ship myShip) {
        if (enemyObj.getVisible() == true) {
            // Declare variables
            int eX = enemyObj.getX();
            int eY = enemyObj.getY();
			int eWidth = enemyObj.getWidth();
			int eHeight = enemyObj.getHeight();

            int shipX = myShip.getX();
            int shipY = myShip.getY();
            int shipWidth = myShip.getWidth();
            int shipHeight = myShip.getHeight();

			// The following conditions were changed from the ones shown in the class demo.
				// I found that the class demo version led to some bugs, so I changed a few things.
			if (((eX >= shipX && eX-shipX <= shipWidth) || 
				(shipX >= eX) && (shipX-eX <= eWidth)) &&
				((eY >= shipY && eY-shipY <= shipHeight) ||
				(shipY >= eY && shipY-eY <= eHeight))) {
					enemyObj.reset();
					return true;
			}
        }
        return false;
    }

	// Checks for a collision with the final enemy.
		// A separate method is needed for this, as the final enemy is not of the same class as the regular enemies.
	public boolean hasHitShip(FinalEnemy enemyObj, Ship myShip) {
        if (enemyObj.getVisible() == true) {
            // Declare variables
            int eX = enemyObj.getX();
            int eY = enemyObj.getY();
			int eWidth = enemyObj.getWidth();
			int eHeight = enemyObj.getHeight();

            int shipX = myShip.getX();
            int shipY = myShip.getY();
            int shipWidth = myShip.getWidth();
            int shipHeight = myShip.getHeight();

			// The following conditions were changed from the ones shown in the class demo.
				// I found that the class demo version led to some bugs, so I changed a few things.
            if (((eX >= shipX && eX-shipX <= shipWidth) || 
				(shipX >= eX) && (shipX-eX <= eWidth)) &&
				((eY >= shipY && eY-shipY <= shipHeight) ||
				(shipY >= eY && shipY-eY <= eHeight))) {
					int x = (int)(Math.random()*500+300);
					int y = (int)(Math.random()*500);
                    enemyObj.reset(x, y);
					return true;
			}
        }
        return false;
    }
}

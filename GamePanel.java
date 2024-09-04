import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

// Import KeyListener classes
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements KeyListener {

	// Instance variables
	private Ship ship;
	private Background backgroundObj1, backgroundObj2, backgroundObj3;
	private Cloud[] clouds;
	private Enemy[] enemies, enemies2;
	private FinalEnemy finalEnemy;
	private Font font, winner;
	private Color textColor;
	private int score, scoreLevel2, finalEnemyScore;
	private int lives;
	private int level;
	private boolean hasPassedOne, hasPassedTwo, hasWon;
	
	public GamePanel() {
		score = 0;
		level = 1;
		lives = 3;
		scoreLevel2 = 0;	// Ensures that tester key still allows level two to be played.
		finalEnemyScore = 0;	// This variable will be used so that player must shoot final enemy multiple times to win.
		hasPassedOne = false;
		hasPassedTwo = false;
		hasWon = false;
		// Instantiate each background for each level
		backgroundObj1 = new Background(1);
		backgroundObj2 = new Background(2);
		backgroundObj3 = new Background(3);
		clouds = new Cloud[75];
		enemies = new Enemy[3];	// Enemies array for level 1 has 3 enemies
		enemies2 = new Enemy[5];		// Enemies array for level 2 has 5 enemies 
        for (int i = 0; i < clouds.length; i++) {
            int x = (int)(Math.random()*1600+750);
            int y = (int)(Math.random()*600);
            clouds[i] = new Cloud(x, y, level);
        }
		ship = new Ship(50, 300);	// Player's character
		// Instantiate each enemy
		enemies[0] = new Enemy(750, 100);
		enemies[1] = new Enemy(750, 300);
		enemies[2] = new Enemy(750, 500);
		enemies2[0] = new Enemy(600, 100);
		enemies2[1] = new Enemy(700, 230);
		enemies2[2] = new Enemy(600, 320);
		enemies2[3] = new Enemy(700, 400);
		enemies2[4] = new Enemy(600, 550);
		// CHALLENGE OPTION 5: FINAL ENEMY
			// The final enemy is much faster and is an instant kill for the player.
			// The player must shoot and hit the final enemy 3 times in order to win the game.
			// However, if the enemy reaches the end or hits the player, the game resets to level 1.
		finalEnemy = new FinalEnemy(600, 200);
		
        setFocusable(true); 

		// Text in the GamePanel
		font = new Font("Arial", Font.PLAIN, 20);
		winner = new Font("Arial", Font.PLAIN, 50);
		textColor = new Color(0, 0, 0);
		score = 0;

		// Set up to listen for key clicks
		addKeyListener(this);
	}

	@Override
	public Dimension getPreferredSize() {
		//Sets the size of the panel
        return new Dimension(800,600);
	}
	
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
		if (level == 1) {
			backgroundObj1.drawBackground(g);
		}
		if (level == 2 && hasPassedOne ) {
			backgroundObj2.drawBackground(g);
		}
		if (level == 3 && hasPassedTwo) {
			backgroundObj3.drawBackground(g);
		}

		// Draw the score, lives, and level
		g.setFont(font);
		g.setColor(textColor);
		g.drawString("Score: " + score, 20, 30);
		g.drawString("Lives: " + lives, 20, 60);
		g.drawString("LEVEL: " + level, 350, 20);
		// Draw the game objects
		// Draw enemies and clouds in level 1
		if (level == 1) {
			for (Cloud each : clouds) {
            	each.drawCloud(g, level);
			}
			for (Enemy each : enemies) {
				each.draw(g);
			}
			// Draw the ship
			ship.draw(g);
		}

		// Draw enemies and clouds in level 2 -- more enemies than in level 1
		if (level == 2) {
			// Draw the clouds with the new color
			for (Cloud each : clouds) {
            	each.drawCloud(g, level);
			}
			// Draw the enemies
			for (Enemy each : enemies2) {
				each.draw(g);
			}
			ship.draw(g);
		}

		if (level == 3) {
			// Draw the clouds with the new color
			for (Cloud each : clouds) {
            	each.drawCloud(g, level);
			}
			if (hasWon && finalEnemyScore == 3) {	// If the user has won, pop up with a "you win" message.
				g.setColor(textColor);
				g.setFont(winner);
				g.drawString("YOU WIN!", 250, 250);
			}
			ship.draw(g);
			finalEnemy.draw(g);
		}
	} 

	public void resetLevel() {
		// This function is used to reset the entire game.
		// Resets the enemy positions and visibility once the level is reached.
		// This is to ensure that when the player loses all 3 lives, each level has been reset to its default settings.
		ship.reset();
		for (Enemy each : enemies) {
			each.reset();
		}
		for (Enemy each : enemies2) {
			each.reset();
		}
		int x = (int)(Math.random()*500+300);
		int y = (int)(Math.random()*500);
		finalEnemy.reset(x, y);
		hasPassedOne = false;
		hasPassedTwo = false;
		hasWon = false;
		level = 1;
		score = 0;
		scoreLevel2 = 0;
		finalEnemyScore = 0;
		lives = 3;
	}
	
	public void keyPressed(KeyEvent e){

		// Interpret the key pressed
		if (e.getKeyCode() == 38) {		// Up arrow was pressed
			if (level == 1 || level == 2 || level == 3) {
				ship.moveUp();
			}
		} 

		if (e.getKeyCode() == 40) {	// Down arrow was pressed
			if (level == 1 || level == 2 || level == 3) {
				ship.moveDown();
			}
		}

		if (e.getKeyCode() == 32) {		// The space bar was pressed
			if (level == 1 || level == 2 || level == 3) {
				ship.fire();	// Turn on fire boolean
			}
		}

		if (e.getKeyCode() == 79) {		
			// The testing 'o' key that allows the player to skip to the next level,
			// either moving to next level or ending the game.
			if (level == 1) {
				hasPassedOne = true;
				level = 2;
			} else if (level == 2) {
				hasPassedTwo = true;
				level = 3;
			} else if (level == 3) {
				finalEnemyScore = 3;
				hasWon = true;
				finalEnemy.disappear();
			}
		}
	}

	public void animate() {
		
		while (!hasWon) {
			// Wait for .01 second
			try {
			    Thread.sleep(10);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}

			// Move the ship's projectile
			ship.moveProjectile(800);

			// Move the clouds continuously left at all times.
			for (Cloud each : clouds) {
				each.moveCloud();
			}
			
			// Move the enemies in each level.
			// Also checks if enemy has made it all the way across the screen. If so, player loses a life.
			// In level 3, if the final enemy reaches all the way across the screen, it's an instant kill and the game restarts.
			if (level == 1) {
				for (Enemy each : enemies) {
					each.move(level);
					if (each.getVisible() && each.getX() <= 0) {
						each.reset();
						lives--;
					}
				}
			}
			if (level == 2) {
				for (Enemy each : enemies2) {
					each.move(level);
					if (each.getVisible() && each.getX() <= 0) {
						each.reset();
						lives--;
					}
				}
			}
			if (level == 3) {
				finalEnemy.move();
				if (finalEnemy.getX() <= 0) {
					lives-=3;
				}
			}

			// Check for collisions
			if (level == 1) {
				for (Enemy each : enemies) {
					if (ship.checkProjectileCollision(each)) {
						score++;		// Later, once score reaches 3, enter the next level.
						each.disappear();
					}
				}
			}
			if (level == 2) {
				for (Enemy each : enemies2) {
					if (ship.checkProjectileCollision(each)) {
						score++;		// Later, once score reaches 8, enter the next level.
						scoreLevel2++;
						each.disappear();
					}
				}
			}
			if (level == 3) {
				if (ship.checkProjectileCollisionFinal(finalEnemy)) {
					finalEnemyScore++;
					score++;
					if (finalEnemyScore == 3) {
						hasWon = true;
					}
					// Randomizes where the final enemy will reappear again.
					int x = (int)(Math.random()*500+300);
					int y = (int)(Math.random()*400+10);
					finalEnemy.reset(x, y);
				}
			}

			// Checks if enemy has hit the ship
			if (level == 1) {
				for (Enemy each : enemies) {
					if (ship.hasHitShip(each, ship)) {
						lives--;
						for (Enemy each2 : enemies) {
							// When player loses 1 life, the remaining enemies restart on the right-hand side.
							if (each2.getVisible()) {
								each2.reset();
							}
						}
					}
				}
			}
			if (level == 2) {
				for (Enemy each : enemies2) {
					if (ship.hasHitShip(each, ship)) {
						lives--;
						for (Enemy each2 : enemies2) {
							// When player loses 1 life, the remaining enemies restart on the right-hand side.
							if (each2.getVisible()) {
								each2.reset();
							}
						}
					}
				}
			}
			if (level == 3) {
				if (ship.hasHitShip(finalEnemy, ship)) {
					hasWon = false;
					resetLevel();
				}
			}

			// CHECKS IF LIVES = 0 --> RESTART GAME BY CALLING A FUNCTION THAT RESETS IT
			if (lives <= 0) {
				resetLevel();
			}

			// Checks the score -- if qualifies, move to next level.
			if (level == 1 && score == 3) {
				// Checks if score has reached 3 and sets level to 2.
				for (Enemy each : enemies) {
					each.disappear();
				}
				level = 2;
				hasPassedOne = true;
			}

			if (level == 2 && scoreLevel2 == 5) {
				// Checks if score has reached 8 and sets level to 3 -- big boss.
				level = 3;
				hasPassedTwo = true;
			}

			if (level == 3 && finalEnemyScore == 3) {
				// Checks if player has hit final enemy 3 times -- shows you win screen.
				finalEnemy.disappear();
				ship.disappear();
				hasWon = true;
				repaint();
			}

			if (hasWon) {
				hasPassedOne = true;
				hasPassedTwo = true;

			}

			// Repaint the graphics
			repaint();
		}
	}

	// You must have method signatures for all methods that are
	// part of an interface.
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	

}


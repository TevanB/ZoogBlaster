import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;

public class Splataze extends JPanel implements ActionListener {
	int timerCounter=0;
	int splatterAnimIndex=0;
	int shotAnimIndex=0;
	char key='s';
	int keyCode=0;
	int shootKey;
	Font font;
	Color playerColor;
	int x1;
	int y1;
	int x2;
	int y2;
	int SPIntervalCounter=0;
	int levelNum=9;
	boolean intersectCD = false;
	boolean shotCD = true;
	boolean moveControl = false;
	boolean runCheck = false;
	boolean enemiesCanMove = true;
	boolean frenzy = false;
	// used for playground size slider
	int movementSpace = 10;
	int enemySpeed = 2;
	int playerSpeed = 10;
	boolean buttonCoordinator = false;
	int playerX = 10;
	int playerY = 30;
	int splatAnimTimer = 0;
	int mouseX;
	int mouseY;
	int direction = 0;
	int enemyAmount;
	int enemyCollAmount;
	int trapAmount = 0;
	int bossX;
	int bossY;
	int bossHealth = 3;
	int bossAmount = 1;
	Graphics g;
	BufferedImage bImage;

	JLabel label;
	JLabel sliderLabel;
	JLabel psTextLabel;
	JLabel esTextLabel;
	JLabel enemySpeedLabel;
	JButton changeButton;
	JButton playgroundMode;
	JSlider slider;
	JSlider sliderSpeed;
	JSlider enemySpeedSlider;
	JOptionPane endGame;
	JFrame frame;
	JPanel sliderP;
	JPanel sliderLabelP;
	JPanel changeButtonP;
	JPanel speedP;
	JPanel speedLabelP;
	JPanel enemySpeedLabelP;
	JPanel enemySpeedSliderP;
	JPanel splatAlivePanel;
	JPanel splatCountPanel;
	JPanel liveCountPanel;
	JPanel levelCountPanel;
	JPanel foodRemainPanel;
	JTextField playerSpeedText;
	JTextField enemySpeedText;
	ImageIcon iIcon;
	Splatter splatter = new Splatter(enemySpeed);
	Maps map = new Maps();
	Rectangle playerColl;
	Sidebar sideBar = new Sidebar(movementSpace, playerSpeed, enemySpeed, levelNum);
	ArrayList<Splatter> enemies;
	ArrayList<Image> splatAnim;
	Ammo ammo;
	ArrayList<Ammo> shots;
	ArrayList<Monster> bosses;
	ArrayList<Traps> trap;
	ArrayList<Chest> chests;
	ArrayList<SplatMark> splatMark;
	// ArrayList<Rectangle>enemyColl;
	ArrayList<Rectangle> travelPointColl;
	ArrayList<Rectangle> trapColl;
	ArrayList<Rectangle> bossColl;
	ArrayList<Rectangle> bulletColl;
	Monster monster;
	Player player;
	Chest chest;
	Timer timer;
	Timer myTimer1;
	Timer myTimer2;
	Timer intersectCooldown;
	Timer focusTimer;
	Timer whamTimer;
	Timer tpTimer;
	Timer shootTimer;
	Timer cooldownTimer;
	Timer gracePeriodTimer;
	Timer frenzyTimer;
	Endscreen endscreen;

	ImageIcon backIMG = new ImageIcon(ImageIO.read(Splataze.class.getResourceAsStream("/pixelclothv5.png")));;
	ImageIcon apple = new ImageIcon(ImageIO.read(Splataze.class.getResourceAsStream("/apple.png.png")));

	public Splataze() throws IOException {
		this.setLayout(new BorderLayout());
		font = new Font("Futura Md BT", Font.BOLD, 15);

		player = new Player(30, 30, 10, movementSpace);
		enemies = new ArrayList<Splatter>();
		bosses = new ArrayList<Monster>();
		trap = new ArrayList<Traps>();
		chests = new ArrayList<Chest>();
		shots = new ArrayList<Ammo>();
		bulletColl = new ArrayList<Rectangle>();
		trapColl = new ArrayList<Rectangle>();
		travelPointColl = new ArrayList<Rectangle>();
		splatMark = new ArrayList<SplatMark>();
		ammo = new Ammo(player.playerX, player.playerY, player.direction);

		for (int i = 0; i < 4; i++) {
			travelPointColl.add(new Rectangle(0, 0, 10, 10, 5, Color.MAGENTA));
		}
		guiSetup();

		enemyAmount = 0;
		enemyCollAmount = 0;
		for (int i = 0; i < enemyAmount; i++) {
			Splatter splatter = new Splatter(enemySpeed);
			enemies.add(splatter);
			enemies.get(i).splatterSpeedX = movementSpace;
			enemies.get(i).splatterSpeedY = movementSpace;
			enemies.get(i).movementSpaceSplatter = movementSpace;
			enemies.get(i).splatterX = bossX;
			enemies.get(i).splatterY = bossY;

		}
		for (int i = 0; i < bossAmount; i++) {
			Monster monster = new Monster(500, 300, 30, 30);
			bosses.add(monster);
			bosses.get(i).health = 3 + levelNum / 2;
			;
		}

		// initialize timers

		timer = new Timer(10, this);
		timer.setInitialDelay(10);
		timer.start();

		// timer to space out spacebar splatter splats
		whamTimer = new Timer(800, new ActionListener() {

			public void actionPerformed(ActionEvent ev) {

				player.color = Color.WHITE;
				player.draw(g);

			}

		});
		whamTimer.setInitialDelay(1);
		whamTimer.start();
		frenzyTimer = new Timer(1500, new ActionListener() {

			public void actionPerformed(ActionEvent ev) {

				try {
					increaseEnemy(1);
				} catch (IOException e) {

				}

			}

		});
		frenzyTimer.setInitialDelay(1);
		gracePeriodTimer = new Timer(999999999, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				enemiesCanMove = true;
				gracePeriodTimer.stop();
			}
		});
		gracePeriodTimer.setInitialDelay(3000);
		tpTimer = new Timer(5000, new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				splatter.splatPoint();
				splatter.getRandom();
				for (int i = 0; i < travelPointColl.size(); i++) {
					travelPointColl.get(i).x = splatter.travelPointX[i];
					travelPointColl.get(i).y = splatter.travelPointY[i];
				}
				for (int i = 0; i < trap.size(); i++) {
					trap.get(i).followPoint(splatter.travelPointX, splatter.travelPointY);
					trapColl.get(i).x = trap.get(i).x - 10;
					trapColl.get(i).y = trap.get(i).y - 10;

				}
				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).getRandom();
					enemies.get(i).splatPoint();
				}

			}

		});

		tpTimer.setInitialDelay(1);
		tpTimer.start();

		label = new JLabel();
		bImage = new BufferedImage(1300, 1000, BufferedImage.TYPE_INT_ARGB);
		g = bImage.getGraphics();

		draw(g);

		if (levelNum > 1) {

			for (int i = 0; i < chests.size(); i++) {
				chests.get(i).draw(g);
			}
		}
		add(label);
		setVisible(true);

		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				key = e.getKeyChar();
				keyCode = e.getKeyCode();
				shootKey = e.getKeyCode();
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					if (!runCheck) {
						runCheck = true;
					} else {
						runCheck = false;
					}
				}
				if (shootKey == 38) {
					direction = 0;
				}
				if (shootKey == 40) {
					direction = 2;
				}
				if (shootKey == 37) {
					direction = 3;
				}
				if (shootKey == 39) {
					direction = 1;
				}

				draw(g);
				moveControl = true;

			}

			public void keyReleased(KeyEvent e) {
				moveControl = false;

			}
		});

		changeButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				movementSpace = slider.getValue();

				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).enemyColl.width = slider.getValue() + 20;
					enemies.get(i).enemyColl.height = slider.getValue() + 20;
				}

				// player speed
				int playerSpeedInt = sliderSpeed.getValue();
				player.playerSpeed = playerSpeedInt;
				player.playerColl.speed = playerSpeedInt;

				int enemySpeedInt = enemySpeedSlider.getValue();
				enemySpeed = enemySpeedInt;
				for (int i = 0; i < enemies.size(); i++) {
					enemies.get(i).enemyColl.speed = enemySpeedInt;
				}

			}

		});
		shootTimer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				for (int i = 0; i < shots.size(); i++) {

					if (shots.get(i).direction == 0) {
						shots.get(i).y -= 2;
						bulletColl.get(i).moveUp();
					}

					if (shots.get(i).direction == 1) {
						shots.get(i).x += 2;
						bulletColl.get(i).moveRight();
					}
					if (shots.get(i).direction == 2) {
						shots.get(i).y += 2;
						bulletColl.get(i).moveDown();
					}
					if (shots.get(i).direction == 3) {
						shots.get(i).x -= 2;
						bulletColl.get(i).moveLeft();
					}
					g.drawImage(shots.get(i).shotImage.get(shotAnimIndex), shots.get(i).x - 5, shots.get(i).y - 5, 15,
							15, null);
				}
			}
		});
		cooldownTimer = new Timer(500, new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				shotCD = true;
			}

		});
		cooldownTimer.setInitialDelay(1000);

	}

	public void actionPerformed(ActionEvent evt) {
		enemyAmount = enemies.size();
		if (bosses.size() == 0) {
			if (enemies.size() == 0) {
				try {
					increaseLevel();
				} catch (IOException e1) {
				}
				if (!frenzy) {
					try {
						increaseEnemy(levelNum * 4 / 3);
					} catch (IOException e) {
					}

				}
			}
		}
		sideBar.updateLabel();
		player.color = Color.GREEN;
		if (moveControl) {
			if (runCheck) {

				try {
					movePlayer(g, frame);
				} catch (IOException e) {
				}

			} else {
				if (timerCounter % 5 == 0) {
					try {
						movePlayer(g, frame);
					} catch (IOException e) {
					}
				}
			}
		}

		// seperate frequency of actions by a counter
		timerCounter++;
		drawBackground(g);
		if (levelNum > 1) {
			drawEnemyPoint(g);
		}
		map.drawMapOne(g);
		player.draw(g);
		drawEnemy(g);
		if (splatMark.size() >= 0) {
			for (int i = 0; i < splatMark.size(); i++) {
				splatMark.get(i).draw(g);
			}
		}
		for (int i = 0; i < chests.size(); i++) {
			chests.get(i).draw(g);

		}
		// end game
		if (gameOver(sideBar.foodRemainCount)) {
			endGame = new JOptionPane();
			endGame.showMessageDialog(null, "You Lost  to Zoog! Zag Splat Count: " + sideBar.splatterSplatCount,
					"Quit Game", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

		sideBar.splatterAliveCount = enemies.size();

		for (int i = 0; i < trap.size(); i++) {
			trap.get(i).draw(g);
		}

		for (int i = 0; i < shots.size(); i++) {
			g.setColor(Color.ORANGE);
			g.fillRect(shots.get(i).x, shots.get(i).y, 5, 5);
			g.setColor(Color.GREEN);
			g.drawRect(bulletColl.get(i).x, bulletColl.get(i).y, bulletColl.get(i).width, bulletColl.get(i).height);

		}
		for (int j = 0; j < enemies.size(); j++) {
			for (int i = 0; i < trapColl.size(); i++) {
				if (trapColl.get(i).intersects(enemies.get(j).enemyColl)) {
					enemies.remove(j);
					break;
				}
			}
		}
		// food collision
		for (int i = 0; i < travelPointColl.size(); i++) {
			for (int j = 0; j < enemies.size(); j++) {
				if (travelPointColl.get(i).intersects(enemies.get(j).enemyColl)) {
					enemies.remove(j);

					sideBar.foodRemainCount--;
				}
			}
		}
		// shots collision
		for (int i = 0; i < shots.size(); i++) {
			if (shots.get(i).x <= 0 || shots.get(i).x >= 1000 || shots.get(i).y <= 0 || shots.get(i).y >= 1000) {

				bulletColl.remove(i);
				shots.remove(i);
			}
		}

		for (int j = 0; j < enemies.size(); j++) {
			for (int i = 0; i < bulletColl.size(); i++) {
				if (bulletColl.get(i).intersects(enemies.get(j).enemyColl)) {
					sideBar.splatterSplatCount += 1;
					try {
						SplatMark tempSplat = new SplatMark(enemies.get(j).splatterX, enemies.get(j).splatterY);
						tempSplat.x = enemies.get(j).splatterX;
						tempSplat.y = enemies.get(j).splatterY;
						splatMark.add(tempSplat);
						System.out.println(enemies.get(j).splatterX + " " + enemies.get(j).splatterY);

					} catch (IOException e) {

					}
					System.out.println("You Splatted Bug #" + (j + 1));
					if (enemies.size() == 1) {
						enemies.clear();
						break;
					}

					enemies.remove(j);

					bulletColl.remove(i);
					shots.remove(i);
				}

			}
		}

		for (int i = 0; i < bulletColl.size(); i++) {
			for (int j = 0; j < bosses.size(); j++) {
				if (bulletColl.get(i).intersects(bosses.get(j).rectColl)) {
					if ((bosses.get(j).health) > 0) {
						System.out.println("You Hit A Boss!");
						bosses.get(j).health--;
						shots.remove(i);
						bulletColl.remove(i);
						if (bosses.get(j).health < 1) {
							bosses.clear();
							bossAmount--;
						}
					}

				}
			}
		}

		for (int i = 0; i < bosses.size(); i++) {
			bosses.get(i).draw(g, 3 + levelNum / 2, bosses.get(i).health);
			g.drawImage(bosses.get(i).zoogSprite.get(splatterAnimIndex), bosses.get(i).locX - 30,
					bosses.get(i).locY - 30, bosses.get(i).width + 60, bosses.get(i).height + 60, null);

			bossX = bosses.get(i).locX;
			bossY = bosses.get(i).locY;
		}

		if (timerCounter % 10 == 0) {
			splatterAnimIndex++;
			player.frame++;
			shotAnimIndex++;

		}

		if (splatterAnimIndex == 6) {
			splatterAnimIndex = 0;
		}
		if (shotAnimIndex == 3) {
			shotAnimIndex = 0;
		}
		if (player.frame == 6) {
			player.frame = 0;
		}

		if (timerCounter == 1 || timerCounter == 50) {
			// goes off less often

			for (int i = 0; i < bosses.size(); i++) {
				bosses.get(i).dnaMove();
				if (bosses.get(i).moveCounter == 4) {
					bosses.get(i).dna.geneticMixup(monster.moveCounter, monster.locX, monster.locY);
				}

				if (timerCounter < 20) {
					this.requestFocus();
				}
			}
		}
		if (timerCounter % 2 == 0) {
			// goes off more often
			if (enemiesCanMove) {
				for (int i = 0; i < enemies.size(); i++) {
					splatterAlgorithm(g, enemies, i, enemies.get(i).enemyColl);

				}
			}
		}
		if (timerCounter > 100) {
			timerCounter = 0;
		}
		drawCollision(g);
	}

	public void addTrap(int amount) {
		for (int i = 0; i < amount; i++) {
			trap.add(new Traps(splatter.travelPointX[0], splatter.travelPointY[0], movementSpace, movementSpace));
			trapColl.add(new Rectangle(trap.get(i).x - 10, trap.get(i).y - 10, trap.get(i).w + 35, trap.get(i).h + 35,
					5, Color.MAGENTA));
			trapColl.get(i).width = trap.get(i).w + 20;
			trapColl.get(i).height = trap.get(i).h + 20;
		}
	}

	public void guiSetup() {
		sliderP = new JPanel();
		sliderP.setBackground(Color.GRAY);
		changeButtonP = new JPanel();
		changeButtonP.setBackground(Color.GRAY);
		speedP = new JPanel();
		speedP.setBackground(Color.GRAY);
		speedLabelP = new JPanel();
		speedLabelP.setBackground(Color.GRAY);
		sliderLabelP = new JPanel();
		sliderLabelP.setBackground(Color.GRAY);
		sliderLabel = new JLabel("Global Size");
		sliderLabel.setFont(font);
		enemySpeedLabel = new JLabel("Enemy Speed");
		enemySpeedLabel.setFont(font);
		enemySpeedLabelP = new JPanel();
		enemySpeedLabelP.setBackground(Color.GRAY);
		enemySpeedSliderP = new JPanel();
		enemySpeedSliderP.setBackground(Color.GRAY);
		enemySpeedLabelP.add(enemySpeedLabel);
		splatAlivePanel = new JPanel();
		splatAlivePanel.setBackground(Color.GRAY);
		splatCountPanel = new JPanel();
		splatCountPanel.setBackground(Color.GRAY);
		liveCountPanel = new JPanel();
		liveCountPanel.setBackground(Color.GRAY);
		foodRemainPanel = new JPanel();
		foodRemainPanel.setBackground(Color.GRAY);
		levelCountPanel = new JPanel();
		levelCountPanel.setBackground(Color.GRAY);

		this.setFocusable(true);

		// lives and scores
		splatAlivePanel.add(sideBar.splatAliveLabel());
		foodRemainPanel.add(sideBar.foodCountLabel());
		splatCountPanel.add(sideBar.splatCountLabel());
		liveCountPanel.add(sideBar.livesCountLabel());
		levelCountPanel.add(sideBar.levelCountLabel());
		sideBar.add(levelCountPanel);
		sideBar.add(splatAlivePanel);
		sideBar.add(foodRemainPanel);
		sideBar.add(splatCountPanel);

		// sliders
		slider = new JSlider(SwingConstants.HORIZONTAL, 10, 80, 10);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBackground(Color.GRAY);
		slider.setName("globalSizeSlider");

		// change button
		changeButton = new JButton("Apply Changes");
		changeButton.setFont(font);
		changeButton.setActionCommand("changeButton");
		changeButtonP.add(changeButton);

		// speed slider
		psTextLabel = new JLabel("Player Speed:");
		psTextLabel.setFont(font);
		playerSpeedText = new JTextField(10);
		speedLabelP.add(psTextLabel);

		sliderSpeed = new JSlider(SwingConstants.HORIZONTAL, 10, 80, 10);
		sliderSpeed.setMajorTickSpacing(10);
		sliderSpeed.setMinorTickSpacing(5);
		sliderSpeed.setPaintTicks(true);
		sliderSpeed.setPaintLabels(true);
		sliderSpeed.setBackground(Color.GRAY);
		sliderSpeed.setName("globalSpeedSlider");
		speedP.add(sliderSpeed);

		// enemy speed slider
		enemySpeedSlider = new JSlider(SwingConstants.HORIZONTAL, 2, 10, 2);
		enemySpeedSlider.setMajorTickSpacing(2);
		enemySpeedSlider.setMinorTickSpacing(1);
		enemySpeedSlider.setPaintTicks(true);
		enemySpeedSlider.setPaintLabels(true);
		enemySpeedSlider.setBackground(Color.GRAY);
		enemySpeedSlider.setName("enemySpeedSlider");
		enemySpeedSliderP.add(enemySpeedSlider);

		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		sideBar.add(sliderLabelP);
		sideBar.add(sliderP);
		sideBar.add(speedLabelP);
		sideBar.add(speedP);
		sideBar.add(enemySpeedLabelP);
		sideBar.add(enemySpeedSliderP);
		sideBar.add(changeButtonP);

		this.add(sideBar);

	}

	public void increaseEnemy(int amount) throws IOException {
		enemyAmount = amount;
		for (int j = 0; j < bosses.size(); j++) {
			for (int i = 0; i < amount; i++) {
				Splatter splatter = new Splatter(enemySpeed);
				splatter.splatterSpeedX = movementSpace;
				splatter.splatterSpeedY = movementSpace;
				splatter.movementSpaceSplatter = movementSpace;
				splatter.splatterX = bosses.get(j).locX;
				splatter.splatterY = bosses.get(j).locY;
				splatter.enemyColl.x = splatter.splatterX - 10;
				splatter.enemyColl.y = splatter.splatterY - 10;

				enemies.add(splatter);
			}
		}
	}

	public void increaseLevel() throws IOException {
		levelNum++;
		sideBar.levelNumber++;
		sideBar.foodRemainCount += levelNum * 3 / 12;
		bossAmount++;
		chests.clear();
		splatMark.clear();
		if (frenzy) {
			frenzyTimer.stop();
			frenzy = false;
		}
		if (levelNum % 10 == 0) {
			chests.add(new Chest());
			frenzy = true;
			frenzyTimer.start();
		}
		bosses.add(new Monster(400, 400, 30, 30));

		for (int i = 0; i < bossAmount; i++) {
			bosses.get(i).health = 3 + levelNum / 2;
		}
		enemies.clear();
		shots.clear();
		bulletColl.clear();
		for (int i = 0; i < chests.size(); i++) {
			chests.get(i).setLocation();
			chests.get(i).setCollLoc();
		}
		trap.clear();
	}

	public static void main(String[] args) throws IOException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Splataze window = new Splataze();
				} catch (IOException e) {

				}

			}
		});
	}

	public void drawEnemyPoint(Graphics g) {
		draw(g);
		for (int i = 0; i < 4; i++) {
			g.drawImage(apple.getImage(), splatter.travelPointX[i] - 10, splatter.travelPointY[i] - 10, 35, 35, null);
		}

		iIcon = new ImageIcon(bImage);
		label.setIcon(iIcon);
		repaint();

	}

	public void drawPlayerColl(Graphics g) {
		g.setColor(Color.MAGENTA);

		iIcon = new ImageIcon(bImage);
		label.setIcon(iIcon);
	}

	public void drawEnemy(Graphics g) {
		for (int enemyNumber = 0; enemyNumber < enemies.size(); enemyNumber++) {
			if (enemies.get(enemyNumber).randomColorChoice == 1) {
				g.drawImage(enemies.get(enemyNumber).spritePurp.get(splatterAnimIndex),
						enemies.get(enemyNumber).splatterX - 25, enemies.get(enemyNumber).splatterY - 25, 60, 60, null);
			}
			if (enemies.get(enemyNumber).randomColorChoice == 2) {
				g.drawImage(enemies.get(enemyNumber).spritePink.get(splatterAnimIndex),
						enemies.get(enemyNumber).splatterX - 25, enemies.get(enemyNumber).splatterY - 25, 60, 60, null);
			}
			if (enemies.get(enemyNumber).randomColorChoice == 3) {
				g.drawImage(enemies.get(enemyNumber).spriteGreen.get(splatterAnimIndex),
						enemies.get(enemyNumber).splatterX - 25, enemies.get(enemyNumber).splatterY - 25, 60, 60, null);
			}
			if (enemies.get(enemyNumber).randomColorChoice == 4) {
				g.drawImage(enemies.get(enemyNumber).spriteOrange.get(splatterAnimIndex),
						enemies.get(enemyNumber).splatterX - 25, enemies.get(enemyNumber).splatterY - 25, 60, 60, null);
			}
			if (enemies.get(enemyNumber).randomColorChoice == 5) {
				g.drawImage(enemies.get(enemyNumber).spriteYellow.get(splatterAnimIndex),
						enemies.get(enemyNumber).splatterX - 25, enemies.get(enemyNumber).splatterY - 25, 60, 60, null);
			}
			if (enemies.get(enemyNumber).randomColorChoice == 6) {
				g.drawImage(enemies.get(enemyNumber).spriteRed.get(splatterAnimIndex),
						enemies.get(enemyNumber).splatterX - 25, enemies.get(enemyNumber).splatterY - 25, 60, 60, null);
			}
		}
	}

	public void splatterAlgorithm(Graphics g, ArrayList<Splatter> enemies, int enemyNumber, Rectangle enemyColl) {
		// Basic AI Algorithm (Non-Genetic Algorithm)
		g.setColor(enemies.get(enemyNumber).splatterColor);

		if (splatter.travelPointX[enemies.get(enemyNumber).randomPointChoice] > enemies.get(enemyNumber).splatterX) {
			if (map.collideMapOneRightEnemy(enemies.get(enemyNumber).splatterX, enemies.get(enemyNumber).splatterY)) {
				enemies.get(enemyNumber).splatterX += enemySpeed;
				enemies.get(enemyNumber).enemyColl.moveRight();
			}
		}
		if (splatter.travelPointX[enemies.get(enemyNumber).randomPointChoice] < enemies.get(enemyNumber).splatterX) {
			if (map.collideMapOneLeftEnemy(enemies.get(enemyNumber).splatterX, enemies.get(enemyNumber).splatterY)) {
				enemies.get(enemyNumber).splatterX -= enemySpeed;
				enemies.get(enemyNumber).enemyColl.moveLeft();
			}
		}
		if (splatter.travelPointY[enemies.get(enemyNumber).randomPointChoice] > enemies.get(enemyNumber).splatterY) {
			if (map.collideMapOneDownEnemy(enemies.get(enemyNumber).splatterX, enemies.get(enemyNumber).splatterY)) {
				enemies.get(enemyNumber).splatterY += enemySpeed;
				enemies.get(enemyNumber).enemyColl.moveDown();
			}
		}
		if (splatter.travelPointY[enemies.get(enemyNumber).randomPointChoice] < enemies.get(enemyNumber).splatterY) {
			if (map.collideMapOneUpEnemy(enemies.get(enemyNumber).splatterX, enemies.get(enemyNumber).splatterY)) {
				enemies.get(enemyNumber).splatterY -= enemySpeed;
				enemies.get(enemyNumber).enemyColl.moveUp();
			}
		}

		iIcon = new ImageIcon(bImage);
		label.setIcon(iIcon);

	}

	public void draw(Graphics g) {

		iIcon = new ImageIcon(bImage);
		label.setIcon(iIcon);

		g.setColor(playerColor);

	}

	public void drawCollision(Graphics g) {

		// player.drawColl(g);
		for (int i = 0; i < enemies.size(); i++) {
			// g.drawRect(enemies.get(i).enemyColl.x, enemies.get(i).enemyColl.y,
			// enemies.get(i).enemyColl.width, enemies.get(i).enemyColl.width);
		}
		for (int i = 0; i < bosses.size(); i++) {
			// g.drawRect(bosses.get(i).rectColl.x, bosses.get(i).rectColl.y,
			// bosses.get(i).rectColl.width, bosses.get(i).rectColl.width);
		}
		g.setColor(Color.LIGHT_GRAY);
		if (levelNum > 1) {
			for (int i = 0; i < travelPointColl.size(); i++) {
			}
		}
		iIcon = new ImageIcon(bImage);
		label.setIcon(iIcon);
	}

	public void movePlayer(Graphics g, JFrame frame) throws IOException {

		if (key == 'w') {
			if (player.playerY > 30) {
				if (map.collideMapOneUp(player)) {
					player.playerColl.moveUp();
					player.playerY -= player.playerSpeed;
					player.direction = 0;
				}
			}

		} else if (key == 'a') {
			if (player.playerX > 15) {
				if (map.collideMapOneLeft(player)) {
					player.playerColl.moveLeft();
					player.playerX -= player.playerSpeed;
					player.direction = 3;
				}
			}
		} else if (key == 's') {
			if (player.playerY < 960) {
				if (map.collideMapOneDown(player)) {
					player.playerColl.moveDown();
					player.playerY += player.playerSpeed;
					player.direction = 2;
				}
			}
		} else if (key == 'd') {
			if (player.playerX < 1025) {
				if (map.collideMapOneRight(player)) {
					player.playerColl.moveRight();
					player.playerX += player.playerSpeed;
					player.direction = 1;
				}
			}
		}

		player.draw(g);
		if (keyCode == KeyEvent.VK_UP) {
			if (shotCD) {
				player.direction = direction;
				if (player.color == Color.GREEN) {
					for (int i = 0; i < enemies.size(); i++) {
						if (player.playerColl.intersects(enemies.get(i).enemyColl)) {
							System.out.println("You Splatted Bug #" + (i + 1));
							sideBar.splatterSplatCount += 1;
							enemies.remove(i);
						}
					}

				}
				if (shots.size() == 6) {
					shots.clear();
					bulletColl.clear();
				}
				cooldownTimer.start();
				Ammo shot = new Ammo(player.playerX, player.playerY, direction);
				shots.add(shot);
				Rectangle shotCollision = new Rectangle(shot.x, shot.y, 5, 5, 2, Color.MAGENTA);
				bulletColl.add(shotCollision);
				shotCD = false;
				shootTimer.start();
			}
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			if (shotCD) {
				player.direction = direction;
				if (player.color == Color.GREEN) {
					for (int i = 0; i < enemies.size(); i++) {
						if (player.playerColl.intersects(enemies.get(i).enemyColl)) {
							System.out.println("You Splatted Bug #" + (i + 1));
							sideBar.splatterSplatCount += 1;
							enemies.remove(i);
						}
					}

				}
				if (shots.size() == 6) {
					shots.clear();
					bulletColl.clear();
				}
				cooldownTimer.start();
				Ammo shot = new Ammo(player.playerX, player.playerY, direction);
				shots.add(shot);
				Rectangle shotCollision = new Rectangle(shot.x, shot.y, 5, 5, 2, Color.MAGENTA);
				bulletColl.add(shotCollision);
				shotCD = false;
				shootTimer.start();
			}
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			if (shotCD) {
				player.direction = direction;
				if (player.color == Color.GREEN) {
					for (int i = 0; i < enemies.size(); i++) {
						if (player.playerColl.intersects(enemies.get(i).enemyColl)) {
							System.out.println("You Splatted Bug #" + (i + 1));
							sideBar.splatterSplatCount += 1;
							enemies.remove(i);
						}
					}

				}
				if (shots.size() == 6) {
					shots.clear();
					bulletColl.clear();
				}
				cooldownTimer.start();
				Ammo shot = new Ammo(player.playerX, player.playerY, direction);
				shots.add(shot);
				Rectangle shotCollision = new Rectangle(shot.x, shot.y, 5, 5, 2, Color.MAGENTA);
				bulletColl.add(shotCollision);
				shotCD = false;
				shootTimer.start();
			}
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			if (shotCD) {
				player.direction = direction;
				if (player.color == Color.GREEN) {
					for (int i = 0; i < enemies.size(); i++) {
						if (player.playerColl.intersects(enemies.get(i).enemyColl)) {
							System.out.println("You Splatted Bug #" + (i + 1));
							sideBar.splatterSplatCount += 1;
							enemies.remove(i);
						}
					}

				}
				if (shots.size() == 6) {
					shots.clear();
					bulletColl.clear();
				}
				cooldownTimer.start();
				Ammo shot = new Ammo(player.playerX, player.playerY, direction);
				shots.add(shot);
				Rectangle shotCollision = new Rectangle(shot.x, shot.y, 5, 5, 2, Color.MAGENTA);
				bulletColl.add(shotCollision);
				shotCD = false;
				shootTimer.start();
			}
		}
		if (key == 32) {
			// spacebar
			for (int i = 0; i < chests.size(); i++) {
				if (player.playerColl.intersects(chests.get(i).collRect)) {
					System.out.println("You opened a chest!");
					int rand = (int) (4 * Math.random());
					addTrap(1);

					chests.remove(i);

				}
			}

		}

	}

	public void drawBackground(Graphics g) {
		/*
		 * if(levelNum%10==0) { g.setColor(Color.BLUE); } else {
		 * g.setColor(Color.DARK_GRAY); }
		 */

		g.fillRect(0, 0, 1100, 1000);
		g.setColor(Color.GREEN);
		for (int i = 0; i < 1100; i += 10) {
			for (int j = 0; j < 1000; j += 10) {
				g.drawRect(i, j, 10, 10);
			}
		}
	}

	public boolean gameOver(int foodLeft) {
		if (foodLeft <= 0) {
			return true;
		}
		return false;
	}

	public boolean pointOverrideTest(int i, int j) {
		if (splatter.travelPointX[0] == i && splatter.travelPointY[0] == j) {
			return false;
		}
		if (splatter.travelPointX[1] == i && splatter.travelPointY[1] == j) {
			return false;
		}
		if (splatter.travelPointX[2] == i && splatter.travelPointY[2] == j) {
			return false;
		}
		if (splatter.travelPointX[3] == i && splatter.travelPointY[3] == j) {
			return false;
		}
		if (splatter.splatterX == i && splatter.splatterY == j) {
			return false;
		}
		if (player.playerX == i && player.playerY == j) {
			return false;
		} else {
			return true;
		}
	}

}

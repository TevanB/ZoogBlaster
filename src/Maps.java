import java.awt.*;
import javax.swing.*;

public class Maps {
	int[]mapOneX;
	int[]mapOneY;
	int[]mapTwoX;
	int[]mapTwoY;
	int[]mapThreeX;
	int[]mapThreeY;
	Color wallColor;
	Maps(){
		wallColor=Color.BLACK;
		mapOneX = new int[320];
		mapOneY = new int[320];
	}
	public void drawMapOne(Graphics g) {
		//Total Squares is 320 (Four Overlaps)
		g.setColor(wallColor);
		int arrayCounter=0;
		//A - 35 Squares
		for(int i=50; i<400; i+=10) {
			g.fillRect(50, i, 10, 10);
			mapOneX[arrayCounter]=50;
			mapOneY[arrayCounter]=i;
			arrayCounter++;
		}
		//B - 35 Squares
		for(int i=50; i<400; i+=10) {
			g.fillRect(i, 50, 10, 10);
			mapOneX[arrayCounter]=i;
			mapOneY[arrayCounter]=50;
			arrayCounter++;
		}
		//C - 45 Squares
		for(int i=500; i<950; i+=10) {
			g.fillRect(i, 50, 10, 10);
			mapOneX[arrayCounter]=i;
			mapOneY[arrayCounter]=50;
			arrayCounter++;
		}
		//D - 35 Squares
		for(int i=50; i<400; i+=10) {
			g.fillRect(940, i, 10, 10);
			mapOneX[arrayCounter]=940;
			mapOneY[arrayCounter]=i;
			arrayCounter++;
		}
		//E - 45 Squares
		for(int i=500; i<950; i+=10) {
			g.fillRect(940, i, 10, 10);
			mapOneX[arrayCounter]=940;
			mapOneY[arrayCounter]=i;
			arrayCounter++;
		}
		//F - 45 Squares
		for(int i=500; i<950; i+=10) {
			g.fillRect(i, 940, 10, 10);
			mapOneX[arrayCounter]=i;
			mapOneY[arrayCounter]=940;
			arrayCounter++;		
		}
		//G - 35 Squares
		for(int i=50; i<400; i+=10) {
			g.fillRect(i, 940, 10, 10);
			mapOneX[arrayCounter]=i;
			mapOneY[arrayCounter]=940;
			arrayCounter++;
		}
		//H
		for(int i=500; i<950; i+=10) {
			g.fillRect(50, i, 10, 10);
			mapOneX[arrayCounter]=50;
			mapOneY[arrayCounter]=i;
			arrayCounter++;
		}
		//System.out.println(arrayCounter);
	}
	public boolean collideMapOneUp(Player player) {
		boolean test=true;
		if(player.playerX>40&&player.playerX<400) {
			if(player.playerY==950) {
				test=false;
			}
			if(player.playerY==60) {
				test=false;
			}
		}
		if(player.playerX>500&&player.playerX<950) {
			if(player.playerY==950) {
				test=false;
			}
			if(player.playerY==60) {
				test=false;
			}
		}
		if(player.playerX==940&&player.playerY==400) {
			test=false;
		}
		if(player.playerX==50&&player.playerY==400) {
			test=false;
		}
		return test;
	}
	public boolean collideMapOneDown(Player player) {
		boolean test=true;
		if(player.playerX>40&&player.playerX<400) {
			if(player.playerY==930) {
				test=false;
			}
			if(player.playerY==40) {
				test=false;
			}
		}
		if(player.playerX>490&&player.playerX<950) {
			if(player.playerY==930) {
				test=false;
			}
			if(player.playerY==40) {
				test=false;
			}
		}
		if(player.playerX==940&&player.playerY==490) {
			test=false;
		}
		if(player.playerX==50&&player.playerY==490) {
			test=false;
		}
		return test;
	}
	public boolean collideMapOneLeft(Player player) {
		boolean test=true;
		if(player.playerX==60||player.playerX==950) {
			if(player.playerY>40&&player.playerY<400) {
				test=false;
			}
			if(player.playerY>490&&player.playerY<950) {
				test=false;
			}
			
		}
		if(player.playerX==400) {
			if(player.playerY==50) {
				test=false;
			}
			if(player.playerY==940) {
				test=false;
			}
		}
		if(player.playerX==940&&player.playerY==940) {
			test=false;
		}
		return test;
	}
	public boolean collideMapOneRight(Player player) {
		boolean test=true;
		if(player.playerX==40||player.playerX==930) {
			if(player.playerY>40&&player.playerY<400) {
				test=false;
			}
			if(player.playerY>490&&player.playerY<950) {
				test=false;
			}
			
		}
		if(player.playerX==490&&player.playerY==50) {
			test=false;
		}
		if(player.playerX==490&&player.playerY==940) {
			test=false;
		}
		return test;
	}
	
	public boolean collideMapOneUpEnemy(int playerX, int playerY) {
		boolean test=true;
		if(playerX>40&&playerX<400) {
			if(playerY==950) {
				test=false;
			}
			if(playerY==60) {
				test=false;
			}
		}
		if(playerX>500&&playerX<950) {
			if(playerY==950) {
				test=false;
			}
			if(playerY==60) {
				test=false;
			}
		}
		if(playerX==940&&playerY==400) {
			test=false;
		}
		if(playerX==50&&playerY==400) {
			test=false;
		}
		return test;
	}
	public boolean collideMapOneDownEnemy(int playerX, int playerY) {
		boolean test=true;
		if(playerX>40&&playerX<400) {
			if(playerY==930) {
				test=false;
			}
			if(playerY==40) {
				test=false;
			}
		}
		if(playerX>490&&playerX<950) {
			if(playerY==930) {
				test=false;
			}
			if(playerY==40) {
				test=false;
			}
		}
		if(playerX==940&&playerY==490) {
			test=false;
		}
		if(playerX==50&&playerY==490) {
			test=false;
		}
		return test;
	}
	public boolean collideMapOneLeftEnemy(int playerX, int playerY) {
		boolean test=true;
		if(playerX==60||playerX==950) {
			if(playerY>40&&playerY<400) {
				test=false;
			}
			if(playerY>490&&playerY<950) {
				test=false;
			}
			
		}
		if(playerX==400) {
			if(playerY==50) {
				test=false;
			}
			if(playerY==940) {
				test=false;
			}
		}
		if(playerX==940&&playerY==940) {
			test=false;
		}
		return test;
	}
	public boolean collideMapOneRightEnemy(int playerX, int playerY) {
		boolean test=true;
		if(playerX==40||playerX==930) {
			if(playerY>40&&playerY<400) {
				test=false;
			}
			if(playerY>490&&playerY<950) {
				test=false;
			}
			
		}
		if(playerX==490&&playerY==50) {
			test=false;
		}
		if(playerX==490&&playerY==940) {
			test=false;
		}
		return test;
	}
	
	public void drawMapTwo(Graphics g) {
		g.setColor(wallColor);
	
	}
	public void drawMapThree(Graphics g) {
		g.setColor(wallColor);
	
	}
}

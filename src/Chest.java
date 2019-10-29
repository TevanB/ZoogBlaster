import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Chest {
	int[] xLoc = new int[80];
	int[] yLoc = new int[80];
	int x,y,w,h;
	Rectangle collRect;
	ImageIcon chest;
	Chest(){
		//set possible locations
		int counter=0;
		for(int i=0; i<80; i++) {
			xLoc[i]=100+i*10;
			yLoc[i]=100+i*10;
			counter++;
		}
		w=10;
		h=10;
		setLocation();
		collRect = new Rectangle(x-7,y-7,w+15,h+15,5,Color.MAGENTA);
	}
	public void setLocation(){
		int rand=(int)(81*Math.random());
		int rand1=(int)(2*Math.random());
		if(rand1==0) {
			x=xLoc[rand];
			y=10;
		}
		else {
			y=yLoc[rand];
			x=10;
		}

	}
	public void setCollLoc() {
		collRect.x=this.x-7;
		collRect.y=this.y-7;
	}
	public void draw(Graphics g) {
		try {
			chest=new ImageIcon(ImageIO.read(Chest.class.getResource("/Treasure-1.png.png")));
		} catch (IOException e) {
		}
		//g.setColor(Color.YELLOW);
		//g.fillRect(x, y, w, h);
		g.drawImage(chest.getImage(), x-5, y-5, w+40, h+40, null);
		g.setColor(Color.PINK);
		//g.drawRect(collRect.x, collRect.y, collRect.width, collRect.height);
	}
}

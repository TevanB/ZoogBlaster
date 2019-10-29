import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.*;
import java.io.IOException;
public class Traps {
	int x, y,w,h,choice;
	Rectangle coll;
	ImageIcon trapIMG;
	Traps(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		coll=new Rectangle(x-15, y-15, w+15,h+15,10,Color.MAGENTA);
		choice=(int) (4*Math.random());

	}
	public void followPoint(int[] x, int[] y) {
		choice=(int) (4*Math.random());
		this.x=x[choice];
		this.y=y[choice];
		coll.x=this.x-10;
		coll.y=this.y-10;
		coll.width=this.w+20;
		coll.height=this.h+20;

	}
	public void draw(Graphics g) {
		//g.fillRect(x, y, w, h);
		try {
			trapIMG = new ImageIcon(ImageIO.read(Traps.class.getResourceAsStream("/Apple Vine-1.png.png")));
		} catch (IOException e) {
		}
		//g.drawRect(coll.x, coll.y, coll.width, coll.height);
		g.drawImage(trapIMG.getImage(), coll.x, coll.y, coll.width+10, coll.height+10, null);
	}
	public boolean splatterCollide(ArrayList<Rectangle>enemyColl) {
		
		for(int i=0; i<enemyColl.size();  i++) {
			if(coll.intersects(enemyColl.get(i))) {
				return true;
			}
		}
		return false;
	}
}

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
public class Ammo {
	int x,y,direction;
	Rectangle coll;
	ArrayList<Image>shotImage;
	Ammo(int x, int y, int direction) throws IOException{
		this.x=x;
		this.y=y;
		this.direction=direction;
		coll=new Rectangle(x,y,10,10,2,Color.MAGENTA);
		shotImage = new ArrayList<Image>();
		ImageIcon frame1=new ImageIcon(ImageIO.read(Ammo.class.getResource("/Shot0.png")));
		ImageIcon frame2=new ImageIcon(ImageIO.read(Ammo.class.getResource("/Shot1.png")));
		ImageIcon frame3=new ImageIcon(ImageIO.read(Ammo.class.getResource("/Shot2.png")));
		ImageIcon frame4=new ImageIcon(ImageIO.read(Ammo.class.getResource("/Shot3.png")));
		shotImage.add(frame1.getImage());
		shotImage.add(frame2.getImage());
		shotImage.add(frame3.getImage());
		shotImage.add(frame4.getImage());
	}
	public void drawAmmo(Graphics g, int x, int y, ArrayList<Integer>shots){
		for(int i=0; i<shots.size(); i++) {
			g.setColor(Color.ORANGE);
			g.fillRect(x, y, 5, 5);
			g.setColor(Color.GREEN);
			g.drawRect(coll.x, coll.y, coll.width, coll.height);
		}
	}
	
}

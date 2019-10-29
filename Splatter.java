//ENEMY CLASS
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.*;
import java.io.IOException;
public class Splatter {
	int[]travelPointX = new int[4];
	int[]travelPointY = new int[4];
	int[]randomNumbers = new int[8];
	int[]possiblePoints = new int[100];
	int splatterX;
	int splatterY;
	int splatterSpeedX ;
	int splatterSpeedY;
	int movementSpaceSplatter=10;
	int randomPointChoice;
	int randomColorChoice;
	//ArrayList<Color> colors;
	ArrayList<Image>spritePurp;
	ArrayList<Image>spritePink;
	ArrayList<Image>spriteGreen;
	ArrayList<Image>spriteOrange;
	ArrayList<Image>spriteYellow;
	ArrayList<Image>spriteRed;
	Rectangle enemyColl;
	Color splatterColor;
	Splatter(int enemySpeed) throws IOException{
		splatterColors();
		enemyColl=new Rectangle(splatterX-10,splatterY-10,30,30,enemySpeed,Color.MAGENTA);
		//sets all possible values to work with grid of 10x10
		for(int i=0; i<movementSpaceSplatter*movementSpaceSplatter; i+=1) {
			possiblePoints[i]=i*movementSpaceSplatter;
		}
		randomColorChoice=(int)(6*Math.random()+1);
		
	}
	public void getRandom() {
				for(int i=0; i<randomNumbers.length; i++) {	
					randomNumbers[i]= (int) (100*Math.random());
				}		
	}
	
	public void splatPoint() {
		
		for(int i=0; i<4; i++) {
			travelPointX[i] = possiblePoints[randomNumbers[i]];
		}
		for(int i=0; i<4; i++) {
			travelPointY[i] = possiblePoints[randomNumbers[i+4]];
		}
		randomPointChoice=(int) (4*Math.random());
	}

	public void splatterColors() throws IOException {
		spritePurp=new ArrayList<Image>();
		ImageIcon sprite1=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPurp.gif")));
		ImageIcon sprite2=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPurp2.gif")));
		ImageIcon sprite3=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPurp3.gif")));
		ImageIcon sprite4=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPurp4.gif")));
		ImageIcon sprite5=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPurp5.gif")));
		ImageIcon sprite6=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPurp6.gif")));
		spritePurp.add(sprite1.getImage());
		spritePurp.add(sprite2.getImage());
		spritePurp.add(sprite3.getImage());
		spritePurp.add(sprite4.getImage());
		spritePurp.add(sprite5.getImage());
		spritePurp.add(sprite6.getImage());
		
		spritePink=new ArrayList<Image>();
		sprite1=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink.png")));
		sprite2=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink2.png")));
		sprite3=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink3.png")));
		sprite4=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink4.png")));
		sprite5=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink5.png")));
		sprite6=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink6.png")));

		spritePink.add(sprite1.getImage());
		spritePink.add(sprite2.getImage());
		spritePink.add(sprite3.getImage());
		spritePink.add(sprite4.getImage());
		spritePink.add(sprite5.getImage());
		spritePink.add(sprite6.getImage());
		
		spriteGreen=new ArrayList<Image>();
		sprite1=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug30.png")));
		sprite2=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug31.png")));
		sprite3=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug32.png")));
		sprite4=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug33.png")));
		sprite5=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug34.png")));
		sprite6=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug35.png")));
		spriteGreen.add(sprite1.getImage());
		spriteGreen.add(sprite2.getImage());
		spriteGreen.add(sprite3.getImage());
		spriteGreen.add(sprite4.getImage());
		spriteGreen.add(sprite5.getImage());
		spriteGreen.add(sprite6.getImage());
		
		spriteOrange=new ArrayList<Image>();
		sprite1=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink.png")));
		sprite2=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink2.png")));
		sprite3=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink3.png")));
		sprite4=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink4.png")));
		sprite5=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink5.png")));
		sprite6=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink6.png")));
		spriteOrange.add(sprite1.getImage());
		spriteOrange.add(sprite2.getImage());
		spriteOrange.add(sprite3.getImage());
		spriteOrange.add(sprite4.getImage());
		spriteOrange.add(sprite5.getImage());
		spriteOrange.add(sprite6.getImage());
		
		spriteYellow=new ArrayList<Image>();
		sprite1=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug40.png")));
		sprite2=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug41.png")));
		sprite3=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug42.png")));
		sprite4=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug43.png")));
		sprite5=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug44.png")));
		sprite6=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug45.png")));		
		spriteYellow.add(sprite1.getImage());
		spriteYellow.add(sprite2.getImage());
		spriteYellow.add(sprite3.getImage());
		spriteYellow.add(sprite4.getImage());
		spriteYellow.add(sprite5.getImage());
		spriteYellow.add(sprite6.getImage());
		
		/*spritePink=new ArrayList<Image>();
		sprite1=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink.png")));
		sprite2=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink2.png")));
		sprite3=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink3.png")));
		sprite4=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink4.png")));
		sprite5=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink5.png")));
		sprite6=new ImageIcon(ImageIO.read(Splatter.class.getResource("/flyPink6.png")));
		spritePink.add(sprite1.getImage());
		spritePink.add(sprite2.getImage());
		spritePink.add(sprite3.getImage());
		spritePink.add(sprite4.getImage());
		spritePink.add(sprite5.getImage());
		spritePink.add(sprite6.getImage());
		*/
		spriteRed=new ArrayList<Image>();
		sprite1=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug60.png")));
		sprite2=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug61.png")));
		sprite3=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug62.png")));
		sprite4=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug63.png")));
		sprite5=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug64.png")));
		sprite6=new ImageIcon(ImageIO.read(Splatter.class.getResource("/Bug65.png")));
		spriteRed.add(sprite1.getImage());
		spriteRed.add(sprite2.getImage());
		spriteRed.add(sprite3.getImage());
		spriteRed.add(sprite4.getImage());
		spriteRed.add(sprite5.getImage());
		spriteRed.add(sprite6.getImage());
		
	}
	
	
}

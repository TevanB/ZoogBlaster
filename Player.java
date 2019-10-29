import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.*;
import java.io.IOException;

public class Player {
	Color color;
	Rectangle playerColl;
	int frame=0;
	int collHeight;
	int collWidth;
	//int direction=0;
	ArrayList<Image>playerImageUp;
	ArrayList<Image>playerImageDown;
	ArrayList<Image>playerImageLeft;
	ArrayList<Image>playerImageRight;
	int playerX,playerY,playerSpeed,movementSpace,direction,shootDirection;
	Player(int x, int y, int pSpeed, int mSpace) throws IOException{
		playerColors();
		playerX=x;
		playerY=y;
		playerSpeed=pSpeed;
		movementSpace=mSpace+50;
		color=Color.GREEN;
		//initialize player collision rectangle
		playerColl=new Rectangle(playerX-15,playerY-10,30,60,playerSpeed,Color.MAGENTA);
		playerColl.width=movementSpace;
		playerColl.height=movementSpace;
		
	}
	public void playerColors() throws IOException {
		playerImageUp=new ArrayList<Image>();
		ImageIcon sprite1=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Front]0.png")));
		ImageIcon sprite2=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Front]1.png")));
		ImageIcon sprite3=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Front]2.png")));
		ImageIcon sprite4=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Front]3.png")));
		ImageIcon sprite5=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Front]4.png")));
		ImageIcon sprite6=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Front]5.png")));
		ImageIcon sprite7=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Front]6.png")));
		playerImageUp.add(sprite1.getImage());
		playerImageUp.add(sprite2.getImage());
		playerImageUp.add(sprite3.getImage());
		playerImageUp.add(sprite4.getImage());
		playerImageUp.add(sprite5.getImage());
		playerImageUp.add(sprite6.getImage());
		playerImageUp.add(sprite7.getImage());

		playerImageRight=new ArrayList<Image>();
		sprite1=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Right]0.png")));
		sprite2=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Right]1.png")));
		sprite3=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Right]2.png")));
		sprite4=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Right]3.png")));
		sprite5=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Right]2.png")));
		sprite6=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Right]1.png")));
		sprite7=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Right]0.png")));
		playerImageRight.add(sprite1.getImage());
		playerImageRight.add(sprite2.getImage());
		playerImageRight.add(sprite3.getImage());
		playerImageRight.add(sprite4.getImage());
		playerImageRight.add(sprite5.getImage());
		playerImageRight.add(sprite6.getImage());
		playerImageRight.add(sprite7.getImage());

		playerImageDown=new ArrayList<Image>();
		sprite1=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Back]0.png")));
		sprite2=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Back]1.png")));
		sprite3=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Back]2.png")));
		sprite4=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Back]3.png")));
		sprite5=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Back]4.png")));
		sprite6=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Back]5.png")));
		sprite7=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Back]6.png")));		playerImageDown.add(sprite1.getImage());
		playerImageDown.add(sprite2.getImage());
		playerImageDown.add(sprite3.getImage());
		playerImageDown.add(sprite4.getImage());
		playerImageDown.add(sprite5.getImage());
		playerImageDown.add(sprite6.getImage());
		playerImageDown.add(sprite7.getImage());

		playerImageLeft=new ArrayList<Image>();
		sprite1=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Left]0.png")));
		sprite2=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Left]1.png")));
		sprite3=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Left]2.png")));
		sprite4=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Left]3.png")));
		sprite5=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Left]2.png")));
		sprite6=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Left]1.png")));
		sprite7=new ImageIcon(ImageIO.read(Player.class.getResourceAsStream("/RobotV1[Left]0.png")));
		playerImageLeft.add(sprite1.getImage());
		playerImageLeft.add(sprite2.getImage());
		playerImageLeft.add(sprite3.getImage());
		playerImageLeft.add(sprite4.getImage());
		playerImageLeft.add(sprite5.getImage());
		playerImageLeft.add(sprite6.getImage());
		playerImageLeft.add(sprite7.getImage());

	}
	public void draw(Graphics g) {
		//g.setColor(Color.GREEN);
		if(direction==2) {
			g.drawImage(playerImageUp.get(frame), playerX-25, playerY-20, 80, 80, null);
		}
		else if(direction==1) {
			g.drawImage(playerImageRight.get(frame), playerX-25, playerY-20, 80, 80, null);

		}
		else if(direction==0) {
			g.drawImage(playerImageDown.get(frame), playerX-25, playerY-20, 80, 80, null);

		}
		else if(direction==3) {
			g.drawImage(playerImageLeft.get(frame), playerX-25, playerY-20, 80, 80, null);

		}
		//g.fillRect(playerX, playerY, movementSpace, movementSpace);
		//ImageIcon sprite6=new ImageIcon("flyPurp.GIF");

	}
	public void drawColl(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.drawRect(playerColl.x, playerColl.y, playerColl.width, playerColl.width);
		
	}
}

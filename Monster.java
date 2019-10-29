import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
public class Monster {
	Graphics g; //for drawing monster
	Rectangle rectColl; //checking collisions
	Color color;
	MonsterDNA dna;
	Timer moveTimer;
	ArrayList<Image>zoogSprite;
	int moveCounter=0;
	int health=3;;
	int locX, locY, width, height;
	Monster(int x, int y, int w, int h) throws IOException{
		
		locX=x;
		locY=x;
		width=w;
		height=h;
		color=Color.RED;
		dna=new MonsterDNA();
		sprites();
		rectColl = new Rectangle(locX-10,locY-10,60,60,60,Color.MAGENTA);

	}
	public void sprites() throws IOException {
		zoogSprite = new ArrayList<Image>();
		ImageIcon sprite1=new ImageIcon(ImageIO.read(Monster.class.getResource("/zoog10.png")));
		ImageIcon sprite2=new ImageIcon(ImageIO.read(Monster.class.getResource("/zoog11.png")));
		ImageIcon sprite3=new ImageIcon(ImageIO.read(Monster.class.getResource("/zoog12.png")));
		ImageIcon sprite4=new ImageIcon(ImageIO.read(Monster.class.getResource("/zoog13.png")));
		ImageIcon sprite5=new ImageIcon(ImageIO.read(Monster.class.getResource("/zoog14.png")));
		ImageIcon sprite6=new ImageIcon(ImageIO.read(Monster.class.getResource("/zoog15.png")));
		
		zoogSprite.add(sprite1.getImage());
		zoogSprite.add(sprite2.getImage());
		zoogSprite.add(sprite3.getImage());
		zoogSprite.add(sprite4.getImage());
		zoogSprite.add(sprite5.getImage());
		zoogSprite.add(sprite6.getImage());
	}
	public void dnaMove() {
		int move = dna.getDNA(moveCounter);
		//insert movement code here
		if(move==0) {
			locY-=60;
			rectColl.y-=60;
		}
		if(move==1) {
			locX+=60;
			rectColl.x+=60;
		}
		if(move==2) {
			locY+=60;
			rectColl.y+=60;
		}
		if(move==3) {
			locX-=60;
			rectColl.x-=60;
		}
		//System.out.println(move);
		moveCounter++;
		if(moveCounter==4) {
			moveCounter=0;
			dna.chanceReset();
			dna.geneticMixup(moveCounter,locX,locY);
			//System.out.println("MoveCounter 4, genetic mixup");
		}
	}
	public void draw(Graphics g, int bossHealth, int currentBossHealth) {
		g.setColor(color);
		g.setColor(Color.WHITE);
		//g.drawString("Lives: " +bossHealth, locX,locY-20);
		g.setColor(Color.RED);
		g.fillRect(locX-10, locY-25, 60, 6);
		g.setColor(Color.GREEN);
		//debug the following
		for(int i=0; i<=(60/bossHealth)*currentBossHealth; i+=(60/bossHealth)) {
			g.fillRect(locX-10, locY-25, i, 6);
		}
	}  
	
}

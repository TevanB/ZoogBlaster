import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class TitleScreen extends JPanel {

    JButton startButton, exitMainMenu, instructionsButton;
    Splataze splat;
    JLabel titleLabel, background;
    Font buttonFont;
    ImageIcon splatButton, instructionButton, exitButton;
    Timer animTimer, moveTimer, constantTimer;
    Splatter splatter = new Splatter(5);
    ArrayList<Splatter>enemies;
    int timerCount=0;
    Point mousePoint;
    JFrame frame;
    JComponent component;
    int mouseX, mouseY;
    int splatterAnimIndex=0;
    TitleScreen() throws IOException{
        this.setLayout(null);
        this.setSize(1300,1000);
        this.setVisible(true);
        
        enemies = new ArrayList<Splatter>();
        
        for(int i=0; i<10; i++) {
        	int random = (int)(13*Math.random()+3);
			Splatter splatter = new Splatter(5);
			splatter.splatterSpeedX=random;
			splatter.splatterSpeedY=random;
			splatter.movementSpaceSplatter=random;
			splatter.splatterX=300;
			splatter.splatterY=300;
			splatter.enemyColl.x=splatter.splatterX-10;
			splatter.enemyColl.y=splatter.splatterY-10;

			enemies.add(splatter);
		}
        PointerInfo a = MouseInfo.getPointerInfo();
        mousePoint=a.getLocation();
        mouseX=mousePoint.x;
        mouseY=mousePoint.y;
        component=this;
        splatButton = new ImageIcon(ImageIO.read(TitleScreen.class.getResourceAsStream("/splatbutton (1).png")));
        instructionButton = new ImageIcon(ImageIO.read(TitleScreen.class.getResourceAsStream("/instructions.png")));
        exitButton = new ImageIcon(ImageIO.read(TitleScreen.class.getResourceAsStream("/exit.png")));
        
        buttonFont = new Font("Arial", Font.PLAIN, 40);


        splat = new Splataze();
        
        setBackground(Color.BLACK);


        startButton = new JButton(splatButton);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setBounds(400, 250, 500,385);

        titleLabel= new JLabel();
        titleLabel.setBounds(440,100,500,100);
        titleLabel.setFont(new Font("Impact", Font.PLAIN, 70));
        titleLabel.setText("Zoog Blaster");
        titleLabel.setForeground(Color.GREEN);

        exitMainMenu = new JButton(exitButton);
        exitMainMenu.setBounds(750, 665, 300,300);

        exitMainMenu.setBorderPainted(false);
        exitMainMenu.setContentAreaFilled(false);

        instructionsButton = new JButton(instructionButton);
        instructionsButton.setBounds(250,665,300,300);
        instructionsButton.setBorderPainted(false);
        instructionsButton.setContentAreaFilled(false);


        //this.add(background);
        this.add(titleLabel);
        this.add(startButton);
        this.add(exitMainMenu);
        this.add(instructionsButton);
        
        
        constantTimer = new Timer(5000, new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		mousePoint=component.getLocationOnScreen();
        		 mouseX=(int)(1301*Math.random());
                 mouseY=(int)(1001*Math.random());
                 //System.out.println(mouseX + " "+ mouseY);
            
        	}
        });
	    constantTimer.start();
        animTimer = new Timer(70, new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		timerCount++;
        		if(timerCount>=100) {
        			timerCount=0;
        		}
        		
        		splatterAnimIndex++;
        		if(splatterAnimIndex==6) {
        			splatterAnimIndex=0;
        		}
        		repaint();
        	}
        });
        animTimer.start();
        
        moveTimer = new Timer(100, new ActionListener() {
        	public void actionPerformed(ActionEvent ev) {
        		PointerInfo a = MouseInfo.getPointerInfo();
                mousePoint=component.getLocationOnScreen();
                //mouseX=(int)(1301*Math.random());
                //mouseY=(int)(1001*Math.random());
        		for(int i=0; i<enemies.size(); i++) {
        			if(enemies.get(i).splatterX<mouseX) {
        				enemies.get(i).splatterX+=enemies.get(i).splatterSpeedX;
        			}
        			if(enemies.get(i).splatterX>mouseX) {
        				enemies.get(i).splatterX-=enemies.get(i).splatterSpeedX;
        			}
        			
        			if(enemies.get(i).splatterY<mouseY) {
        				enemies.get(i).splatterY+=enemies.get(i).splatterSpeedY;
        			}
        			if(enemies.get(i).splatterY>mouseY) {
        				enemies.get(i).splatterY-=enemies.get(i).splatterSpeedY;
        			}
        		}
        		repaint();
        	}
        });
        moveTimer.start();
        
    }
   public void paintComponent(Graphics g) {
	   g.setColor(Color.BLACK);
	   g.fillRect(0, 0, 1300, 1000);
	   drawEnemy(g);
    }
    public void drawEnemy(Graphics g) {
    	for(int enemyNumber=0; enemyNumber<enemies.size(); enemyNumber++) {
			if(enemies.get(enemyNumber).randomColorChoice==1) {
				g.drawImage(enemies.get(enemyNumber).spritePurp.get(splatterAnimIndex),enemies.get(enemyNumber).splatterX-25, enemies.get(enemyNumber).splatterY-25,200,200, null);
			}
			if(enemies.get(enemyNumber).randomColorChoice==2) {
				g.drawImage(enemies.get(enemyNumber).spritePink.get(splatterAnimIndex),enemies.get(enemyNumber).splatterX-25, enemies.get(enemyNumber).splatterY-25,200,200, null);
			}
			if(enemies.get(enemyNumber).randomColorChoice==3) {
				g.drawImage(enemies.get(enemyNumber).spriteGreen.get(splatterAnimIndex),enemies.get(enemyNumber).splatterX-25, enemies.get(enemyNumber).splatterY-25,200,200, null);
			}
			if(enemies.get(enemyNumber).randomColorChoice==4) {
				g.drawImage(enemies.get(enemyNumber).spriteOrange.get(splatterAnimIndex),enemies.get(enemyNumber).splatterX-25, enemies.get(enemyNumber).splatterY-25,200,200, null);
			}
			if(enemies.get(enemyNumber).randomColorChoice==5) {
				g.drawImage(enemies.get(enemyNumber).spriteYellow.get(splatterAnimIndex),enemies.get(enemyNumber).splatterX-25, enemies.get(enemyNumber).splatterY-25,200,200, null);
			}
			if(enemies.get(enemyNumber).randomColorChoice==6) {
				g.drawImage(enemies.get(enemyNumber).spriteRed.get(splatterAnimIndex),enemies.get(enemyNumber).splatterX-25, enemies.get(enemyNumber).splatterY-25,200,200, null);
			}		
		}
    }
    public static void main(String [] args){


    }


}


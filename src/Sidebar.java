import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.font.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
public class Sidebar extends JPanel {
	Color c= Color.GRAY;
	Graphics g;
	JLabel splatCountLabel;
	JLabel splatAliveLabel;
	JLabel foodLeftLabel;
	JLabel liveCountLabel;
	JLabel levelCountLabel;
	Font font;
	BufferedImage bImage;
	ImageIcon iIcon;
	int splatterSplatCount=0;
	int splatterAliveCount=0;
	int foodRemainCount=10;
	int lifeCount=10;
	int levelNumber;
	Sidebar(int scaleAmount, int playerSpeed, int enemySpeed, int levelCount){
		font = new Font("Futura Md BT", Font.BOLD, 15);
		levelNumber=levelCount;
		bImage = new BufferedImage(200,1100,BufferedImage.TYPE_INT_ARGB);
		g=bImage.getGraphics();
		
		setLocation(1050,0);
		setSize(250,1000);
		setBackground(c);
		
	}
	
	public JLabel splatCountLabel() {
		splatCountLabel=new JLabel("Zags Splatted: "+splatterSplatCount);
		splatCountLabel.setFont(font);
		return splatCountLabel;

	}
	public JLabel splatAliveLabel() {
		splatAliveLabel=new JLabel("Zags Alive: "+splatterAliveCount);
		splatAliveLabel.setFont(font);
		return splatAliveLabel;

	}
	public JLabel foodCountLabel() {
		foodLeftLabel=new JLabel("Food Left: "+foodRemainCount);
		foodLeftLabel.setFont(font);
		return foodLeftLabel;

	}
	public JLabel livesCountLabel() {
		liveCountLabel = new JLabel("Lives Count: "+lifeCount);
		liveCountLabel.setFont(font);
		return liveCountLabel;

	}
	public JLabel levelCountLabel() {
		levelCountLabel = new JLabel("LEVEL "+levelNumber);
		levelCountLabel.setFont(font);
		return levelCountLabel;

	}
	public void updateLabel() {
		splatCountLabel.setText("Zags Splatted: "+splatterSplatCount);
		liveCountLabel.setText("Lives Count: "+lifeCount);
		levelCountLabel.setText("LEVEL "+levelNumber);
		foodLeftLabel.setText("Food Left: "+foodRemainCount);
		splatAliveLabel.setText("Zags Alive: "+splatterAliveCount);

	}
	
}

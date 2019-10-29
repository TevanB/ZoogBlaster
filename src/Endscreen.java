import javax.swing.*;
import java.awt.*;

public class Endscreen extends JPanel {
	
	JLabel loser;
	
	public Endscreen() {
		
		
		this.setSize(1300,1000);
		this.setBackground(Color.BLACK);
		this.setVisible(false);
		
		loser =  new JLabel("haha nigga u lose");
		loser.setForeground(Color.RED);
		loser.setBounds(0300,200,500,500);
		
		this.add(loser);
		
		

	}

}

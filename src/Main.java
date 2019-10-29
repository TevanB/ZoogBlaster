import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Main extends JFrame implements ActionListener{

    //Splataze splatoke;
    TitleScreen menu;
    Instructions instructions;
    Sidebar sidebar;
    
    static ImageIcon sprayCursor;
    Main() throws IOException{
        setTitle("ZoogBlaster 3.0");
        setSize(1300,1010);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        menu = new TitleScreen();
        instructions = new Instructions();
        sprayCursor = new ImageIcon(ImageIO.read(Main.class.getResourceAsStream("/spraycursor.png")));

        sidebar = new Sidebar(0,0,0,0);

        menu.startButton.addActionListener(this);
        menu.startButton.setActionCommand("start");

        menu.exitMainMenu.addActionListener(this);
        menu.exitMainMenu.setActionCommand("exit");

        menu.instructionsButton.addActionListener(this);
        menu.instructionsButton.setActionCommand("instructions");

        instructions.back.addActionListener(this);
        instructions.back.setActionCommand("back");
        
      

        getContentPane().add(menu);
        getContentPane().add(instructions);

        
       

        setVisible(true);



    }

    public static void main(String [] args) throws IOException{

        SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
                try {
					Main main = new Main();
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Image img = sprayCursor.getImage();
					Cursor cursor = toolkit.createCustomCursor(img, new Point(0,0), "spraycan");
		            main.setCursor(cursor);
				} catch (IOException e) {
				}
               
        	}
        });
    }

    public void actionPerformed (ActionEvent evt){

        if(evt.getActionCommand().equals("start")){
            Splataze splatoke;
			try {
				splatoke = new Splataze();
				getContentPane().add(splatoke);
	            menu.setVisible(false);
	            instructions.setVisible(false);
			} catch (IOException e) {
			}
        }
        else if(evt.getActionCommand().equals("instructions")){
           menu.setVisible(false);
           getContentPane().add(instructions);

        }

        else if(evt.getActionCommand().equals("back")){
            instructions.setVisible(false);
            menu.setVisible(true);
        }
        else if (evt.getActionCommand().equals("exit")){

            System.exit(0);
        }
    }
}

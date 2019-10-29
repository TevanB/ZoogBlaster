import javax.swing.*;
import java.awt.*;

public class Instructions extends JPanel {

    JLabel howToPlay, control1,control2,control3,control4, objective;
    JButton back;
    Font titleFont, labelFont1,labelFont2;



    Instructions(){

        this.setSize(1300,1000);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setVisible(true);

        titleFont = new Font("Arial",  Font.PLAIN, 70);
        labelFont1 = new Font("Arial",  Font.PLAIN, 20);
        labelFont2 = new Font("Arial",  Font.PLAIN, 30);


        back = new JButton("Back To Main Menu");
        back.setBounds(50,50, 200,50);
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setBackground(Color.GREEN);

        howToPlay = new JLabel ();
        howToPlay.setBounds(450,200, 500,100);
        howToPlay.setFont(titleFont);
        howToPlay.setForeground(Color.GREEN);
        howToPlay.setText("How To Play");

        objective = new JLabel();
        objective.setBounds(300, 300, 1000, 100) ;
        objective.setFont(labelFont1);
        objective.setForeground(Color.GREEN);
        objective.setText("Splat all the flies before they steal the food, or else you lose!");

        control1 = new JLabel();
        control1.setBounds(250,400, 1000, 100);
        control1.setFont(labelFont2);
        control1.setForeground(Color.GREEN);
        control1.setText("W = Move Up | A = Move Left | S = Move Down | D = Move Right");
        
        control2 = new JLabel();
        control2.setBounds(250,500, 1000, 100);
        control2.setFont(labelFont2);
        control2.setForeground(Color.GREEN);
        control2.setText("Press SHIFT to toggle running.");
        
        control3 = new JLabel();
        control3.setBounds(250,600, 1000, 100);
        control3.setFont(labelFont2);
        control3.setForeground(Color.GREEN);
        control3.setText("Press SPACE while on a chest to collect it.");

        control4 = new JLabel();
        control4.setBounds(250,700, 1000, 100);
        control4.setFont(labelFont2);
        control4.setForeground(Color.GREEN);
        control4.setText("Use Arrow Keys to shoot a Spray Projectile in the key's direction.");

        this.add(back);
        this.add(howToPlay);
        this.add(objective);
        this.add(control1);
        this.add(control2);
        this.add(control3);
        this.add(control4);

    }

    public static void main(String  [] args){


    }
}

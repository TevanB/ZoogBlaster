import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SplatMark {
	int x,y;
	ArrayList<Image>splatAnim;
	int splatAnimIndex=20;
	
	SplatMark(int x, int y) throws IOException{
		x=this.x;
		y=this.y;
		imageSetup();
		
	}
	public void draw(Graphics g) {
		g.drawImage(splatAnim.get(20), this.x, this.y, 50, 50, null);
	}
	public void imageSetup() throws IOException {
		splatAnim = new ArrayList<Image>();
		ImageIcon sprite1=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat01.png")));
		ImageIcon sprite2=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat02.png")));
		ImageIcon sprite3=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat03.png")));
		ImageIcon sprite4=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat04.png")));
		ImageIcon sprite5=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat05.png")));
		ImageIcon sprite6=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat06.png")));
		ImageIcon sprite7=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat07.png")));
		ImageIcon sprite8=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat08.png")));
		ImageIcon sprite9=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat09.png")));
		ImageIcon sprite10=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat10.png")));
		ImageIcon sprite12=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat11.png")));
		ImageIcon sprite13=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat12.png")));
		ImageIcon sprite14=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat13.png")));
		ImageIcon sprite15=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat14.png")));
		ImageIcon sprite16=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat15.png")));
		ImageIcon sprite17=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat16.png")));
		ImageIcon sprite18=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat17.png")));
		ImageIcon sprite19=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat18.png")));
		ImageIcon sprite20=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat19.png")));
		ImageIcon sprite21=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat20.png")));
		ImageIcon sprite22=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat21.png")));
		ImageIcon sprite23=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat22.png")));
		ImageIcon sprite24=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat23.png")));
		ImageIcon sprite25=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat24.png")));
		ImageIcon sprite26=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat25.png")));
		ImageIcon sprite27=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat26.png")));
		ImageIcon sprite28=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat27.png")));
		ImageIcon sprite29=new ImageIcon(ImageIO.read(SplatMark.class.getResourceAsStream("/Splat28.png")));

		splatAnim.add(sprite1.getImage());
		splatAnim.add(sprite2.getImage());
		splatAnim.add(sprite3.getImage());
		splatAnim.add(sprite4.getImage());
		splatAnim.add(sprite5.getImage());
		splatAnim.add(sprite6.getImage());
		splatAnim.add(sprite7.getImage());
		splatAnim.add(sprite8.getImage());
		splatAnim.add(sprite9.getImage());
		splatAnim.add(sprite10.getImage());
		splatAnim.add(sprite12.getImage());
		splatAnim.add(sprite13.getImage());
		splatAnim.add(sprite14.getImage());
		splatAnim.add(sprite15.getImage());
		splatAnim.add(sprite16.getImage());
		splatAnim.add(sprite17.getImage());
		splatAnim.add(sprite18.getImage());
		splatAnim.add(sprite19.getImage());
		splatAnim.add(sprite20.getImage());
		splatAnim.add(sprite21.getImage());
		splatAnim.add(sprite22.getImage());
		splatAnim.add(sprite23.getImage());
		splatAnim.add(sprite24.getImage());
		splatAnim.add(sprite25.getImage());
		splatAnim.add(sprite26.getImage());
		splatAnim.add(sprite27.getImage());
		splatAnim.add(sprite28.getImage());
		splatAnim.add(sprite29.getImage());
		

	
	
	}
}

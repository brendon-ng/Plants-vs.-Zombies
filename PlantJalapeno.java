import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PlantJalapeno extends Plant{
	private boolean exploding=false;
	public PlantJalapeno(Space space, ContentPanel contentPanel) {
		super("jalapeno", 100, 300, 125, 0, null, new ImageIcon("jalapeno.png").getImage(), space, false, new ImageIcon("jalapenoseed.png"), contentPanel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Plant getNewPlant(Space space, ContentPanel contentPanel) {
		
		return new PlantJalapeno(space,contentPanel);
	}

	@Override
	public Bullet getBullet(Space space, ContentPanel contentPanel) {
		// TODO Auto-generated method stub
		return null;
	}
	public void explode(){
		setImage(new ImageIcon("jalapenoexplode.png").getImage());
		exploding=true;
	}
	
	public void draw(Graphics g, int x, int y){
		
		g.drawImage(getImage(), x, y, null);
		if(exploding){
			Image newImage=new ImageIcon("fire.png").getImage();
			g.drawImage(newImage,x-newImage.getWidth(null), y, null);
		}
		
	}

}

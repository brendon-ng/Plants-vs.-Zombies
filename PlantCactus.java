import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PlantCactus extends Plant{

	public PlantCactus(Space space, ContentPanel contentPanel) {
		super("cactus", 150, 100, 250, 2, new Spike(space,contentPanel), new ImageIcon("cactus.png").getImage(), space, false, new ImageIcon("cactusseed.png"), contentPanel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Plant getNewPlant(Space space, ContentPanel contentPanel) {
		
		return new PlantCactus(space, contentPanel);
	}

	@Override
	public Bullet getBullet(Space space, ContentPanel contentPanel) {
		
		return new Spike(space,contentPanel);
	}
	
	public void draw(Graphics g, int x, int y){
		
		g.drawImage(getImage(), x-5, y-13, null);
		
		if(getHp()<getHealth()){
			g.setColor(Color.BLACK);
			g.drawRect(x+(getImage().getWidth(null)/2)-25, getSpace().getCenterY()+15, 50, 10);
			g.setColor(Color.GREEN);
			double percentHealth=(double)getHp()/(double)getHealth();
			double barWidth=percentHealth*50;
			
			g.fillRect(x+(getImage().getWidth(null)/2)-25, getSpace().getCenterY()+15, (int)barWidth, 10);
		}
	}

}

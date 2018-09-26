import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PlantTallNut extends Plant {

	public PlantTallNut(Space space, ContentPanel contentPanel) {
		super("tall-nut", 3000, 200, 125, 0, null, new ImageIcon("tallnut.png").getImage(), space, false, new ImageIcon("tallnutseed.png"), contentPanel);
		
	}
	
	public Plant getNewPlant(Space space,ContentPanel contentPanel) {
		return new PlantTallNut(space,contentPanel);
	}
	
	public Bullet getBullet(Space space,ContentPanel contentPanel){
		return null;
	}
	
	public void shoot(){
		return;
	}
	
	public void draw(Graphics g, int x, int y){
		
		g.drawImage(getImage(), x, y-30, null);
		
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

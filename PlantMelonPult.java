import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PlantMelonPult extends Plant{

	public PlantMelonPult(Space space, ContentPanel contentPanel) {
		super("melonpult", 150, 100, 300, 3, new Melon(space,contentPanel), new ImageIcon("melonpult.png").getImage(), space, false, new ImageIcon("melonpultseed.png"), contentPanel);
		
	}

	@Override
	public Plant getNewPlant(Space space, ContentPanel contentPanel) {
		
		return new PlantMelonPult(space,contentPanel);
	}

	@Override
	public Bullet getBullet(Space space, ContentPanel contentPanel) {
		
		return new Melon(space,contentPanel);
	}
	
	public void draw(Graphics g, int x, int y){
		
		g.drawImage(getImage(), x-7, y, null);
		
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

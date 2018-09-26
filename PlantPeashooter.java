import java.awt.Graphics;

import javax.swing.ImageIcon;

public class PlantPeashooter extends Plant{

	public PlantPeashooter(Space space, ContentPanel contentPanel){
		super("peashooter", 150, 100, 100,2, new Pea(space,contentPanel), new ImageIcon("peashooter.png").getImage(),
				space,false,new ImageIcon("peashooterseed.png"),contentPanel);
	}
	
	
	
	public Plant getNewPlant(Space space, ContentPanel contentPanel) {
		return new PlantPeashooter(space,contentPanel);
	}
	
	public Bullet getBullet(Space space,ContentPanel contentPanel){
		return new Pea(space,contentPanel);
	}

	
	  
}

import java.awt.Image;

import javax.swing.ImageIcon;

public class Shovel extends Plant{

	
	public Shovel(Space space, ContentPanel contentPanel){
		super("shovel", 100, 100, 0,2, null, new ImageIcon("shovel.png").getImage(),
				space,false,new ImageIcon("peashooterseed.png"),contentPanel);
	}

	@Override
	public Plant getNewPlant(Space space, ContentPanel contentPanel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bullet getBullet(Space space, ContentPanel contentPanel) {
		// TODO Auto-generated method stub
		return null;
	}

}

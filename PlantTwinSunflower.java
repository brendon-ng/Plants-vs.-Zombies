import java.awt.Image;

import javax.swing.ImageIcon;

public class PlantTwinSunflower extends Plant{

	public PlantTwinSunflower(Space space, ContentPanel contentPanel) {
		super("twinsunflower", 150, 100, 150,(int)(Math.random()*6)+7, new Sun(space,contentPanel,false), new ImageIcon("twinsunflower.png").getImage(),
				space,false,new ImageIcon("twinsunflowerseed.png"),contentPanel);
		
	}
	public void shoot(){
		
		Bullet newBullet = this.getBullet(getSpace(),getContentPanel());
		
		getContentPanel().getSuns().add((Sun)newBullet);
		
	}
	
	public Plant getNewPlant(Space space,ContentPanel contentPanel) {
		return new PlantTwinSunflower(space,contentPanel);
	}
	
	public Bullet getBullet(Space space,ContentPanel contentPanel){
		return new Sun(space,contentPanel,false);
	}

}

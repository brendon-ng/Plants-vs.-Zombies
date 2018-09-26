import java.awt.Graphics;
import javax.swing.ImageIcon;

public class PlantSunflower extends Plant {

	public PlantSunflower(Space space,ContentPanel contentPanel){
		super("sunflower", 150, 100, 50,(int)(Math.random()*6)+15, new Sun(space,contentPanel,false), new ImageIcon("sunflower.png").getImage(),
				space,false,new ImageIcon("sunflowerseed.png"),contentPanel);
	}
	
	public void shoot(){
		
		Bullet newBullet = this.getBullet(getSpace(),getContentPanel());
		
		getContentPanel().getSuns().add((Sun)newBullet);
		
	}
	
	public Plant getNewPlant(Space space,ContentPanel contentPanel) {
		return new PlantSunflower(space,contentPanel);
	}
	
	public Bullet getBullet(Space space,ContentPanel contentPanel){
		return new Sun(space,contentPanel,false);
	}
}


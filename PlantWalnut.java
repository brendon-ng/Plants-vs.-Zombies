import javax.swing.ImageIcon;

public class PlantWalnut extends Plant{
	public PlantWalnut(Space space, ContentPanel contentPanel){
		super("walnut", 1500, 200, 50,0, null, new ImageIcon("walnut.png").getImage(),
				space,false,new ImageIcon("walnutseed.png"),contentPanel);
	}
	
	public Plant getNewPlant(Space space,ContentPanel contentPanel) {
		return new PlantWalnut(space,contentPanel);
	}
	
	public Bullet getBullet(Space space,ContentPanel contentPanel){
		return null;
	}
	
	public void shoot(){
		return;
	}
	
	
	
}

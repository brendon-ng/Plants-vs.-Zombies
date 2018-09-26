import javax.swing.ImageIcon;

public class PlantSnowPea extends Plant{
	public PlantSnowPea(Space space, ContentPanel contentPanel){
		super("snowPea", 150, 100, 150,2, new SnowPea(space,contentPanel), new ImageIcon("snowpeashooter.png").getImage(),
				space,false,new ImageIcon("snowpeaseed.png"),contentPanel);
	}

	@Override
	public Plant getNewPlant(Space space, ContentPanel contentPanel) {
		return new PlantSnowPea(space,contentPanel);
	}

	@Override
	public Bullet getBullet(Space space, ContentPanel contentPanel) {
		return new SnowPea(space, contentPanel);
	}
}

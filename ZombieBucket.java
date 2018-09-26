import javax.swing.ImageIcon;

public class ZombieBucket extends Zombie {
	public ZombieBucket(int row, ContentPanel contentPanel){
		super("bucketZombie", 400, 100, new ImageIcon("bucketzombie.png").getImage(), row, false, contentPanel);
	}
	
	public Zombie getNewZombie(int row, ContentPanel contentPanel){
		return new ZombieBucket(row, contentPanel);
	}
}

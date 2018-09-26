import javax.swing.ImageIcon;

public class ZombieFootball extends Zombie{
	public ZombieFootball(int row, ContentPanel contentPanel){
		super("footballZombie", 300, 200, new ImageIcon("footballzombie.png").getImage(), row, false, contentPanel);
	}
	
	public Zombie getNewZombie(int row, ContentPanel contentPanel){
		return new ZombieFootball(row, contentPanel);
	}
}

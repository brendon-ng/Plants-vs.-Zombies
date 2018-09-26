import javax.swing.ImageIcon;

public class ZombieTrack extends Zombie{
	public ZombieTrack(int row, ContentPanel contentPanel){
		super("trackZombie", 100, 200, new ImageIcon("trackzombie.png").getImage(), row, false, contentPanel);
	}
	
	public Zombie getNewZombie(int row, ContentPanel contentPanel){
		return new ZombieTrack(row, contentPanel);
	}
}

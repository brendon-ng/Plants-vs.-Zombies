import javax.swing.ImageIcon;

public class ZombieCone extends Zombie{
	public ZombieCone(int row, ContentPanel contentPanel){
		super("coneZombie", 200, 100, new ImageIcon("conezombie.png").getImage(), row, false, contentPanel);
	}
	
	public Zombie getNewZombie(int row, ContentPanel contentPanel){
		return new ZombieCone(row, contentPanel);
	}
}

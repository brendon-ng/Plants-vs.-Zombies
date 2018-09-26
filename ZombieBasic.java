import java.awt.Image;

import javax.swing.ImageIcon;

public class ZombieBasic extends Zombie{
	public ZombieBasic(int row, ContentPanel contentPanel){
		super("basicZombie", 100, 100, new ImageIcon("basiczombie.png").getImage(), row, false, contentPanel);
	}
	
	public Zombie getNewZombie(int row, ContentPanel contentPanel){
		return new ZombieBasic(row, contentPanel);
	}
}

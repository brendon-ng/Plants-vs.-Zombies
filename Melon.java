import javax.swing.ImageIcon;

public class Melon extends Bullet{
	public Melon(Space shooterSpace, ContentPanel contentPanel){
		super(30, 100, new ImageIcon("melon.png").getImage(), shooterSpace.getRow(), shooterSpace,contentPanel);
	}
	
}

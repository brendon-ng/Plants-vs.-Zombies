import java.awt.Image;

import javax.swing.ImageIcon;

public class Pea extends Bullet{
	public Pea(Space shooterSpace, ContentPanel contentPanel){
		super(10, 100, new ImageIcon("pea.png").getImage(), shooterSpace.getRow(), shooterSpace,contentPanel);
	}
}

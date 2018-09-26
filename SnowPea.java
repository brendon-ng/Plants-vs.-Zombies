import javax.swing.ImageIcon;

public class SnowPea extends Bullet{
	public SnowPea(Space shooterSpace, ContentPanel contentPanel){
		super(10, 100, new ImageIcon("snowpea.png").getImage(), shooterSpace.getRow(), shooterSpace,contentPanel);
	}
}

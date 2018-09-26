import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class PlantRepeater extends Plant{

	public PlantRepeater(Space space, ContentPanel contentPanel) {
		super("repeater", 150, 100, 200, 2, new Pea(space,contentPanel), new ImageIcon("repeater.png").getImage(), space, false, new ImageIcon("repeaterseed.png"), contentPanel);
		
	}

	@Override
	public Plant getNewPlant(Space space, ContentPanel contentPanel) {
		
		return new PlantRepeater(space,contentPanel);
	}

	@Override
	public Bullet getBullet(Space space, ContentPanel contentPanel) {
		
		return new Pea(space, contentPanel);
	}
	
	public void shoot(){
		super.shoot();
		
		Timer delay=new Timer(300,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				shootSecond();
			}
		});
		delay.setRepeats(false);
		delay.start();
	}
	public void shootSecond(){
		super.shoot();
	}

}

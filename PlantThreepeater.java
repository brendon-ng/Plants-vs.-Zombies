import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class PlantThreepeater extends Plant{
	public PlantThreepeater(Space space, ContentPanel contentPanel) {
		super("Threepeater", 150, 100, 325, 2, new Pea(space,contentPanel), new ImageIcon("threepeater.png").getImage(), space, false, new ImageIcon("threepeaterseed.png"), contentPanel);
		
	}

	@Override
	public Plant getNewPlant(Space space, ContentPanel contentPanel) {
		
		return new PlantThreepeater(space,contentPanel);
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
				Timer delay2=new Timer(300,new ActionListener(){
					public void actionPerformed(ActionEvent e){
						shootSecond();
						
					}
				});
				delay2.setRepeats(false);
				delay2.start();
				
			}
		});
		delay.setRepeats(false);
		delay.start();
	}
	public void shootSecond(){
		super.shoot();
	}
}

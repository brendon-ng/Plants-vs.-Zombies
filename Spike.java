import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Spike extends Bullet{
	public Spike(Space shooterSpace, ContentPanel contentPanel){
		super(1, 150, new ImageIcon("spike.png").getImage(), shooterSpace.getRow(), shooterSpace,contentPanel);
	}
	
	public void hit(Zombie zombie){
	
	}
	public void actionPerformed(ActionEvent e) {
		
		
		setX(getX()+getSpeed()/15);
		
		Zombie front=null;
		ArrayList<Bullet> bullets=null;
		ArrayList<Zombie> zombies=null;
		
		switch(getRow()){
		case 0:
			zombies=getContentPanel().getZombies0();
			bullets=getContentPanel().getBullets0();
			break;
		case 1:
			zombies=getContentPanel().getZombies1();
			bullets=getContentPanel().getBullets1();
			break;
		case 2:
			zombies=getContentPanel().getZombies2();
			bullets=getContentPanel().getBullets2();
			break;
		case 3:
			zombies=getContentPanel().getZombies3();
			bullets=getContentPanel().getBullets3();
			break;
		case 4:
			zombies=getContentPanel().getZombies4();
			bullets=getContentPanel().getBullets4();
			break;
		default:
			zombies=null;
			bullets=null;
		}
		
		if(zombies!=null){
			for(Zombie i:zombies){
				if(i!=null&&i.containsPoint(getX())){
					i.getHit(this);
					
				
					
				}
			}
		}
		
		if(bullets!=null){
			if(getX()>1050){
				bullets.remove(this);
				setX(-10000000);
				getBulletTimer().stop();
			}	
		}
		
		if(getX()>1050){
			bullets.remove(this);
			setX(-10000000);
			getBulletTimer().stop();
		}
		
		
	}
}

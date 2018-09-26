import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Sun extends Bullet implements MouseListener, ActionListener{
	private boolean moving;
	private Timer sunTimer;
	public Sun(Space shooterSpace,ContentPanel contentPanel,boolean moving){
		super(0, 0, new ImageIcon("sun.png").getImage(), shooterSpace.getRow(), shooterSpace,contentPanel);
		this.moving=moving;
		if(moving){
			sunTimer=new Timer(125,this);
			sunTimer.start();
		}
	}
	
	public void draw(Graphics g){
		
		g.drawImage(getImage(), getX()-60, getY(), null);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(moving){
			setY(getY()+1);
		}
	}
	public void gameOver(){
		getBulletTimer().stop();
		if(moving)
			sunTimer.stop();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

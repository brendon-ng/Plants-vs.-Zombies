import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.Timer;

public class Bullet implements ActionListener {
	private int damage;
	private int speed;
	private Image image;
	private int row;
	private Space shooterSpace;
	private Timer bulletTimer;
	private int x;
	private int y;
	private ContentPanel contentPanel;
	
	public Bullet(int damage, int speed, Image image, int row, Space shooterSpace,ContentPanel contentPanel) {
		this.damage = damage;
		this.speed = speed;
		this.image = image;
		this.row = row;
		this.shooterSpace=shooterSpace;
		this.contentPanel=contentPanel;
		x=shooterSpace.getCenterX()+30;
		y=shooterSpace.getCenterY()-30;
		bulletTimer=new Timer(30,this);
		bulletTimer.start();
		
		
		
		
		
		
	}
	
	
	
	public void draw(Graphics g){
	
		g.drawImage(image, x, y, null);
		
	}
	
	public void hit(Zombie zombie){
		bulletTimer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		x+=speed/15;
		
		Zombie front=null;
		ArrayList<Bullet> bullets=null;
		ArrayList<Zombie> zombies=null;
		
		switch(row){
		case 0:
			zombies=contentPanel.getZombies0();
			bullets=contentPanel.getBullets0();
			break;
		case 1:
			zombies=contentPanel.getZombies1();
			bullets=contentPanel.getBullets1();
			break;
		case 2:
			zombies=contentPanel.getZombies2();
			bullets=contentPanel.getBullets2();
			break;
		case 3:
			zombies=contentPanel.getZombies3();
			bullets=contentPanel.getBullets3();
			break;
		case 4:
			zombies=contentPanel.getZombies4();
			bullets=contentPanel.getBullets4();
			break;
		default:
			zombies=null;
			bullets=null;
		}
		
		if(zombies!=null){
			for(Zombie i:zombies){
				if(i!=null&&i.containsPoint(x)){
					i.getHit(this);
					bullets.remove(this);
					x=-10000000;
					bulletTimer.stop();
				}
			}
		}
		
		if(bullets!=null){
			if(x>1050){
				bullets.remove(this);
				x=-10000000;
				bulletTimer.stop();
			}	
		}
		
		
	}



	public Image getImage() {
		return image;
	}



	public void setImage(Image image) {
		this.image = image;
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public Space getShooterSpace() {
		return shooterSpace;
	}



	public int getDamage() {
		return damage;
	}



	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public void gameOver(){
		bulletTimer.stop();
	}



	public Timer getBulletTimer() {
		return bulletTimer;
	}



	public void setBulletTimer(Timer bulletTimer) {
		this.bulletTimer = bulletTimer;
	}



	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}



	public int getRow() {
		return row;
	}



	public void setRow(int row) {
		this.row = row;
	}



	public ContentPanel getContentPanel() {
		return contentPanel;
	}



	public void setContentPanel(ContentPanel contentPanel) {
		this.contentPanel = contentPanel;
	}



	public void setShooterSpace(Space shooterSpace) {
		this.shooterSpace = shooterSpace;
	}
	
	
	
	
	
	
}

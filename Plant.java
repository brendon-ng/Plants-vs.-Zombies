import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.swing.*;

public abstract class Plant implements ActionListener {
	private String name;
	private int health;
	private int recharge;
	private int cost;
	private Bullet bullet;
	private Image image;
	private Space space;
	private boolean beingEaten;
	private ImageIcon seedCard;
	private ContentPanel contentPanel;
	private Timer plantTimer;
	private int firingSpeed;
	private ArrayList<Zombie> zombieList;
	private int hp;
	private Timer checkTimer;
	
	public Plant(String name, int health, int recharge, int cost, int firingSpeed, Bullet bullet, Image image, 
			Space space, boolean beingEaten,ImageIcon seedCard, ContentPanel contentPanel){
		this.name=name;
		this.health=health;
		this.recharge=recharge;
		this.cost=cost;
		this.bullet=bullet;
		this.image=image;
		this.space=space;
		this.beingEaten=false;
		this.seedCard=seedCard;
		this.contentPanel=contentPanel;
		this.firingSpeed=firingSpeed;
		hp=health;
		
		switch(space.getRow()){
		case 0:
			zombieList=contentPanel.getZombies0();
			break;
		case 1:
			zombieList=contentPanel.getZombies1();
			break;
		case 2:
			zombieList=contentPanel.getZombies2();
			break;
		case 3:
			zombieList=contentPanel.getZombies3();
			break;
		case 4:
			zombieList=contentPanel.getZombies4();
			break;
		default:
			zombieList=new ArrayList<Zombie>();
		}
		
		plantTimer=new Timer(firingSpeed*1000,this);
		checkTimer=new Timer(80, this);
		plantTimer.start();
		checkTimer.start();
		
	}
	

	
	public void draw(Graphics g, int x, int y){
		
		g.drawImage(getImage(), x, y, null);
		
		if(hp<health){
			g.setColor(Color.BLACK);
			g.drawRect(x+(image.getWidth(null)/2)-25, space.getCenterY()+15, 50, 10);
			g.setColor(Color.GREEN);
			double percentHealth=(double)hp/(double)health;
			double barWidth=percentHealth*50;
			
			g.fillRect(x+(image.getWidth(null)/2)-25, space.getCenterY()+15, (int)barWidth, 10);
		}
	}
	
	public abstract Plant getNewPlant(Space space, ContentPanel contentPanel);
	
	public abstract Bullet getBullet(Space space,ContentPanel contentPanel);

	public void shoot(){
		boolean shoot=false;
		for(Zombie i:zombieList){
			if(i!=null&&i.getX()<1023&&i.getX()>150){
				shoot=true;
			}
		}
		if(shoot){
			Bullet newBullet = this.getBullet(space,contentPanel);
			
			switch(space.getRow()){
				case 0:
					contentPanel.getBullets0().add(newBullet);
					break;
				case 1:
					contentPanel.getBullets1().add(newBullet);
					break;
				case 2:
					contentPanel.getBullets2().add(newBullet);
					break;
				case 3:
					contentPanel.getBullets3().add(newBullet);
					break;
				case 4:
					contentPanel.getBullets4().add(newBullet);
					break;
			}
		}
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(plantTimer)){
			shoot();
		}
		
		if(hp<=0){						//If dead	
			System.out.print("dead");
			plantTimer.stop();
			checkTimer.stop();
			space.setPlant(null);
			space.setFilled(false);
			
		}
		
		
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getRecharge() {
		return recharge;
	}

	public void setRecharge(int recharge) {
		this.recharge = recharge;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	public boolean isBeingEaten() {
		return beingEaten;
	}

	public void setBeingEaten(boolean beingEaten) {
		this.beingEaten = beingEaten;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}



	public ImageIcon getSeedCard() {
		return seedCard;
	}



	public void setSeedCard(ImageIcon seedCard) {
		this.seedCard = seedCard;
	}



	public ContentPanel getContentPanel() {
		return contentPanel;
	}
	
	public void gameOver(){
		plantTimer.stop();
	}



	public int getHp() {
		return hp;
	}



	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void changeHp(int change){
		hp+=change;
	}
	
	public boolean containsPoint(int point){
		return (point>space.getCenterX()-37&&point<space.getCenterX()+37);
	}



	public Timer getPlantTimer() {
		return plantTimer;
	}



	public void setPlantTimer(Timer plantTimer) {
		this.plantTimer = plantTimer;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

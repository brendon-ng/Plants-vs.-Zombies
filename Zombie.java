import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

public abstract class Zombie implements ActionListener{
	private String name;
	private int health;
	private int speed;
	private Image image;
	private int row;
	private boolean eating;
	private ContentPanel contentPanel;
	private int x;
	private Timer zombieTimer;
	private Timer eatingTimer;
	private int hp;
	ArrayList<Zombie> rowZombies;
	private Space[] spaces;
	private Plant eatingPlant;
	private boolean slow;
	private int slowTime;
	private Timer slowTimer;
	
	private int rowY;
	
	
	public Zombie(String name, int health, int speed, Image image, int row, boolean eating, ContentPanel contentPanel){
		this.name=name;
		this.health=health;
		hp=health;
		this.speed=speed;
		this.image=image;
		this.row=row;
		this.eating=false;
		this.contentPanel=contentPanel;
		if(row<10)
			spaces=contentPanel.getSpaces()[row];
		x=1030;
		switch(row){
			case 0:
				rowY=ContentPanel.ROW2;
				break;
			case 1:
				rowY=ContentPanel.ROW3;
				break;
			case 2:
				rowY=ContentPanel.ROW4;
				break;
			case 3:
				rowY=ContentPanel.ROW5;
				break;
			case 4:
				rowY=ContentPanel.ROW6;
				break;
			default:
				//System.out.println("something went wrong zombie class");
				
		}
		
		
		switch(row){
		case 0:
			rowZombies=contentPanel.getZombies0();
			break;
		case 1:
			rowZombies=contentPanel.getZombies1();
			break;
		case 2:
			rowZombies=contentPanel.getZombies2();
			break;
		case 3:
			rowZombies=contentPanel.getZombies3();
			break;
		case 4:
			rowZombies=contentPanel.getZombies4();
			break;
		default:
			rowZombies=null;
		}
		zombieTimer=new Timer(100,this);
		eatingTimer=new Timer(500,this);
		zombieTimer.start();
		slowTimer=new Timer(1000,this);
		slowTimer.start();
	}
	
	public void draw(Graphics g){
		g.drawImage(image,x,rowY-10-image.getHeight(null),null);
		if(hp<health){
			g.setColor(Color.BLACK);
			g.drawRect(x+(image.getWidth(null)/2)-25, rowY-50, 50, 10);
			g.setColor(Color.RED);
			double percentHealth=(double)hp/(double)health;
			double barWidth=percentHealth*50;
			
			g.fillRect(x+(image.getWidth(null)/2)-25, rowY-50, (int)barWidth, 10);
		}

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(slowTimer)){
			if (slowTime>0){
				slowTime--;
			}
			if(slowTime==0){
				zombieTimer=new Timer(80,this);
				zombieTimer.start();
			}
		}
		
		
		
		if(e.getSource().equals(zombieTimer)&&eating==false){
			if(!slow)
				x-=speed/100;				//move zombie
			else
				x-=speed/100;
		}
		
		if(hp<=0){						//If dead	
			rowZombies.remove(this);
			x=100000;
			zombieTimer.stop();
			eating=false;
			stopEating(null);
		}
		
		if(x<170&&row>=0&&row<10){		//if reached house
			contentPanel.gameOver();
			if(!contentPanel.isGameOver()){
				//System.out.print("GAMEOVER");
				contentPanel.setGameOver(true);
				try {
					PlantsVsZombies.playSound("gameover.wav");
				} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Timer delay=new Timer(10000,new ActionListener(){
					public void actionPerformed(ActionEvent e){
						PlantsVsZombies.restart();
						//PlantsVsZombies.newLevel();
					}
				});
				delay.setRepeats(false);
				delay.start();
				
			}
			
			
			
			
			
			
		/*	Timer delay=new Timer(3000,new ActionListener(){
				public void actionPerformed(ActionEvent e){
					PlantsVsZombies.restart();
				}
			});
			delay.setRepeats(false);
			delay.start();
		*/	
		}
		
		if(spaces!=null){
			for(Space i:spaces){
				if(i.getPlant()!=null&&i.getPlant().containsPoint(x+30)){
					eatPlant(i.getPlant());
				}
			}
		}
		
		if(e.getSource().equals(eatingTimer)){
			
			eatingPlant.changeHp(-10);
		}
		
		if(eatingPlant!=null&&eatingPlant.getHp()<=0){
			stopEating(eatingPlant);
		}
		
		
	}
	
	public void eatPlant(Plant plant){
		eating=true;
		eatingTimer.start();
		eatingPlant=plant;
	}
	public void stopEating(Plant plant){
		eating=false;
		eatingTimer.stop();
	}
	
	
	public void getHit(Bullet bullet){
		hp-=bullet.getDamage();
		if(bullet instanceof SnowPea){
			if(slow){
				slowTime=10;
			}
			else{
				slowTime=10;
				zombieTimer=new Timer(200,this);
				zombieTimer.start();
			}
		}
		
	}
	
	public boolean containsPoint(int bulletX){
		return (bulletX>x&&bulletX<x+image.getWidth(null));
	}
	
	public abstract Zombie getNewZombie(int row, ContentPanel contentPanel);

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

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getRow() {
		return row;
	}

	
	public void setRow(int row) {
		this.row = row;
	}

	public boolean isEating() {
		return eating;
	}

	public void setEating(boolean eating) {
		this.eating = eating;
	}

	public ContentPanel getContentPanel() {
		return contentPanel;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getRowY() {
		return rowY;
	}

	public void setRowY(int rowY) {
		this.rowY = rowY;
	}
	
	public void gameOver(){
		zombieTimer.stop();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	
}

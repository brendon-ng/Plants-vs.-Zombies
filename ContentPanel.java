import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.Timer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class ContentPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	
	private SelectionPanel selectionPanel;
	
	private Space[][] spaces=new Space[5][9];
	private ArrayList<Bullet> bullets0=new ArrayList<Bullet>();
	private ArrayList<Bullet> bullets1=new ArrayList<Bullet>();
	private ArrayList<Bullet> bullets2=new ArrayList<Bullet>();
	private ArrayList<Bullet> bullets3=new ArrayList<Bullet>();
	private ArrayList<Bullet> bullets4=new ArrayList<Bullet>();
	private ArrayList<Zombie> zombies0=new ArrayList<Zombie>();
	private ArrayList<Zombie> zombies1=new ArrayList<Zombie>();
	private ArrayList<Zombie> zombies2=new ArrayList<Zombie>();
	private ArrayList<Zombie> zombies3=new ArrayList<Zombie>();
	private ArrayList<Zombie> zombies4=new ArrayList<Zombie>();
	private ArrayList<Sun> suns= new ArrayList<Sun>();
	
	private Zombie front0=null;
	private Zombie front1=null;
	private Zombie front2=null;
	private Zombie front3=null;
	private Zombie front4=null;
	
	private Zombie[] possibleZombies;
	private Zombie[] previousLevelZombies;
	
	
	Timer mainTimer = new Timer(30,this);
	Timer zombieTimer=new Timer(10000,this);
	Timer sunTimer=new Timer(10700,this);
	Timer levelTimer=new Timer(1000,this);
	Timer zombieTimer2=new Timer(7000,this);
	int seconds=0;
	
	private int level;
	private int sun;

	private int mouseX;
	private int mouseY;
	
	private boolean gameOver=false;
	private boolean victory=false;
	Clip clip;
	
	public final static int COL1 = 255,
			COL2=334,     
            COL3 = 412,   
            COL4=492, 
            COL5=574,   
            COL6=657,
            COL7=738,
            COL8=817,
            COL9=897,
            COL10=994,
            ROW1=67,
            ROW2=164,
            ROW3=265,
            ROW4=366,
            ROW5=462,
            ROW6=560;
           
	
	public ContentPanel(int level,String[]choices){
		this.requestFocusInWindow();
		
		this.repaint();

		this.setLayout(new BorderLayout());
		
		Plant plant1=null;
		Plant plant2=null;
		Plant plant3=null;
		Plant plant4=null;
		Plant plant5=null;
		if(level>1){
			for(int i=0;i<5;i++){
				String str=choices[i];
				Plant plant=null;
				if(str.equals("peashooter")){
					plant=new PlantPeashooter(new Space(10,10),this);
				}
				if(str.equals("sunflower")){
					plant=new PlantSunflower(new Space(10,10),this);
				}
				if(str.equals("walnut")){
					plant=new PlantWalnut(new Space(10,10),this);
				}
				if(str.equals("snowpea")){
					plant=new PlantSnowPea(new Space(10,10),this);
				}
				if(str.equals("jalapeno")){
					plant=new PlantJalapeno(new Space(10,10),this);
				}
				if(str.equals("repeater")){
					plant=new PlantRepeater(new Space(10,10),this);
				}
				if(str.equals("tallnut")){
					plant=new PlantTallNut(new Space(10,10),this);
				}
				if(str.equals("twinsunflower")){
					plant=new PlantTwinSunflower(new Space(10,10),this);
				}
				if(str.equals("cactus")){
					plant=new PlantCactus(new Space(10,10),this);
				}
				if(str.equals("melonpult")){
					plant=new PlantMelonPult(new Space(10,10),this);
				}
				if(str.equals("sunflower")){
					plant=new PlantSunflower(new Space(10,10),this);
				}
				if(str.equals("threepeater")){
					plant=new PlantThreepeater(new Space(10,10),this);
				}
				
				if(i==0){
					plant1=plant;
				}
				if(i==1){
					plant2=plant;
				}
				if(i==2){
					plant3=plant;
				}
				if(i==3){
					plant4=plant;
				}
				if(i==4){
					plant5=plant;
				}
			}
			selectionPanel=new SelectionPanel(plant1,plant2,plant3,plant4,plant5,this);
		}
		else{
			selectionPanel=new SelectionPanel(new PlantPeashooter(new Space(10,10),this),new PlantSunflower(new Space(10,10),this),null,null,null,this);
		}
		
		
		this.add(selectionPanel,BorderLayout.WEST);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.level=level;
		mainTimer.start();
		zombieTimer.start();
		sunTimer.start();
		zombieTimer2.start();
		levelTimer.start();
		
		
		// https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
		try {
			File soundFile = new File("grasswalk.wav"); // Open an audio input stream from a wave File
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip(); // Get a sound clip resource.
			clip.open(audioIn); // Open audio clip and load samples from the audio input stream.
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}
		
		sun=50;
		
		if(level==1){
			possibleZombies=new Zombie[]{new ZombieBasic(10,this)};
			previousLevelZombies=new Zombie[]{new ZombieBasic(10,this)};
		}
		else if(level==2){
			possibleZombies=new Zombie[]{new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieCone(10,this)};
			previousLevelZombies=new Zombie[]{new ZombieBasic(10,this)};
		}
		else if(level==3){
			possibleZombies=new Zombie[]{new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieCone(10,this),new ZombieBucket(10,this)};
			previousLevelZombies=new Zombie[]{new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieCone(10,this)};
		}
		else if(level==4){
			possibleZombies=new Zombie[]{new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieCone(10,this),new ZombieBucket(10,this),new ZombieCone(10,this),new ZombieBucket(10,this),new ZombieTrack(10,this)};
			previousLevelZombies=new Zombie[]{new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieCone(10,this),new ZombieBucket(10,this)};
		}
		else{
			possibleZombies=new Zombie[]{new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieCone(10,this),new ZombieBucket(10,this),new ZombieCone(10,this),new ZombieBucket(10,this),new ZombieTrack(10,this),new ZombieTrack(10,this), new ZombieFootball(10,this), new ZombieFootball(10,this)};
			previousLevelZombies=new Zombie[]{new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieBasic(10,this),new ZombieCone(10,this),new ZombieBucket(10,this),new ZombieCone(10,this),new ZombieBucket(10,this),new ZombieTrack(10,this)};
		}
		
		for(int i=0;i<spaces.length;i++){					//Create spaces
			for(int j=0;j<spaces[0].length;j++){
				spaces[i][j]=new Space(i,j);
			}
		}
		
		bullets0.clear();
		bullets1.clear();
		bullets2.clear();
		bullets3.clear();
		bullets4.clear();
		
		
		
		
		
	
	}
	
	public void paintComponent(Graphics g){	
		Image img = new ImageIcon("frontyard.png").getImage();
		g.drawImage(img, 0, 0, null);							//Draw background
		
		g.drawImage(new ImageIcon("sunlabel.png").getImage(), 125, 15, null);
		Font font=new Font("Helvetica",Font.BOLD,30);			//Draw Sun amount indicator
		g.setFont(font);
		FontMetrics dimensions= g.getFontMetrics(font);
		g.drawString(sun+"", 164+(100-dimensions.stringWidth(sun+""))/2, 20+((32-dimensions.getHeight())/2)+dimensions.getAscent());
																//Draws sun amount in the center of sun label
		g.drawImage(new ImageIcon("shovel.jpg").getImage(), 285, 14, null);
		
		for(int i=0;i<spaces.length;i++){						//paint each space
			for(int j=0;j<spaces[0].length;j++){
				spaces[i][j].draw(g);
			}
		}

		drawSelection(g,mouseX,mouseY);							//mouse selection (if applicable)
		
		for(int i=0;i<bullets0.size();i++){						//draw each bullet
			if(bullets0.get(i)!=null)
				bullets0.get(i).draw(g);
		}	
		for(int i=0;i<bullets1.size();i++){
			if(bullets1.get(i)!=null)
				bullets1.get(i).draw(g);
		}
		for(int i=0;i<bullets2.size();i++){
			if(bullets2.get(i)!=null)
				bullets2.get(i).draw(g);
		}
		for(int i=0;i<bullets3.size();i++){
			if(bullets3.get(i)!=null)
				bullets3.get(i).draw(g);
		}
		for(int i=0;i<bullets4.size();i++){
			if(bullets4.get(i)!=null)
				bullets4.get(i).draw(g);
		}
		
		
		
		if(zombies0.size()>0){
			for(int i=zombies0.size()-1;i>=0;i--){						//draw each Zombie
				if(zombies0.get(i)!=null)
					zombies0.get(i).draw(g);
			}
		}
		if(zombies1.size()>0){
			for(int i=zombies1.size()-1;i>=0;i--){						
				if(zombies1.get(i)!=null)
					zombies1.get(i).draw(g);
			}
		}
		if(zombies2.size()>0){
			for(int i=zombies2.size()-1;i>=0;i--){						
				if(zombies2.get(i)!=null)
					zombies2.get(i).draw(g);
			}
		}
		if(zombies3.size()>0){
			for(int i=zombies3.size()-1;i>=0;i--){					
				if(zombies3.get(i)!=null)
					zombies3.get(i).draw(g);
			}
		}
		if(zombies4.size()>0){
			for(int i=zombies4.size()-1;i>=0;i--){					
				if(zombies4.get(i)!=null)
					zombies4.get(i).draw(g);
			}
		}
		
		for(int i=0;i<suns.size();i++){
			suns.get(i).draw(g);
		}
		
		
		drawMessage(g);
		
		
		if(gameOver==true){
			g.drawImage(new ImageIcon("gameover.png").getImage(), 275, 85, null);
			
		}
		
		
		g.setFont(new Font("Helvetica",Font.PLAIN,20));
		//g.drawString("X: "+mouseX+", Y: "+mouseY, 875, 585); //temp
		
		
		
		
		
	
		
	
		
		
		
		
		
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		
		if(mouseX>=285&&mouseX<=325&&mouseY>=14&&mouseY<=54){
			if(selectionPanel.getPlantSelection() instanceof Shovel){
				selectionPanel.setPlantSelection(null);
			}
			else{
				selectionPanel.shovel();
			}
			
			
		}
	
		
		int row=100;
		int column=100;
		
		
		if(selectionPanel.getPlantSelection()!=null){
			if(mouseX>=COL1&&mouseX<COL2){
				column=0;
			}
			else if(mouseX>=COL2&&mouseX<COL3){
				column=1;
			}
			else if(mouseX>=COL3&&mouseX<COL4){
				column=2;
			}
			else if(mouseX>=COL4&&mouseX<COL5){
				column=3;
			}
			else if(mouseX>=COL5&&mouseX<COL6){
				column=4;
			}
			else if(mouseX>=COL6&&mouseX<COL7){
				column=5;
			}
			else if(mouseX>=COL7&&mouseX<COL8){
				column=6;
			}
			else if(mouseX>=COL8&&mouseX<COL9){
				column=7;
			}
			else if(mouseX>=COL9&&mouseX<COL10){
				column=8;
			}
			else{
				if(!(selectionPanel.getPlantSelection() instanceof Shovel)){
					selectionPanel.setPlantSelection(null);
					repaint();
				}
			}
			
			if(mouseY>=ROW1&&mouseY<ROW2){
				row=0;
			}
			else if(mouseY>=ROW2&&mouseY<ROW3){
				row=1;
			}
			else if(mouseY>=ROW3&&mouseY<ROW4){
				row=2;
			}
			else if(mouseY>=ROW4&&mouseY<ROW5){
				row=3;
			}
			else if(mouseY>=ROW5&&mouseY<ROW6){
				row=4;
			}
			else{
				if(!(selectionPanel.getPlantSelection() instanceof Shovel)){
					selectionPanel.setPlantSelection(null);
					repaint();
				}
				
			}
		}
		
		
		if(row<5&&column<9&&spaces[row][column]!=null&&(spaces[row][column].isFilled()==false||selectionPanel.getPlantSelection() instanceof Shovel)){
			selectionPanel.plant(row,column);
			//System.out.println("planted");
		}
		
		
		for(int j=0;j<suns.size();j++){
			Sun i=suns.get(j);
			
			if(mouseX>i.getX()-60&&mouseX<i.getX()&&mouseY>i.getY()&&mouseY<i.getY()+60){
				changeSun(25);
				i.setX(1000);
				i.setY(1000);
				
				suns.remove(j);
				j--;
			}
		}
		
		
	
	}

	public void placeRandomZombie(){
		int row=(int)(Math.random()*5);
		Zombie zombie;
		Zombie newZombie=null;
		if(seconds<20){
			
		}
		if(seconds>=20&&seconds<100){
			Zombie[]basicZombies=new Zombie[]{new ZombieBasic(10,this)};
			zombie=basicZombies[0];
			newZombie=zombie.getNewZombie(row, this);
		}
		if(seconds>=100&&seconds<180){
			zombie=previousLevelZombies[(int)(Math.random()*previousLevelZombies.length)];
			newZombie=zombie.getNewZombie(row, this);
		}
		if(seconds>=180&&seconds<195){
			zombie=previousLevelZombies[(int)(Math.random()*previousLevelZombies.length)];
			newZombie=zombie.getNewZombie(row, this);
			//wave
		}
		if(seconds>=195&&seconds<285){
			zombie=possibleZombies[(int)(Math.random()*possibleZombies.length)];
			newZombie=zombie.getNewZombie(row, this);
			
		}
		if(seconds>=285&&seconds<300){
			zombie=possibleZombies[(int)(Math.random()*possibleZombies.length)];
			newZombie=zombie.getNewZombie(row, this);
			//wave
		}
		if(seconds>=300){
			
		}
		
		
		
		switch(row){
		case 0:
			zombies0.add(newZombie);
			break;
		case 1:
			zombies1.add(newZombie);
			break;
		case 2:
			zombies2.add(newZombie);
			break;
		case 3:
			zombies3.add(newZombie);
			break;
		case 4:
			zombies4.add(newZombie);
			break;
		
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(levelTimer)&&seconds==1){
			zombieTimer=new Timer(0,this);
			zombieTimer.stop();
			zombieTimer2.stop();
		}
		if(e.getSource().equals(levelTimer)&&seconds==20){
			zombieTimer=new Timer(15000,this);
			placeRandomZombie();
			zombieTimer.start();
			zombieTimer2.stop();
			//System.out.println("start level");
		}
		if(e.getSource().equals(levelTimer)&&seconds==100){
			zombieTimer=new Timer(13000,this);
			placeRandomZombie();
			zombieTimer.start();
			zombieTimer2=new Timer(11000,this);
			placeRandomZombie();
			zombieTimer2.start();
		}
		if(e.getSource().equals(levelTimer)&&seconds==180){
			zombieTimer=new Timer(3000,this);
			placeRandomZombie();
			zombieTimer.start();
			zombieTimer2=new Timer (4000,this);
			placeRandomZombie();
			zombieTimer2.start();
			//wave
		}
		if(e.getSource().equals(levelTimer)&&seconds==195){
			zombieTimer=new Timer(13000,this);
			placeRandomZombie();
			zombieTimer.start();
			zombieTimer2=new Timer(11000,this);
			placeRandomZombie();
			zombieTimer2.start();
		}
		if(e.getSource().equals(levelTimer)&&seconds==285){
			zombieTimer=new Timer(3000,this);
			placeRandomZombie();
			zombieTimer.start();
			zombieTimer2=new Timer (2000,this);
			placeRandomZombie();
			zombieTimer2.start();
			//wave
		}
		if(e.getSource().equals(levelTimer)&&seconds==300){
			zombieTimer.stop();
			zombieTimer2.stop();
			
		}
		if(e.getSource().equals(levelTimer)&&seconds>300){
			boolean over=true;
			for(Zombie i:zombies0){
				if(i!=null&&i.getX()<1023&&i.getX()>150){
					over=false;
				}
			}
			for(Zombie i:zombies1){
				if(i!=null&&i.getX()<1023&&i.getX()>150){
					over=false;
				}
			}
			for(Zombie i:zombies2){
				if(i!=null&&i.getX()<1023&&i.getX()>150){
					over=false;
				}
			}
			for(Zombie i:zombies3){
				if(i!=null&&i.getX()<1023&&i.getX()>150){
					over=false;
				}
			}
			for(Zombie i:zombies4){
				if(i!=null&&i.getX()<1023&&i.getX()>150){
					over=false;
				}
			}
			
			if(over){
				//System.out.println("You Win");
				levelTimer.stop();
				mainTimer.stop();
				if(!victory){
					victory=true;
					try {
						PlantsVsZombies.playSound("victory.wav");
					} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				repaint();
				gameOver();
				Timer delay=new Timer(5000,new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(level<5){
							PlantsVsZombies.newLevel();
							stopMusic();
						}
						else{
							PlantsVsZombies.win();
						}
						
					}
				});
				delay.setRepeats(false);
				delay.start();
			}
		}
		
		
		if(e.getSource().equals(mainTimer)){
			repaint();
		}
		if(e.getSource().equals(zombieTimer)){
			//System.out.println("sa ");
		}
		if(e.getSource().equals(zombieTimer)||e.getSource().equals(zombieTimer2)){
			//System.out.print("zombieTimer");
			placeRandomZombie();
		}
		if(e.getSource().equals(sunTimer)){
			suns.add(new Sun(new Space(10,10),this,true));
		}
		if(e.getSource().equals(levelTimer)){
			seconds++;
			//System.out.println(seconds);
			
		}
		
		
		if(zombies0.size()>0){
			int x=100000;
			for(Zombie i:zombies0){
				if (i!=null&&i.getX()<x){
					x=i.getX();
					front0=i;
				}
			}
		}
		else{
			front0=null;
		}
		if(zombies1.size()>0){
			int x=100000;
			for(Zombie i:zombies1){
				if (i!=null&&i.getX()<x){
					x=i.getX();
					front1=i;
				}
			}
		}
		else{
			front1=null;
		}
		if(zombies2.size()>0){
			int x=100000;
			for(Zombie i:zombies2){
				if (i!=null&&i.getX()<x){
					x=i.getX();
					front2=i;
				}
			}
		}
		else{
			front2=null;
		}
		if(zombies3.size()>0){
			int x=100000;
			for(Zombie i:zombies3){
				if (i!=null&&i.getX()<x){
					x=i.getX();
					front3=i;
				}
			}
		}
		else{
			front3=null;
		}
		if(zombies4.size()>0){
			int x=100000;
			for(Zombie i:zombies4){
				if (i!=null&&i.getX()<x){
					x=i.getX();
					front4=i;
				}
			}
		}
		else{
			front4=null;
		}
		
		
	}

	
	public void playMusic(String fileName) {
		Clip clip1;
		// https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
		try {
			File soundFile = new File(fileName); // Open an audio input stream from a wave File
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip1 = AudioSystem.getClip(); // Get a sound clip resource.
			clip1.open(audioIn); // Open audio clip and load samples from the audio input stream.
			clip1.start();
			clip1.loop(clip.LOOP_CONTINUOUSLY);
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}
	
	public void gameOver(){
		
		
		levelTimer.stop();
		mainTimer.stop();
		zombieTimer.stop();
		sunTimer.stop();
		zombieTimer2.stop();
		clip.stop();
		
		for(Zombie i:zombies0){
			if(i!=null)
				i.gameOver();
		}
		for(Zombie i:zombies1){
			if(i!=null)
				i.gameOver();
		}
		for(Zombie i:zombies2){
			if(i!=null)
				i.gameOver();
		}
		for(Zombie i:zombies3){
			if(i!=null)
				i.gameOver();
		}
		for(Zombie i:zombies4){
			if(i!=null)
				i.gameOver();
		}
		for(Bullet i: bullets0){
			if(i!=null)
			i.gameOver();
		}
		for(Bullet i: bullets1){
			if(i!=null)
			i.gameOver();
		}
		for(Bullet i: bullets2){
			if(i!=null)
			i.gameOver();
		}
		for(Bullet i: bullets3){
			if(i!=null)
			i.gameOver();
		}
		for(Bullet i: bullets4){
			if(i!=null)
			i.gameOver();
		}
		for(Space[]i:spaces){
			for(Space j:i){
				if(j.getPlant()!=null)
					j.getPlant().gameOver();
			}
		}
		for(Sun i: suns){
			if(i!=null)
				i.gameOver();
		}
		repaint();
		
		
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		repaint();
	
		
	}
	public void drawSelection(Graphics g, int x, int y){
		
		if(selectionPanel.getPlantSelection()!=null){
			g.drawImage(selectionPanel.getPlantSelection().getImage(), x-37, y-37, null);
		}
	}
	
	public void drawMessage(Graphics g){
		if(seconds<6){
			if(level==1)
				g.drawImage(new ImageIcon("level1.png").getImage(), 275, 85, null);
			if(level==2)
				g.drawImage(new ImageIcon("level2.png").getImage(), 275, 85, null);
			if(level==3)
				g.drawImage(new ImageIcon("level3.png").getImage(), 275, 85, null);
			if(level==4)
				g.drawImage(new ImageIcon("level4.png").getImage(), 275, 85, null);
			if(level==5)
				g.drawImage(new ImageIcon("level5.png").getImage(), 275, 85, null);
		}
		if(victory){
			g.drawImage(new ImageIcon("victory.png").getImage(), 275, 85, null);
			if(level<4)
				g.drawImage(new ImageIcon("newplants.png").getImage(), 275, 85, null);
		}
		if(seconds>=180&&seconds<183){
			g.drawImage(new ImageIcon("firstwave.png").getImage(),275,85,null);
		}
		if(seconds>=285&&seconds<288){
			g.drawImage(new ImageIcon("finalwave.png").getImage(),275,85,null);
		}
		
	}

	public Space[][] getSpaces() {
		return spaces;
	}

	public void setSpaces(Space[][] spaces) {
		this.spaces = spaces;
	}

	public int getSun() {
		return sun;
	}

	public void setSun(int sun) {
		this.sun = sun;
	}
	
	public void changeSun(int sun) {
		this.sun += sun;
	}

	

	public ArrayList<Bullet> getBullets0() {
		return bullets0;
	}

	public void setBullets0(ArrayList<Bullet> bullets0) {
		this.bullets0 = bullets0;
	}

	public ArrayList<Bullet> getBullets1() {
		return bullets1;
	}

	public void setBullets1(ArrayList<Bullet> bullets1) {
		this.bullets1 = bullets1;
	}

	public ArrayList<Bullet> getBullets2() {
		return bullets2;
	}

	public void setBullets2(ArrayList<Bullet> bullets2) {
		this.bullets2 = bullets2;
	}

	public ArrayList<Bullet> getBullets3() {
		return bullets3;
	}

	public void setBullets3(ArrayList<Bullet> bullets3) {
		this.bullets3 = bullets3;
	}

	public ArrayList<Bullet> getBullets4() {
		return bullets4;
	}

	public void setBullets4(ArrayList<Bullet> bullets4) {
		this.bullets4 = bullets4;
	}

	
	public ArrayList<Zombie> getZombies0() {
		return zombies0;
	}

	public void setZombies0(ArrayList<Zombie> zombies0) {
		this.zombies0 = zombies0;
	}

	public ArrayList<Zombie> getZombies1() {
		return zombies1;
	}

	public void setZombies1(ArrayList<Zombie> zombies1) {
		this.zombies1 = zombies1;
	}

	public ArrayList<Zombie> getZombies2() {
		return zombies2;
	}

	public void setZombies2(ArrayList<Zombie> zombies2) {
		this.zombies2 = zombies2;
	}

	public ArrayList<Zombie> getZombies3() {
		return zombies3;
	}

	public void setZombies3(ArrayList<Zombie> zombies3) {
		this.zombies3 = zombies3;
	}

	public ArrayList<Zombie> getZombies4() {
		return zombies4;
	}

	public void setZombies4(ArrayList<Zombie> zombies4) {
		this.zombies4 = zombies4;
	}

	public ArrayList<Sun> getSuns() {
		return suns;
	}

	public void setSuns(ArrayList<Sun> suns) {
		this.suns = suns;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		
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

	public Zombie getFront1() {
		return front1;
	}

	public Zombie getFront2() {
		return front2;
	}

	public Zombie getFront3() {
		return front3;
	}

	public Zombie getFront4() {
		return front4;
	}

	public Zombie getFront0() {
		return front0;
		
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void stopMusic(){
		clip.stop();
	}
	
	

		
	
	
	
	
	
	
}

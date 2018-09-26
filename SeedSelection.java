import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SeedSelection extends JPanel implements MouseListener, MouseMotionListener{
	private int level;
	private int mouseX;
	private int mouseY;
	private int selections=0;
	private String[] choices=new String[5];
	private Image seedInfo;
	Clip clip;
	private Image[]images=new Image[5];
	
	public SeedSelection(int level){
		this.level=level;
		this.requestFocusInWindow();
		this.repaint();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		seedInfo=new ImageIcon("blank.png").getImage();
		try {
			File soundFile = new File("chooseyourseeds.wav"); // Open an audio input stream from a wave File
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip(); // Get a sound clip resource.
			clip.open(audioIn); // Open audio clip and load samples from the audio input stream.
			clip.start();
			clip.loop(clip.LOOP_CONTINUOUSLY);
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	public void paintComponent(Graphics g){	
		Image img = new ImageIcon("plantselection.png").getImage();
		g.drawImage(img, 0, 0, null);							//Draw background
		
		
		//279,43
		g.drawImage(seedInfo, 279, 43, null);
		
		if(images[0]!=null){
			g.drawImage(images[0], 53, 105, null);
		}
		if(images[1]!=null){
			g.drawImage(images[1], 53, 190, null);
		}
		if(images[2]!=null){
			g.drawImage(images[2], 53, 275, null);
		}
		if(images[3]!=null){
			g.drawImage(images[3], 53, 360, null);
		}
		if(images[4]!=null){
			g.drawImage(images[4], 53, 445, null);
		}
		
		if(level==2)
			g.drawImage(new ImageIcon("blocklevel2.png").getImage(), 641, 43, null);
		if(level==3)
			g.drawImage(new ImageIcon("blocklevel3.png").getImage(), 641, 43, null);
		
		g.drawImage(new ImageIcon("5plants.png").getImage(), 841, 435, null);
		//g.setFont(new Font("Helvetica",Font.PLAIN,20));
		//g.drawString("X: "+mouseX+", Y: "+mouseY, 875, 585); //temp
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		
		int row=10;
		int column=10;
		if(mouseX>=645&&mouseX<=738){
			column=0;
		}
		if(mouseX>=744&&mouseX<=838){
			column=1;
		}
		if(mouseX>=841&&mouseX<=937){
			column=2;
		}
		
		if(mouseY>=74&&mouseY<=193){
			row=0;
		}
		if(mouseY>=195&&mouseY<=313){
			row=1;
		}
		if(mouseY>=314&&mouseY<=432){
			row=2;
		}
		if(mouseY>=433&&mouseY<=550){
			row=3;
		}
		
		if(row>3||column>2||(row==3&&column==2)){
			seedInfo=new ImageIcon("blank.png").getImage();
		}
		if(row==0&&column==0){
			seedInfo=new ImageIcon("peashootercard.png").getImage();
		}
		if(row==0&&column==1){
			seedInfo=new ImageIcon("sunflowercard.png").getImage();
		}
		if(row==0&&column==2){
			seedInfo=new ImageIcon("walnutcard.png").getImage();
		}
		if(row==1&&column==0){
			seedInfo=new ImageIcon("snowpeacard.png").getImage();
		}
		if(row==1&&column==1){
			seedInfo=new ImageIcon("jalapenocard.png").getImage();
		}
		if(row==1&&column==2&&level>=3){
			seedInfo=new ImageIcon("repeatercard.png").getImage();
		}
		if(row==2&&column==0&&level>=3){
			seedInfo=new ImageIcon("tallnutcard.png").getImage();
		}
		if(row==2&&column==1&&level>=3){
			seedInfo=new ImageIcon("twinsunflowercard.png").getImage();
		}
		if(row==2&&column==2&&level>=4){
			seedInfo=new ImageIcon("cactuscard.png").getImage();
		}
		if(row==3&&column==0&&level>=4){
			seedInfo=new ImageIcon("melonpultcard.png").getImage();
		}
		if(row==3&&column==1&&level>=4){
			seedInfo=new ImageIcon("threepeatercard.png").getImage();
		}
		
		repaint();
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		repaint();
		
		int row=10;
		int column=10;
		if(mouseX>=645&&mouseX<=738){
			column=0;
		}
		if(mouseX>=744&&mouseX<=838){
			column=1;
		}
		if(mouseX>=841&&mouseX<=937){
			column=2;
		}
		
		if(mouseY>=74&&mouseY<=193){
			row=0;
		}
		if(mouseY>=195&&mouseY<=313){
			row=1;
		}
		if(mouseY>=314&&mouseY<=432){
			row=2;
		}
		if(mouseY>=433&&mouseY<=550){
			row=3;
		}
		
		
		
		if(row>3||column>2||(row==3&&column==2)){
			
		}
		if(row==0&&column==0){
			choices[selections]="peashooter";
			images[selections]=new ImageIcon("peashooter.png").getImage();
			selections++;
			
		}
		if(row==0&&column==1){
			choices[selections]="sunflower";
			images[selections]=new ImageIcon("sunflower.png").getImage();
			selections++;
		}
		if(row==0&&column==2){
			choices[selections]="walnut";
			images[selections]=new ImageIcon("walnut.png").getImage();
			selections++;
		}
		if(row==1&&column==0){
			choices[selections]="snowpea";
			images[selections]=new ImageIcon("snowpeashooter.png").getImage();
			selections++;
		}
		if(row==1&&column==1){
			choices[selections]="jalapeno";
			images[selections]=new ImageIcon("jalapeno.png").getImage();
			selections++;
		}
		if(row==1&&column==2&&level>=3){
			choices[selections]="repeater";
			images[selections]=new ImageIcon("repeater.png").getImage();
			selections++;
		}
		if(row==2&&column==0&&level>=3){
			choices[selections]="tallnut";
			images[selections]=new ImageIcon("tallnut.png").getImage();
			selections++;
		}
		if(row==2&&column==1&&level>=3){
			choices[selections]="twinsunflower";
			images[selections]=new ImageIcon("twinsunflower.png").getImage();
			selections++;
		}
		if(row==2&&column==2&&level>=4){
			choices[selections]="cactus";
			images[selections]=new ImageIcon("cactus.png").getImage();
			selections++;
		}
		if(row==3&&column==0&&level>=4){
			choices[selections]="melonpult";
			images[selections]=new ImageIcon("melonpult.png").getImage();
			selections++;
		}
		if(row==3&&column==1&&level>=4){
			choices[selections]="threepeater";
			images[selections]=new ImageIcon("threepeater.png").getImage();
			selections++;
		}
		repaint();
		
		if(selections==5){
			Timer delay=new Timer(500,new ActionListener(){
				public void actionPerformed(ActionEvent e){
					clip.stop();
					PlantsVsZombies.beginLevel(choices);
				}
			});
			delay.setRepeats(false);
			delay.start();
			
		}
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

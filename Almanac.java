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

public class Almanac extends JPanel implements MouseListener, MouseMotionListener {
	private int mouseX;
	private int mouseY;
	private Clip clip;
	private Image seedInfo;
	public Almanac(){
	
		this.requestFocusInWindow();
		this.repaint();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		
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
		repaint();
	}
	
	public void paintComponent(Graphics g){	
		Image img = new ImageIcon("amanac.png").getImage();
		g.drawImage(img, 0, 0, null);							//Draw background
		
		
		//279,43
		g.drawImage(seedInfo, 279, 43, null);
		
		
		g.setFont(new Font("Helvetica",Font.PLAIN,20));
	//	g.drawString("X: "+mouseX+", Y: "+mouseY, 875, 585); //temp
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
		if(row==1&&column==2){
			seedInfo=new ImageIcon("repeatercard.png").getImage();
		}
		if(row==2&&column==0){
			seedInfo=new ImageIcon("tallnutcard.png").getImage();
		}
		if(row==2&&column==1){
			seedInfo=new ImageIcon("twinsunflowercard.png").getImage();
		}
		if(row==2&&column==2){
			seedInfo=new ImageIcon("cactuscard.png").getImage();
		}
		if(row==3&&column==0){
			seedInfo=new ImageIcon("melonpultcard.png").getImage();
		}
		if(row==3&&column==1){
			seedInfo=new ImageIcon("threepeatercard.png").getImage();
		}
		
		repaint();
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		repaint();
		
		if(mouseX>=18&&mouseX<=233&&mouseY>=249&&mouseY<=368){
			clip.stop();
			PlantsVsZombies.restart();
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

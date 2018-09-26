import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Win extends JPanel implements MouseListener {
	private Clip clip;
	
	public Win(){
	
		this.requestFocusInWindow();
		this.repaint();
		this.addMouseListener(this);
		
		
		
		try {
			File soundFile = new File("themesong.wav"); // Open an audio input stream from a wave File
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
		Image img = new ImageIcon("win.png").getImage();
		g.drawImage(img, 0, 0, null);							//Draw background
		
		
		
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		clip.stop();
		PlantsVsZombies.restart();
		
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

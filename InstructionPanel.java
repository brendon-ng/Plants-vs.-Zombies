import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class InstructionPanel extends JPanel implements MouseListener{
	Clip clip;
	public InstructionPanel(){
	
		this.requestFocusInWindow();
		this.repaint();
		this.addMouseListener(this);
		
		
		
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
		Image img = new ImageIcon("instructions.png").getImage();
		g.drawImage(img, 0, 0, null);							//Draw background
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Timer delay=new Timer(500,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clip.stop();
				PlantsVsZombies.level1();
			}
		});
		delay.setRepeats(false);
		delay.start();
		
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

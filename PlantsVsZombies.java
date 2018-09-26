import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class PlantsVsZombies {
	private static JFrame window = new JFrame("Plants Vs. Zombies");
	private static int level;
	private static Clip clip;
	
	public static void main(String[] args) {
		
		window.setSize(1024, 610);
		window.setLocation(50, 50);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel introScreen=new IntroScreen();
		window.setContentPane(introScreen);
		
		window.setVisible(true);
		introScreen.requestFocusInWindow();
		
		try {
			File soundFile = new File("thememusic.wav"); // Open an audio input stream from a wave File
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
	
	public static void mainMenu(){
		JPanel introScreen=new IntroScreen();
		window.setContentPane(introScreen);
		
		window.setVisible(true);
		introScreen.requestFocusInWindow();
		
		try {
			File soundFile = new File("thememusic.wav"); // Open an audio input stream from a wave File
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
	
	public static void playLevel(){
		clip.stop();
		InstructionPanel content= new InstructionPanel();
		window.setContentPane(content);
		window.setVisible(true);
		content.requestFocusInWindow();  
	}
	public static void level1(){
		clip.stop();
		level=1;
		ContentPanel content= new ContentPanel(1,new String[]{"peashooter","sunflower",null,null,null});
		window.setContentPane(content);
		window.setVisible(true);
		content.requestFocusInWindow();  
	}
	
	public static void newLevel(){
		clip.stop();
		level++;
		SeedSelection seedSelection= new SeedSelection(level);
		window.setContentPane(seedSelection);
		window.setVisible(true);
	}
	
	public static void beginLevel(String[]choices){
		clip.stop();
		ContentPanel content= new ContentPanel(level,choices);
		window.setContentPane(content);
		window.setVisible(true);
	}
	public static void restart(){
		clip.stop();
		Timer delay=new Timer(500,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mainMenu();
			}
		});
		delay.setRepeats(false);
		delay.start();
		
	}
	
	public static void almanac(){
		clip.stop();
		Almanac content= new Almanac();
		window.setContentPane(content);
		window.setVisible(true);
	}
	
	public static void howToPlay(){
		clip.stop();
		HowTo content= new HowTo();
		window.setContentPane(content);
		window.setVisible(true);
	}
	
	public static void win(){
		clip.stop();
		Win content= new Win();
		window.setContentPane(content);
		window.setVisible(true);
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		PlantsVsZombies.level = level;
	}
	
	public static void playSound(String fileName) throws MalformedURLException, LineUnavailableException, UnsupportedAudioFileException, IOException{
	    File url = new File(fileName);
	    Clip clip = AudioSystem.getClip();

	    AudioInputStream ais = AudioSystem.
	        getAudioInputStream( url );
	    clip.open(ais);
	    clip.start();
	    
	    //Code From https://stackoverflow.com/questions/20354508/sound-effects-in-java
	}
	
}




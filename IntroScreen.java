import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class IntroScreen extends JPanel implements MouseListener{
	public Image loading;
	private int mouseX;
	private int mouseY;
	public IntroScreen(){
		requestFocusInWindow();
		repaint();
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		Image img = new ImageIcon("introscreen.png").getImage();
		g.drawImage(img, 0, 0, null);
		if(loading!=null)
			g.drawImage(loading, (1024/2)-loading.getWidth(null)/2, (610/2)-loading.getHeight(null)/2, null);

		g.setFont(new Font("Helvetica",Font.PLAIN,20));
	//	g.drawString("X: "+mouseX+", Y: "+mouseY, 875, 585); //temp
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		repaint();
		if(mouseX>=307&&mouseX<=720&&mouseY>=370&&mouseY<=464){
			loading=new ImageIcon("loadingadventure.png").getImage();
			repaint();
			System.out.print("clicked");
			repaint();
			
			Timer delay=new Timer(100,new ActionListener(){
				public void actionPerformed(ActionEvent e){
					playLevel();
					//PlantsVsZombies.newLevel();
				}
			});
			delay.setRepeats(false);
			delay.start();
		}
		
		if(mouseX>=551&&mouseX<=892&&mouseY>=496&&mouseY<=573){
			PlantsVsZombies.almanac();
		}
		
		if(mouseX>=131&&mouseX<=475&&mouseY>=496&&mouseY<=573){
			PlantsVsZombies.howToPlay();
		}
		
		if(mouseX>=43&&mouseX<=202){
			if(mouseY>=223&&mouseY<=269){
				PlantsVsZombies.setLevel(1);
				PlantsVsZombies.newLevel();
			}
			if(mouseY>=280&&mouseY<=326){
				PlantsVsZombies.setLevel(2);
				PlantsVsZombies.newLevel();
			}
			if(mouseY>=341&&mouseY<=385){
				PlantsVsZombies.setLevel(3);
				PlantsVsZombies.newLevel();
			}
			if(mouseY>=399&&mouseY<=445){
				PlantsVsZombies.setLevel(4);
				PlantsVsZombies.newLevel();
			}
		}
		
		
		
		
		
		
		
		
		
		
	}
	public void playLevel(){
		PlantsVsZombies.playLevel();
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

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class SelectionPanel extends JPanel implements ActionListener{
	private Plant plant1;
	private Plant plant2;
	private Plant plant3;
	private Plant plant4;
	private Plant plant5;
	private Plant shovel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private ContentPanel contentPanel;
	
	private Plant plantSelection=null;
	
	public SelectionPanel(Plant p1, Plant p2, Plant p3, Plant p4, Plant p5, ContentPanel contentPanel){
		plant1=p1;
		plant2=p2;
		plant3=p3;
		plant4=p4;
		plant5=p5; 
		shovel=new Shovel(new Space(10,10),contentPanel);
		this.contentPanel=contentPanel;
		
		this.setLayout(new GridLayout(5,1));
		if(plant1!=null)
			button1=new JButton(plant1.getSeedCard());
		else
			button1=new JButton("Slot Empty");
		
		if(plant2!=null)
			button2=new JButton(plant2.getSeedCard());
		else
			button2=new JButton("Slot Empty");
		
		if(plant3!=null)
			button3=new JButton(plant3.getSeedCard());
		else
			button3=new JButton("Slot Empty");
		
		if(plant4!=null)
			button4=new JButton(plant4.getSeedCard());
		else
			button4=new JButton("Slot Empty");
		
		if(plant5!=null)
			button5=new JButton(plant5.getSeedCard());
		else
			button5=new JButton("Slot Empty");
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(button4);
		this.add(button5);
		
			
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		this.requestFocusInWindow();
		Object button= e.getSource();
		
		
		if (button.equals(button1)){
			if(plantSelection!=null&&plantSelection.equals(plant1)){
				plantSelection=null;
			}
			else{
				if(contentPanel.getSun()>=plant1.getCost())
					plantSelection=plant1;
			}	
		}
		if (button.equals(button2)){
			if(plantSelection!=null&&plantSelection.equals(plant2)){
				plantSelection=null;
			}
			else{
				if(contentPanel.getSun()>=plant2.getCost())
					plantSelection=plant2;
			}	
		}
		if (button.equals(button3)){
			if(plantSelection!=null&&plantSelection.equals(plant3)){
				plantSelection=null;
			}
			else{
				if(contentPanel.getSun()>=plant3.getCost())
					plantSelection=plant3;
			}	
		}
		if (button.equals(button4)){
			if(plantSelection!=null&&plantSelection.equals(plant4)){
				plantSelection=null;
			}
			else{
				if(contentPanel.getSun()>=plant4.getCost())
					plantSelection=plant4;
			}	
		}
		if (button.equals(button5)){
			if(plantSelection!=null&&plantSelection.equals(plant5)){
				plantSelection=null;
			}
			else{
				if(contentPanel.getSun()>=plant5.getCost())
					plantSelection=plant5;
			}	
		}
		
		
	}
	
	public void plant(int row, int column){
		if(plantSelection instanceof Shovel){
			if(contentPanel.getSpaces()[row][column].getPlant()!=null){
				contentPanel.getSpaces()[row][column].getPlant().gameOver();
			}
			contentPanel.getSpaces()[row][column].setPlant(null);
			contentPanel.getSpaces()[row][column].setFilled(false);
			contentPanel.repaint();
			plantSelection=null;
			return;
			
		}
		if(plantSelection instanceof PlantJalapeno){
			Plant newPlant=null;
			newPlant=plantSelection.getNewPlant(contentPanel.getSpaces()[row][column],contentPanel);
		
			contentPanel.getSpaces()[row][column].setPlant(newPlant);
			contentPanel.getSpaces()[row][column].setFilled(true);
			
			contentPanel.setSun(contentPanel.getSun()-plantSelection.getCost());
			plantSelection=null;
			contentPanel.repaint();
			((PlantJalapeno)newPlant).explode();
			
			Timer delay=new Timer(500,new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					
					
					ArrayList<Zombie> zombies;
					if(row==0){
						zombies=contentPanel.getZombies0();
					}
					else if(row==1){
						zombies=contentPanel.getZombies1();
					}
					else if(row==2){
						zombies=contentPanel.getZombies2();
					}
					else if(row==3){
						zombies=contentPanel.getZombies3();
					}
					else if(row==4){
						zombies=contentPanel.getZombies4();
					}
					else{
						zombies=null;
					}
					
					for(Zombie i: zombies){
						if(i!=null){
							i.setHp(0);
							i.gameOver();
						}
					}
					
					if(contentPanel.getSpaces()[row][column].getPlant()!=null){
						contentPanel.getSpaces()[row][column].getPlant().gameOver();
					}
					contentPanel.getSpaces()[row][column].setPlant(null);
					contentPanel.getSpaces()[row][column].setFilled(false);
					contentPanel.repaint();
					plantSelection=null;
					return;
				}
			});
			delay.setRepeats(false);
			delay.start();
			return;
		}
		
		Plant newPlant=null;
		newPlant=plantSelection.getNewPlant(contentPanel.getSpaces()[row][column],contentPanel);
	
		contentPanel.getSpaces()[row][column].setPlant(newPlant);
		contentPanel.getSpaces()[row][column].setFilled(true);
		
		contentPanel.setSun(contentPanel.getSun()-plantSelection.getCost());
		plantSelection=null;
		contentPanel.repaint();
		
	}

	public Plant getPlant1() {
		return plant1;
	}

	public void setPlant1(Plant plant1) {
		this.plant1 = plant1;
	}

	public Plant getPlant2() {
		return plant2;
	}

	public void setPlant2(Plant plant2) {
		this.plant2 = plant2;
	}

	public Plant getPlant3() {
		return plant3;
	}

	public void setPlant3(Plant plant3) {
		this.plant3 = plant3;
	}

	public Plant getPlant4() {
		return plant4;
	}

	public void setPlant4(Plant plant4) {
		this.plant4 = plant4;
	}

	public Plant getPlant5() {
		return plant5;
	}

	public void setPlant5(Plant plant5) {
		this.plant5 = plant5;
	}

	public Plant getPlantSelection() {
		return plantSelection;
	}

	public void setPlantSelection(Plant plantSelection) {
		this.plantSelection = plantSelection;
	}
	
	public void shovel(){
		if(plantSelection==null){
			plantSelection=shovel;
			//System.out.println("shovel");

		}
		if(plantSelection!=null&&!(plantSelection instanceof Shovel)){
			plantSelection=shovel;
		}
		
		
		
	}
	
	
	
	
	
	
}

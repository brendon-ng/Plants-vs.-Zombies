import java.awt.Graphics;

public class Space {
	private int row;
	private int column;
	private Plant plant;
	private boolean isFilled;
	private int centerX;
	private int centerY;
	private int rightX;
	
	public Space(int row, int column){
		this.row=row;
		this.column=column;
		this.plant=null;
		isFilled=false;
		switch(column){
			case 0: 
				centerX=295;
				rightX=334;
				break;
			case 1: 
				centerX=373;
				rightX=412;
				break;
			case 2: 
				centerX=452;
				rightX=492;
				break;
			case 3: 
				centerX=533;
				rightX=574;
				break;
			case 4: 
				centerX=616;
				rightX=657;
				break;
			case 5: 
				centerX=698;
				rightX=738;
				break;
			case 6: 
				centerX=778;
				rightX=817;
				break;
			case 7: 
				centerX=857;
				rightX=897;
				break;
			case 8: 
				centerX=946;
				rightX=994;
				break;
			default:
				centerX=(int)(Math.random()*580)+320;
				break;
				
		}
		switch(row){
			case 0:
				centerY=116;
				break;
			case 1:
				centerY=215;
				break;
			case 2:
				centerY=316;
				break;
			case 3:
				centerY=414;
				break;
			case 4:
				centerY=511;
				break;
			default:
				centerY=-50;
				break;
				
		}
	}
	
	public void plant(Plant plant){
		this.plant=plant;
	}
	
	public void draw(Graphics g){
		if(plant==null){
			return;
		}
		plant.draw(g, centerX-37, centerY-37);
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getRightX() {
		return rightX;
	}

	public void setRightX(int rightX) {
		this.rightX = rightX;
	}
	
	
}

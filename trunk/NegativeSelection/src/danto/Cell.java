package danto;
/**
* represent the circle as cell in AIS
* @author Walesa Danto
* 
* 2007-2011 Informatic Engineering - Institut Teknologi Telkom
* 2012-now  Electrical Engineering - Institut Teknologi Bandung
*/
import java.util.Random;

class Cell implements Comparable {
	public double posX, posY;
	public double radian;
	public double affinity;
//	public double affinity;
	private int representation;

	public Cell(int maxX, int maxY, double rad) {
		Random rand = new Random();
		posX = rand.nextFloat()*maxX;
		posY = rand.nextFloat()*maxY;
		radian = rad;
		representation = 0;
		affinity = 0;
	}
	public Cell(double maxX, double maxY, double rad, int i) {
		posX = maxX;
		posY = maxY;
		radian = rad;
		representation = 0;
		affinity = 0;
	}

	public void distanceRepresentation() {
		representation = 2;
	}

	public void binaryRepresentation() {
		representation = 1;
	}

	public void overlapRepresentation() {
		representation = 0;
	}
	
	public double match(Cell other) {
		if (representation == 0) {
			double xDiff = (posX) - (other.posX);
			double yDiff = (posY) - (other.posY);
			affinity = (xDiff*xDiff)+(yDiff*yDiff);
			return affinity;
		} else {
			return 600;
		}
	}
	public int compareTo(Object other) {
		if (this.affinity > ((Cell) other).affinity) {
			return 1;
		} else if (this.affinity < ((Cell) other).affinity) {
			return -1;
		} else {
			return 0;
		}
	}
	public String printCell(){
		return (this.posX+" | "+this.posY);
	}

}

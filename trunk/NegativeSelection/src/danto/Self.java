package danto;

/**
* represent the array of circles as group of self cells in AIS
* @author Walesa Danto
* 
* 2007-2011 Informatic Engineering - Institut Teknologi Telkom
* 2012-now  Electrical Engineering - Institut Teknologi Bandung
*/
public class Self extends Cells {
	private double selfRad = 20;
	
	public Self(int maxX, int maxY) {
		super(maxX, maxY);
		super.rad = selfRad;
	}
	public Self(double size,String file) {
		super(size, file);
		super.rad = selfRad;
	}
	public Self(int number, int maxX, int maxY) {
		super(number, maxX, maxY);
		super.rad = selfRad;
		// TODO Auto-generated constructor stub
	}

	public void setSelfRad(double selfRad) {
		this.selfRad = selfRad;
	}
}

package danto;

import java.awt.Color;

/**
 * represent the array of circles as testing data
 * 
 * @author Walesa Danto
 * 
 *         2007-2011 Informatic Engineering - Institut Teknologi Telkom 2012-now
 *         Electrical Engineering - Institut Teknologi Bandung
 */
public class Testing extends Cells {
	private double selfRad = 10;
	private boolean view = false;
	private double miss;

	public Testing(int maxX, int maxY) {
		super(maxX, maxY);
		super.rad = selfRad;
		setColor(Color.BLUE);
	}

	public Testing(double size, String file) {
		super(size, file);
		super.rad = selfRad;
		setColor(Color.BLUE);
	}

	public Testing(int number, int maxX, int maxY) {
		super(number, maxX, maxY);
		super.rad = selfRad;
		setColor(Color.BLUE);
		// TODO Auto-generated constructor stub
	}

	protected boolean isAllow(double x, double y, double rad) {
		Cell c = new Cell(x, y, rad, 0);
		boolean result = false;
		for (int i = 0; i < getFilled(); i++) {
			if (c.match(this.cells[i]) < (0.5 * 0.5 * rad * rad)) {
				result = true;
			} else {
				result = false;
			}
		}
		return result;
	}

	protected boolean isAllow(Cells c, int count) {
		//		
		boolean result = false;
		System.out.println(c.rad);
		for (int i = 0; i < c.getFilled(); i++) {
//			System.out
//					.println(c.getCells()[i].match(this.cells[count])
//							+ " | "
//							+ (0.5 * 0.5 * c.getCells()[i].radian * c
//									.getCells()[i].radian));
			if (c.getCells()[i].match(this.cells[count]) < (0.5 * 0.5 * c
					.getCells()[i].radian * c.getCells()[i].radian)) {
				System.out.println("true");
				return true;
			}
		}
		return result;
	}

	public void countAccuracy(Cells c) {
		// Cell[] cell = c.getCells();
		double miss = 0;
		for (int i = 0; i < getFilled(); i++) {
			if (isAllow(c, i)) {
				miss++;
			}
		}
		setMiss(miss);
		// System.out.println("miss : " + miss);
		// System.out.println("all  : " + getFilled());
		// setView(true);
		// return (double) (miss / (cell.length / 100));
	}

	public String printAccuracy(Cells c) {
		// Cell[] cell = c.getCells();
		// double miss = 0;
		// for (int i = 0; i < getFilled(); i++) {
		// if (isAllow(c,i)) {
		// miss++;
		// }
		// }
		// setView(true);
		return "accuracy = " + getMiss() + " / " + getFilled() + " = "
				+ ((getMiss() * 100.0f / getFilled())) + " % ";
		// + (100.0 - (getMiss() * 100.0f / getFilled())) + " % ";

	}

	public void setSelfRad(double selfRad) {
		this.selfRad = selfRad;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public boolean isView() {
		return view;
	}

	public void setMiss(double miss) {
		this.miss = miss;
	}

	public double getMiss() {
		return miss;
	}
}

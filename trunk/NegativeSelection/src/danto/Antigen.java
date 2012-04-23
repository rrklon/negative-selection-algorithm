package danto;

/**
 * represent the array of circle as antigen in AIS
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
import java.awt.Color;

public class Antigen extends Cells {
	private Self self;
	protected double radian = 50;
	protected Color color = Color.GREEN;
	private boolean isStillStep = true;
	private boolean isLastView = true;
	private double lokalCov = 0.999;
	private double globalCov = 0.999;
	public static int VARIABLE_DETECTOR = 1;
	public static int STATIC_DETECTOR = 0;

	public Antigen(int maxX, int maxY) {
		super(maxX, maxY);
	}

	public Antigen(int number, int maxX, int maxY) {
		super(number, maxX, maxY);
	}

	public Antigen(int number, int maxX, int maxY, Self self, double lCov, double gCov) {
		// lokalCov = lCov;
		// globalCov = gCov;
		super();
		super.setColor(Color.GREEN);
		this.self = self;
		this.cells = new Cell[number];
		int i = 0;
		Cell[] s = self.getCells();
		float lokalDens = 0;
		float globalDens = 0;
		boolean jump = false;
		main: while (i < number) {
			lokalDens = 0;
			if (!jump) {globalDens = 0;}
			jump = false;
			Cell c = new Cell(maxX, maxY, maxX);
			int j = 0;
			detect: while (j < filled) {  // check detector density amon detectors 
				if (c.match(cells[j]) <= ((0.5 * 0.5 * c.radian * c.radian))) {
					lokalDens++;
					if (lokalDens >= 1.0 / (1.0 - lokalCov)) {
						jump = true;
						globalDens++;
						break detect;    // kalo density penuh generate baru
					}
				}j++;
			}
			if (!jump) {
				int k = 0;
				while (k < self.getCells().length) {
					if ((c.match(s[k]) - (0.5 * 0.5 * this.self.rad * this.self.rad)) <= ((0.5 * 0.5 * c.radian * c.radian))) {
						c.radian = (float) Math.sqrt(c.match(s[k]));
					}k++;
				}
				if (c.radian > this.self.rad) {
					addCell(c);i++;
				} else {globalDens++;}//if (globalDens > 1 / (1 - globalCov)) {	break main;	}
			}
			if (globalDens > 1 / (1 - globalCov)) {
				break main;
			}
		}
		 setStillStep(true);
		 setLastView(false);
		 System.out.println("############################################");
		 System.out.println("count : " + i);
		 System.out.println(globalDens + " - " + (1 / (1 - globalCov)));
		 System.out.println(lokalDens + " - " + (1 / (1 - lokalCov)));
		 System.out.println("self radius : "+this.self.rad);
		
	}

	public Antigen(int number, int maxX, int maxY, Self self) {

		super();
		super.setColor(Color.GREEN);
		this.self = self;
		this.cells = new Cell[number];
		int i = 0;
		while (i < number) {
			Cell c = new Cell(maxX, maxY, radian);
			if (isAllow(c.posX, c.posY, c.radian)) {
				this.cells[i] = c;
				i++;
			}
		}
		setStillStep(true);
		setLastView(false);
	}

	protected boolean isAllow(double x, double y, double rad) {
		boolean isNotSelf = true;
		Cell antigen = new Cell(x, y, rad, 0);
		Cell[] self = this.self.getCells();
		int i = 0;
		while (i < self.length && isNotSelf) {
			if (self[i].match(antigen) > ((0.5 * 0.5 * rad * rad))) {
				isNotSelf = true;
			} else {
				isNotSelf = false;
			}
			i++;
		}
		return isNotSelf;
	}

	public boolean isStillStep() {
		return isStillStep;
	}

	public void setStillStep(boolean isStillStep) {
		this.isStillStep = isStillStep;
	}

	public boolean isLastView() {
		return isLastView;
	}

	public void setLastView(boolean isLastView) {
		this.isLastView = isLastView;
	}
}

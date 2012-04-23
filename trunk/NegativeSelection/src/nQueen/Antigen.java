package nQueen;

/**
 * represent the array of detectors / antigen in AIS
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */

public class Antigen extends Cells {
	private Self self;

	private double lokalCov = 0.99;
	private double globalCov = 0.999;
	
	public Antigen(int numCell, Self self) {
		this.self = self;
		this.cells = new Cell[numCell];
		int i = 0;
		self.getCells()[0].printMeasure();
		Cell[] s = self.getCells();
		// lokalCov = lCov;
		// globalCov = gCov;
		float lokalDens = 0;
		float globalDens = 0;
		boolean jump = false;
		main: while (i < numCell) {
			lokalDens = 0;
			if (!jump) {
				globalDens = 0;
			}
			jump = false;
			Cell c = new Cell(nQueen);
			c.setRadian(1000);
			
			

//			if(!jump){
//				c = new Cell(nQueen, "0010000000001000000000010001000010000000000000100100000000000100");
//				c.setSelfMeasure();
//				c.printMeasure(1);
//				self.getCells()[0].printMeasure(1);
//				System.out.println("measure start");
//				System.out.println(c.measureDistance(self.getCells()[0].getMeasure(),4*nQueen,c.getMeasure().length)
////						-0.5 * 0.5 *self.getRad()
//						);
//				c.setRadian(c.measureDistance(self.getCells()[0].getMeasure(),4*nQueen,c.getMeasure().length)-1);
//				System.out.println("measure stop");
//				break main;
//			}
			
			
			
			int j = 0;
			
			detect: while (j < getFilled()) {
				if (c.measureDistance(cells[j].getMeasure(),4*nQueen,c.getMeasure().length) -1 <= ((c.getRadian()))) {
					lokalDens++;
					if (lokalDens >= 1.0 / (1.0 - lokalCov)) {
						jump = true;
						globalDens++;
						break detect;
					}
				}
				j++;
			}
			if (!jump) {
				int k = 0;

				while (k < self.getCells().length) {
					if ((c.measureDistance(s[k].getMeasure(),4*nQueen,c.getMeasure().length - 1) )
							<= ((c.getRadian()))) {
						c.setRadian((c.measureDistance(s[k].getMeasure(),4*nQueen,c.getMeasure().length)));
					}
					k++;
				}
				if (c.getRadian() > 0) {
					addCell(c);
					if (i%10 == 0) System.out.println();
//					c.printMeasure();
//					System.out.print("adding detectors : "+c.getRadian()+"|");
					c.printMeasure(4*nQueen);
					
					i++;
				} else {
					globalDens++;
				}
//				 if (globalDens > 1 / (1 - globalCov)) {
//				 break main;
//				 }
			}
			if (globalDens > 1 / (1 - globalCov)) {
				break main;
			}
		}
		System.out.println("\n############################################");
		System.out.println("count : " + i);
//		System.out.println(globalDens + " - " + (1 / (1 - globalCov)));
//		System.out.println(lokalDens + " - " + (1 / (1 - lokalCov)));
//		System.out.println("self radius : " + this.self.getRad());
//		setStillStep(true);
//		setLastView(false);
	}

}

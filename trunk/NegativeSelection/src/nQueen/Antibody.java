package nQueen;

/**
 * represent the array of data testing / antibody in AIS
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */

public class Antibody extends Cells {
	private int i = 0; // step
	private Antigen antigen;
	private int maxTired = 10000;
	private int tired = 0;

	public Antibody(int numCell, Antigen antigen) {
		this.antigen = antigen;
		cells = new Cell[numCell];
		int i = 0;
		while (i < cells.length) {
			Cell c = new Cell(nQueen);
			if (isAllow(c.getMeasure())) {
				addCell(c);
				i++;
			}
			if (tired > maxTired){
				System.out.println("\n\nsorry walesa,,I'm tired after trying "+maxTired+" times, and still not found anything");
				break;
			}
		}
	}

	public boolean isAllow(int[] msr) {
		// generate antibody
		boolean res = false;
		for (int i = 0; i < antigen.getFilled(); i++) {
			if (antigen.getCells()[i].measureDistance(msr,nQueen*4,msr.length) > ((antigen.getCells()[i].getRadian()))) {
				
				res = true;
//				System.out.print("\n "+antigen.getCells()[i].measureDistance(msr,nQueen*4,msr.length)+" - ");
				
			} else {
				System.out.print(".");
				res = false;
				tired++;
				return res;
			}
			// System.out.println("maxAntibody :" + maxAntibody);
			// System.out.println("Antibody Generated : " + i);
			// System.out.println("maxCell :" + maxCell);
			// System.out.println("cell Generated : " + countCell);
			// return true;
		}
		System.out.println();
		for (int j = nQueen*4; j < msr.length; j++) {
//			System.out.print(" - ");
			System.out.print(msr[j]);
			
		}
		System.out.println("\n"+res);
		tired = 0;
		return res;
	}
	
	public void printAntibody(){
		for (int i = 0; i < getFilled(); i++) {
			System.out.println();
			cells[i].printMeasure(nQueen*4);
			cells[i].printCell();
			
//			System.out.println();
		}
	}

}

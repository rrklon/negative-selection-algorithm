package whe;
/**
 * represent the array of data testing / antibody in AIS
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
import java.util.Random;

public class Antibody extends Cell {
	private int i = 0; // step
	private int[][] antigenCell;
	private int maxAntibody;
//	private double localDensity = 0.2;

	public Antibody(Antigen antigen) {
		super(antigen.getX(), antigen.getY());
		antigenCell = antigen.getCell();
		// TODO Auto-generated constructor stub
		// int valueAntibody
		maxAntibody = this.getX() * this.getY();
	}

	public boolean isGenerateCellFinish() {
		// generate antibody
		Random r = new Random();
		countCell++;
		boolean limit = (i > maxAntibody * density) || (maxCell > maxCell * density);
		if (i < maxAntibody && countCell < maxCell) {
			int x = r.nextInt(this.getX());                           // 1. input new samples
			int y = r.nextInt(this.getY());							  // 1. input new samples
			if ((antigenCell[x][y] == 0) && (this.cell[x][y] == 0)) { // 2. Match any detectors?
				if ((measureDensity(x, y, distance) > localDensity || limit)) {
					// prefer generate di dekat kumpulan
//					System.out.println("========");
//					System.out.println("Padat : "+(measureDensity(x, y, distance) > localDensity));
//					System.out.println("limit : "+limit);
//					System.out.println("compare density : "+measureDensity(x, y, distance) +" | "+localDensity);
//					System.out.println("========");
					setCell(x, y, 1);                                 // 3. Self
					i++;
				}
			}
			return false;
		} else {                                                      // 4. end 
			System.out.println("maxAntibody :" + maxAntibody);
			System.out.println("Antibody Generated : " + i);
			System.out.println("maxCell :" + maxCell);
			System.out.println("cell Generated : " + countCell);
			return true;
		}

	}

}

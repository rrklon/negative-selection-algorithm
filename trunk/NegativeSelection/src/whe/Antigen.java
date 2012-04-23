package whe;
/**
 * represent the array of detectors / antigen in AIS
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
import java.util.Random;

public class Antigen extends Cell {

	private double numAntigen; // scale in %
	private int i = 0; // step
	private double maxAntigen;
	private int[][] selfCell;

	public Antigen(Self self, int numAntigen) {
		super(self.getX(), self.getY());
		this.numAntigen = numAntigen;

		// self cell
		this.selfCell = self.getCell();
		// int valueAntigen
		maxAntigen = self.getX() * self.getY() - self.getNumber();
		maxAntigen = maxAntigen * this.numAntigen / 100;
	}

	public boolean isGenerateCellFinish() {
		// generate antigen
		Random r = new Random();
		countCell++;
		if (i < maxAntigen && countCell < maxCell) {
			int x = r.nextInt(this.getX());                        // 1. generate random candidates
			int y = r.nextInt(this.getY());                        // 1. generate random candidates
			boolean limit = (i > maxAntigen * density) || (maxCell > maxCell * density);
			if ((selfCell[x][y] == 0) && (this.cell[x][y] == 0)) { // 2. match self samples 
				if (!(measureDensity(x, y, distance) > localDensity) || limit) {
					// System.out.println("========");
					// System.out.println("Padat : "+(measureDensity(x, y,
					// distance) > localDensity));
					// System.out.println("limit : "+(i > maxAntigen *
					// density));
					// System.out.println("compare density : "+measureDensity(x,
					// y, distance) +" | "+localDensity);
					// System.out.println("========");
					// prefer generate di tempat yang jarang
					setCell(x, y, 1);                              // 3. accept as new detectors   
					i++;
				}
			}
			return false;
		} else {                                                   // 4. enough detectors / too much try
			System.out.println("maxAntigen :" + maxAntigen);
			System.out.println("Antigen Generated : " + i);
			System.out.println("maxCell :" + maxCell);
			System.out.println("cell Generated : " + countCell);
			return true;
		}

	}

	public void setNumAntigen(int numAntigen) {
		this.numAntigen = numAntigen;
	}

}

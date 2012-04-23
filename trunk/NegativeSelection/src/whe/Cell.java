package whe;
/**
 * represent the array as cell in AIS
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
public class Cell {
	private int x;
	private int y;
	protected int[][] cell;
	private int number;

	protected int countCell = 0; //count
//	protected double localDensity = 0.4; // scale in %
	protected double localDensity = 1.0; // scale in %
	protected int distance = 1;
//	protected double density = 0.6; // scale in %
	protected double density = 0.0; // scale in %
	protected int maxCell = 400;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		cell = new int[this.x][this.y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				cell[i][j] = 0;
			}
		}
		number = 0;
	}

	public void printCell() {
		for (int i = 0; i < this.x; i++) {
			for (int j = 0; j < this.y; j++) {
				System.out.print(cell[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void setCell(int x, int y, int value) {
		cell[x][y] = value;
		number++;
	}

	public void setCell(int idx, int value) {

		cell[idx / x][idx % y] = value;
		number++;
	}

	public void setCell(int idx) {

		if (cell[idx / x][idx % y] == 0){
			cell[idx / x][idx % y] = 1;
		}else{
			cell[idx / x][idx % y] = 0;
		}
		number++;
	}

	protected double measureDensity(int x, int y, int distance) {
		double dens = 0;
		double tot = 0;
		boolean isMeasure = true;
		for (int i = 1; i <= distance; i++) {
			dens = dens + measureDistance(x, y, x + i, y, isMeasure)
					+ measureDistance(x, y, x, y + i, isMeasure)
					+ measureDistance(x, y, x + i, y + i, isMeasure)
					+ measureDistance(x, y, x + i, y - i, isMeasure)
					+ measureDistance(x, y, x - i, y - i, isMeasure)
					+ measureDistance(x, y, x - i, y + i, isMeasure)
					+ measureDistance(x, y, x, y - i, isMeasure)
					+ measureDistance(x, y, x - i, y, isMeasure);
		}
		isMeasure = false;
		for (int i = 1; i <= distance; i++) {
			tot = tot + measureDistance(x, y, x + i, y, isMeasure)
					+ measureDistance(x, y, x, y + i, isMeasure)
					+ measureDistance(x, y, x + i, y + i, isMeasure)
					+ measureDistance(x, y, x + i, y - i, isMeasure)
					+ measureDistance(x, y, x - i, y - i, isMeasure)
					+ measureDistance(x, y, x - i, y + i, isMeasure)
					+ measureDistance(x, y, x, y - i, isMeasure)
					+ measureDistance(x, y, x - i, y, isMeasure);
		}
//		System.out.println(dens +" | "+tot+" | "+dens/tot);
		return dens / tot;
	}

	protected double measureDistance(int x1, int y1, int x2, int y2,
			boolean isMeasure) {

		double dist = 0;
//		System.out.println("ismeasure :"+isMeasure);
//		System.out.println(x1 +" - "+y1);
		if (x2 >= 0 && y2 >= 0 && x2 < this.x && y2 < this.y) {
			if (this.cell[x2][y2] != 0 || !isMeasure) {
				dist = ((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2));
//				System.out.println(dist);
				// dist = Math.sqrt(dist);
				if (dist == 0){
					dist = 0;
				}else{
					dist = 1 / dist;	
				}
				
			}
		}
		return dist;
	}
	public boolean isGenerateCellFinish() {
		return true;
	}
	// setter getter
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int[][] getCell() {
		return cell;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getNumber() {
		return number;
	}

	public void setDensity(Double density) {
		this.density = density;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setLocalDensity(int localDensity) {
		this.localDensity = localDensity;
	}

}

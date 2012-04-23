package nQueen;

import java.util.Random;

/**
 * represent the array as cell in AIS
 * 
 * @author Walesa Danto
 * 
 *         2007-2011 Informatic Engineering - Institut Teknologi Telkom 2012-now
 *         Electrical Engineering - Institut Teknologi Bandung
 */
public class Cell {

	private int[][] cell;
	private int[] measure;
	private int ukuran;
	private double radian = 100;
	private int dist;

	public Cell(int scale) {
		ukuran = scale;
		measure = new int[8 * ukuran - 1];
		generateCell(ukuran);
		Random rand = new Random();
		int i = 0;
		while (i < ukuran) {
			// int x = rand.nextInt(ukuran - 1);
			int y = rand.nextInt(ukuran);
			// if (cell[x][y] == 0) {
			if (sweepXY(i, y)) {
				// System.out.println("\nget CELL ");

				cell[i][y] = 1;
				// printCell();
				i++;
			}
		}
		generateMeasure();
	}

	public Cell(int n, String scale) {
		ukuran = n;
		if (scale.length() % n == 0) {
			measure = new int[8 * ukuran - 1];
			generateCell(ukuran);
			for (int i = 0; i < scale.length(); i++) {
				int y = i % n;
				int x = i / n;
//				System.out.println(x + " " + y + " " + scale.charAt(i));
				cell[x][y] = Integer.parseInt(scale.charAt(i) + "");
//				System.out.println("cell : " + cell[x][y]);
			}
			generateMeasure();

		}
	}

	public boolean sweepXY(int x, int y) {
		boolean res = false;
		for (int i = 0; i < ukuran; i++) {
			// System.out.print(" | ");
			if (cell[i][y] == 0 && cell[x][i] == 0) {
				res = true;
			} else {
				res = false;
				return res;
			}
		}
		return res;
	}

	public void generateCell(int num) {
		this.cell = new int[num][num];
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				this.cell[i][j] = 0;
			}
		}
	}

	public void measureOrdinat(int numMeasure) {
		// ordinat
		for (int i = 0; i < ukuran; i++) {
			for (int j = 0; j < ukuran; j++) {
				if (cell[i][j] == 1) {
					measure[numMeasure] = i;
					numMeasure++;
					measure[numMeasure] = j;
					numMeasure++;
				}
			}
		}
	}

	public void generateMeasure() {

		int numMeasure = 0;
		int count = 0;

		measureOrdinat(numMeasure);

		numMeasure = ukuran * 2;
		// horison
		for (int i = 0; i < ukuran; i++) {
			for (int j = 0; j < ukuran; j++) {
				if (cell[i][j] == 1) {
					count++;
				}
			}
			measure[numMeasure] = count;
			numMeasure++;
			count = 0;
		}
		// vertical
		for (int i = 0; i < ukuran; i++) {
			for (int j = 0; j < ukuran; j++) {
				if (cell[j][i] == 1) {
					count++;
				}
			}
			measure[numMeasure] = count;
			numMeasure++;
			count = 0;
		}
		// diagonal1
//		System.out.println();
		for (int ix = 0; ix < ukuran; ix++) {
			int j = 0;
			int i = ix;
			while (i > -1) {
				if (cell[i][j] == 1) {
//					System.out.print(i + " " + j + " - ");
					count++;
				}
				i--;
				j++;
			}
			measure[numMeasure] = count;
			numMeasure++;
			count = 0;
		}
//		System.out.println();
		for (int jy = 1; jy < ukuran; jy++) {
			int i = ukuran - 1;
			int j = jy;
			while (j < 8) {
				if (cell[i][j] == 1) {
//					System.out.print(i + " " + j + " - ");
					count++;
				}
				i--;
				j++;
			}
			measure[numMeasure] = count;
			numMeasure++;
			count = 0;
		}
		// diagonal2
//		System.out.println();
		for (int ix = 0; ix < ukuran; ix++) {
			int j = ukuran - 1;
			int i = ix;
			while (i > -1) {
				if (cell[i][j] == 1) {
//					System.out.print(i + " " + j + " - ");
					count++;
				}
				i--;
				j--;
			}
			measure[numMeasure] = count;
			numMeasure++;
			count = 0;
		}
//		System.out.println();
		for (int j = ukuran - 1 - 1; j > -1; j--) {
			int i = ukuran - 1;
			while (j > -1) {
				if (cell[i][j] == 1) {
//					System.out.print(i + " " + j + " - ");
					count++;
				}
				i--;
				j--;
			}
			measure[numMeasure] = count;
			numMeasure++;
			count = 0;
		}
	}

	public int measureDistance(int[] otherMeasure) {
		return measureDistance(otherMeasure, 0, measure.length);
	}

	// public int measureDistance(int[] otherMeasure, int start, int end) {
	// int rCon = 0;
	// int dist = 0;
	// for (int i = start; i < end; i++) {
	// if (i >= start && i < end) {
	// if ((measure[i] == otherMeasure[i])) {
	// dist++;
	// rCon = dist;
	// } else {
	// dist = 0;
	// }
	//
	// }
	// }
	// return (end - start) - rCon;
	// }

//	public int measureDistance(int[] otherMeasure, int start, int end) {
//		int dist = 0;
//		for (int i = start; i < end; i++) {
//			if (i >= start && i < end) {
//
//				dist = dist
//						+ ((measure[i] - otherMeasure[i]) * (measure[i] - otherMeasure[i]));
//
//			}
//		}
//		return dist;
//	}
	public int measureDistance(int[] otherMeasure, int start, int end) {
//		int dist = 0;
//		
////		int[] m1 = sortMeasure(measure, start, end);
////		int[] m2 = sortMeasure(measure, start, end);
//		for (int i = start; i < end; i++) {
////			if (i >= start && i < end) {
////				dist = dist
////						+ ((measure[i] - otherMeasure[i]) * (measure[i] - otherMeasure[i]));
////			}
//			
//			
//			if ((measure[i] == 0)){
//				dist++;
//			}
//			if ((otherMeasure[i] == 0)){
//				dist--;
//			}
//		}
//		return Math.abs(dist);
	int dist = 0;
		
//		int[] m1 = sortMeasure(measure, start, end);
//		int[] m2 = sortMeasure(otherMeasure, start, end);
		
		
//		for (int i = 0; i < measure.length; i++) {
//			if (i >= start && i < end) {
//				dist = dist
//						+ ((measure[i] - otherMeasure[i]) * (measure[i] - otherMeasure[i]));
//			}
//			if ((m1[i] != m2[i])){
//				dist++;
//			}
			dist = count0(measure, start, end) - count0(otherMeasure, start, end);
//			dist = (measure[i]*measure[i])-(otherMeasure[i]*otherMeasure[i]);
//		}
		return Math.abs(dist);
	}
	
	public int[] sortMeasure(int[] measure, int start, int end){
		int[] res = measure;
		for (int i = start; i < end; i++) {
			res[i-start] = measure[i];
		}
		
		for (int i = 1; i < res.length; i++) {
			for (int j = 0; j < i; j++) {
				if (res[i] > res[j]){
					int temp = res[j];
					res[j] = res[i];
					res[i] = temp;
				}
			}
		}
		return res;
	}
	
	public int count0(int[] measure, int start, int end){
		int res = 0;
		for (int i = start; i < end; i++) {
			if ( measure[i] == 0 ){
				res++;
			}
		}
		return res;
	}
	public void printMeasure() {
		for (int i = 0; i < measure.length; i++) {
			if (i == 0)
				System.out.println("\nordinat");
			if (i == 2 * ukuran)
				System.out.println("\nhorison");
			if (i == 3 * ukuran)
				System.out.println("\nvertikal");
			if (i == 4 * ukuran)
				System.out.println("\ndiagonal");
			System.out.print(measure[i]);

		}
		System.out.println();
		System.out.println();
	}

	public void printMeasure(int start) {
		System.out.println("-----");
		for (int i = start - 1; i < measure.length; i++) {
			System.out.print(measure[i]);
		}
		System.out.println();
		System.out.println("-----");

	}

	public void printCell() {
		System.out.println();
		System.out.println("#########################################");

		for (int i = 0; i < ukuran; i++) {
			for (int j = 0; j < ukuran; j++) {
				if (cell[i][j] == 1) {
					System.out.print("| x |");
				} else {
					System.out.print("|   |");
				}
			}

			System.out.println();
			System.out.println("----------------------------------------");
		}
		System.out.println("#########################################");
		System.out.println();
	}

	public void setMeasure(int[] measure) {
		this.measure = measure;
	}

	public int[] getMeasure() {
		return measure;
	}

	public void setRadian(double radian) {
		this.radian = radian;
	}

	public double getRadian() {
		return radian;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public int getDist() {
		return dist;
	}

	public void setSelfMeasure() {
//		for (int j = ukuran * 4; j < measure.length; j++) {
//			measure[j] = 1;
//		}
		measure[ukuran * 4 + 2] = 3;
		// measure[19] = 0;
	}
}

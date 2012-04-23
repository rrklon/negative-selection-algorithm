package danto;
/**
* represent the array of circle as group of same cell in AIS
* @author Walesa Danto
* 
* 2007-2011 Informatic Engineering - Institut Teknologi Telkom
* 2012-now  Electrical Engineering - Institut Teknologi Bandung
*/
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Cells {
	protected Cell[] cells;
	protected Color color = Color.RED;
	protected int filled = 0;
	protected double rad = 5;
	protected int step = 0;
	protected static int number = 1000;
	public Cell max;
	public Cell min;
//	protected String path;
	
	public Cells(int maxX, int maxY) {
		this(number,maxX,maxY);
		
	}
	public Cells(double size, String pathFile) {
		loadData(size, pathFile);
		
	}
	public Cells(int number, int maxX, int maxY) {
		cells = new Cell[number];
		while (filled < number) {
			Cell c = new Cell(maxX, maxY, rad);
			if (isAllow(c.posX, c.posY, c.radian)) {
				addCell(c);
			}
		}
	}

	public Cells() {

	}

	public void loadData(double size, String filename){
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			// loop until file is finshed
			String line = br.readLine();
			if(line==null) {
				System.out.println("It failed to read the header line of the training data file: "+filename);
				System.exit(0);
			}
			String[] token = line.split("\\s");
			int col = Integer.parseInt(token[0]);
			int setSize = Integer.parseInt(token[1]);
			
			double[] rawData = new double[col];
			
			cells = new Cell[setSize];
			for(int i=0; i<setSize; i++) {
				line = br.readLine();
				token = line.split("\\s");
				
				double[] pos = new double[2];
				
				for(int j=0; j<col; j++) {
					pos[j] = Double.parseDouble(token[j])*size ; 
//					System.out.print(pos[j]+" - ");
				}
//				System.out.println();
				addCell(new Cell(pos[0], pos[1], rad, 0));
			}
		} catch(FileNotFoundException e1) {
			System.out.println("Input data file "+filename+" was not found.");
			System.exit(1);
		} catch(IOException e2) {
			System.out.println("It failed to read data file "+filename);
			System.exit(2);
		} // end of try
	}
	protected boolean isAllow(double x, double y, double rad) {
		return true;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Cell[] getCells() {
		return cells;
	}

	public Color getColor() {
		return color;
	}

	public int getStep() {
		return step;
	}

	public void setStep() {
		this.step++;
	}

	public void resetStep() {
		this.step = 0;
	}
	
	public void addCell(Cell cell){
//		if (max == null){
//			System.out.println("start");
//			max = cell;
//		}else{
//			if (max.posX < cell.posX){
//				System.out.println("pos 1");
//				max.posX = cell.posX;
//			}
//			if (max.posY < cell.posY){
//				System.out.println("pos 2");
//				max.posY = cell.posY;
//			}
//		}
//		if (min == null){
//			System.out.println("start 2");
//			min = cell;
//		}else{
//			if (min.posX > cell.posX){
//				System.out.println("pos 3");
//				min.posX = cell.posX;
//			}
//			if (min.posY > cell.posY){
//				System.out.println("pos 4");
//				min.posY = cell.posY;
//			}
//		}
//		System.out.println(min.printCell()+" | "
//				+max.printCell()
//				)
//				;
		cells[filled] = cell;
		filled++;
	}
	public int getFilled() {
		return filled;
	}

}
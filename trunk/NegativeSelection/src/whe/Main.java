package whe;
/**
 * main program for running
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
//		Self self = new Self(10, 10);
//		for (int i = 0; i < 10; i++) {
//			for (int j = 0; j < 10; j++) {
//				if ((i != 0) && (i != 3) && (i != 6) && (i != 9) && (j != 0) && (j != 9)){
//					self.setCell(j, i, 1);
//				}
//				if( (((i == 3) || (i == 6)) && ((j == 7) || (j == 8))) ){
//					self.setCell(j, i, 1);
//				}
//			}
//		}
//		System.out.println("====self====");
//		self.printCell();
//		System.out.println("========");
//		Antigen antigen = new Antigen(self, 100);
//		System.out.println("====antigen====");
//		antigen.printCell();
//		System.out.println("========");
//		System.out.println("====antibody====");
//		Antibody antibody = new Antibody(antigen);
//		antibody.generateAntibody(antigen);
//		System.out.println("========");
		
	        JFrame frame = new Viewer(10,"self",null);
	        frame.setTitle("Self Cell Creator");
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
	        frame.pack();
	        frame.setResizable(true);
	        frame.setLocationRelativeTo( null );
	        frame.setVisible(true);
	     
	}
	
}

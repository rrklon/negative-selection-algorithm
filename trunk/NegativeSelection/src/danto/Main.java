package danto;
/**
* main class for running he simulation of negative selection algorithm
* @author Walesa Danto
* 
* 2007-2011 Informatic Engineering - Institut Teknologi Telkom
* 2012-now  Electrical Engineering - Institut Teknologi Bandung
*/
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Main {
	
//	public static final String trainingDataPath = "F:\\workKpei\\NegativeSelection\\2DSyntheticData\\Cross-thickneg_train.txt";
//	public static final String testingDataPath  = "F:\\workKpei\\NegativeSelection\\2DSyntheticData\\Cross-thick_train.txt";
	public static final String testingDataPath  = "F:\\workKpei\\NegativeSelection\\2DSyntheticData\\Combneg_train.txt";
	public static final String trainingDataPath = "F:\\workKpei\\NegativeSelection\\2DSyntheticData\\Comb_train.txt";
	public static final int numAntigen = 700;
	public static final int typAntigen = 1;
	public static final int size = 500;
	
	public static void main(String args[]) {

		JFrame jFrame = new JFrame();
		NegativeSelectionDraw nsDraw = new NegativeSelectionDraw();

		Self 
//		self = new Self(1000, size, size);
		self = new Self(size,trainingDataPath);
		self.setSelfRad(20);
		
		Testing testing = new Testing(size, testingDataPath);
		testing.setSelfRad(3);
		
		nsDraw.setSelf(self);
		nsDraw.setMaxX(size);
		nsDraw.setMaxY(size);
		nsDraw.generateAntigen(numAntigen, typAntigen);
		nsDraw.setTesting(testing);
		
//		System.out.println(self.max.printCell() +" to "+self.min.printCell());
//		System.out.println(self.min.printCell() +" to "+self.max.printCell());
//		System.out.println(nsDraw.getSelf().min.printCell() +" to "+nsDraw.getSelf().max.printCell());
//		System.out.println(nsDraw.getAntigen().min.printCell()+" to "+nsDraw.getAntigen().max.printCell());
		
		
		Panel panel = new Panel();

		Dimension boardSize = new Dimension(nsDraw.getMaxX(), nsDraw.getMaxY());

		jFrame.getContentPane().add(panel);

		JLayeredPane layeredPane = new JLayeredPane();
		panel.add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel board = nsDraw;
		layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);

		board.setPreferredSize(boardSize);
		board.setBounds(0, 0, boardSize.width, boardSize.height);

		panel.add(nsDraw.getButton());
		jFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		jFrame.setSize(nsDraw.getMaxX(), nsDraw.getMaxY()+100);
		jFrame.setVisible(true);
	}
}

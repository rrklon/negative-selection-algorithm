package whe;
/**
 * listener for the button control
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class ControllerButton implements ActionListener {
	Timer timer = new Timer(50, this);
	Viewer viewer;

	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JFrame frame = null;
		if (viewer.getMode() == "self") {
			System.out.println("Generate Antigen");
			Viewer viewer = new Viewer(10, "antigen", this.viewer.getCell());
			frame = viewer;
			frame.setTitle("Antigen - Detector Generator");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.pack();
			frame.setResizable(true);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} else if (viewer.getMode() == "antigen"
				|| viewer.getMode() == "antibody") {
			if (!refreshBoard(viewer.getCell())) {
				if (timer.isRunning() == false) {
					viewer.getButton().setEnabled(false);
					timer.start();
				}
			} else {
				
				if (viewer.getMode() == "antigen") {
					viewer.getButton().setLabel("Generate Antibody");
					viewer.getButton().setEnabled(true);
					viewer.setMode("antigenOK");
				} else if (viewer.getMode() == "antibody") {
					viewer.getButton().setLabel("Finish");
					viewer.getButton().setEnabled(false);
					viewer.setMode("antibodyOK");
				}
				timer.stop();
			}
		} else if (viewer.getMode() == "antigenOK") {
			System.out.println("Generate Antibody");
			frame = new Viewer(10, "antibody", this.viewer.getCell());
//			this.viewer.getCell().printCell();
			frame.setTitle("Antibody - Detector Test");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.pack();
			frame.setResizable(true);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		} else {

		}
	}

	public boolean refreshBoard(Cell cell) {
		if (viewer.getMode() == "antigen") {
			Antigen antigen = (Antigen) viewer.getCell();
			return generateCell(antigen, Color.RED, Color.WHITE);
		} else if (viewer.getMode() == "antibody") {
			Antibody antibody = (Antibody) viewer.getCell();
			return generateCell(antibody, Color.GREEN, Color.RED);
		} else {
			return false;
		}
	}

	public boolean generateCell(Cell cell, Color col1, Color col2) {
		boolean generateFinish = true;
		if (cell instanceof Antibody) {
			Antibody antibody = (Antibody) cell;
			generateFinish = antibody.isGenerateCellFinish();
		} else if (cell instanceof Antigen) {
			Antigen antigen = (Antigen) cell;
			generateFinish = antigen.isGenerateCellFinish();
		}

		if (!generateFinish) {
			viewer.updateBoard(cell.getCell(), cell.getX(), col1, col2);
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
			return false;
		} else {
			return true;
		}
	}
}

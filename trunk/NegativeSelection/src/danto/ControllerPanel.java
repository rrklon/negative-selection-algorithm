package danto;

/**
 * action listener class for button in CircleDraw class
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ControllerPanel implements ActionListener {
	private JPanel jPanel;
	private Timer timer = new Timer(1, this);

	public void setjPanel(JPanel jPanel) {
		this.jPanel = jPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if ( ((NegativeSelectionDraw) jPanel).getTesting().isView()){
			((NegativeSelectionDraw) jPanel).repaint();
		}else{
		if (((NegativeSelectionDraw) jPanel).getAntigen() == null ) {
			Antigen antigen = null;
			if (((NegativeSelectionDraw) jPanel).antigenType == Antigen.STATIC_DETECTOR){
				antigen = new Antigen(500,
						((NegativeSelectionDraw) jPanel).getMaxX(), ((NegativeSelectionDraw) jPanel)
								.getMaxY(), ((NegativeSelectionDraw) jPanel).getSelf());	
			}else if (((NegativeSelectionDraw) jPanel).antigenType == Antigen.VARIABLE_DETECTOR){
				System.out.println(((NegativeSelectionDraw) jPanel).getSelf().rad);
				antigen = new Antigen(300,
						((NegativeSelectionDraw) jPanel).getMaxX(), ((NegativeSelectionDraw) jPanel)
								.getMaxY(), ((NegativeSelectionDraw) jPanel).getSelf(), 0.05f, 0.05f);
			}
			
			((NegativeSelectionDraw) jPanel).setAntigen(antigen);
			((NegativeSelectionDraw) jPanel).getButton().setLabel(
					"Simulate Generating Antigen");
		} else {
			if (((NegativeSelectionDraw) jPanel).getAntigen().isStillStep()
					|| ((NegativeSelectionDraw) jPanel).getAntigen().isLastView()
					) {
				if (timer.isRunning() == false) {
					timer.start();
					((NegativeSelectionDraw) jPanel).getButton().setEnabled(false);
					((NegativeSelectionDraw) jPanel).getButton().setLabel(
							"Please Wait . . . ");
				}
				((NegativeSelectionDraw) jPanel).getButton().setLabel("wait : "+((NegativeSelectionDraw) jPanel).getAntigen().getStep()+" of "+((NegativeSelectionDraw) jPanel).getAntigen().getFilled());
				((NegativeSelectionDraw) jPanel).repaint();
				if (((NegativeSelectionDraw) jPanel).getAntigen().isLastView()) {
					timer.stop();
					((NegativeSelectionDraw) jPanel).getButton().setEnabled(true);
					((NegativeSelectionDraw) jPanel).getButton().setLabel("Test Detector");

				}
			}
		}
		}
		
	}
}

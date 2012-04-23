package danto;

/**
 * User interface class for simulating Negative selection
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class NegativeSelectionDraw extends JPanel {
	private Shape circle;
	private int maxX = 600;
	private int maxY = 600;
	private ControllerPanel controllerPanel = new ControllerPanel();
	private Self self;
	private Antigen antigen;
	private Testing testing;
	private Button button;
	public int antigenType = 1;

	public NegativeSelectionDraw() {
		super();
		controllerPanel.setjPanel(this);
		button = new Button("Generate");
		button.addActionListener(controllerPanel);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (self == null) {
			self = new Self(1000, maxX, maxY);
		}

		drawCells(g, self);
		if (testing.isView()) {
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			drawCells(g, antigen);
			drawCells(g, testing);
			button.setLabel(testing.printAccuracy(antigen));
			// drawStepCells(g);
			antigen = null;
			testing.setView(false);
		}

		if (antigen != null) {
			if (antigen.isLastView()) {
				drawCells(g, antigen);
				antigen.setLastView(false);
				testing.setView(true);
				button.setLabel("Calculating Accuracy");
				testing.countAccuracy(antigen);
				button.setLabel("Viewing Accuracy");
				// antigen = null;
			} else {
				drawStepCells(g);
			}
		}

	}

	public void drawCircle(Graphics g, double x, double y, double rad, Color col) {
		circle = new Ellipse2D.Double(x - rad / 2, y - rad / 2, rad, rad);
		Graphics2D ga = (Graphics2D) g;
		ga.setPaint(col);
		ga.fill(circle);
		ga.setComposite(makeComposite(0.4F));
	}

	public void drawCells(Graphics g, Cells cells) {
		Cell[] c = cells.getCells();

		for (int i = 0; i < cells.getFilled(); i++) {
			drawCircle(g, c[i].posX, c[i].posY, c[i].radian, cells.getColor());
		}
	}

	public void drawStepCells(Graphics g) {
		Cell[] c = antigen.getCells();
		int j = antigen.getStep();
		int i = 0;
		while (i < j) {
			drawCircle(g, c[i].posX, c[i].posY, c[i].radian, antigen.getColor());
			i++;
		}
		if (j == antigen.getFilled()) {
			antigen.resetStep();
			antigen.setStillStep(false);
			antigen.setLastView(true);
		} else {
			antigen.setStep();
		}
	}

	private AlphaComposite makeComposite(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public void setAntigen(Antigen antigen) {
		this.antigen = antigen;
	}

	public void generateAntigen(int antigenNum, int antigenTyp) {
		if (antigenTyp == Antigen.STATIC_DETECTOR) {
			antigen = new Antigen(antigenNum, getMaxX(), getMaxY(), getSelf());
		} else if (antigenTyp == Antigen.VARIABLE_DETECTOR) {
			System.out.println(getSelf().rad);
			antigen = new Antigen(antigenNum, getMaxX(), getMaxY(), getSelf(),
					0.05f, 0.05f);
		}

		setAntigen(antigen);
		getButton().setLabel("Simulate Generating Antigen");
	}

	public Antigen getAntigen() {
		return antigen;
	}

	public void setSelf(Self self) {
		this.self = self;
	}

	public Self getSelf() {
		return self;
	}

	public Button getButton() {
		return button;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}

	public Testing getTesting() {
		return testing;
	}
}

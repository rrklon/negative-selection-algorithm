package whe;
/**
 * viewer for GUI
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Viewer extends JFrame {
	/**
	 * 
	 */
	// private ControllerSelf controller;
	private Cell cell;
	private JLayeredPane layeredPane;
	private JPanel board;
	private String mode;
	private ControllerButton actionListener;
	private Button button;

	public Viewer(int sisi, String mode, Cell c) {
		Dimension boardSize = new Dimension(300, 300);

		actionListener = new ControllerButton();
		actionListener.setViewer(this);

		this.mode = mode;
		// Use a Layered Pane for this this aplication

		if (c != null) {
			setCell(c);
		}
		Panel panel = new Panel();
		getContentPane().add(panel);

		layeredPane = new JLayeredPane();
		// getContentPane().add(layeredPane);
		panel.add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		board = new JPanel();
		layeredPane.add(board, JLayeredPane.DEFAULT_LAYER);
		board.setLayout(new GridLayout(sisi, sisi));
		board.setPreferredSize(boardSize);
		board.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < sisi * sisi; i++) {
			JPanel square = new JPanel(new BorderLayout());
			board.add(square);
			square.setName(i + "");
			square.setBackground(Color.white);
			square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		if (mode == "self") {
			Button button = new Button("Generate Self Cell");
			button.addActionListener(actionListener);
			panel.add(button);
			ControllerBoard controller = new ControllerBoard();
			controller.setViewer(this);

			this.cell = new Self(10, 10);
			controller.setSelf(this.cell);

			layeredPane.addMouseListener(controller);
		} else if (mode == "antigen") {
			Antigen antigen = new Antigen((Self) this.cell, 100);
			setCell(antigen);
			button = new Button("Generate Antigen");
			button.addActionListener(actionListener);
			// button.setEnabled(false);
			panel.add(button);

		} else {

			Antibody antibody = new Antibody((Antigen) this.cell);
			setCell(antibody);
			button = new Button("Generate Antibody");
			button.addActionListener(actionListener);
			panel.add(button);
		}

	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public int mousePressed(MouseEvent e) {
		Component c = board.findComponentAt(e.getX(), e.getY());
		return Integer.parseInt(c.getName());
	}

	public void updateBoard(int[][] cell, int num, Color col) {
		updateBoard(cell, num, col, Color.WHITE);
	}

	public void updateBoard(int[][] cell, int num) {
		updateBoard(cell, num, Color.GREEN, Color.WHITE);
	}

	public void updateBoard(int[][] cell, int num, Color col1, Color col2) {
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				Component c = board.getComponent(i * 10 + j);
				if (cell[i][j] == 0) {
					c.setBackground(col2);
				} else {
					c.setBackground(col1);
				}
			}
		}
		this.cell.cell = cell;

	}

	public Button getButton() {
		return button;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public Cell getCell() {
		return cell;
	}

}

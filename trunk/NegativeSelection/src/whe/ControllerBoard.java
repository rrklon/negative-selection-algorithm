package whe;
/**
 * listener for controlling the board coloring process 
 * @author Walesa Danto
 * 
 * 2007-2011 Informatic Engineering - Institut Teknologi Telkom
 * 2012-now  Electrical Engineering - Institut Teknologi Bandung
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerBoard implements MouseListener{
	Viewer viewer;
	Cell cell;
	
	
	public void setViewer(Viewer viewer) {
		this.viewer = viewer;
	}
	
	public void setSelf(Cell cell) {
		this.cell = cell;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println(e.getX()+" "+ e.getY());
		if (cell instanceof Self) {
			Self self = (Self) cell;
			int i = viewer.mousePressed(e);
			self.setCell(i);
//			self.printCell();
			viewer.updateBoard(self.getCell(),self.getX());
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

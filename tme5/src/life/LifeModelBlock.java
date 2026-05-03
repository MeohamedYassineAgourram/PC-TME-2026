package life;

import java.util.concurrent.atomic.AtomicInteger;

public class LifeModelBlock extends LifeModel{
	
	private boolean tour_de_refresh = false; 
	
	public LifeModelBlock(int rows, int cols) {
		super(rows, cols);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void updateNext(int startRow, int endRow) {
		
		while(tour_de_refresh == true) {
			try {
				wait(); //rappel : équivalent à this.wait() du synchronized
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.updateNext(startRow, endRow);
		setRefresh_update(true);
		notifyAll();
	}
	
	public synchronized void refreshCurrent() {
		while(tour_de_refresh == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.refreshCurrent();
		setRefresh_update(false);
		notifyAll();
	}
	
	public synchronized int getRows() {
		return super.getRows();
	}
	
	public synchronized int getCols() {
		return super.getCols();
	}

	public synchronized boolean isAlive(int r, int c) {
		return super.isAlive(r, c);
	}
	
	public synchronized void setAlive(int r, int c, boolean alive) {
		super.setAlive(r, c, alive);
	}

	public synchronized void clear() {
		super.clear();
	}
	
	public synchronized AtomicInteger getUpdateCount() {
		return super.getUpdateCount();
	}
	public synchronized AtomicInteger getRefreshCount() {
		return super.getRefreshCount();
	}
	

	public synchronized void updateFrom(LifeModel mcopy) {
		super.updateFrom(mcopy);
	}

	public boolean getRefresh_update() {
		return tour_de_refresh;
	}

	public void setRefresh_update(boolean tour_de_refresh) {
		this.tour_de_refresh = tour_de_refresh;
	}

}

package life;

import java.util.concurrent.atomic.AtomicInteger;

public class LifeModelSync extends LifeModel {

	public LifeModelSync(int rows, int cols) {
		super(rows, cols);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void updateNext(int startRow, int endRow) {
		super.updateNext(startRow, endRow);
	}
	
	public synchronized void refreshCurrent() {
		super.refreshCurrent();
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

}

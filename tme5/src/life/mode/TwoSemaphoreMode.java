package life.mode;

import java.util.concurrent.atomic.AtomicInteger;

import life.LifeModel;
import life.ui.LifePanel;
import life.sync.SimpleSemaphore;

public final class TwoSemaphoreMode implements LifeMode {
	@Override
	public String getName() {
		return "twosem";
	}

	@Override
	public LifeModel createModel(int rows, int cols) {
		return new LifeModel(rows, cols);
	}
	
	@Override
	public void startSimulation(LifeModel model, LifePanel panel, AtomicInteger updateDelayMs, AtomicInteger refreshDelayMs, int n) {
		int rows = model.getRows();
		
		SimpleSemaphore ready = new SimpleSemaphore(n);
		SimpleSemaphore done = new SimpleSemaphore(0);

		for (int i = 0; i < n; i++) {
			int startRow = (i * rows) / n;
			int endRow = ((i + 1) * rows) / n;
			Thread updater = new Thread(new Updater(model, startRow, endRow, updateDelayMs, ready, done), "updater-" + i);
			updater.start();
		}

		Thread refresher = new Thread(new Refresher(model, refreshDelayMs, panel, ready, done, n), "refresher");
		refresher.start();
	}
	
	
	static final class Updater implements Runnable {
		private final LifeModel model;
		private final int startRow;
		private final int endRow;
		private final AtomicInteger delayMs;
		
		private final SimpleSemaphore ready;
		private final SimpleSemaphore done;

		Updater(LifeModel model, int startRow, int endRow, AtomicInteger delayMs, SimpleSemaphore ready, SimpleSemaphore done) {
			this.model = model;
			this.startRow = startRow;
			this.endRow = endRow;
			this.delayMs = delayMs;
			this.ready = ready;
			this.done = done;
		}

		@Override
		public void run() {
			try {
				while (true) {
					ready.acquire(1);
					model.updateNext(startRow, endRow);
					done.release(1);

					int d = delayMs.get();
					if (d > 0) {
						Thread.sleep(d);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Thread " + Thread.currentThread().getName() + " quitting");
			}
		}
	}
	
	
	

	static final class Refresher implements Runnable {
		private final LifeModel model;
		private final AtomicInteger delayMs;
		private final LifePanel panel;
		private final SimpleSemaphore ready;
		private final SimpleSemaphore done;
		private final int n;

		Refresher(LifeModel model, AtomicInteger delayMs, LifePanel panel,
				SimpleSemaphore ready, SimpleSemaphore done, int n) {
			this.model = model;
			this.delayMs = delayMs;
			this.panel = panel;
			this.ready = ready;
			this.done = done;
			this.n = n;
		}

		@Override
		public void run() {
			try {
				while (true) {
					done.acquire(n);
					model.refreshCurrent();
					panel.repaint();
					ready.release(n);

					int d = delayMs.get();
					if (d > 0) {
						Thread.sleep(d);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Thread " + Thread.currentThread().getName() + " quitting");
			}
		}
	}

}

package life.mode;

import java.util.concurrent.atomic.AtomicInteger;

import life.LifeModel;
import life.sync.Turn;
import life.ui.LifePanel;

public final class ExternalMode implements LifeMode {
	@Override
	public String getName() {
		return "external";
	}
	
	@Override
	public LifeModel createModel(int rows, int cols) {
		return new LifeModel(rows, cols);
	}

	@Override
	public void startSimulation(LifeModel model, LifePanel panel, AtomicInteger updateDelayMs, AtomicInteger refreshDelayMs,
			int workers) {
		
		Turn turn = new Turn();
		
		Thread updater = new Thread(new Updater(model, 0, model.getRows(), updateDelayMs, turn), "updater");
		updater.start();

		Thread refresher = new Thread(new Refresher(model, refreshDelayMs, panel, turn), "refresher");
		refresher.start();
	}

	static final class Updater implements Runnable {
		private final LifeModel model;
		private final int startRow;
		private final int endRow;
		private final AtomicInteger delayMs;
		private final Turn turn;

		Updater(LifeModel model, int startRow, int endRow, AtomicInteger delayMs, Turn turn) {
			this.model = model;
			this.startRow = startRow;
			this.endRow = endRow;
			this.delayMs = delayMs;
			this.turn = turn;
		}

		@Override
		public void run() {
			try {
				while (true) {
					turn.startTurn(true); //hypothèse : player1 == udapter
					model.updateNext(startRow, endRow);
					turn.endTurn();
					
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
		private final Turn turn;

		Refresher(LifeModel model, AtomicInteger delayMs, LifePanel panel, Turn turn) {
			this.model = model;
			this.delayMs = delayMs;
			this.panel = panel;
			this.turn = turn;
		}

		@Override
		public void run() {
			try {
				while (true) {
					turn.startTurn(false); //hypothèse : player2 == refresher 
					model.refreshCurrent();
					panel.repaint();
					turn.endTurn();
					
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

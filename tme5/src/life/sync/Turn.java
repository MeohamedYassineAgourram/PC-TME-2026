package life.sync;

public class Turn {
	
	private boolean isPlayerOneTurn = true;
	
	public synchronized void startTurn(boolean isPlayerOne) {
		while (isPlayerOne != isPlayerOneTurn) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void endTurn() {
		isPlayerOneTurn = !isPlayerOneTurn;
		notifyAll();
	}
}

package pc.crawler;

public class ActivityMonitor {
	
	private int compteur;
	
	public ActivityMonitor() {
		compteur = 0;
	}
	
	public synchronized void taskStarted() {
		compteur ++;
	}
	
	public synchronized void taskCompleted() {
		compteur --;
		if(compteur == 0) {
			notifyAll();
		}
	}
	
	public synchronized void awaitCompletion() throws InterruptedException {
		while(compteur > 0) {
			wait();
		}
	}
	
	
}

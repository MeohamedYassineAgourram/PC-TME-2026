package pc.quicksort;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSortParallèle extends RecursiveAction {
	
	private static final int THRESHOLD = 100000;
	
	private final int[] array;
	private int low;
	private int high;
	
	public QuickSortParallèle(int[] array, int low, int high) {
		this.array = array;
		this.low = low;
		this.high = high;
	}
	

	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		if (low < high) {
			if(high-low <= THRESHOLD) {
				QuickSort.quickSort(array, low, high);
			}
			else {
				int pi = QuickSort.partition(array, low, high);
				
				QuickSortParallèle tache_gauche = new QuickSortParallèle(array,low,pi);
				QuickSortParallèle tache_droite = new QuickSortParallèle(array,pi+1,high);
				
				invokeAll(tache_gauche,tache_droite);
			}
		}
	}
	
	// Partie Question 4:
	
	public static void quickSortParallèle_poolModifiable(int[] array, int nb_thread) {
		ForkJoinPool pool = new ForkJoinPool(nb_thread);
		pool.invoke(new QuickSortParallèle(array,0,array.length-1));
		pool.shutdown();
	}
	
	public static void quickSortParallèle_poolDefaut(int[] array) {
		ForkJoinPool.commonPool().invoke(new QuickSortParallèle(array,0,array.length-1));
	}
}

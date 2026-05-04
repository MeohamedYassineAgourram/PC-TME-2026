package pc.quicksort;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int size = 1_000_000;

        int[] original = QuickSort.generateRandomArray(size);

        // Test séquentiel
        int[] arraySeq = Arrays.copyOf(original, original.length);
        long startSeq = System.currentTimeMillis();
        QuickSort.quickSort(arraySeq, 0, arraySeq.length - 1);
        long endSeq = System.currentTimeMillis();
        System.out.println("Temps séquentiel : " + (endSeq - startSeq) + " ms");

        // Test parallèle avec plusieurs nombres de threads
        int[] nbThreadsTests = {1, 2, 4, 8};
        for (int nbThreads : nbThreadsTests) {
            int[] arrayPar = Arrays.copyOf(original, original.length);
            long startPar = System.currentTimeMillis();
            QuickSortParallèle.quickSortParallèle_poolModifiable(arrayPar, nbThreads);
            long endPar = System.currentTimeMillis();
            System.out.println("Temps parallèle avec " + nbThreads + " threads : " + (endPar - startPar) + " ms");
        }
        
        //TEst parallèle avec thread pool par défaut
        int[] arrayPar_poolDefaut = Arrays.copyOf(original, original.length);
        long startPar_poolDefaut = System.currentTimeMillis();
        QuickSortParallèle.quickSortParallèle_poolDefaut(arrayPar_poolDefaut);
        long endPar_poolDefaut = System.currentTimeMillis();
        System.out.println("Temps parallèle avec thread pool : " + (endPar_poolDefaut - startPar_poolDefaut) + " ms");
    }
}
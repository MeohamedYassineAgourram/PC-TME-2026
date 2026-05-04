package pc.mandelbrot;

import java.util.concurrent.RecursiveAction;

public class MandelbrotTask extends RecursiveAction {
	private final int THRESHOLD = 5000;
	
	//les attributs de compute:
	private BoundingBox boundingBox;
	private int maxIterations;
	private int[] imageBuffer;
	
	//taille de l'affichage :
	
	private int deb_x;
	private int deb_y;
	private int fin_x;
	private int fin_y;
	
	public MandelbrotTask(BoundingBox boundingBox,int maxIterations,int[] imageBuffer,int deb_x,int deb_y,int fin_x,int fin_y) {
		this.boundingBox = boundingBox;
		this.maxIterations = maxIterations;
		this.imageBuffer = imageBuffer;
		this.deb_x = deb_x;
		this.deb_y = deb_y;
		this.fin_x = fin_x;
		this.fin_y = fin_y;
	}
	

	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		int nb_pixels = (fin_y-deb_y) * (fin_x-deb_x);
		
		if(nb_pixels <= THRESHOLD) {
			compute_partie(boundingBox, maxIterations, imageBuffer,deb_x,deb_y,fin_x,fin_y);
		}
		
		else {
			int millieu_y = (deb_y + fin_y)/2;
			MandelbrotTask haut_horizontalement = new MandelbrotTask(boundingBox,maxIterations,imageBuffer,deb_x,deb_y,fin_x,millieu_y);
			MandelbrotTask bas_horizontalement = new MandelbrotTask(boundingBox,maxIterations,imageBuffer,deb_x,millieu_y,fin_x,fin_y);
			haut_horizontalement.fork();
			bas_horizontalement.compute();
			haut_horizontalement.join();
			//après recherche c'est équivalent de faire invokeAll(haut_horizontalement,bas_horizontalement)
		}
		
	}
	
	public static void compute_partie(BoundingBox boundingBox, int maxIterations, int[] imageBuffer, int deb_x, int deb_y, int fin_x, int fin_y ) {
		long start = System.currentTimeMillis();
		// Iterate over each pixel
		for (int py = deb_y; py < fin_y; py++) {
			for (int px = deb_x; px < fin_x; px++) {
				int color = MandelbrotCalculator.computePixelColor(boundingBox, maxIterations, px, py);

				// Set the pixel in the image buffer
				imageBuffer[py * boundingBox.width + px] = color;
			}
		}
		//System.out.println("Rendered image in " + (System.currentTimeMillis() - start) + " ms");
	}
	
	
}

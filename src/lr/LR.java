package lr;

import lr.format.simple.FormatSimple;
// import lr.format.wavefront.WavefrontFormat;
// import lr.format.wavefront.material.MaterialFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Classe principale
 * 
 * auteurs : Christophe Renaud, Samuel Delepoulle, Franck Vandewiele
 */
class LR {
	static final int LARGEUR = 1980;
	static final int HAUTEUR = 1080;
	static final int NBRAYONS = 300;
	static final int NIVEAU = 2;
	
	// valeurs originelles : 
	// static final int LARGEUR = 1980;
	// static final int HAUTEUR = 1080;
	// static final int NBRAYONS = 10;
	// static final int NIVEAU = 2;
	public static void main(String[] args) {
		long start = System.nanoTime();
		int nb_threads = 10;
		Thread[] threads = new Thread[nb_threads];
		Renderer r = new Renderer(LARGEUR, HAUTEUR);
		Scene sc = new FormatSimple().charger("simple.txt");
		sc.display();
		r.setScene(sc);
		r.setNiveau(NIVEAU);

		// r.renderFullImage(NBRAYONS);
		ExecutorService espool = Executors.newFixedThreadPool(nb_threads);
		for (int i = 0; i < threads.length; i++) {
			ParallelRenderer pr = new ParallelRenderer(i * (HAUTEUR / threads.length),
					i * (HAUTEUR / threads.length) + (HAUTEUR / threads.length), NBRAYONS, r);
			Thread t = new Thread(pr);
			espool.submit(()->t.start());
			threads[i] = t;
		 	// t.start();
		}

		 for (int i = 0; i < threads.length; i++) {
		 	try {
		 		threads[i].join();
		 	} catch (Exception e) {
		 		System.out.println(e);
		 	}
		 }

		espool.shutdown();
		Image image = r.getIm();

		image.save("image" + NIVEAU, "png");
		//new MaterialFormat().charger("chaise_plan.mtl");
		System.out.println("Temps : " + (System.nanoTime() - start));
	}
}

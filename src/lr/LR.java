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
	static final int NBRAYONS = 100;
	static final int NIVEAU = 2;
	
	// valeurs originelles : 
	// static final int LARGEUR = 1980;
	// static final int HAUTEUR = 1080;
	// static final int NBRAYONS = 10;
	// static final int NIVEAU = 2;
	public static void main(String[] args) {
		// Début du temps d'éxecution (optionnel)
		long start = System.nanoTime();

		// Initialisation des variables
		int nb_threads = 10;
		Thread[] threads = new Thread[nb_threads];
		Renderer r = new Renderer(LARGEUR, HAUTEUR);
		Scene sc = new FormatSimple().charger(args[0]);
		sc.display();
		r.setScene(sc);
		r.setNiveau(NIVEAU);

		// Création d'un pool de Threads pour optimiser
		ExecutorService espool = Executors.newFixedThreadPool(nb_threads);
		for (int i = 0; i < threads.length; i++) {
			// Division des lignes de l'image entre les différents Threads
			ParallelRenderer pr = new ParallelRenderer(i * (HAUTEUR / threads.length),
					i * (HAUTEUR / threads.length) + (HAUTEUR / threads.length), NBRAYONS, r);
			Thread t = new Thread(pr);
			espool.submit(() -> t.start());
			threads[i] = t;
			// t.start();
		}

		// Attente de la fin des threads
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		// Fermerture du pool de Threads (impoortant sinon le programme ne se finit pas)
		espool.shutdown();
		// Rendu de l'image
		Image image = r.getIm();

		//Sauvegarde de l'image
		image.save("image" + NIVEAU, "png");

		//Calcul du temps d'éxecution (optionnel)
		System.out.println("Temps : " + (System.nanoTime() - start));
	}
}

/** 
 * Classe principale 
 * 
 * auteurs : Christophe Renaud, Samuel Delepoulle, Franck Vandewiele
 */
class LR {
	static final int LARGEUR = 1980;
	static final int HAUTEUR = 1080;
	static final int NBRAYONS = 1;
	static final int NIVEAU = 2;

	public static void main(String[] args) {

		Renderer r = new Renderer(LARGEUR, HAUTEUR);
		Scene sc = new Scene("simple.txt");
		sc.display();
		r.setScene(sc);
		r.setNiveau(NIVEAU);

		Image image = r.renderFullImage(NBRAYONS);
		image.save("image" + NIVEAU, "png");
	}
}

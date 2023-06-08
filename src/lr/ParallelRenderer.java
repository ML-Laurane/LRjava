package lr;

public class ParallelRenderer implements Runnable {
    // Variables de la classe
    // Les lignes minimum et maximum pour savoir quelles lignes calculer
    private int min, max;
    // Le nombre de rayons à calculer
    private int nb_rayons;
    // L'image pour laquelle on va calculer les lignes
    private Renderer rend;

    // Constructeur de l'Objet pour initialiser les variables
    public ParallelRenderer(int min, int max, int nbrayons, Renderer r) {
        this.min = min;
        this.max = max;
        this.nb_rayons = nbrayons;
        this.rend = r;
    }

    // Méthode pour calculer les lignes de l'image entre la ligne min et la ligne max
    public void run(){
        for (int i = this.min; i < this.max; i++) {
            rend.renderLine(i, this.nb_rayons);
        }
    }

    // Min getter
    public int getMin(){
        return this.min;
    }
    // Max getter
    public int getMax() {
        return this.max;
    }
    
    // Min setter
    public void setMin(int min) {
        this.min = min;
    }
    
    // Max setter
    public void setMax(int max){
        this.max = max;
    }
}

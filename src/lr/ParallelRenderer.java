package lr;

public class ParallelRenderer implements Runnable{
    private int min, max;
    private int nb_rayons;
    private Renderer rend;

    public ParallelRenderer(int min, int max, int nbrayons, Renderer r) {
        this.min = min;
        this.max = max;
        this.nb_rayons = nbrayons;
        this.rend = r;
    }
    public void run(){
        for (int i = this.min; i < this.max; i++) {
            rend.renderLine(i, this.nb_rayons);
        }
    }

    // getter / setter 
    public int getMin(){
        return this.min;
    }
    public int getMax(){
        return this.max;
    }
    public void setMin(int min){
        this.min = min;
    }
    public void setMax(int max){
        this.max = max;
    }
}

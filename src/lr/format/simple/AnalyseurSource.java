package lr.format.simple;

import lr.Intensite;
import lr.Point;
import lr.Scene;
import lr.Source;

import java.io.IOException;
import java.io.StreamTokenizer;

public class AnalyseurSource extends AnalyseurAbstrait {

    public AnalyseurSource(FormatSimple format) {
        super(format);
    }

    @Override
    public String getNom() {
        return "source";
    }

    @Override
    public void analyser(StreamTokenizer tokenizer, Scene scene) throws IOException {
        float x, y, z;
        float r, v, b;

        // saisie des coordonnées de la source
        tokenizer.nextToken();
        x = (float) tokenizer.nval;
        tokenizer.nextToken();
        y = (float) tokenizer.nval;
        tokenizer.nextToken();
        z = (float) tokenizer.nval;

        // saisie de l'intensite de la source
        tokenizer.nextToken();
        r = (float) tokenizer.nval;
        tokenizer.nextToken();
        v = (float) tokenizer.nval;
        tokenizer.nextToken();
        b = (float) tokenizer.nval;
        Intensite puiss = new Intensite(r, v, b);
        Source source = new Source(puiss, new Point(x, y, z));
        scene.ajouter(source);
    }


}

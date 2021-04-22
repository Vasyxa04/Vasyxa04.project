package problem;

import javax.media.opengl.GL2;

public class Polygon {
    Vector[] vertex;
    Polygon(Vector[] arr){
        vertex = arr;
    }
    public void render(GL2 gl) {
        Figures.renderPolygon(gl, vertex);
    }
    public double Square(){
        int c = 0;
        double S = 0;
        for (Vector v : vertex){
            if (v != null){
                c++;
            }
        }
        if (c != 0) {
            for (int i = 0; i < c - 1; i++) {
                S = S + vertex[i].x * vertex[i + 1].y - vertex[i + 1].x * vertex[i].y;
            }
            S = 0.5 * Math.abs(S + vertex[c - 1].x * vertex[0].y - vertex[0].x * vertex[c - 1].y);
        }
        return S;
    }
}

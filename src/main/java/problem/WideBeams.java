package problem;

import javax.media.opengl.GL2;
import java.util.Random;
public class WideBeams {
    Vector a;
    Vector b;
    Vector c;
    Vector d;

    Vector l;
    Vector r;
    boolean IsSolution;

    public WideBeams(Vector a, Vector b) {
        this.a = a;
        this.b = b;
        IsSolution = false;
        l = new Vector(a, b);
        r = l.rotate();
        c = b.sum(r.norm().multiply(3));
        d = a.sum(r.norm().multiply(3));
    }

    public void render(GL2 gl) {
        Figures.renderQuad(gl, new Point(a.x, a.y), new Point(b.x, b.y), new Point(c.x, c.y), new Point(d.x, d.y), IsSolution);
    }

    public static WideBeams getRandomWideBeams() {
        Random r = new Random();
        double x1 = r.nextDouble() * 2 - 1;
        double y1 = r.nextDouble() * 2 - 1;
        double x2 = r.nextDouble() * 2 - 1;
        double y2 = r.nextDouble() * 2 - 1;
        Vector v1 = new Vector(x1, y1);
        Vector v2 = new Vector(x2, y2);
        return new WideBeams(v1, v2);
    }

    public Vector[] intersection(WideBeams w) {
        Vector[] arr = new Vector[12];
        int i = 0;
        Vector inter;
        Point p;
        Line AB1 = new Line(a, b);
        Line BC1 = new Line(b, c);
        Line CD1 = new Line(c, d);
        Line DA1 = new Line(d, a);
        Line AB2 = new Line(w.a, w.b);
        Line BC2 = new Line(w.b, w.c);
        Line CD2 = new Line(w.c, w.d);
        Line DA2 = new Line(w.d, w.a);
        inter = AB1.intersection(AB2);
        if (inter != null && ((inter.x >= AB1.x1) && (inter.x <= AB1.x2) || (inter.x >= AB1.x2) && (inter.x <= AB1.x1)) && ((inter.x >= AB2.x1) && (inter.x <= AB2.x2) || (inter.x >= AB2.x2) && (inter.x <= AB2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = AB1.intersection(BC2);
        if (inter != null && ((inter.x >= AB1.x1) && (inter.x <= AB1.x2) || (inter.x >= AB1.x2) && (inter.x <= AB1.x1)) && ((inter.x >= BC2.x1) && (inter.x <= BC2.x2) || (inter.x >= BC2.x2) && (inter.x <= BC2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = AB1.intersection(CD2);
        if (inter != null && ((inter.x >= AB1.x1) && (inter.x <= AB1.x2) || (inter.x >= AB1.x2) && (inter.x <= AB1.x1)) && ((inter.x >= CD2.x1) && (inter.x <= CD2.x2) || (inter.x >= CD2.x2) && (inter.x <= CD2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = AB1.intersection(DA2);
        if (inter != null && ((inter.x >= AB1.x1) && (inter.x <= AB1.x2) || (inter.x >= AB1.x2) && (inter.x <= AB1.x1)) && ((inter.x >= DA2.x1) && (inter.x <= DA2.x2) || (inter.x >= DA2.x2) && (inter.x <= DA2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = BC1.intersection(AB2);
        if (inter != null && ((inter.x >= BC1.x1) && (inter.x <= BC1.x2) || (inter.x >= BC1.x2) && (inter.x <= BC1.x1)) && ((inter.x >= AB2.x1) && (inter.x <= AB2.x2) || (inter.x >= AB2.x2) && (inter.x <= AB2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = BC1.intersection(BC2);
        if (inter != null && ((inter.x >= BC1.x1) && (inter.x <= BC1.x2) || (inter.x >= BC1.x2) && (inter.x <= BC1.x1)) && ((inter.x >= BC2.x1) && (inter.x <= BC2.x2) || (inter.x >= BC2.x2) && (inter.x <= BC2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = BC1.intersection(CD2);
        if (inter != null && ((inter.x >= BC1.x1) && (inter.x <= BC1.x2) || (inter.x >= BC1.x2) && (inter.x <= BC1.x1)) && ((inter.x >= CD2.x1) && (inter.x <= CD2.x2) || (inter.x >= CD2.x2) && (inter.x <= CD2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = BC1.intersection(DA2);
        if (inter != null && ((inter.x >= BC1.x1) && (inter.x <= BC1.x2) || (inter.x >= BC1.x2) && (inter.x <= BC1.x1)) && ((inter.x >= DA2.x1) && (inter.x <= DA2.x2) || (inter.x >= DA2.x2) && (inter.x <= DA2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = CD1.intersection(AB2);
        if (inter != null && ((inter.x >= CD1.x1) && (inter.x <= CD1.x2) || (inter.x >= CD1.x2) && (inter.x <= CD1.x1)) && ((inter.x >= AB2.x1) && (inter.x <= AB2.x2) || (inter.x >= AB2.x2) && (inter.x <= AB2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = CD1.intersection(BC2);
        if (inter != null && ((inter.x >= CD1.x1) && (inter.x <= CD1.x2) || (inter.x >= CD1.x2) && (inter.x <= CD1.x1)) && ((inter.x >= BC2.x1) && (inter.x <= BC2.x2) || (inter.x >= BC2.x2) && (inter.x <= BC2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = CD1.intersection(CD2);
        if (inter != null && ((inter.x >= CD1.x1) && (inter.x <= CD1.x2) || (inter.x >= CD1.x2) && (inter.x <= CD1.x1)) && ((inter.x >= CD2.x1) && (inter.x <= CD2.x2) || (inter.x >= CD2.x2) && (inter.x <= CD2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = CD1.intersection(DA2);
        if (inter != null && ((inter.x >= CD1.x1) && (inter.x <= CD1.x2) || (inter.x >= CD1.x2) && (inter.x <= CD1.x1)) && ((inter.x >= DA2.x1) && (inter.x <= DA2.x2) || (inter.x >= DA2.x2) && (inter.x <= DA2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = DA1.intersection(AB2);
        if (inter != null && ((inter.x >= DA1.x1) && (inter.x <= DA1.x2) || (inter.x >= DA1.x2) && (inter.x <= DA1.x1)) && ((inter.x >= AB2.x1) && (inter.x <= AB2.x2) || (inter.x >= AB2.x2) && (inter.x <= AB2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = DA1.intersection(BC2);
        if (inter != null && ((inter.x >= DA1.x1) && (inter.x <= DA1.x2) || (inter.x >= DA1.x2) && (inter.x <= DA1.x1)) && ((inter.x >= BC2.x1) && (inter.x <= BC2.x2) || (inter.x >= BC2.x2) && (inter.x <= BC2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = DA1.intersection(CD2);
        if (inter != null && ((inter.x >= DA1.x1) && (inter.x <= DA1.x2) || (inter.x >= DA1.x2) && (inter.x <= DA1.x1)) && ((inter.x >= CD2.x1) && (inter.x <= CD2.x2) || (inter.x >= CD2.x2) && (inter.x <= CD2.x1))){
            arr[i] = inter;
            i++;
        }
        inter = DA1.intersection(DA2);
        if (inter != null && ((inter.x >= DA1.x1) && (inter.x <= DA1.x2) || (inter.x >= DA1.x2) && (inter.x <= DA1.x1)) && ((inter.x >= DA2.x1) && (inter.x <= DA2.x2) || (inter.x >= DA2.x2) && (inter.x <= DA2.x1))){
            arr[i] = inter;
            i++;
        }
        if (new Point(a.x, a.y).IsInside(w)){
            arr[i] = new Vector(a.x, a.y);
            i++;
        }
        if (new Point(b.x, b.y).IsInside(w)){
            arr[i] = new Vector(b.x, b.y);
            i++;
        }
        if (new Point(c.x, c.y).IsInside(w)){
            arr[i] = new Vector(c.x, c.y);
            i++;
        }
        if (new Point(d.x, d.y).IsInside(w)){
            arr[i] = new Vector(d.x, d.y);
            i++;
        }
        if (new Point(w.a.x, w.a.y).IsInside(this)){
            arr[i] = new Vector(w.a.x, w.a.y);
            i++;
        }
        if (new Point(w.b.x, w.b.y).IsInside(this)){
            arr[i] = new Vector(w.b.x, w.b.y);
            i++;
        }
        if (new Point(w.c.x, w.c.y).IsInside(this)){
            arr[i] = new Vector(w.c.x, w.c.y);
            i++;
        }
        if (new Point(w.d.x, w.d.y).IsInside(this)){
            arr[i] = new Vector(w.d.x, w.d.y);
            i++;
        }
        return arr;
    }
}

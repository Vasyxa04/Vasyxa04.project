package problem;

import java.util.ArrayList;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2GL3;
import static javax.media.opengl.GL.*;
import static javax.media.opengl.GL2.GL_QUADS;


public class Figures {

    public static int N = 50;

    public static void renderLine(GL2 gl, Point a, Point b, int z) {
        gl.glColor3d(1, 0.5, 0);
        gl.glLineWidth(z);
        gl.glBegin(GL_LINES);
        gl.glVertex2d(a.x, a.y);
        gl.glVertex2d(b.x, b.y);
        gl.glEnd();
    }

    public static void renderPoint(GL2 gl, Point a, int z) {
        gl.glColor3d(1, 0.5, 0);
        gl.glPointSize(z);
        gl.glBegin(GL_POINTS);
        gl.glVertex2d(a.x, a.y);
        gl.glEnd();
    }

    public static void renderTriangle(GL2 gl, Point a, Point b, Point c, boolean x) {
        if (x) {
            gl.glColor3d(1, 0.5, 0);
            gl.glPointSize(3);
            gl.glLineWidth(5);
            gl.glBegin(GL_TRIANGLES);
            gl.glVertex2d(a.x, a.y);
            gl.glVertex2d(b.x, b.y);
            gl.glVertex2d(c.x, c.y);
            gl.glEnd();
        } else {
            gl.glColor3d(1, 0.5, 0);
            gl.glLineWidth(5);
            gl.glBegin(GL_LINE_STRIP);
            gl.glVertex2d(a.x, a.y);
            gl.glVertex2d(b.x, b.y);
            gl.glVertex2d(c.x, c.y);
            gl.glVertex2d(a.x, a.y);
            gl.glEnd();
        }
    }

    public static void renderQuad(GL2 gl, Point a, Point b, Point c, Point d, boolean x) {
        if (x) gl.glColor3d(1, 0, 0);
        else gl.glColor3d(1, 0.5, 0);
        gl.glLineWidth(5);
        gl.glBegin(GL_LINE_STRIP);
        gl.glVertex2d(a.x, a.y);
        gl.glVertex2d(b.x, b.y);
        gl.glVertex2d(c.x, c.y);
        gl.glVertex2d(d.x, d.y);
        gl.glVertex2d(a.x, a.y);
        gl.glEnd();
    }

    public static void renderCircle(GL2 gl, Point a, double x, boolean y) {
        if (!y) {
            gl.glColor3d(1, 0.5, 0);
            gl.glLineWidth(5);
            gl.glPointSize(5);
            gl.glVertex2d(a.x, a.y);
            gl.glBegin(GL_LINE_LOOP);
            for (int i = 0; i < N; i++) {
                double r = i * Math.PI * 2 / N;
                gl.glVertex2d(Math.cos(r) * x + a.x, Math.sin(r) * x + a.y);
            }
            gl.glEnd();
        } else {
            gl.glColor3d(1, 0.5, 0);
            gl.glLineWidth(5);
            gl.glPointSize(5);
            gl.glVertex2d(a.x, a.y);
            gl.glBegin(GL_TRIANGLE_FAN);
            for (int i = 0; i < N; i++) {
                double r = i * Math.PI * 2 / N;
                gl.glVertex2d(Math.cos(r) * x + a.x, Math.sin(r) * x + a.y);
            }
            gl.glEnd();
        }
    }
    public static void renderPolygon(GL2 gl, problem.Vector[] arr){
        gl.glBegin(GL_TRIANGLE_FAN);
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != null){
                gl.glColor3d(1, 0, 0);
                gl.glVertex2d(arr[i].x, arr[i].y);
            }
        }
        gl.glEnd();
    }
}
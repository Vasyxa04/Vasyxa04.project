package problem;

public class Vector {
double x, y;

public Vector(Vector p1, Vector p2) {
x = p2.x - p1.x;
y = p2.y - p1.y;
}

Vector(double x, double y) {
this.x = x;
this.y = y;
}

public Vector rotate(double a) {
double cs = Math.cos(a), sn = Math.sin(a);
double rrx = x * cs - y * sn;
double rry = x * sn + y * cs;
return new Vector(rrx, rry);
}

public double length() {
return Math.sqrt(x * x + y * y);
}

public Vector norm() {
return multiply(1 / length());
}

public Vector rotate() {
return rotate(Math.PI / 2);
}

public Vector sum(Vector b) {
return new Vector(x + b.x, y + b.y);
}

public Vector multiply(double b) {
return new Vector(x * b, y * b);
}

}

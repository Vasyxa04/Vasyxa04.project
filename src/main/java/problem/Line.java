package problem;

class Line {
    double A;
    double B;
    double C;
    double delta;
    double x1, y1, x2, y2;

    Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        A = y1 - y2;
        B = x2 - x1;
        C = x1 * y2 - x2 * y1;
        delta = 0.001;
    }

    Line(double a, double b, double c) {
        this.x1 = 0;
        this.x2 = 0;
        this.y1 = 0;
        this.y2 = 0;
        A = a;
        B = b;
        C = c;
        delta = 0.001;
    }

    Line(Vector a, Vector b) {
        this.x1 = a.x;
        this.y1 = a.y;
        this.x2 = b.x;
        this.y2 = b.x;
        A = a.y - b.y;
        B = b.x - a.x;
        C = a.x * b.y - b.x * a.y;
        delta = 0.001;
    }

    @Override
    public String toString() {
        boolean fb = B < 0;
        boolean fc = C < 0;
        B = Math.abs(B);
        C = Math.abs(C);
        String s = String.format("%.2fx " + ((fb) ? "-" : "+") + " %.2fy " + ((fc) ? "-" : "+") + " %.2f = 0", A, B, C);
        return s;
    }

    public double distanceToZero() {
        return Math.abs(C / (Math.sqrt(A * A + B * B)));
    }

    public double distanceToPoint(Point p) {
        return Math.abs((A * p.x + B * p.y + C) / (Math.sqrt(A * A + B * B)));
    }

    public boolean isParallel(Line l) {
        if (A != 0 && B != 0 && l.A != 0 && B != 0)
            return (Math.abs(A * l.B - B * l.A) < delta);
        else if (B == 0 && l.B == 0)
            return true;
        else if (A == 0 && l.A == 0)
            return true;
        else
            return false;
    }

    public Vector intersection(Line l) {
        if (isParallel(l)) {
            return null;
        }
        else {
            double x = (C / B - l.C / l.B) / (l.A / l.B - A / B);
            double y = x * (-A / B) - C / B;
            return new Vector(x, y);
        }
    }

    public boolean oneSide(Point a, Point b) {
        int sa = 0;
        int sb = 0;
        double ty = (-A / B) * a.x - C / B;
        if (Math.abs(ty - a.y) < delta)
            sa = 0;
        else if (ty < a.y)
            sa = 1;
        else
            sa = 2;
        double ty2 = (-A / B) * b.x - C / B;
        if (Math.abs(ty2 - b.y) < delta)
            sb = 0;
        else if (ty2 < b.y)
            sb = 1;
        else
            sb = 2;
        if (sa == sb || sa == 0 || sb == 0)
            return true;
        else
            return false;
    }

    public Line normalize() {
        if (C != 0) {
            A /= C;
            B /= C;
            C = 1;
        } else if (A != 0) {
            B /= A;
            A = 1;
        } else {
            B = 1;
        }
        return new Line(A, B, C);
    }

    public Line perpendicularLine(Point p) {
        double ta = -B;
        double tb = A;
        double tc = B * p.x - A * p.y;
        return new Line(ta, tb, tc);
    }

    public Point inter(Line l) {
        double x = (C / B - l.C / l.B) / (l.A / l.B - A / B);
        double y = x * (-A / B) - C / B;
        if (Math.abs(x) < delta)
            x = 0;
        if (Math.abs(y) < delta)
            y = 0;
        return new Point(x, y);
    }

    public Point nearPoint(Point p) {
        double ta = -B;
        double tb = A;
        double tc = B * p.x - A * p.y;
        return inter(new Line(ta, tb, tc));
    }

    public Line parallelLine(Point p) {
        double tc = -A * p.x - B * p.y;
        return new Line(A, B, tc);
    }

    public Point middlePoint(Point p) {
        Point tp = nearPoint(p);
        double x = (p.x + tp.x) / 2;
        double y = (p.y + tp.y) / 2;
        return new Point(x, y);
    }

    public Point symmetricPoint(Point p) {
        Point tp = nearPoint(p);
        double x = tp.x * 2 - p.x;
        double y = tp.y * 2 - p.y;
        return new Point(x, y);
    }

    public boolean insideTreug(Point t) {
        if (A == 0 || B == 0 || C == 0)
            return false;
        double ax = -C / A;
        double by = -C / B;
        if ((t.x <= 0 && ax <= 0 && ax <= t.x) || (t.x >= 0 && ax >= 0 && ax >= t.x)) {
            if ((t.y <= 0 && by <= 0 && by <= t.y) || (t.y >= 0 && by >= 0 && by >= t.y)) {
                if (oneSide(t, new Point(0, 0)))
                    return true;
                return false;
            }
        }
        return false;
    }

    public Line rotatedLine(Point p) {
        return new Line(y1 - p.y + p.x, p.y - (x1 - p.x), y2 - p.y + p.x, p.y - (x2 - p.x));
    }

    public Line bisectrix(Line l) {
        if ((A / B) * (l.A / l.B) == -1)
            return null;
        if (isParallel(l))
            return new Line((A + l.A) / 2, (B + l.B) / 2, (C + l.C) / 2);
        if ((B > 0 && l.B > 0) || (B < 0 && l.B < 0)) {
            l.B = -l.B;
            l.A = -l.A;
            l.C = -l.C;
        }
        double sqrt1 = Math.sqrt(l.A * l.A + l.B * l.B);
        double sqrt2 = Math.sqrt(A * A + B * B);
        return new Line(A * sqrt1 - l.A * sqrt2, B * sqrt1 - l.B * sqrt2, C * sqrt1 - l.C * sqrt2);
    }
}


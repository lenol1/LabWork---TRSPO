package TRSPO_1;

public class points {
    double x_n, y_n;
    public points(double x_n, double y_n){
        this.x_n = x_n;
        this.y_n = y_n;
    }

    public double getX_n() { return x_n; }
    public double getY_n() { return y_n; }

    public void setX_n(double x_n) { this.x_n = x_n; }
    public void setY_n(double y_n) { this.y_n = y_n; }

    @Override
    public String toString() { return "(" + x_n + "; " + y_n + ')'; }
}

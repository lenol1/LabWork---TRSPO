package TRSPO_1;

import java.util.ArrayList;

import static TRSPO_1.LR1.*;

public class extPoints implements Runnable {

    private ArrayList<points> _points;

    public extPoints(ArrayList<points> points1) {
        this._points = points1;
    }
    @Override
    public void run() {
        double[][] extreme = new double[2][2];
        double[]position = new double[_points.size()];
        long s_time, e_time;
        s_time = System.currentTimeMillis();
        extremePoints(_points, extreme);
        checkPoints(_points,extreme[0][0],extreme[1][0],extreme[0][1],extreme[1][1],position);
        checkPosition(position,_points);
        e_time = System.currentTimeMillis();
        System.out.println("Витрачено часу - "+(e_time-s_time)+"мс");
    }
}
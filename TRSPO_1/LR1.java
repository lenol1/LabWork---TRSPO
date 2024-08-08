package TRSPO_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LR1 {
    public static void generatePoints(ArrayList<points> points, int n) {
        double fn, sn;
        double max = 10.0; double min = -5.0;
        for (int i = 0; i < n; i++) {
            fn = Math.floor((Math.random() * (max - min) + min) * 10) / 10;
            sn = Math.floor((Math.random() * (max - min) + min) * 10) / 10;
            points.add(new points(fn, sn));
        }
    }
    public static void gatherPoints(ArrayList<points> p1, ArrayList<points> p2, ArrayList<points> allp) {
        for (int i = 0; i < p1.size() * 2; i++)
            if (i < p1.size())
                allp.add(i, p1.get(i));
            else
                allp.add(i, p2.get(i - p2.size()));
    }
    public static void printPoints(ArrayList<points> p, int n) {
        System.out.println("Перший набір точок:");
        for (int i = 0; i < p.size()/2; i++)
            System.out.println((i + 1) + ". " + p.get(i));
        System.out.println("Другий набір точок:");
         for(int i = p.size()/2;i< p.size();i++)
                System.out.println((i - p.size()/2 + 1) + ". " + p.get(i));
    }
    public static void extremePoints(ArrayList<points> allp, double[][] extreme) {
        extreme[0][0] = extreme[1][0] = allp.get(0).getX_n();
        extreme[0][1] = extreme[1][1] = allp.get(0).getY_n();
        for (points point : allp) {
            if (point.getX_n() < extreme[1][0])
                extreme[1][0] = point.getX_n();
            if (point.getX_n() > extreme[0][0])
                extreme[0][0] = point.getX_n();
            if (point.getY_n() < extreme[1][1])
                extreme[1][1] = point.getY_n();
            if (point.getY_n() > extreme[0][1])
                extreme[0][1] = point.getY_n();
        }
        System.out.println("Точки екстремуму:\nмаксимум( " + extreme[0][0] + "; " + extreme[0][1]
                + ")\nмінімум( " + extreme[1][0] + "; " + extreme[1][1]+")\n");
    }
    public static void checkPoints(ArrayList<points> allp, double maxX, double minX,
                                   double maxY, double minY, double[] pos) {
        for (int i = 0; i < allp.size(); i++)
            pos[i] = Math.floor((maxX - allp.get(i).getX_n()) * (minY - allp.get(i).getY_n())
                    - (minX - allp.get(i).getX_n()) * (maxY - allp.get(i).getY_n()) * 10) / 10;
    }
    public static void checkPosition(double[] pos, ArrayList<points> allp) {
        int index = 0;
        int i;
        for (i = 0; i < pos.length; i++) {
            if (pos[i] > 0) {
                System.out.println("Точка " + allp.get(i) + "знаходить справа від прямої");
                index++;
            } else if (pos[i] == 0)
                System.out.println("Точка " + allp.get(i) + "знаходить на прямій");
            else
                System.out.println("Точка " + allp.get(i) + "знаходить зліва від прямої");

            if (i == pos.length / 2 - 1 || i == pos.length - 1) {
                if (index == pos.length / 2 || index == 0)
                    System.out.println("Набір точок знаходяться на одній стороні\n");
                else {
                    System.out.println("Сукупність точок знаходиться по різні сторони, " +
                            "а отже, ці два набори не можна розділити прямою," +
                            " щоб точки кожного були по одну \n");
                }
                index = 0;
            }
        }
    }
    public static void dividePoints(ArrayList<points> allp, ArrayList<points> p1, ArrayList<points> p2) {
        int half = allp.size() / 2;
        for (int i = 0; i < allp.size(); i++)
            if (i < half)
                p2.add(i, allp.get(i));
            else
                p1.add(i - half, allp.get(i));
    }
    public static void fileRead(String filename, ArrayList<points>allp) throws IOException {
        FileReader frd = new FileReader(filename +".txt");
        Scanner scan = new Scanner(frd);
        String line; String[] fpoints; int i = 0;
        if (!scan.hasNextLine())
            System.out.println("Файл пустий");
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            fpoints = line.split(";");
            allp.add(i, new points(Double.parseDouble(fpoints[0])
                    , Double.parseDouble(fpoints[1])));
            i++;
        }
        frd.close();
    }
    public static void saveData(ArrayList<points>allp, Scanner in) throws IOException {
        int hlpr; String filename;
        System.out.println("Зберегти дані? 1 - Так; 0 - Ні");
        hlpr = in.nextInt();
        if (hlpr == 1){
            System.out.println("Введіть назву файлу: ");
            filename = in.next();
            FileWriter write_d = new FileWriter(filename + ".txt");
            write_d.write((allp.toString()));
            write_d.close();
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int n, index, i;
        index = 1;
        String filename, line;
        long s_time, e_time;
        ArrayList<points> points1 = new ArrayList<points>();
        ArrayList<points> points2 = new ArrayList<points>();
        ArrayList<points> allpoints = new ArrayList<points>();
        while(index!=0){
            System.out.println("\n1 - випадкові значення даних\n2 - зчитати з дані файлу" +
                    "\n3 - результат "+"\n4 - exit");
        index = in.nextInt();
        switch (index) {
            case 1:
                allpoints.clear(); points1.clear(); points2.clear();
                System.out.print("Введіть кількість точок в обох наборах: ");
                n = in.nextInt();
                generatePoints(points1, n);
                generatePoints(points2, n);
                gatherPoints(points1, points2, allpoints);
                saveData(allpoints,in);
                break;
            case 2:
                allpoints.clear(); i = 0;
                System.out.print("Введіть назву файлу: ");
                filename = in.next();
                fileRead(filename,allpoints);
                dividePoints(allpoints, points1, points2);
                break;
            case 3:
                n = points1.size();
                printPoints(allpoints, n * 2);
                double[] position = new double[allpoints.size()];
                double[][] extremep = new double[2][2];
                s_time = System.currentTimeMillis();
                extremePoints(allpoints, extremep);
                checkPoints(allpoints, extremep[0][0], extremep[1][0],
                        extremep[0][1], extremep[1][1], position);
                checkPosition(position, allpoints);
                e_time = System.currentTimeMillis();
                System.out.println("Витраченно часу на обчислення: "+(e_time-s_time)+"мс");
                break;
            case 4:
                System.exit(0);
            }
        }
    }
}
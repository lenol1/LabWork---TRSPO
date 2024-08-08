package TRSPO_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static TRSPO_1.LR1.*;

public class pMain {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String filename;
        int index = 1; int n,i;
        ArrayList<points>points1 = new ArrayList<>();
        ArrayList<points>points2 = new ArrayList<>();
        ArrayList<points>allpoints = new ArrayList<>();
            System.out.println("\n1 - випадкові значення даних\n2 - зчитати з дані файлу");
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
            }
        printPoints(allpoints, allpoints.size());
        extPoints ext1 = new extPoints(points1);
        extPoints ext2 = new extPoints(points2);
        Thread thread1 = new Thread(ext1);
        Thread thread2 = new Thread(ext2);
        thread1.start();
        thread2.start();
    }
}
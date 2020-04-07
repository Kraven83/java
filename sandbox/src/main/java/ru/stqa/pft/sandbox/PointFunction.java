package ru.stqa.pft.sandbox;

public class PointFunction {

  public static void main(String[] args) {
    Point a = new Point(3.0,5.0);
    Point b = new Point(1.0,5.0);

    System.out.println("Длина между точками равна " + distance(a,b));
    System.out.println("Длина между точками равна " + a.distance(b));
  }

  public static double distance(Point a, Point b) {
    return Math.sqrt(Math.pow(a.x - b.x,2.0) + Math.pow(a.y - b.y,2.0));
  }
}

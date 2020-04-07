package ru.stqa.pft.sandbox;

public class Point {
  double x;
  double y;

  public Point (double x, double y){
    this.x = x;
    this.y = y;
  }

  public double distance(Point b) {
    return Math.sqrt(Math.pow(this.x - b.x,2) + Math.pow(this.y - b.y,2));
  }


}

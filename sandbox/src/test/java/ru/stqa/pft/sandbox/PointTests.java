package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistEquals() {
    Point x = new Point(1, 1);
    Point y = new Point(1,101);
    Assert.assertEquals(x.distance(y),100);
  }

  @Test
  public void testDistMoreZero() {
    Point x = new Point(1, 1);
    Point y = new Point(1,101);
    Assert.assertTrue(x.distance(y) > 0);
  }

}

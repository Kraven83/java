package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

  //protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME); //явное указание браузера
  // -Dbrowser=firefox - параметр для запуска в FireFox
  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)); // браузер берется из параметров, по дефолту - Хром

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown(){
    app.stop();
  }

}

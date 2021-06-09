package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    click(By.linkText("groups"));
  }


  public void homePage() {
    click(By.linkText("home"));
  }

  public void contactsNoneGroup(){
    homePage();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[none]");
  }

  public void contactsAllGroup() {
    homePage();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
  }
}

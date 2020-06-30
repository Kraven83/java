package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstame());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("home"), contactData.getHomePhone());

    if(creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "' ]")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void initModificationContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void initModificationContactById(int id) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
  }

  public ContactData infoFropEditForm(ContactData contact) {
    //initContactModificationById(contact.getId());
    initModificationContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(home)
            .withMobilePhone(mobile).withWorkPhone(work);
  }

  // This method was in the lecture N5.9
  private void initContactModificationById(int id){
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../../"));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    // wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
    // wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click();
    // wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }

  public void create(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    contactCache = null;
    submitContactCreation();
  }

  public void modify(ContactData contact) {
    initModificationContactById(contact.getId());
    fillContactForm(contact, false);
    contactCache = null;
    submitContactModification();
  }

  public void delete(int index){
    selectContact(index);
    deleteSelectedContact();
    contactCache = null;
    wd.switchTo().alert().accept();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    wd.switchTo().alert().accept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements){
      String firstname = element.findElements(By.cssSelector("td")).get(2).getText();
      String lastname  = element.findElements(By.cssSelector("td")).get(1).getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    Contacts contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element : elements){
      //String firstname = element.findElements(By.cssSelector("td")).get(2).getText();
      //String lastname  = element.findElements(By.cssSelector("td")).get(1).getText();
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      //String[] phones = cells.get(5).getText().split("\n");
      String allphones = cells.get(5).getText();

      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allphones));
    }
    return contactCache;
  }

}

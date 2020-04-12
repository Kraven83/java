package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test1", "test2", "test3", "8-900-999-0000",  "Test Company", "Test addres", "test@test.ru", "[none]"));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initModificationContact();
    app.getContactHelper().fillContactForm(new ContactData("test15", "test25", "test35", "8-700-777-0000",  "Test Company2", "Test addres2", "test2@test2.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
  }

}

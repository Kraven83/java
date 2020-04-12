package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{
  @Test
  public void testContactModification() {
    app.getContactHelper().initModificationContact();
    app.getContactHelper().fillContactForm(new ContactData("test15", "test25", "test35", "8-700-777-0000",  "Test Company2", "Test addres2", "test2@test2.ru", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
  }

}

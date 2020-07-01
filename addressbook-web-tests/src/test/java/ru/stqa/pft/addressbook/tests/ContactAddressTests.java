package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.contact().isThereAContact()) {
      ContactData contactPrecondition = new ContactData().withFirstname("test1").withLastname("test2").withNickname("test3").withHomePhone("8-900-999-0000")
              .withAddress("Test addres").withCompany("Test Company").withEmail("test@test.ru").withGroup("test1");
      app.contact().create(contactPrecondition);
    }
  }

  @Test
  public void testContactAddress(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress().trim(), equalTo(contactInfoFromEditForm.getAddress().trim()));
  }

}

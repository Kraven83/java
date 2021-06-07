package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      ContactData contactPrecondition = new ContactData().withFirstname("test1").withLastname("test2").withNickname("test3").withHomePhone("8-900-999-0000")
              .withAddress("Test addres").withCompany("Test Company").withEmail("test@test.ru").withGroup("test1").withPhoto(new File("src/test/resources/stru.png"));
      app.contact().create(contactPrecondition);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("test15").withLastname("test25").withNickname("test35").withHomePhone("8-700-777-0000")
            .withAddress("Test addres2").withCompany("Test Company2").withEmail("test@test2.ru").withPhoto(new File("src/test/resources/stru.png"));
    app.contact().modify(contact);
    app.goTo().homePage();

    Contacts after = app.db().contacts();
    MatcherAssert.assertThat(after.size(), CoreMatchers.equalTo(before.size()));
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}

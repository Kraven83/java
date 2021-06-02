package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  //@Test(enabled = false)
  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData().withFirstname("test10").withLastname("test20").withNickname("test3").withHomePhone("8-900-999-0000")
            .withAddress("Test addres 0").withCompany("Test Company").withEmail("test0@test.ru").withGroup("test1").withPhoto(photo);

    app.contact().create(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    //Assert.assertEquals(after.size(),before.size() + 1);
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}

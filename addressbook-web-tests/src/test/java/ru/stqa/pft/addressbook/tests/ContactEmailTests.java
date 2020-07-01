package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.contact().isThereAContact()) {
      ContactData contactPrecondition = new ContactData().withFirstname("test1").withLastname("test2").withNickname("test3")
              .withEmail("test@test.ru").withGroup("test1").withEmail2("test2@test2.ru").withEmail3("test3@test3.ru");
      app.contact().create(contactPrecondition);
    }
  }

  @Test
  public void testContactAddress(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }
  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail().trim(), contact.getEmail2().trim(), contact.getEmail3().trim())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}

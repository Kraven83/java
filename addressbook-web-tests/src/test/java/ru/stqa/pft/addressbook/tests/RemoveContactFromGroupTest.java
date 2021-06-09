package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

public class RemoveContactFromGroupTest extends TestBase  {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactsNoneGroup();
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      ContactData contactPrecondition = new ContactData().withFirstname("test100").withLastname("test2")
              .withNickname("test3").withHomePhone("8-900-999-0000").withAddress("Test addres")
              .withCompany("Test Company").withEmail("test@test.ru").withPhoto(new File("src/test/resources/stru.png"));
      app.contact().create(contactPrecondition);
    }
    if(app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
    }
  }

  @Test
  public void testRemoveContactFromGroup(){
    app.goTo().homePage();
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    if(!contact.getGroups().contains(group)){
      app.contact().addToGroup(contact,group);
      app.goTo().contactsAllGroup();
      app.goTo().homePage();
    }

    contact = app.db().contacts().iterator().next();
    group = app.db().groups().iterator().next();
    Assert.assertTrue(contact.getGroups().contains(group));

    app.contact().removeFromGroup(contact,group);

    GroupData groupAfterAdd = app.db().groups().iterator().next();
    ContactData contactAfterAdd = app.db().contacts().iterator().next();
    Assert.assertFalse(contactAfterAdd.getGroups().contains(groupAfterAdd));
  }





}

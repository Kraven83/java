package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.User;
import ru.stqa.pft.mantis.model.UserList;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @BeforeMethod
  public void ensurePreconditions() throws MessagingException, IOException {
      if(app.db().userlist().size() == 1) {
        long now = System.currentTimeMillis();
        String username = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        app.registration().newUser(username, password, email);
      }
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {

    // Получение списка пользователей и выбор пользователя, для которого будет изменен пароль
    UserList userlist = app.db().userlist();
    User user = userlist.iterator().next();
    //System.out.println(user.getUsername() + "|" + user.getEmail());

    // Вход в систему под администратором
    String adminLogin = app.getProperty("web.adminLogin");
    String adminPass = app.getProperty("web.adminPassword");
    app.manager().login(adminLogin, adminPass);

    // Новый пароль для пользователя
    long now = System.currentTimeMillis();
    String newUserPassword = String.format("user%s",now);

    // Проверка, что под этим паролем пользователь не может быть авторизован (перед изменением пароля)
    HttpSession session = app.newSession();
    assertFalse(session.login(user.getUsername(),newUserPassword));

    // Смена пароля выбранному пользователю
    app.manager().changeUserPassword(user,newUserPassword);

    // Проверка нового пароля (после изменения)
    assertTrue(session.login(user.getUsername(),newUserPassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }

}

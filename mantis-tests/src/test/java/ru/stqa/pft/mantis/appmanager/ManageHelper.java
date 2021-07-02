package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ManageHelper extends HelperBase{

  public ManageHelper(ApplicationManager app) {
    super(app);
  }

  public void login (String username, String password) {
    goToLoginPage();
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void goToLoginPage() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
  }

  public void changeUserPassword(User user, String newUserPassword) throws MessagingException, IOException {
    goToManagePage();
    goToManageUserPage();
    openUserEditPageForID(user.getId());
    resetPassword(user.getEmail(),newUserPassword);
    goToManagePage();
  }

  private void resetPassword(String email, String newPassword) throws MessagingException, IOException {
    click(By.cssSelector("input[value='Reset Password']"));
    //Thread.sleep(10000);
    List<MailMessage> mailMessages = app.mail().waitForMail(1,60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    wd.get(confirmationLink);
    type(By.name("password"), newPassword);
    type(By.name("password_confirm"), newPassword);
    click(By.cssSelector("input[value='Update User']"));
  }

  private void openUserEditPageForID(int id) {
    wd.findElement(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + id + "']")).click();
  }

  private void goToManageUserPage() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  private void goToManagePage() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_overview_page.php");
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}

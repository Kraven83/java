package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.User;
import ru.stqa.pft.mantis.model.UserList;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;
  private ApplicationManager app;

  public DbHelper(ApplicationManager app)  throws NullPointerException {
    this.app = app;
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    //"C:/Users/Kraven/Documents/GitHub/java/mantis-tests/src/test/resources/hibernate.cfg.xml"
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }

  public UserList userlist() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from User").list();
    session.getTransaction().commit();
    session.close();
    return new UserList(result);
  }

}

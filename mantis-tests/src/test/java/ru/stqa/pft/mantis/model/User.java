package ru.stqa.pft.mantis.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="mantis_user_table")
public class User {
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Column(name="username")
  private String username;
  @Column(name="email")
  private String email;

  public int getId() {    return id;  }
  public String getUsername() {    return username;  }
  public String getEmail() {    return email;  }



}

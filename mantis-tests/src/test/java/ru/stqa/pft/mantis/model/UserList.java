package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserList extends ForwardingSet<User> {

  private Set<User> delegate;

  public UserList() {
    this.delegate = new HashSet<User>();
  }
  public UserList(Collection<User> users) {
    this.delegate = new HashSet<User>(users);
  }
  public UserList(UserList userList) {
    this.delegate = new HashSet<User>(userList.delegate);
  }

  @Override
  protected Set<User> delegate() {
    return delegate;
  }

  public UserList without(User user) {
    UserList userList = new UserList(this);
    userList.remove(user);
    return userList;
  }
}

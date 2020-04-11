package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String nickname;
  private final String homePhone;
  private final String company;
  private final String address;
  private final String email;

  public ContactData(String firstname, String lastname, String nickname, String homePhone, String company, String address, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.homePhone = homePhone;
    this.company = company;
    this.address = address;
    this.email = email;
  }
  public String getFirstame() {
    return firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public String getNickname() {
    return nickname;
  }
  public String getHomePhone() { return homePhone;}
  public String getCompany() {
    return company;
  }
  public String getAddress() {
    return address;
  }
  public String getEmail() {
    return email;
  }

}

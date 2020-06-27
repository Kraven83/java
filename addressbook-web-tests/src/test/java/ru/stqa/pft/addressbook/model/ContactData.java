package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String nickname;
  private String homePhone;
  private String company;
  private String address;
  private String email;
  private String group;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }
  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }
  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }
  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }
  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }
  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }
  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public int getId() {
    return id;
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
  public String getGroup() {
    return group;
  }

}

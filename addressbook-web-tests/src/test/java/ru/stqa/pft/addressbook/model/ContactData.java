package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name="firstname")
  private String firstname;
  @Expose
  @Column(name="lastname")
  private String lastname;
  @Expose
  @Column(name="nickname")
  private String nickname;
  @Expose
  @Column(name="home")
  @Type(type="text")
  private String homePhone="";
  @Column(name="work")
  @Type(type="text")
  private String workPhone="";
  @Transient
  private String allPhones="";
  @Column(name="mobile")
  @Type(type="text")
  private String mobilePhone="";
  @Expose
  @Column(name="company")
  private String company;
  @Expose
  @Column(name="address")
  @Type(type="text")
  private String address;
  @Expose
  @Column(name="email")
  @Type(type="text")
  private String email="";
  @Column(name="email2")
  @Type(type="text")
  private String email2="";
  @Column(name="email3")
  @Type(type="text")
  private String email3="";
  @Transient
  private String allemails="";
  @Column(name="photo")
  @Type(type="text")
  private String photo;

  @Expose
  @Transient
  private String group;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name="group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public Groups getGroups() {
    return new Groups(groups);
  }

  public Integer getGroupsCount() {
    return new Groups(groups).size();
  }

  public File getPhoto() {
    return new File(photo);
  }

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
  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }
  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
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
  public ContactData withEmail2(String email) {
    this.email2 = email;
    return this;
  }
  public ContactData withEmail3(String email) {
    this.email3 = email;
    return this;
  }
  public ContactData withAllEmails(String email) {
    this.allemails = email;
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

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            "}";
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
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
  public String getWorkPhone() { return workPhone;}
  public String getMobilePhone() { return mobilePhone;}
  public String getAllPhones() { return allPhones;}
  public String getCompany() {
    return company;
  }
  public String getAddress() {
    return address;
  }
  public String getEmail() {
    return email;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }
  public String getAllEmails() {
    return allemails;
  }
  public String getGroup() {
    return group;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}

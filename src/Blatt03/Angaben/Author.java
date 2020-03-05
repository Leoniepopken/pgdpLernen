
public class Author {
  private String firstName;
  private String lastName;
  private Date birthday;
  private String residence;
  private String email;

  public Author(String firstName, String lastName, Date birthday, String residence, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.residence = residence;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public Date getBirthday() {
    return birthday;
  }
  public String getResidence() {
    return residence;
  }
  public String getEmail() {
    return email;
  }
  public String toString() {
    return this.firstName + " " + this.lastName;
  }
  public String getContactInformation() {
    return this.firstName + " " + this.lastName + "Terminal.NEWLINE" + "<" + this.email + ">" + "Terminal.NEWLINE"
        + this.residence;

//		return "<" + this.email + ">"+ Terminal.NEWLINE
//				+ this.firstName + " " + this.lastName  + Terminal.NEWLINE
//				+ this.residence;

  }

  public int getAgeAt(Date today) {
    return this.birthday.getAgeInYearsAt(today);
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public void setResidence(String residence) {
    this.residence = residence;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean equals (Author author){
    if (author == null) {return false;}
    if (this.getFirstName().equals(author.getFirstName()) && this.getLastName().equals(author.getLastName())
            && this.getBirthday() == author.getBirthday() && this.getResidence().equals(author.getResidence())
            && this.getEmail().equals(author.getEmail())){
      return true;
    }
    return false;
  }
}

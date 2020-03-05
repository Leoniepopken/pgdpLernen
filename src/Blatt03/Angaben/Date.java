
public class Date {
  private int day;
  private int month;
  private int year;
  public Date() {
    //this.day = Terminal.TODAYS_DAY;
    //this.month = Terminal.TODAYS_MONTH;
    //this.year = Terminal.TODAYS_YEAR;
  }
  public Date(int day, int month, int year) {
    setMonth(month);
    setYear(year);
    setDay(day);
  }

  public int getDay() {
    return day;
  }
  public int getMonth() {
    return month;
  }
  public int getYear() {
    return year;
  }
  private int daysSince1970() {
    return (this.year - 1970) * 30 * 12 + (this.month - 1) * 30 + (this.day - 1);
  }
  public int getAgeInYearsAt(Date today) {
    int ageInDays = this.getAgeInDaysAt(today);
    int ageInMonths = ageInDays / 30;
    int ageInYears = ageInMonths / 12;
    return ageInYears;
  }

  public int getAgeInDaysAt(Date today) {
    return today.daysSince1970() - this.daysSince1970();
  }
  public String toString() {
    return this.day + "/" + this.month + "/" + this.year;
  }

  int februar = 0;
  int [] monate = new int [] {31, februar, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public void setDay(int day) {
    this.day = day;
    if (year % 4 == 0){
      februar = 29;
      monate[1] = februar;
    }
    else {
      februar = 28;
      monate[1] = februar;
    }
    if (day < 1){
      this.day = -1;
    }
    if (monate[month - 1] < day){
      this.day = -1;
    }
  }

  public void setMonth(int month) {
    this.month = month;
    if (month < 1 || month > 12){
      this.month = -1;
    }
  }

  public void setYear(int year) {
    this.year = year;
    if (year < 0){
      this.year = -1;
    }
  }

  public boolean equals(Date date){
    if (date == null){
      return false;
    }
    if (this.getDay() == date.getDay()  && this.getMonth() == date.getMonth() && this.getYear() == date.getYear()){
      return true;
    }
    return false;
  }

  public static void main (String [] args){
    System.out.println(new Date(29, 2, 2020));
  }
}

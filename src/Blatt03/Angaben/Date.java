/**
 * The class {@code Date} represents a date.
 * 
 * A {@code Date} consists of a day, a month and a year.
 * 
 *
 */
public class Date {
  /**
   * the day of the date
   */
  private int day;

  /**
   * the month of the date
   */
  private int month;

  /**
   * the year of the date
   */
  private int year;

  /**
   * Constructs a date that represents the current date.
   */
  public Date() {
    this.day = Terminal.TODAYS_DAY;
    this.month = Terminal.TODAYS_MONTH;
    this.year = Terminal.TODAYS_YEAR;
  }

  /**
   * Constructs a date with the given values.
   * 
   * @param day   the day
   * @param month the month
   * @param year  the year
   */
  public Date(int day, int month, int year) {
    setMonth(month);
    setYear(year);
    setDay(day);
  }

  /**
   * Returns the day of the date.
   * 
   * @return the day of the date
   */
  public int getDay() {
    return day;
  }

  /**
   * Returns the month of the date.
   * 
   * @return the month of the date
   */
  public int getMonth() {
    return month;
  }

  /**
   * Returns the year of the date.
   * 
   * @return the year of the date
   */
  public int getYear() {
    return year;
  }

  /**
   * Returns the days of this date since 01/01/1970. We have to admit that this is
   * a very simple implementation that assumes that every month has exactly 30
   * days and that there are no leap years.
   * 
   * @return the days since 01/01/1970
   */
  private int daysSince1970() {
    // 30 days for every month
    return (this.year - 1970) * 30 * 12 + (this.month - 1) * 30 + (this.day - 1);
  }

  /**
   * Returns the full years between the specified date and the date represented by
   * this instance.
   * 
   * @param today the specified date
   * @return the full years
   */
  public int getAgeInYearsAt(Date today) {
    int ageInDays = this.getAgeInDaysAt(today);
    int ageInMonths = ageInDays / 30;
    int ageInYears = ageInMonths / 12;
    return ageInYears;
  }

  /**
   * Returns the days between the specified date and the date represented by this
   * instance.
   * 
   * @param today the specified date
   * @return the days
   */
  public int getAgeInDaysAt(Date today) {
    return today.daysSince1970() - this.daysSince1970();
  }

  /**
   * Returns a string representation of this date.
   */
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

  /**
   * @param year
   * wenn das jahr negativ ist, wird -1 rein geschrieben
   */
  public void setYear(int year) {
    this.year = year;
    if (year < 0){
      this.year = -1;
    }
  }

  public static void main (String [] args){
    System.out.println(new Date(29, 2, 2020));
  }
}

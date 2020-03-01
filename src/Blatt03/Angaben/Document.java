/**
 * The class {@code Document} represents a document.
 * 
 * @see Date
 * @see Author
 *
 */
public class Document {
  /**
   * the title of the document
   */
  private String title;

  /**
   * the language of the document
   */
  private String language;

  /**
   * summary of the document
   */
  private String summary;

  /**
   * content of the document
   */
  private String content;

  /**
   * the release date of the document
   * 
   * @see Date
   */
  private Date releaseDate;

  /**
   * the {@link Author} of the document
   * 
   * @see Author
   */
  private Author author;

  /**
   * Constructs a document with the given values.
   * 
   * @param title       the document's title
   * @param language    the language the document is written in
   * @param summary     short summary of the document
   * @param releaseDate the release date of the document
   * @param author      the author of the document
   */
  public Document(String title, String language, String summary, Date releaseDate, Author author, String content) {
    this.title = title;
    this.language = language;
    this.summary = summary;
    this.releaseDate = releaseDate;
    this.author = author;
    this.content = content;
  }

  /**
   * Returns the title of the document.
   * 
   * @return the title of the document
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the language the document is written in.
   * 
   * @return the language the document is written in
   */
  public String getLanguage() {
    return language;
  }

  /**
   * Returns the text content of the document.
   * 
   * @return the text content of the document
   */
  public String getContent() {
    return content;
  }

  /**
   * Returns a short summary of the document.
   * 
   * @return a short summary of the document
   */
  public String getSummary() {
    return summary;
  }

  /**
   * Returns the release date of the document.
   * 
   * @return the release date of the document
   */
  public Date getReleaseDate() {
    return releaseDate;
  }

  /**
   * Returns the author of the document.
   * 
   * @return the author of the document
   * @see Author
   */
  public Author getAuthor() {
    return author;
  }

  /**
   * Returns a brief string representation of this document.
   */
  public String toString() {
    return this.title + " by " + this.author.toString();
  }

  /**
   * Returns the age of this document at the specified date in days.
   * 
   * @param today the specified date
   * @return the age of this document at the specified date:
   */
  public int getAgeAt(Date today) {
    return this.releaseDate.getAgeInDaysAt(today);
  }
}

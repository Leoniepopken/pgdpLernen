/**
 * The class {@code Review} represents a review of a {@link Document}.
 * 
 * @see Document
 * @see Author
 *
 */
public class Review {
  /**
   * the {@link Author} of the review
   * 
   * @see Author
   */
  private Author author;

  /**
   * the reviewed {@link Document}
   * 
   * @see Document
   */
  private Document reviewedDocument;

  /**
   * the language of the review
   */
  private String language;

  /**
   * the release date of the review
   * 
   * @see Date
   */
  private Date releaseDate;

  /**
   * the rating of the document, ranging from 0 to 10
   */
  private int rating;

  /**
   * the text content of the review
   */
  private String content;

  /**
   * Constructs a review with the given values
   * 
   * @param author           the author of the review
   * @param reviewedDocument the document that has been reviewed
   * @param language         the language the review is written in
   * @param releaseDate      the release date of the review
   * @param rating           the rating of the reviewed document
   */
  public Review(Author author, Document reviewedDocument, String language, Date releaseDate, int rating, String content) {
    this.author = author;
    this.reviewedDocument = reviewedDocument;
    this.language = language;
    this.releaseDate = releaseDate;
    this.rating = rating;
    this.content = content;
  }

  /**
   * Returns the author of the review.
   * 
   * @return the author of the review
   * @see Author
   */
  public Author getAuthor() {
    return author;
  }

  /**
   * Returns the reviewed document.
   * 
   * @return the reviewed document
   * @see Document
   */
  public Document getReviewedDocument() {
    return reviewedDocument;
  }

  /**
   * Returns the language the review is written in.
   * 
   * @return the language the review is written in
   */
  public String getLanguage() {
    return language;
  }

  /**
   * Returns the release date of the review.
   * 
   * @return the release date of the review
   * @see Date
   */
  public Date getReleaseDate() {
    return releaseDate;
  }

  /**
   * Returns how the reviewed document has been rated.
   * 
   * @return how the reviewed document has been rated
   */
  public int getRating() {
    return rating;
  }

  /**
   * Returns the written review text content.
   * 
   * @return the written review text content
   */
  public String getContent() {
    return content;
  }


  /**
   * Returns a brief string representation of this review.
   */
  public String toString() {
    return this.reviewedDocument.toString() + " is rated " + this.rating + " by " + this.author.toString();
  }

  /**
   * Returns the age of this review at the specified date in days.
   * 
   * @param today the specified date
   * @return the age of this review at the specified date:
   */
  public int getAgeAt(Date today) {
    return this.releaseDate.getAgeInDaysAt(today);
  }
}

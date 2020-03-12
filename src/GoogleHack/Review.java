
public class Review {

  private Author author;

  public void setAuthor(Author author) {
    this.author = author;
  }
  public void setReviewedDocument(Document reviewedDocument) {
    this.reviewedDocument = reviewedDocument;
  }
  public void setLanguage(String language) {
    this.language = language;
  }
  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }
  public void setRating(int rating) {
    this.rating = rating;
  }
  public void setContent(String content) {
    this.content = content;
  }

  private Document reviewedDocument;
  private String language;
  private Date releaseDate;
  private int rating;
  private String content;

  public Review(Author author, Document reviewedDocument, String language, Date releaseDate, int rating, String content) {
    this.author = author;
    this.reviewedDocument = reviewedDocument;
    this.language = language;
    this.releaseDate = releaseDate;
    this.rating = rating;
    this.content = content;
  }

  public Author getAuthor() {
    return author;
  }
  public Document getReviewedDocument() {
    return reviewedDocument;
  }
  public String getLanguage() {
    return language;
  }
  public Date getReleaseDate() {
    return releaseDate;
  }
  public int getRating() {
    return rating;
  }
  public String getContent() {
    return content;
  }
  public String toString() {
    return this.reviewedDocument.toString() + " is rated " + this.rating + " by " + this.author.toString();
  }
  public int getAgeAt(Date today) {
    return this.releaseDate.getAgeInDaysAt(today);
  }

  public boolean equals(Review review){
    if (review == null) {return false;}
    if (this.getAuthor().equals(review.getAuthor()) && this.getReviewedDocument().equals(review.getReviewedDocument())
        && this.getLanguage().equals(review.getLanguage()) && this.getReleaseDate().equals(review.getReleaseDate())
        && this.getRating() == review.getRating() && this.getContent().equals(review.getContent())){
      return true;
    }
    return false;
  }
}

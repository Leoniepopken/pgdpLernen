/**
 * A Collection of {@link LinkedDocument}s.
 * 
 * This class ensures, that only {@link LinkedDocument}s are added to this
 * collection.
 * 
 * @author
 *
 */
public class LinkedDocumentCollection extends DocumentCollection {

  /**
   * Empty constructor that just utilizes constructor of super class.
   */
  public LinkedDocumentCollection() {
    super();
  }

  /**
   * The specified {@link Document} is added to this collection only if it is of
   * type {@link LinkedDocument} and if it is not already contained.
   * 
   * @param doc the document to add
   */
  public void prependDocument(LinkedDocument doc) {
    if (!(this.contains(doc))) {
      super.prependDocument(doc);
    }
  }

  /**
   * The specified {@link Document} is added to this collection only if it is of
   * type {@link LinkedDocument} and if it is not already contained.
   * 
   * @param doc the document to add
   */
  @Override
  public void appendDocument(Document doc) {
    if ((doc instanceof LinkedDocument) && !(this.contains(doc))) {
      super.appendDocument(doc);
    }
  }

  /**
   * Private helper method that crawls this collection.
   * 
   * This method adds all LinkedDocuments of this collection to the specified
   * {@link LinkedDocumentCollection}, if they are not already contained.
   * Additionally, this method recursively crawls all outgoing links of all
   * {@link LinkedDocument}s of this collection.
   * 
   * @param resultCollection in and out parameter. All LinkedDocuments are
   *                         (recursively) added to this
   *                         {@link LinkedDocumentCollection}.
   */
  private void crawl(LinkedDocumentCollection resultCollection) {
    if (this.numDocuments() == 0) {
      return;
    }

    /*
     * loop over all documents of this collection and add them to the in/out
     * parameter, if not already contained.
     */
    for (int i = 0; i < this.numDocuments(); i++) {
      LinkedDocument doc = (LinkedDocument) this.get(i);

      if (!resultCollection.contains(doc)) {
        resultCollection.appendDocument(doc);

        /* do the same recursively */
        doc.getOutgoingLinks(resultCollection).crawl(resultCollection);
      }
    }
  }

  /**
   * This method crawls this {@link LinkedDocumentCollection} and returns a new
   * {@link LinkedDocumentCollection}.
   * 
   * The returned LinkedDocumentCollection contains all LinkedDocuments of this
   * LinkedDocumentCollection plus any LinkedDocuments that the LinkedDocuments of
   * this collection link to. If these additional LinkedDocuments again link to
   * other LinkedDocuments they will be included as well, and so on.
   * 
   * @return a {@link LinkedDocumentCollection} that contains all
   *         {@link LinkedDocument}s of this collection plus any
   *         {@link LinkedDocument}s that are linked either directly or
   *         indirectly.
   */
  public LinkedDocumentCollection crawl() {
    /* prepare the resulting collection and begin crawling ... */
    LinkedDocumentCollection resultCollection = new LinkedDocumentCollection();
    this.crawl(resultCollection);
    return resultCollection;
  }

  /**
   * This method calculates all incoming links for every {@link LinkedDocument} in
   * this collection. The incoming links are assigned to every LinkedDocument via
   * the {@link LinkedDocument#addIncomingLink(LinkedDocument)} method.
   */
  public void calculateIncomingLinks() {
    /* loop over all documents in this collection */
    for (int i = 0; i < this.numDocuments(); i++) {
      LinkedDocument doc = (LinkedDocument) this.get(i);

      /* again, loop over all documents of this collection */
      for (int j = 0; j < this.numDocuments(); j++) {
        LinkedDocument incomingDoc = (LinkedDocument) this.get(j);

        /*
         * Check if doc is contained in the outgoing links of incomingDoc. If so, add to
         * incoming links of doc.
         */
        if (incomingDoc.getOutgoingLinks().contains(doc)) {
          doc.addIncomingLink(incomingDoc);
        }
      }
    }
  }

  /**
   * Returns a string representation of this {@link LinkedDocumentCollection}
   * using the IDs of the {@link LinkedDocument}s.
   * 
   * @return a string representation of this collection
   */
  public String toString() {
    if (this.numDocuments() == 0) {
      return "[]";
    }

    if (this.numDocuments() == 1) {
      return "[" + ((LinkedDocument) this.get(0)).getID() + "]";
    }

    String res = "[";
    for (int i = 0; i < this.numDocuments() - 1; i++) {
      res += ((LinkedDocument) this.get(i)).getID() + ", ";
    }
    res += ((LinkedDocument) this.get(this.numDocuments() - 1)).getID() + "]";
    return res;
  }

  /**
   * Finds a {@link LinkedDocument} with the given id 
   * if contained in this {@link LinkedDocumentCollection}
   * 
   * @param id           the id to be looked up
   * @return the found {@link LinkedDocument} or null
   */
  public LinkedDocument findByID(String id) {
    for (int i = 0; i < this.numDocuments(); i++)
      if (((LinkedDocument) this.get(i)).getID().equals(id))
        return ((LinkedDocument) this.get(i));
    return null;
  }

}

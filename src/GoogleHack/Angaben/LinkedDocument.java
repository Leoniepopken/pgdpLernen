public class LinkedDocument extends Document{

    private String ID; //unveränderlich -> static
    private String title;
    private String language;
    private String summary;
    private String content;
    private Date releaseDate;
    private Author author;

    public LinkedDocument(String title, String language, String summary, Date releaseDate, Author author,
                          String content, String ID) {
        super(title, language, summary, releaseDate, author, content);
        this.title = title;
        this.language = language;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.author = author;
        this.ID = ID;
        wordCounts = new WordCountsArray(1);
        addContent(content);
    }


    public String getID(){
        return ID;
    }

    @Override
    public boolean equals(Document doc){
        if (doc.getClass() == LinkedDocument())
    }

    private String [] findOutgoingIDs(String text){

    }

    private void setLinkCountZero(){

    }

    public void addIncomingLink(LinkedDocument incomingLink){

    }

    public static LinkedDocument createLinkedDocumentFromFile(String fileName){

    }

    private void createOutgoingDocumentCollection(){

    }

    public LinkedDocumentCollection getOutgoingLinks(){

    }

    public LinkedDocumentCollection getIncomingLinks(){

    }
}

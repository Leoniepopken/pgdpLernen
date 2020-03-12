public class LinkedDocument extends Document{

    private final String ID;
    private String title;
    private String language;
    private String summary;
    private String content;
    private Date releaseDate;
    private Author author;
    private String[] links;
    LinkedDocumentCollection outgoingLinks;
    LinkedDocumentCollection ingoingLinks;

    public LinkedDocument(String title, String language, String summary, Date releaseDate, Author author,
                          String content, String ID) {
        super(title, language, summary, releaseDate, author, content);
        this.ID = ID;
        wordCounts = new WordCountsArray(1);
        addContent(content);
        this.links = findOutgoingIDs(content);
    }


    public String getID(){
        return ID;
    }

    @Override
    public boolean equals(Document doc){
        if (doc instanceof LinkedDocument && ((LinkedDocument) doc).getID().equals(this.getID())){
            return true;
        } else {
            return false;
        }
    }

    private String [] findOutgoingIDs(String text){
        //Rausfinden, wie lang das Array überhaupt sein soll
        int countDoppelPunkte = 0;
        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == ':'){
                countDoppelPunkte++;
            }
        }
        String [] array = new String[countDoppelPunkte];
        int index = 0;
        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == ':' && text.substring(i-4, i-1).equals("link")){
                String element = "";
                int j = i + 1;
                while (text.charAt(j) != ' '){
                    element += text.charAt(j);
                    j++;
                }
                array[i] = element;
            }
        }
        return array;
    }

    private void setLinkCountZero(){
        for (int i = 0; i < wordCounts.size(); i++){
            if(wordCounts.getWord(i).startsWith("link:")){
                wordCounts.setCount(i, 0);
            }
        }
    }

    public void addIncomingLink(LinkedDocument incomingLink){
        if (!incomingLink.equals(this.getID())) {
            ingoingLinks.appendDocument(incomingLink);
        }
    }

    public static LinkedDocument createLinkedDocumentFromFile(String fileName){
        String [] array = Terminal.readFile(fileName);
        if (array.length > 2){
            return null;
        } else {
            LinkedDocument doc = new LinkedDocument(array[0], "", "",
                    new Date(1, 1, 1 ),
                    new Author("", "", new Date(1, 1 , 1), "", ""),
                    array[1], fileName);
            return doc;
        }
    }

    private void createOutgoingDocumentCollection(){
        String [] array = findOutgoingIDs(this.getContent());
        for (int i = 0; i < array.length; i++){
            if (!array[i].equals(this.getID())) {
                LinkedDocument doc = createLinkedDocumentFromFile(array[i]);
                outgoingLinks.appendDocument(doc);
            }
        }
    }

    public LinkedDocumentCollection getOutgoingLinks(){
        LinkedDocumentCollection ldc = new LinkedDocumentCollection();
        if(outgoingLinks == null){
            createOutgoingDocumentCollection();
            return ldc;
        } else {
            return outgoingLinks;
        }
    }

    public LinkedDocumentCollection getIncomingLinks(){
        return ingoingLinks;
    }
}

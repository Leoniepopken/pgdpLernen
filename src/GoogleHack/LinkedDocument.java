import java.util.Arrays;

public class LinkedDocument extends Document{

    private final String ID;
    private String[] links;
    LinkedDocumentCollection outgoingLinks;
    LinkedDocumentCollection ingoingLinks;

    public LinkedDocument(String title, String language, String summary, Date releaseDate, Author author,
                          String content, String ID) {
        super(title, language, summary, releaseDate, author, content);
        setLinkCountZero();
        this.ID = ID;
        this.links = findOutgoingIDs(content);
    }

    public String[] getLinks() {
        return links;
    }

    public String getID(){
        return ID;
    }

    @Override
    public boolean equals(Document doc){
        return doc instanceof LinkedDocument && ((LinkedDocument) doc).getID().equals(this.getID());
    }

    public String [] findOutgoingIDs(String text){
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
            if(text.charAt(i) == ':' && text.substring(i-4, i).equals("link")){
                String element = "";
                int j = i + 1;
                while (text.charAt(j) != ' '){
                    element += text.charAt(j);
                    j++;
                }
                array[index] = element;
                index++;
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
        if (!incomingLink.getID().equals(this.getID())) {
            ingoingLinks.appendDocument(incomingLink);
        }
    }

    public static LinkedDocument createLinkedDocumentFromFile(String fileName){
        String [] array = Terminal.readFile(fileName);
        if (array.length != 2){
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
        for (int i = 0; i < links.length; i++){
            if (!links[i].equals(this.getID())) {
                LinkedDocument doc = createLinkedDocumentFromFile(links[i]);
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

    public static void main(String[] args) {
        LinkedDocument doc = new LinkedDocument("Pgdp", null, null, null, null,
                "zahlen von link:eins bis link:zwei sind langweilig", "numbers");
        System.out.println(Arrays.toString(doc.links));
    }
}

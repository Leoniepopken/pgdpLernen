public class TestIt {
    public static void main (String [] args) {

        DocumentCollection dc = new DocumentCollection();

        while (true) {

            String s = Terminal.askString("> ");

            if (s.equals("exit")) {
                return;
            }

            if (s.startsWith("add")) {
                String fliesstext = s.substring(s.indexOf(':') + 1);
                String titel = s.substring(3, s.indexOf(':'));
                Document doc = new Document(
                        titel, "Java", "main", new Date(10, 10, 1010),
                        new Author("Leonie", "Poppi",
                                new Date(10, 10, 1),
                                "Wuhan", "leonie69@hotmail.com"),
                        fliesstext);
                dc.appendDocument(doc);
            }

            if (s.equals("list")) {
                String output = "";
                for (int i = 0; i < dc.numDocuments(); i++) {
                    output += dc.get(i).getTitle() + "\n";
                }
                System.out.println(output);
            }

            if(s.startsWith("count")){
                String word = s.substring(6);
                for (int i = 0; i < dc.numDocuments(); i++) {
                    int index = dc.get(i).getWordCounts().getIndexOfWord(word);
                    String title = dc.get(i).getTitle();
                    if (index == -1){
                        System.out.println(title + " " + 0);
                    } else {
                        System.out.println(title + " " + dc.get(i).getWordCounts().getCount(index));
                    }
                }
            }
        }
    }
}

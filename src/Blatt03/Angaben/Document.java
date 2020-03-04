import java.util.Arrays;

public class Document {

    private String title;
    private String language;
    private String summary;
    private String content;
    private Date releaseDate;
    private Author author;
    public static final String[] SUFFICES = new String[]{"ie", "ab", "al", "ant", "artig", "bar", "chen", "ei", "eln", "en", "end", "ent", "er", "fach", "fikation", "fizieren", "fähig",
            "gemäß", "gerecht", "haft", "haltig", "heit", "ie", "ieren", "ig", "in", "ion", "iren", "isch", "isieren", "isierung",
            "ismus", "ist", "ität", "iv", "keit", "kunde", "legen", "lein", "lich", "ling", "logie", "los", "mal", "meter", "mut",
            "nis", "or", "sam", "schaft", "tum", "ung", "voll", "wert", "würdig"};
    public WordCountsArray wordCounts;

    public Document(String title, String language, String summary, Date releaseDate, Author author, String content) {
        this.title = title;
        this.language = language;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.author = author;
        wordCounts = new WordCountsArray(1);
        addContent(content);
    }

    public WordCountsArray getWordCounts() {
        return wordCounts;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getContent() {
        return content;
    }

    public String getSummary() {
        return summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public String toString() {
        return this.title + " by " + this.author.toString();
    }

    public int getAgeAt(Date today) {
        return this.releaseDate.getAgeInDaysAt(today);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    private static String[] tokenize(String content) {
        int anzahlLeerzeichen = 0;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == ' ') {
                anzahlLeerzeichen++;
            }
        }
        String[] tokens = new String[anzahlLeerzeichen + 1];
        int m = 0;
        String wort = "";
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) != ' ') {
                wort += content.charAt(i);
            } else {
                tokens[m++] = wort;
                wort = "";
            }
        }
        tokens[m] = wort;
        return tokens;
    }

    private static boolean sufficesEqual(String w1, String w2, int n) {
        if (n > w1.length() || n > w2.length()) {
            return false;
        }
        int zählW1 = w1.length() - 1;
        int zählW2 = w2.length() - 1;
        while (n > 0) {
            if (w1.charAt(zählW1) != w2.charAt(zählW2)) {
                return false;
            }
            n--;
            zählW1--;
            zählW2--;
        }
        return true;
    }

    private static String findSuffix(String word) {
        for (int i = 0; i < SUFFICES.length; i++) {
            if (sufficesEqual(word, SUFFICES[i], SUFFICES[i].length())) {
                return SUFFICES[i];
            } else {
                return " ";
            }
        }
        return " ";
    }

    private static String cutSuffix(String word, String suffix) {
        if (findSuffix(word).equals(" ")) {
            return word;
        } else {
            int längeSuffix = findSuffix(word).length();
            return word.substring(0, word.length() - längeSuffix);
        }
    }

    private void addContent(String content) {
        String[] tokens = tokenize(content);
        for (int i = 0; i < tokens.length; i++) {
            String resultierendesWort = cutSuffix(tokens[i], findSuffix(tokens[i]));
            wordCounts.add(resultierendesWort, 1);
        }
    }

    public static void main(String[] args) {
        Date releaseDate = new Date(20, 9, 2006);
        Date geburtstag = new Date(20, 10, 2000);
        Author leonie = new Author("Leonie", "Popken", geburtstag, "München", "leonie.popken");
        Document d = new Document("Pgdp", "spanisch", "das kommt mir spanisch vor", releaseDate, leonie,
                "leonie es war einmal ein joscha der der leonie versuchte pgdp beizubringen");
        System.out.println(d.getWordCounts());
    }


}

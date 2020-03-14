public class RezeptFinder {

    String [] zutatas;
    Rezept [] rezepts = {
            new Rezept("bild", "man nehme ein ei und schlage es in die Pfanne", "Sunny-Side-Up",
                    new String[]{"Ei", "Öl", "Bacon"}),
            new Rezept("bild", "Protein-Porrige", "Protein, Wasser und Hafer in Schüssel und umrühren",
                    new String[]{"Protein", "Haferflocken", "Milch"})
    };

    public void zutatenFiltern (String zutaten){
        zutaten = zutaten.substring(zutaten.indexOf('?'));
        zutaten = zutaten.substring(1, zutaten.indexOf(' '));
        zutatas = zutaten.split("=on&");
        zutatas[zutatas.length - 1] = zutatas[zutatas.length - 1].replace("=on", "");
    }

    public Rezept[] möglicheFinden (){
        return null; //TODO
    }
}

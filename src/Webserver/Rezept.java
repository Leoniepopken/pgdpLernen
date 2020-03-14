public class Rezept {

    String bild;
    String anleitung;
    String title;
    String [] zutaten;

    public Rezept(String bild, String anleitung, String title, String[] zutaten) {
        this.bild = bild;
        this.anleitung = anleitung;
        this.title = title;
        this.zutaten = zutaten;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public String getAnleitung() {
        return anleitung;
    }

    public void setAnleitung(String anleitung) {
        this.anleitung = anleitung;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getZutaten() {
        return zutaten;
    }

    public void setZutaten(String[] zutaten) {
        this.zutaten = zutaten;
    }

    public int fehlendeZutatas (String [] vorhandeneZutatas){
        int vorhanden = 0;
        for (int i = 0; i < zutaten.length; i++) {
            for (int j = 0; j < vorhandeneZutatas.length; j++) {
                if(zutaten[i].equals(vorhandeneZutatas[j])){
                    vorhanden += 1;
                }
            }
        }
        return zutaten.length - vorhanden;
    }
}

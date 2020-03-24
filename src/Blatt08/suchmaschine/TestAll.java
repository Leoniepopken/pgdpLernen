import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAll {
  String text = "harry versuchte uebrigens nicht zum ersten male die sache zu erklaeren";
  String text2 =
      "harry versuchte uebrigens nicht zum ersten male die sache zu harry versuchte uebrigens nicht zum ersten male die sache zu";
  String text3 =
      "harry versuchte uebrigens nicht zum ersten male die sache zu erklaeren harry versuchte uebrigens nicht zum ersten male die sache zu erklaeren harry versuchte uebrigens nicht zum ersten male die sache zu erklaeren";
  Document doc = new Document("harry Potter", "Deutsch", "Top Seller",
      new Date(5, 6, 1998), new Author("Joanne", "K. Rowling", new Date(31, 7, 1965),
          "Londen", "joanne@potter.com"),
      text);
  Document doc2 = new Document("harry Potter 2", "Deutsch", "Top Seller",
      new Date(5, 6, 1998), new Author("Joanne", "K. Rowling", new Date(31, 7, 1965),
          "Londen", "joanne@potter.com"),
      text2);
  Document doc3 = new Document("harry Potter 3", "Deutsch", "Top Seller",
      new Date(5, 6, 1998), new Author("Joanne", "K. Rowling", new Date(31, 7, 1965),
          "Londen", "joanne@potter.com"),
      text3);

  @Test
  public void atestVererbung() throws IllegalAccessException, IllegalArgumentException,
      InvocationTargetException, ClassNotFoundException {
    Class<?> clazz = Class.forName("LinkedDocument");
    assertEquals("Vererbung stimmt nicht.", "document",
        clazz.getSuperclass().getName().toLowerCase());
    clazz = Class.forName("LinkedDocumentCollection");
    assertEquals("Vererbung stimmt nicht", "documentcollection",
        clazz.getSuperclass().getName().toLowerCase());
  }

  @Test
  public void btestID() {
    fail("getID wird nicht getestet");
  }

  @Test
  public void ctestEquals() {
    fail("equals wird nicht getestet");
  }

  @Test
  public void dtestFindOutgoingIds() throws IllegalAccessException,
      IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
    Class<?> clazz = Class.forName("LinkedDocument");
    Method[] methods = clazz.getDeclaredMethods();
    for (Method m : methods) {
      // Test Method findOutgoingIds, 4.1 iii)
      if (m.getName().toLowerCase().equals("findoutgoingids")) {
        assertTrue("nicht private", Modifier.isPrivate(m.getModifiers()));
        assertEquals("Rückgabetyp stimmt nicht", "[Ljava.lang.String;",
            m.getReturnType().getName());
        assertEquals("Parametermenge stimmt nicht", 1, m.getParameterTypes().length);

        LinkedDocument ld =
            new LinkedDocument("Hans", "de", "Kurzes Maerchen", new Date(),
                new Author("Brueder", "Grimm", new Date(), "Wohnort irgendwo",
                    "hans@grimm.comm"),
                "hans hatte sieben link:dateisieben jahre bei seinem herrn gedient",
                "ISBN:1");
        m.setAccessible(true);
        String[] s = (String[]) m.invoke(ld,
            "es ist einmal eine alte geiss link:dateiziege gewesen die hatte sieben link:dateisieben junge zicklein");
        assertEquals("Falsche Ausgabe", 2, s.length);
        return;
      }
    }
    fail("Method not found");
  }

  @Test
  public void etestSetLinkCountZero() throws IllegalAccessException,
      IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
    Class<?> clazz = Class.forName("LinkedDocument");
    Method[] methods = clazz.getDeclaredMethods();
    for (Method m : methods) {
      if (m.getName().toLowerCase().equals("setlinkcountzero")) {
        assertTrue("nicht private", Modifier.isPrivate(m.getModifiers()));
        assertEquals("Rückgabetyp stimmt nicht", "void", m.getReturnType().getName());
        assertEquals("Parametermenge stimmt nicht", 0, m.getParameterTypes().length);
        LinkedDocument ld =
            new LinkedDocument("Hans", "de", "Kurzes Maerchen", new Date(),
                new Author("Brueder", "Grimm", new Date(), "Wohnort irgendwo",
                    "hans@grimm.comm"),
                "hans hatte sieben link:dateisieben jahre bei seinem herrn gedient",
                "ISBN:1");
        m.setAccessible(true);
        int index = ld.getWordCounts().getIndexOfWord("link:dateisieb");
        int count = ld.getWordCounts().getCount(index);
        // System.out.println(count);
        m.invoke(ld);
        int count2 = ld.getWordCounts().getCount(index);
        assertEquals("Falsches Ergebnis", 0, count2);
        return;
      }
    }
    fail("Method not found");
  }

  @Test
  public void ftestAppendPrepend() {
    LinkedDocument ld1 = new LinkedDocument("Hans", "de", "Kurzes Maerchen", new Date(),
        new Author("Brueder", "Grimm", new Date(), "Wohnort irgendwo", "hans@grimm.comm"),
        "hans hatte sieben link:dateisieben jahre bei seinem herrn gedient", "ISBN:1");
    LinkedDocument ld2 = new LinkedDocument("Hans", "de", "Kurzes Maerchen", new Date(),
        new Author("Brueder", "Grimm", new Date(), "Wohnort irgendwo", "hans@grimm.comm"),
        "es ist einmal eine alte geiss link:dateiziege gewesen die hatte sieben link:dateisieben junge zicklein",
        "ISBN:2");
    LinkedDocumentCollection ldc = new LinkedDocumentCollection();

    ldc.prependDocument(ld1);
    ldc.prependDocument(ld2);
    ldc.appendDocument(ld2);

    LinkedDocument ldt = (LinkedDocument) ldc.getFirstDocument();
    assertTrue("Ergebnis stimmt nicht", ldt.getID().equals(ld2.getID()));
    ldt = (LinkedDocument) ldc.getLastDocument();
    assertTrue("Ergebnis stimmt nicht", ldt.getID().equals(ld1.getID()));
  }

  @Test
  public void gtestVI() {
    fail("Nummer 6. wird nicht getestet (Geht auch nicht)");
  }

  @Test
  public void htestOthers() {
    LinkedDocument atxt = LinkedDocument.createLinkedDocumentFromFile("a.txt");
    LinkedDocument btxt = LinkedDocument.createLinkedDocumentFromFile("b.txt");
    LinkedDocument ctxt = LinkedDocument.createLinkedDocumentFromFile("c.txt");
    LinkedDocument dtxt = LinkedDocument.createLinkedDocumentFromFile("d.txt");
    LinkedDocument etxt = LinkedDocument.createLinkedDocumentFromFile("e.txt");

    LinkedDocumentCollection ldc = new LinkedDocumentCollection();
    ldc.appendDocument(atxt);
    int sizeBefore = ldc.numDocuments();
    ldc = ldc.crawl();
    assertFalse("crawl berechnet was falsch. Groeße Returnvalue:",
        (ldc.numDocuments() != 5) || (ldc.numDocuments() < sizeBefore));
    ldc.calculateIncomingLinks();

    for (int i = 0; i < ldc.numDocuments(); i++) {
      LinkedDocument ld = (LinkedDocument) ldc.get(i);
      switch (ld.getID()) {
        case "a.txt":
          atxt = ld;
          break;
        case "b.txt":
          btxt = ld;
          break;
        case "c.txt":
          ctxt = ld;
          break;
        case "d.txt":
          dtxt = ld;
          break;
        case "e.txt":
          etxt = ld;
          break;
      }
    }

    assertFalse("getOutgoingLinks berechnet falsch. Groeße Returnvalue: " + atxt.getOutgoingLinks().numDocuments(),
        (atxt != null) && (atxt.getOutgoingLinks().numDocuments() != 2));
    assertFalse("getOutgoingLinks berechnet falsch. Groeße Returnvalue: " + btxt.getOutgoingLinks().numDocuments(),
        (btxt != null) && (btxt.getOutgoingLinks().numDocuments() != 0));
    assertFalse("getIncomingLinks berechnet falsch. Groeße Returnvalue: " + ctxt.getIncomingLinks().numDocuments(),
        (ctxt != null) && (ctxt.getIncomingLinks().numDocuments() != 2));
    assertFalse("getIncomingLinks berechnet falsch. Groeße Returnvalue: " + btxt.getIncomingLinks().numDocuments(),
        (btxt != null) && (btxt.getIncomingLinks().numDocuments() != 2));
  }

  @org.junit.Test
  public void itestnoOfDocumentsContainingWords() throws Exception {
    DocumentCollection docCol = new DocumentCollection();
    docCol.prependDocument(doc);
    docCol.prependDocument(doc2);
    docCol.prependDocument(doc3);

    // Mache wca und wca2 vergleichbar
    Method[] methodsDocCol = DocumentCollection.class.getDeclaredMethods();
    for (Method m : methodsDocCol) {
      if (m.getName().toLowerCase().equals("addzerowordstodocuments")) {
        m.setAccessible(true);
        m.invoke(docCol);
      }
    }

    assertEquals("noOfDocumentsContainingWord ist falsch implementiert", 3,
        docCol.noOfDocumentsContainingWord("versuchte"));
  }

  @org.junit.Test
  public void jtestCalculateWeights() throws Exception {
    DocumentCollection docCol = new DocumentCollection();
    docCol.prependDocument(doc);
    docCol.prependDocument(doc2);
    docCol.prependDocument(doc3);

    // Mache wca und wca2 vergleichbar
    Method[] methodsDocCol = DocumentCollection.class.getDeclaredMethods();
    for (Method m : methodsDocCol) {
      if (m.getName().toLowerCase().equals("addzerowordstodocuments")) {
        m.setAccessible(true);
        m.invoke(docCol);
      }
    }

    WordCountsArray wca2 = docCol.get(0).getWordCounts();
    WordCountsArray wca = docCol.get(1).getWordCounts();

    // Teste calculateWeights(DocumentCollection dc)
    Method[] methods = WordCountsArray.class.getDeclaredMethods();
    boolean found = false;
    for (Method m : methods) {
      if (m.getName().toLowerCase().equals("calculateweights")) {
        m.setAccessible(true);
        m.invoke(wca, docCol);
        found = true;
      }
    }
    assertTrue("calculateweights not found", found);

    WordCount[] internalWca = null;
    Field[] fields = WordCountsArray.class.getDeclaredFields();
    for (Field f : fields) {
      if (f.getType().isArray()) {
        f.setAccessible(true);
        internalWca = (WordCount[]) f.get(wca);
      }
    }

    assertNotNull(
        "There seems to be a problem with WordCountsArray, no internal array found!",
        internalWca);
    assertEquals("calculateWeights ist falsch implementiert", 0.5753641449035617,
        internalWca[0].getWeight(), 0.00001);
  }

  @org.junit.Test
  public void ktestCalculateNormalizedWeights() throws Exception {
    Method[] methods = WordCountsArray.class.getDeclaredMethods();
    DocumentCollection docCol = new DocumentCollection();
    docCol.prependDocument(doc);
    docCol.prependDocument(doc2);
    docCol.prependDocument(doc3);

    // Mache wca und wca2 vergleichbar
    Method[] methodsDocCol = DocumentCollection.class.getDeclaredMethods();
    for (Method m : methodsDocCol) {
      if (m.getName().toLowerCase().equals("addzerowordstodocuments")) {
        m.setAccessible(true);
        m.invoke(docCol);
      }
    }

    WordCountsArray wca = docCol.get(1).getWordCounts();

    // Teste calculateNormalizedWeights(DocumentCollection dc)
    boolean found = false;
    for (Method m : methods) {
      if (m.getName().toLowerCase().equals("calculatenormalizedweights")) {
        m.setAccessible(true);
        m.invoke(wca, docCol);
        found = true;
      }
    }
    assertTrue("Not Found", found);
    
    WordCount[] internalWca = null;
    Field[] fields = WordCountsArray.class.getDeclaredFields();
    for (Field f : fields) {
      if (f.getType().isArray()) {
        f.setAccessible(true);
        internalWca = (WordCount[]) f.get(wca);
      }
    }

    assertNotNull(
        "There seems to be a problem with WordCountsArray, no internal array found!",
        internalWca);
    assertEquals("calculateNormalizedWeights ist falsch implementiert", 0.316227766016838,
        internalWca[0].getNormalizedWeight(), 0.00001);
  }

  @org.junit.Test
  public void ltestScalarProduct() throws Exception {
    Method[] methods = WordCountsArray.class.getDeclaredMethods();
    DocumentCollection docCol = new DocumentCollection();
    docCol.prependDocument(doc);
    docCol.prependDocument(doc2);
    docCol.prependDocument(doc3);

    // Mache wca und wca2 vergleichbar
    Method[] methodsDocCol = DocumentCollection.class.getDeclaredMethods();
    for (Method m : methodsDocCol) {
      if (m.getName().toLowerCase().equals("addzerowordstodocuments")) {
        m.setAccessible(true);
        m.invoke(docCol);
      }
    }
    WordCountsArray wca2 = docCol.get(0).getWordCounts();
    WordCountsArray wca = docCol.get(1).getWordCounts();
    // Teste scalarProduct(WordCountsArray wca, DocumentCollection dc)

    // Rechne normalizedWeights fuer wca2
    for (Method m : methods) {
      if (m.getName().toLowerCase().equals("calculatenormalizedweights")) {
        m.setAccessible(true);
        m.invoke(wca2, docCol);
      }
    }

    double scalar = -1;

    for (Method m : methods) {
      if (m.getName().toLowerCase().equals("scalarproduct")
          && m.getParameterTypes().length == 2) {
        m.setAccessible(true);
        scalar = (double) m.invoke(wca, wca2, docCol);
      }
    }

    assertFalse("scalarProduct not found", scalar == -1);
    assertEquals("scalarProduct falsch implementiert", 0.7954236646622413, scalar,
        0.00001);
  }

  @org.junit.Test
  public void mtestMatch() throws Exception {
    LinkedDocument atxt = new LinkedDocument("harry", "", "", null, null,
        "blablabla link:B.txt tralalalal link:C.txt tetsetse ende", "harry.txt");

    LinkedDocumentCollection ldc = new LinkedDocumentCollection();
    ldc.prependDocument(atxt);

    ldc.match("blablabla ");
    double d = ldc.getQuerySimilarity(0);
    assertEquals("match falsch implementiert", 0.20840410544601642, d, 0.00001);
  }
}


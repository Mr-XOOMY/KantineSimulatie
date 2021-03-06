import java.util.*;

public class KantineAanbod {
    // interne opslag voorraad
    private HashMap<String, ArrayList<Artikel>> aanbod;
    private HashMap<String, Integer> startVoorraad;
    private HashMap<String, Double> prijzen;
    private static KantineAanbod kantineAanbod;

    /**
     * Constructor. Het eerste argument is een lijst met artikelnamen, het tweede argument is
     * eenlijst met prijzen en het derde argument is een lijst met hoeveelheden. Let op: de
     * dimensies van de drie arrays moeten wel gelijk zijn!
     */
    public KantineAanbod(String[] artikelnaam, double[] prijs, int[] hoeveelheid) {
        aanbod = new HashMap<String, ArrayList<Artikel>>();
        startVoorraad = new HashMap<String, Integer>();
        prijzen = new HashMap<String, Double>();
        for (int i = 0; i < artikelnaam.length; i++) {
            ArrayList<Artikel> artikelen = new ArrayList<Artikel>();
            int getal = new Random().nextInt(2);
            if(getal == 0){
                for (int j = 0; j < hoeveelheid[i]; j++) {
                    Artikel artikel = new Artikel(artikelnaam[i], (float) prijs[i]);
                    float korting = (float) (prijs[i] / 100) * 20;
                    artikel.setKorting(korting);
                    artikelen.add(artikel);
                }
            }else {
                for (int j = 0; j < hoeveelheid[i]; j++) {
                    Artikel artikel = new Artikel(artikelnaam[i], (float) prijs[i]);
                    artikelen.add(artikel);
                }
            }

            startVoorraad.put(artikelnaam[i], hoeveelheid[i]);
            prijzen.put(artikelnaam[i], prijs[i]);
            aanbod.put(artikelnaam[i], artikelen);
        }
    }

    private void vulVoorraadAan(String productnaam) {
        ArrayList<Artikel> huidigeVoorraad = aanbod.get(productnaam);
        int startHoeveelheid = startVoorraad.get(productnaam);
        int huidigeHoeveelheid = huidigeVoorraad.size();
        double prijs = prijzen.get(productnaam);
        for (int j = huidigeHoeveelheid; j < startHoeveelheid; j++) {
            huidigeVoorraad.add(new Artikel(productnaam, (float) prijs));
        }
        aanbod.put(productnaam, huidigeVoorraad);
    }

    /*
     * Private methode om de lijst van artikelen te krijgen op basis van de naam van het artikel.
     * Retourneert null als artikel niet bestaat.
     */
    private ArrayList<Artikel> getArrayList(String productnaam) {
        return aanbod.get(productnaam);
    }

    /**
     * Private methode om een Artikel van de stapel artikelen af te pakken. Retourneert null als de
     * stapel leeg is.
     */
    private Artikel getArtikel(ArrayList<Artikel> stapel) {
        if (stapel == null) {
            return null;
        }
        if (stapel.size() == 0) {
            return null;
        } else {
            Artikel a = stapel.get(0);
            stapel.remove(0);
            if (stapel.size() <= 10)
                vulVoorraadAan(a.getNaam());
            return a;
        }
    }

    /**
     * Publieke methode om een artikel via naam van de stapel te pakken. Retouneert null als artikel
     * niet bestaat of niet op voorraad is.
     *
     * @param productnaam
     * @return
     */
    public Artikel getArtikel(String productnaam) {
        return getArtikel(getArrayList(productnaam));
    }

    /**
     *
     * @return
     */
    public static KantineAanbod getKantineAanbod() {
        return kantineAanbod;
    }

    /**
     *
     * @param kantineAanbod
     */
    public void setKantineAanbod(KantineAanbod kantineAanbod) {
        this.kantineAanbod = kantineAanbod;
    }
}

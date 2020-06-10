import java.util.Random;

public class Persoon {
    private int bsn;
    private String voornaam;
    private String achternaam;
    private Datum geboortedatum;
    private char geslacht;
    private Betaalwijze betaalwijze;
    private Contant c;
    private Pinpas p;

    /**
     * Constructor
     * @param bsn Burger Service Nummer (BSN)
     * @param voornaam Voornaam
     * @param achternaam Achternaam
     * @param geboortedatum Geboortedatum
     * @param geslacht Geslacht
     */
    public Persoon(int bsn, String voornaam, String achternaam, Datum geboortedatum, String geslacht) {
        this.bsn = bsn;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        setGeslacht(geslacht);
        c = new Contant();
        p = new Pinpas();
        p.setKredietLimiet(-5.0);
    }

    /**
     * Constructor
     */
    public Persoon() {
//        setGeslacht("Onbekend");
//        this.bsn = 0;
//        this.voornaam = "Onbekend";
//        this.achternaam = "Onbekend";
//        this.geboortedatum = "Onbekend";
    }

    /**
     * Getter voor het Burger Service Nummer (BSN)
     * @return Geeft BSN terug
     */
    public int getBsn() {
        return bsn;
    }

    /**
     * Getter voor de voornaam
     * @return Geeft voornaam terug
     */
    public String getVoornaam() {
        return voornaam;
    }

    /**
     * Getter voor de achternaam
     * @return Geeft achternaam terug
     */
    public String getAchternaam() {
        return achternaam;
    }

    /**
     * Getter voor de geboortedatum
     * @return Geeft geboortedatum terug
     */
    public String getGeboortedatum() {
        return geboortedatum.getDatumAsString();
    }

    /**
     * Getter voor het geslacht
     * @return Geeft geslacht terug
     */
    public String getGeslacht() {
        if(geslacht == 'm') {
            return "Man";
        }
        else if(geslacht == 'v') {
            return "Vrouw";
        }
        else {
            return "Onbekend";
        }
    }

    /**
     * Setter voor het Burger Service Nummer (BSN)
     * @param bsn Burger Service Nummer (BSN)
     */
    public void setBSN(int bsn) {
        this.bsn = bsn;
    }

    /**
     * Setter voor de voornaam
     * @param voornaam Voornaam
     */
    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    /**
     * Setter voor de achternaam
     * @param achternaam Achternaam
     */
    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    /**
     * Setter voor de geboortedatum
     * @param geboortedatum Geboortedatum
     */
    public void setGeboortedatum(Datum geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    /**
     * Setter voor het geslacht
     * @param geslacht Geslacht
     */
    public void setGeslacht(String geslacht) {

        geslacht = geslacht.toLowerCase().trim();

        if(geslacht.equals("m") || geslacht.equals("v")) {
            this.geslacht = geslacht.charAt(0);
        }
        else {
            this.geslacht = 'o';
        }
    }

    /**
     * Getter betaalwijze
     * @return Verkrijgt betaalwijze
     */
    public Betaalwijze getBetaalwijze() {
        return betaalwijze;
    }

    /**
     * Setter betaalwijze
     */
    public void setBetaalwijze() {
        int getal = new Random().nextInt(2);
        if(getal == 0){
            betaalwijze = c;
        }
        else {
            betaalwijze = p;
        }

    }

    @Override
    public String toString() {
        return "BSN: " + getBsn() + "\nVoornaam: " + getVoornaam() + "\nAchternaam: " + getAchternaam() + "\nGeboortedatum: " + getGeboortedatum() + "\nGeslacht: " + getGeslacht();
    }

}

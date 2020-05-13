public class Artikel {
    private String naam;
    private float prijs;

    /**
     * Constructor
     * @param naam Artikelnaam
     * @param prijs Artikelprijs
     */
    public Artikel(String naam, float prijs) {
        this.naam = naam;
        this.prijs = prijs;
    }

    /**
     * Parameterloze Constructor
     */
    public Artikel() {

    }

    /**
     * Getter voor naam Artikel
     * @return Artikelnaam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * Getter voor prijs Artikel
     * @return Artikelprijs
     */
    public float getPrijs() {
        return prijs;
    }

    /**
     * Setter voor naam Artikel
     * @param naam Ingegeven Artikelnaam
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Setter voor prijs Artikel
     * @param prijs Ingegeven Artikelprijs
     */
    public void setPrijs(float prijs) {
        this.prijs = prijs;
    }

    @Override
    public String toString() {
        return "Artikelnaam: " + getNaam() + "\nPrijs: " + getPrijs();
    }
}
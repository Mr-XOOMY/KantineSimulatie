public class Kantine {

    private Kassa kassa;
    private KassaRij kassarij;

    /**
     * Constructor
     */
    public Kantine() {
        kassarij = new KassaRij();
        kassa = new Kassa(kassarij);
    }

    /**
     * In deze methode wordt een Persoon en Dienblad gemaakt en aan elkaar gekoppeld. Maak twee
     * Artikelen aan en plaats deze op het dienblad. Tenslotte sluit de Persoon zich aan bij de rij
     * voor de kassa.
     */
    public void loopPakSluitAan() {
        Persoon persoon = new Persoon(123456789, "Pietje", "Puk", new Datum(20, 05, 2020), "man");
        Dienblad dienblad = new Dienblad(persoon);
        Artikel kaas = new Artikel("Kaas", 5.00f);
        Artikel kalasjnikov = new Artikel("Kalasjnikov", 400.00f);
        dienblad.voegToe(kaas);
        dienblad.voegToe(kalasjnikov);
        kassarij.sluitAchteraan(dienblad);
    }

    /**
     * Deze methode handelt de rij voor de kassa af.
     */
    public void verwerkRijVoorKassa() {
        while (kassarij.erIsEenRij()) {
            kassarij.eerstePersoonInRij();
        }
    }

    /**
     * Deze methode geeft het object van het type Kassa terug
     * @return
     */
    public Kassa getKassa() {
        return kassa;
    }
}

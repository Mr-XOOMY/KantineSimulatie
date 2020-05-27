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

    public void loopPakSluitAan(Dienblad dienblad, String[] artikelnamen) {
        for (String artikel : artikelnamen){
            Artikel nieuwArtikel = new Artikel(artikel, 1.00f);
            dienblad.voegToe(nieuwArtikel);
        }
        //kassarij.sluitAchteraan(dienblad);
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
     * Deze methode telt het geld uit de kassa
     *
     * @return hoeveelheid geld in kassa
     */
    public double hoeveelheidGeldInKassa() {
        return kassa.hoeveelheidGeldInKassa();
    }

    /**
     * Deze methode geeft het aantal gepasseerde artikelen.
     *
     * @return het aantal gepasseerde artikelen
     */
    public int aantalArtikelen() {
        return kassa.aantalArtikelen();
    }

    /**
     * Deze methode reset de bijgehouden telling van het aantal artikelen en "leegt" de inhoud van
     * de kassa.
     */
    public void resetKassa() {
        kassa.resetKassa();
    }
}

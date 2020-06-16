import java.util.Iterator;

public class Kassa {
    private KassaRij kassaRij;
    private static int totaalAantalArtikelen = 0;
    private static double totaalPrijs = 0;
    private static double totaalPrijsArtikelen = 0;
    /**
     * Constructor
     */
    public Kassa(KassaRij kassaRij) {
        this.kassaRij = kassaRij;
    }

    /**
     * Vraag het aantal artikelen en de totaalprijs op. Tel deze gegevens op bij de controletotalen
     * die voor de kassa worden bijgehouden. De implementatie wordt later vervangen door een echte
     * betaling door de persoon.
     *
     * @param klant die moet afrekenen
     */
    public void rekenAf(Dienblad klant) {

        Iterator<Artikel> lijst_artikelen = klant.getArtikel();
        Artikel artikel;

        while(lijst_artikelen.hasNext()) {
            artikel = lijst_artikelen.next();
            totaalAantalArtikelen++;
            totaalPrijs += artikel.getPrijs();
            totaalPrijsArtikelen += artikel.getPrijs();
        }

        if(klant.getKlant() instanceof KortingskaartHouder){
            if(((KortingskaartHouder) klant.getKlant()).heeftMaximum()){
                if(((totaalPrijsArtikelen / 100) * ((KortingskaartHouder) klant.getKlant()).geefKortingsPercentage()) > ((KortingskaartHouder) klant.getKlant()).geefMaximum()) {
                    totaalPrijsArtikelen -= ((KortingskaartHouder) klant.getKlant()).geefMaximum();
                }
            }else {
                totaalPrijsArtikelen -= (totaalPrijsArtikelen / 100) * ((KortingskaartHouder) klant.getKlant()).geefKortingsPercentage();
            }
        }

        // Controleert betaling
        klant.getKlant().setBetaalwijze();
        Betaalwijze betaalwijze =  klant.getKlant().getBetaalwijze();


        totaalPrijsArtikelen = 0;
    }

    /**
     * Geeft het aantal artikelen dat de kassa heeft gepasseerd, vanaf het moment dat de methode
     * resetKassa is aangeroepen.
     *
     * @return aantal artikelen
     */
    public int aantalArtikelen() {
        return totaalAantalArtikelen;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kassa zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return totaalPrijs;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        totaalAantalArtikelen = 0;
        totaalPrijs = 0;
    }
}

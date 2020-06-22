import java.time.LocalDate;
import java.util.Iterator;

public class Kassa {
    private KassaRij kassaRij;
    private static int totaalAantalArtikelen = 0;
    private static double totaalPrijsKassa = 0;
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
    public void rekenAf(Dienblad klant, Kassa kassa) {

        Factuur factuur = new Factuur(klant, LocalDate.now(), kassa);

        // Controleert betaling
        klant.getKlant().setBetaalwijze();
        Betaalwijze betaalwijze =  klant.getKlant().getBetaalwijze();


        try {
            betaalwijze.betaal(totaalPrijsArtikelen);
        }catch(TeWeinigGeldException message) {
            String klant_naam = klant.getKlant().getVoornaam();
            System.out.println(klant_naam + message.getMessage());

        }

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
     * verhoog aantal artikelen met 1
     */
    public void setTotaalAantalArtikelen(){
        this.totaalAantalArtikelen += 1;
    }

    /**
     *
     * @param artikelPrijs
     */
    public void setTotaalPrijsKassa(float artikelPrijs){
        totaalPrijsKassa += artikelPrijs;
    }

    /**
     *
     * @param artikelPrijs
     */
    public void setTotaalPrijsArtikelen(float artikelPrijs){
        totaalPrijsArtikelen += artikelPrijs;
    }

    /**
     * Geeft het totaalbedrag van alle artikelen die de kassa zijn gepasseerd, vanaf het moment dat
     * de methode resetKassa is aangeroepen.
     *
     * @return hoeveelheid geld in de kassa
     */
    public double hoeveelheidGeldInKassa() {
        return totaalPrijsKassa;
    }

    /**
     * reset de waarden van het aantal gepasseerde artikelen en de totale hoeveelheid geld in de
     * kassa.
     */
    public void resetKassa() {
        totaalAantalArtikelen = 0;
        totaalPrijsKassa = 0;
    }
}

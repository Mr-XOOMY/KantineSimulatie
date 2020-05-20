import java.util.Iterator;

public class Kassa {
    private KassaRij kassaRij;
    private int totaalAantalArtikelen = 0;
    private double totaalPrijs = 0;

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
        totaalAantalArtikelen = klant.getAantalArtikelen();
        totaalPrijs = klant.getTotaalPrijs();
        System.out.println(totaalAantalArtikelen + aantalArtikelen());
        System.out.println(totaalPrijs + hoeveelheidGeldInKassa());
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

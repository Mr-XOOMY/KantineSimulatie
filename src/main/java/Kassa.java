import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class Kassa {

    private EntityManager manager;
    private KassaRij kassaRij;
    private static int totaalAantalArtikelen = 0;
    private static double totaalPrijsKassa = 0;
    private static double totaalPrijsArtikelen = 0;
    private static double totaalKorting = 0;
    /**
     * Constructor
     */
    public Kassa(KassaRij kassaRij, EntityManager manager) {
        this.manager = manager;
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

        Factuur factuur = new Factuur(klant, LocalDate.now());
        setTotaalAantalArtikelen(factuur);
        setTotaalPrijsArtikelen(factuur);
        setTotaalKorting(factuur);
        setTotaalPrijsKassa(factuur);

        // Controleert betaling
        klant.getKlant().setBetaalwijze();
        Betaalwijze betaalwijze =  klant.getKlant().getBetaalwijze();
        String klant_naam = klant.getKlant().getVoornaam();

        EntityTransaction transaction = null;
        try {
            transaction = manager.getTransaction();
            transaction.begin();
            betaalwijze.betaal((totaalPrijsArtikelen - totaalKorting));
            manager.persist(factuur);
            transaction.commit();
            System.out.println(klant_naam + factuur.toString());
        }catch(TeWeinigGeldException message) {
            if(transaction != null){
                transaction.rollback();
            }
            System.out.println(klant_naam + " kan niet €" +(totaalPrijsArtikelen - totaalKorting)+ " betalen. " + klant_naam + message.getMessage());
        }
        totaalPrijsArtikelen = 0;
        totaalKorting = 0;
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
    public void setTotaalAantalArtikelen(Factuur factuur){
        totaalAantalArtikelen += factuur.getTotaalAantalArtikelen();
    }

    /**
     *
     * @param factuur
     */
    public void setTotaalPrijsArtikelen(Factuur factuur){
        totaalPrijsArtikelen = factuur.getTotaal();
    }

    /**
     *
     * @param factuur
     */
    public void setTotaalKorting(Factuur factuur){
        totaalKorting = factuur.getKorting();
    }

    /**
     *
     * @param factuur
     */
    public void setTotaalPrijsKassa(Factuur factuur){
        totaalPrijsKassa += factuur.getTotaal();
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

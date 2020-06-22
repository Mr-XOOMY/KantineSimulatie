import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;

public class Factuur implements Serializable {

    private Long id;
    private LocalDate datum;
    private double korting;
    private double totaal;

    public Factuur() {
        totaal=0;
        korting=0;
    }

    public Factuur(Dienblad klant, LocalDate datum, Kassa kassa){
        this();
        this.datum = datum;
        verwerkBestelling(klant, kassa);
    }

    /**
     * Verwerk artikelen en pas kortingen toe.
     *
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen.
     *
     * @param klant
     */
    private void verwerkBestelling(Dienblad klant, Kassa kassa){
        Iterator<Artikel> lijst_artikelen = klant.getArtikel();
        Artikel artikel;

        while(lijst_artikelen.hasNext()) {
            artikel = lijst_artikelen.next();
            kassa.setTotaalAantalArtikelen();
            float artikelNieuwePrijs;
            if(artikel.getKorting() > 0){

                artikelNieuwePrijs = artikel.getPrijs() - artikel.getKorting();
                artikel.setPrijs(artikelNieuwePrijs);

            }else{
                if(klant.getKlant() instanceof KortingskaartHouder){
                    if(((KortingskaartHouder) klant.getKlant()).heeftMaximum()){
                        if(((artikel.getPrijs() / 100) * ((KortingskaartHouder) klant.getKlant()).geefKortingsPercentage()) > ((KortingskaartHouder) klant.getKlant()).geefMaximum()) {
                            artikelNieuwePrijs = (float) (artikel.getPrijs() - ((KortingskaartHouder) klant.getKlant()).geefMaximum());
                            artikel.setPrijs(artikelNieuwePrijs);
                        }
                    }else {
                        artikelNieuwePrijs = (float) (artikel.getPrijs() - (artikel.getPrijs() / 100) * ((KortingskaartHouder) klant.getKlant()).geefKortingsPercentage());
                        artikel.setPrijs(artikelNieuwePrijs);
                    }
                }
            }

            kassa.setTotaalPrijsKassa(artikel.getPrijs());
            kassa.setTotaalPrijsArtikelen(artikel.getPrijs());
        }
    }

    /**
     *
     * @return het totaalbedrag
     */
    public double getTotaal(){
        return totaal;
    }

    /**
     *
     * @return de toegepaste korting
     */
    public double getKorting(){
        return korting;
    }

    /**
     *
     * @return een printpaar bonnetje
     */
    public String toString(){
        // nog make
        return "null";
    }
}

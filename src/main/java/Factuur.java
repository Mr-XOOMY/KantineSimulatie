import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;

@Embeddable
public class Factuur implements Serializable {

    @Column(name = "id")
    private Long id;
    @Column(name = "datum")
    private LocalDate datum;
    @Column(name = "korting")
    private double korting;
    @Column(name = "totaal")
    private double totaal;
    @Column(name = "totaalAantalArtikelen")
    private int totaalAantalArtikelen = 0;

    public Factuur() {
        totaal=0;
        korting=0;
    }

    public Factuur(Dienblad klant, LocalDate datum){
        this();
        this.datum = datum;
        verwerkBestelling(klant);
    }

    /**
     * Verwerk artikelen en pas kortingen toe.
     *
     * Zet het totaal te betalen bedrag en het
     * totaal aan ontvangen kortingen.
     *
     * @param klant
     */
    private void verwerkBestelling(Dienblad klant){
        Iterator<Artikel> lijst_artikelen = klant.getArtikel();
        Artikel artikel;

        while(lijst_artikelen.hasNext()) {
            artikel = lijst_artikelen.next();
            totaalAantalArtikelen++;
            if(artikel.getKorting() > 0){
                korting += artikel.getPrijs() - artikel.getKorting();
            }else{
                if(klant.getKlant() instanceof KortingskaartHouder){
                    if(((KortingskaartHouder) klant.getKlant()).heeftMaximum()){
                        if(((artikel.getPrijs() / 100) * ((KortingskaartHouder) klant.getKlant()).geefKortingsPercentage()) > ((KortingskaartHouder) klant.getKlant()).geefMaximum()) {
                            korting += (float) (artikel.getPrijs() - ((KortingskaartHouder) klant.getKlant()).geefMaximum());
                        }
                    }else {
                        korting += (float) (artikel.getPrijs() - (artikel.getPrijs() / 100) * ((KortingskaartHouder) klant.getKlant()).geefKortingsPercentage());
                    }
                }
            }
            totaal += artikel.getPrijs();
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

    public int getTotaalAantalArtikelen(){
        return totaalAantalArtikelen;
    }

    /**
     *
     * @return een printpaar bonnetje
     */
    @Override
    public String toString(){
        return " moet €" + (totaal - korting) + " betalen.";
    }
}

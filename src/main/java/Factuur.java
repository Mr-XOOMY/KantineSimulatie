import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "factuur")

@Embeddable
public class Factuur implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "datum")
    private LocalDate datum;
    @Column(name = "korting")
    private double korting;
    @Column(name = "totaal")
    private double totaal;
    @Column(name = "totaalAantalArtikelen")
    private int totaalAantalArtikelen = 0;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "factuur_factuurregel", joinColumns = @JoinColumn(name = "factuur_id"), inverseJoinColumns = @JoinColumn(name = "factuurregel_id"))
    private List<FactuurRegel> regels = new ArrayList<>();

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
        double tempKorting = 0;

        while(lijst_artikelen.hasNext()) {
            artikel = lijst_artikelen.next();
            FactuurRegel factuurRegel = new FactuurRegel(this, artikel);
            regels.add(factuurRegel);
            totaalAantalArtikelen++;
            if(artikel.getKorting() > 0){
                korting += artikel.getKorting();
                tempKorting = artikel.getKorting();
            }else{
                if(klant.getKlant() instanceof KortingskaartHouder){
                    if(((KortingskaartHouder) klant.getKlant()).heeftMaximum()){
                        if(((artikel.getPrijs() / 100) * ((KortingskaartHouder) klant.getKlant()).geefKortingsPercentage()) > ((KortingskaartHouder) klant.getKlant()).geefMaximum()) {
                            korting += ((KortingskaartHouder) klant.getKlant()).geefMaximum();
                            tempKorting = ((KortingskaartHouder) klant.getKlant()).geefMaximum();
                        }
                    }else {
                        korting += (artikel.getPrijs() / 100) * ((KortingskaartHouder) klant.getKlant()).geefKortingsPercentage();
                        tempKorting = (artikel.getPrijs() / 100) * ((KortingskaartHouder) klant.getKlant()).geefKortingsPercentage();
                    }
                }
            }
            totaal += artikel.getPrijs() - tempKorting;
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
        for (FactuurRegel factuurRegel : regels){
            System.out.println(factuurRegel.toString());
        }
        return " moet €" + (totaal - korting) + " betalen.";
    }
}

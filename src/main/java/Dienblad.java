import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Dienblad {
    private Stack<Artikel> artikelen;
    private Persoon klant;

    /**
     * Constructor
     */
    public Dienblad() {
        artikelen = new ArrayList<>();
    }

    /**
     *
     * @param klant
     */
    public Dienblad(Persoon klant){
        this();
        this.klant = klant;
    }

    /**
     * Methode om artikel aan dienblad toe te voegen
     *
     * @param artikel
     */
    public void voegToe(Artikel artikel) {
        artikelen.add(artikel);
        System.out.println(artikelen.toString());
    }

    /**
     * Methode om aantal artikelen op dienblad te tellen
     *
     * @return Het aantal artikelen
     */
    public int getAantalArtikelen() {
        return artikelen.size();
    }

    /**
     * Verwijderd alle artikelen in de ArrayList artikelen
     */
    public void setAantalArtikelen() {
        artikelen.clear();
    }

    /**
     * Methode om de totaalprijs van de artikelen op dienblad uit te rekenen
     *
     * @return De totaalprijs
     */
    public double getTotaalPrijs() {
        int totaal = 0;
        for(Artikel artikel : artikelen){
            totaal += artikel.getPrijs();
        }
        return totaal;
    }
}


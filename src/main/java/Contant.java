public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public boolean betaal(double tebetalen) {
        if(saldo < tebetalen) {
            return false;
        }
        saldo -= tebetalen;
        return true;
    }
}

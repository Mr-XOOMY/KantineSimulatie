public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     *
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public boolean betaal(double tebetalen) {
        if(kredietlimiet < tebetalen) {
            return false;
        }else {
            kredietlimiet -= tebetalen;
            if ((saldo - tebetalen) < 0){
                saldo = 0;
            }else {
                saldo -= tebetalen;
            }
            return true;
        }
    }
}

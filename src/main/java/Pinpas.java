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
    public void betaal(double tebetalen) throws TeWeinigGeldException {

            if ((saldo - tebetalen) > kredietlimiet) {
                saldo -= tebetalen;
            } else {
                throw new TeWeinigGeldException("Te-Weinig-Geld");
            }
        }
    }
public class Docent extends Persoon implements KortingskaartHouder {

    private String afkorting;
    private String afdeling;

    /**
     *
     * @param bsn Burger
     * @param bsn Burger Service Nummer (BSN)
     * @param voornaam Voornaam
     * @param achternaam Achternaam
     * @param geboortedatum Geboortedatum
     * @param geslacht Geslacht
     * @param afkorting 4 letterige afkorting
     * @param afdeling Afdelingsnaam
     */
    public Docent(int bsn, String voornaam, String achternaam, Datum geboortedatum, String geslacht, String afkorting, String afdeling){
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.afkorting = afkorting;
        this.afdeling = afdeling;
    }

    /**
     *
     * @return Geeft de waarde van afkorting terug
     */
    public String getAfkorting() {
        return afkorting;
    }

    /**
     *
     * @return Geeft de waarde van afdeling terug
     */
    public String getAfdeling() {
        return afdeling;
    }

    /**
     *
     * @param afkorting Stelt de waarde van afkorting in
     */
    public void setAfkorting(String afkorting) {
        this.afkorting = afkorting;
    }

    /**
     *
     * @param afdeling Stelt de waarde van afdeling in
     */
    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    @Override
    public String toString() {
        return "BSN: " + getBsn() + "\nVoornaam: " + getVoornaam() + "\nAchternaam: " + getAchternaam() + "\nGeboortedatum: " + getGeboortedatum() + "\nGeslacht: " + getGeslacht() + "\nAfkorting: " + getAfkorting() + "\nAfdeling: " + getAfdeling();
    }

    /**
     *
     * @return Kortings percentage
     */
    @Override
    public double geefKortingsPercentage() {
        return 25;
    }

    /**
     *
     * @return Maximum ja/nee
     */
    @Override
    public boolean heeftMaximum() {
        return true;
    }

    /**
     *
     * @return Maximum kortings waarde
     */
    @Override
    public double geefMaximum() {
        return 1.00;
    }
}

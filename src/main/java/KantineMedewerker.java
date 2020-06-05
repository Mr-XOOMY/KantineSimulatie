public class KantineMedewerker extends Persoon{

    private int medewerkersNummer;
    private boolean magAchterDeKassaStaan;

    /**
     *
     * @param bsn Burger
     * @param bsn Burger Service Nummer (BSN)
     * @param voornaam Voornaam
     * @param achternaam Achternaam
     * @param geboortedatum Geboortedatum
     * @param geslacht Geslacht
     * @param medewerkersNummer Medewerkersnummer
     * @param magAchterDeKassaStaan Mag achter de kassa staan ja/nee
     */
    public KantineMedewerker(int bsn, String voornaam, String achternaam, Datum geboortedatum, String geslacht, int medewerkersNummer, boolean magAchterDeKassaStaan){
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.medewerkersNummer = medewerkersNummer;
        this.magAchterDeKassaStaan = magAchterDeKassaStaan;
    }

    /**
     *
     * @return Geeft de waarde van medewerkersNummer terug
     */
    public int getMedewerkersNummer() {
        return medewerkersNummer;
    }

    /**
     *
     * @return Geeft de waarde van magAchterDeKassaStaan terug
     */
    public String isMagAchterDeKassaStaan() {
        if (magAchterDeKassaStaan){
            return "ja";
        }
        return "nee";
    }

    /**
     *
     * @param medewerkersNummer Stelt de waarde van medewerkersNummer in
     */
    public void setMedewerkersNummer(int medewerkersNummer) {
        this.medewerkersNummer = medewerkersNummer;
    }

    /**
     *
     * @param magAchterDeKassaStaan Stelt de waarde van magAchterDeKassaStaan in
     */
    public void setMagAchterDeKassaStaan(boolean magAchterDeKassaStaan) {
        this.magAchterDeKassaStaan = magAchterDeKassaStaan;
    }

    @Override
    public String toString() {
        return "BSN: " + getBsn() + "\nVoornaam: " + getVoornaam() + "\nAchternaam: " + getAchternaam() + "\nGeboortedatum: " + getGeboortedatum() + "\nGeslacht: " + getGeslacht() + "\nMedewerkersnummer: " + getMedewerkersNummer() + "\nMag achter de kassa staan: " + isMagAchterDeKassaStaan();
    }
}

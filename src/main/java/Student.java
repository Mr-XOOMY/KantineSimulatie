public class Student extends Persoon {
    private int studentnummer;
    private String studierichting;

    /**
     * Constructor
     * @param studentnummer
     * @param studierichting
     */
    public Student(int bsn, String voornaam, String achternaam, Datum geboortedatum, String geslacht, int studentnummer, String studierichting) {
        super(bsn, voornaam, achternaam, geboortedatum, geslacht);
        this.studentnummer = studentnummer;
        this.studierichting = studierichting;
    }

    /**
     * Getter voor studentnummer als type int
     * @return Studentnummer
     */
    public int getStudentnummer() {
        return studentnummer;
    }

    /**
     * Getter voor studierichting als type String
     * @return Studierichting
     */
    public String getStudierichting() {
        return studierichting;
    }
}

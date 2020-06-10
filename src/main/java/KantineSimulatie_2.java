import java.util.*;

public class KantineSimulatie_2 {

    // kantine
    private Kantine kantine;

    // kantineaanbod
    private KantineAanbod kantineaanbod;

    // random generator
    private Random random;

    // aantal artikelen
    private static final int AANTAL_ARTIKELEN = 4;

    // artikelen
    private static final String[] artikelnamen =
            new String[] {"Koffie", "Broodje pindakaas", "Broodje kaas", "Appelsap"};

    // prijzen
    private static double[] artikelprijzen = new double[] {1.50, 2.10, 1.65, 1.65};

    // minimum en maximum aantal artikelen per soort
    private static final int MIN_ARTIKELEN_PER_SOORT = 10000;
    private static final int MAX_ARTIKELEN_PER_SOORT = 20000;

    // minimum en maximum aantal personen per dag
    private static final int MIN_PERSONEN_PER_DAG = 50;
    private static final int MAX_PERSONEN_PER_DAG = 100;

    // minimum en maximum artikelen per persoon
    private static final int MIN_ARTIKELEN_PER_PERSOON = 1;
    private static final int MAX_ARTIKELEN_PER_PERSOON = 4;

    /**
     * Constructor
     *
     */
    public KantineSimulatie_2() {
        kantine = new Kantine();
        random = new Random();
        int[] hoeveelheden =
                getRandomArray(AANTAL_ARTIKELEN, MIN_ARTIKELEN_PER_SOORT, MAX_ARTIKELEN_PER_SOORT);
        kantineaanbod = new KantineAanbod(artikelnamen, artikelprijzen, hoeveelheden);

        kantineaanbod.setKantineAanbod(kantineaanbod);
    }

    /**
     * Methode om een array van random getallen liggend tussen min en max van de gegeven lengte te
     * genereren
     *
     * @param lengte
     * @param min
     * @param max
     * @return De array met random getallen
     */
    private int[] getRandomArray(int lengte, int min, int max) {
        int[] temp = new int[lengte];
        for (int i = 0; i < lengte; i++) {
            temp[i] = getRandomValue(min, max);
        }

        return temp;
    }

    /**
     * Methode om een random getal tussen min(incl) en max(incl) te genereren.
     *
     * @param min
     * @param max
     * @return Een random getal
     */
    private int getRandomValue(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Methode om op basis van een array van indexen voor de array artikelnamen de bijhorende array
     * van artikelnamen te maken
     *
     * @param indexen
     * @return De array met artikelnamen
     */
    private String[] geefArtikelNamen(int[] indexen) {
        String[] artikelen = new String[indexen.length];

        for (int i = 0; i < indexen.length; i++) {
            artikelen[i] = artikelnamen[indexen[i]];

        }

        return artikelen;
    }

    /**
     * Deze methode simuleert een aantal dagen
     * in het verloop van de kantine
     *
     * @param dagen
     */
    public void simuleer(int dagen) {
        int[] aantal = new int[dagen];
        double[] omzet = new double[dagen];
        // for lus voor dagen
        for(int i = 0; i < dagen; i++) {

            // bedenk hoeveel personen vandaag binnen lopen
            int aantalpersonen = 100 ;

            // laat de personen maar komen...
//            int aantalStudent = 0;
//            int aantalDocent = 0;
//            int aantalKantineMedewerker = 0;
            Dienblad dienblad = null;
            Persoon persoon;
            int totaalArtikelen = 0;
            for (int j = 0; j < aantalpersonen; j++) {

                // genereert getal tussen range 0 - 99 en + 1 zorgt voor range 1 - 100
                int getal =  random.nextInt(101 - 1) + 1;

                // maak persoon en dienblad aan, koppel ze
                // en bedenk hoeveel artikelen worden gepakt
                int aantalartikelen = getRandomValue(1, 5);
                

                if (getal >= 1 && getal <= 89){
                    persoon = new Student(123456789, "Pietje", "Puk", new Datum(29, 02, 2000), "man", 123456789, "IT");
                }else if (getal >= 90 && getal <= 99){
                    persoon = new Docent(123456789, "Klaasje", "Puk", new Datum(29, 02, 2020), "man", "doce", "IT");
                }else if (getal == 100){
                    persoon = new KantineMedewerker(123456789, "Jantje", "Puk", new Datum(20, 05, 2020), "man", 987654321, true);
                }else {
                    persoon = new Persoon(123456789, "Individu", "Puk", new Datum(20, 05, 2020), "man");
                }


                dienblad = new Dienblad(persoon);
                dienblad.getKlant().setBetaalwijze();
                dienblad.getKlant().getBetaalwijze().setSaldo(5);
                System.out.println(persoon.toString());

                // genereer de "artikelnummers", dit zijn indexen
                // van de artikelnamen
                int[] tepakken = getRandomArray(aantalartikelen, 0, AANTAL_ARTIKELEN-1);

                // vind de artikelnamen op basis van
                // de indexen hierboven
                String[] artikelen = geefArtikelNamen(tepakken);

                // loop de kantine binnen, pak de gewenste artikelen, sluit aan
                kantine.loopPakSluitAan(dienblad, artikelen);

            }

            // verwerk rij voor de kassa
            kantine.verwerkRijVoorKassa();


            // druk de dagtotalen af en hoeveel personen binnen zijn gekomen
            System.out.println("#############################################");
            System.out.println("");
            System.out.println("Aantal artikelen -> " + kantine.getKassa().aantalArtikelen());
            System.out.println("Geld in kassa -> "+ kantine.getKassa().hoeveelheidGeldInKassa());
            System.out.println("Aantal klanten -> " + aantalpersonen);
            System.out.println();
            aantal[i] = kantine.getKassa().aantalArtikelen();
            omzet[i] = kantine.getKassa().hoeveelheidGeldInKassa();
            // reset de kassa voor de volgende dag
            kantine.getKassa().resetKassa();



        }

        System.out.println("#############################################");
        System.out.println();
        System.out.println("Gemiddelde aantal artikelen per dag: " + Administratie.berekenGemiddeldAantal(aantal));
        System.out.println("Gemiddelde omzet totaal: " + (Administratie.berekenGemiddeldeOmzet(omzet)));
        System.out.println("Dagomzet: ");
        for(int i = 0; i < Administratie.berekenDagOmzet(omzet).length; i++){
            switch(i){
                case 0:
                    System.out.println("Maandag: " + Administratie.berekenDagOmzet(omzet)[i]);
                    break;
                case 1:
                    System.out.println("Dinsdag: " + Administratie.berekenDagOmzet(omzet)[i]);
                    break;
                case 2:
                    System.out.println("Woensdag: " + Administratie.berekenDagOmzet(omzet)[i]);
                    break;
                case 3:
                    System.out.println("Donderdag: " + Administratie.berekenDagOmzet(omzet)[i]);
                    break;
                case 4:
                    System.out.println("Vrijdag: " + Administratie.berekenDagOmzet(omzet)[i]);
                    break;
                case 5:
                    System.out.println("Zaterdag: " + Administratie.berekenDagOmzet(omzet)[i]);
                    break;
                case 6:
                    System.out.println("Zondag: " + Administratie.berekenDagOmzet(omzet)[i]);
                    break;
            }
        }



    }

    /**
    * Start een simulatie
    */
    public static void main(String[] args) {
        int dagen;

        if (args.length == 0) {
            dagen = 4;
        } else {
            dagen = Integer.parseInt(args[0]);
        }

        KantineSimulatie_2 kantineSimulatie = new KantineSimulatie_2();
        kantineSimulatie.simuleer(dagen);
    }
}

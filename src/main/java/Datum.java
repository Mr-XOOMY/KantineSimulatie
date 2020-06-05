public class Datum {

	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Constructor
	 */
	public Datum(){
		dag=0;
		maand=0;
		jaar=0;
	}

	/**
	 *
	 * @param dag Dag als een nummer
	 * @param maand Maand als een nummer
	 * @param jaar Jaar als een nummer
	 */
	public Datum(int dag, int maand, int jaar){
		this();
		if(bestaatDatum(dag, maand, jaar)){
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		}
	}

	/**
	 *
	 * @param dag Dag als een nummer
	 */
	public void setDag(int dag) {
		this.dag = dag;
	}

	/**
	 *
	 * @param maand Maand als een nummer
	 */
	public void setMaand(int maand) {
		this.maand = maand;
	}

	/**
	 *
	 * @param jaar Jaar als een nummer
	 */
	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public int getDag() {
		return dag;
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public int getMaand() {
		return maand;
	}

	/**
	 * Getter voor String weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public int getJaar() {
		return jaar;
	}

	/**
	 * Getter voor het testen of de datum bestaat
	 *
	 * @param dag Dag als een nummer
	 * @param maand Maand als een nummer
	 * @param jaar Jaar als een nummer
	 * @return Boolean waarde onwaar
	 */
	public boolean bestaatDatum(int dag, int maand, int jaar) {
		boolean dagBestaat = (dag >= 1 && dag <= 31);
		boolean maandBestaat = (maand >= 1 && maand <= 12);
		boolean jaarBestaat = (jaar >= 1900 && jaar <= 2100);
		boolean combiBestaat = true;

		switch (maand){
			case 2:
				combiBestaat = (((jaar % 4) == 0) && ((jaar % 400) == 0) || ((jaar % 100) != 0)) ? (dag <= 29) : (dag <= 28);
				break;
			case 4:
				combiBestaat = (dag <= 30);
				break;
			case 6:
				combiBestaat = (dag <= 30);
				break;
			case 9:
				combiBestaat = (dag <= 30);
				break;
			case 11:
				combiBestaat = (dag <= 30);
				break;
		}

		return ((dagBestaat) && (maandBestaat) && (jaarBestaat) && (combiBestaat));
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
		return dag + "/" + maand + "/" + jaar;
	}
}

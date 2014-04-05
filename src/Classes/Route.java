package Classes;

public class Route {
	public int startPointIndex;
	public int endPointIndex;
	public int temps;
	public int longueur;
	
	public Route(int start, int end, int temps, int longueur)
	{
		startPointIndex = start;
		endPointIndex = end;
		this.temps = temps;
		this.longueur = longueur;
	}

	/**
	 * @return the startPointIndex
	 */
	public int getStartPointIndex() {
		return startPointIndex;
	}

	/**
	 * @param startPointIndex the startPointIndex to set
	 */
	public void setStartPointIndex(int startPointIndex) {
		this.startPointIndex = startPointIndex;
	}

	/**
	 * @return the endPointIndex
	 */
	public int getEndPointIndex() {
		return endPointIndex;
	}

	/**
	 * @param endPointIndex the endPointIndex to set
	 */
	public void setEndPointIndex(int endPointIndex) {
		this.endPointIndex = endPointIndex;
	}

	/**
	 * @return the temps
	 */
	public int getTemps() {
		return temps;
	}

	/**
	 * @param temps the temps to set
	 */
	public void setTemps(int temps) {
		this.temps = temps;
	}

	/**
	 * @return the longueur
	 */
	public int getLongueur() {
		return longueur;
	}

	/**
	 * @param longueur the longueur to set
	 */
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
}

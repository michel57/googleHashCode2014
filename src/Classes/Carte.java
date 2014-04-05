package Classes;
import java.util.ArrayList;
import java.util.List;

import Classes.Point;
public class Carte
{
	public int nbIntersections;
	public int nbRues;
	public int nbSecondes;
	public int nbVehicules;
	public int intersectInit;
	public List<Point> l = new ArrayList<Point>();
	public MatriceAdjacence matrice;
	
	public Carte(int nbIntersections, int nbRues, int nbSecondes, int nbVehicules, int intersectInit, List<Point> l )
	{
		super();
		this.nbIntersections = nbIntersections;
		this.nbRues = nbRues;
		this.nbSecondes = nbSecondes;
		this.nbVehicules = nbVehicules;
		this.intersectInit = intersectInit;
		this.l = l;
		matrice = new MatriceAdjacence(nbIntersections);
	}

	/**
	 * @return the nbIntersections
	 */
	public int getNbIntersections()
	{
		return nbIntersections;
	}

	/**
	 * @param nbIntersections the nbIntersections to set
	 */
	public void setNbIntersections(int nbIntersections)
	{
		this.nbIntersections = nbIntersections;
	}

	/**
	 * @return the nbRues
	 */
	public int getNbRues()
	{
		return nbRues;
	}

	/**
	 * @param nbRues the nbRues to set
	 */
	public void setNbRues(int nbRues)
	{
		this.nbRues = nbRues;
	}

	/**
	 * @return the nbSecondes
	 */
	public int getNbSecondes()
	{
		return nbSecondes;
	}

	/**
	 * @param nbSecondes the nbSecondes to set
	 */
	public void setNbSecondes(int nbSecondes)
	{
		this.nbSecondes = nbSecondes;
	}

	/**
	 * @return the nbVehicules
	 */
	public int getNbVehicules()
	{
		return nbVehicules;
	}

	/**
	 * @param nbVehicules the nbVehicules to set
	 */
	public void setNbVehicules(int nbVehicules)
	{
		this.nbVehicules = nbVehicules;
	}

	/**
	 * @return the intersectInit
	 */
	public int getIntersectInit()
	{
		return intersectInit;
	}

	/**
	 * @param intersectInit the intersectInit to set
	 */
	public void setIntersectInit(int intersectInit)
	{
		this.intersectInit = intersectInit;
	}

	/**
	 * @return the l
	 */
	public List<Point> getL()
	{
		return l;
	}

	/**
	 * @param l the l to set
	 */
	public void setL(List<Point> l)
	{
		this.l = l;
	}
	

}

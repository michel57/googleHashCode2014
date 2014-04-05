package Classes;

import java.util.ArrayList;
import java.util.List;

public class Vehicule
{
	public int vehiculeId;
	public List<Integer> path;
	public int tempsTotal = 0;
	
	public Vehicule() {
		super();
		path = new ArrayList<Integer>();

	}
	public Vehicule(List<Integer> path) {
		super();
		this.path = path;
	}

	public void addToPath(int i) {
		path.add(i);
	}
	/**
	 * @return the vehiculeId
	 */
	public int getVehiculeId()
	{
		return vehiculeId;
	}
	/**
	 * @param vehiculeId the vehiculeId to set
	 */
	public void setVehiculeId(int vehiculeId)
	{
		this.vehiculeId = vehiculeId;
	}
	/**
	 * @return the path
	 */
	public List<Integer> getPath()
	{
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(List<Integer> path)
	{
		this.path = path;
	}
	
	
}

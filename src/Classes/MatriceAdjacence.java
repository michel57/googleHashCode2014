package Classes;

public class MatriceAdjacence {
	public static final int DOUBLESENS = 2;
	
	private Route[][] matrice;
	
	public MatriceAdjacence(int N)
	{
		matrice = new Route[N][N];
	}
	
	public void AjouterRoute(int start, int end, int temps, int longueur, int sens)
	{
		matrice[start][end] = new Route(start, end, temps, longueur);
		if(sens == DOUBLESENS)
			matrice[end][sens] = new Route(end, start, temps, longueur);
	}
	
	public Route getRoute(int start, int end)
	{
		return matrice[start][end];
	}
}

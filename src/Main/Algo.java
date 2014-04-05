package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Classes.Carte;
import Classes.Point;
import Classes.Route;
import Classes.Vehicule;

public class Algo {	
	public static List<Vehicule> solve(Carte carte)
	{
		List<Vehicule> res = initVehicules(carte.nbVehicules, carte.intersectInit);
		
		byte[][] exploration = new byte[carte.nbIntersections][carte.nbIntersections];
		
		int tempsFinal = carte.nbSecondes;
		int intersectionCourante;
		int max;
		Route route;
		Route prochaineRoute;
		Vehicule courant;
		
		for(int i = 0; i < carte.nbVehicules; i++)
		{
			courant = res.get(i);
			intersectionCourante = carte.intersectInit;
			prochaineRoute = null;
			while(courant.tempsTotal < tempsFinal)
			{
				max = 0;
				prochaineRoute = null;
				for(int end = 0; end < carte.nbIntersections; end ++)
				{
					if(exploration[intersectionCourante][end] == 0)
					{
						route = carte.matrice.getRoute(intersectionCourante,end);
						if(route != null 
								&& route.longueur > max
								&& route.temps + courant.tempsTotal < tempsFinal)
						{
							prochaineRoute = route;
							max = route.longueur;
						}
					}
				}
				
				if(prochaineRoute == null)
				{
					max = Integer.MAX_VALUE;
					for(int end = 0; end < carte.nbIntersections; end ++)
					{
						route = carte.matrice.getRoute(intersectionCourante,end);
						if(route != null 
								&& route.longueur < max
								&& route.temps + courant.tempsTotal < tempsFinal)
						{
							prochaineRoute = route;
							max = route.longueur;
						}
					}
				}
				if(prochaineRoute == null)
				{
					break;
				}
					courant.tempsTotal += prochaineRoute.temps;
					courant.addToPath(prochaineRoute.endPointIndex);
					exploration[intersectionCourante][prochaineRoute.endPointIndex] = 1;
					intersectionCourante = prochaineRoute.endPointIndex;
				//}
			}
		}
		
		return res;
	}
	
	public static List<Vehicule> initVehicules(int nb, int start)
	{
		List<Vehicule> res = new ArrayList<Vehicule>(nb);
		Vehicule v;
		for(int i = 0; i < nb; i++)
		{
			v = new Vehicule();
			v.addToPath(start);
			res.add(v);
		}
		return res;
	}
	
	public static int longueur;
	
	public static List<Vehicule> solve3(Carte carte)
	{
		List<Vehicule> res = initVehicules(carte.nbVehicules, carte.intersectInit);
		
		boolean[][] exploration = new boolean[carte.nbIntersections][carte.nbIntersections];
		
		for(int i = 0; i < carte.nbIntersections; i++)
			for(int j = 0; j < carte.nbIntersections; j++)
				exploration[i][j] = false;
		
		Vehicule v;
		List<Route> listeRoute;
		Random r = new Random();
		int current_node;
		Route route;
		int l = 0;
		
		for(int i = 0; i < carte.nbVehicules; i++)
		{
			v = res.get(i);
			current_node = carte.intersectInit;
			while(v.tempsTotal < carte.nbSecondes)
			{
				listeRoute = getRoutes(carte, current_node);
				if(listeRoute.size() == 0)
					break;
				int e = 0;
				for(; e < 10; e++)
				{
					int rand = r.nextInt(listeRoute.size());
					//System.out.println(rand);
					route = listeRoute.get(rand);
					if(route.temps + v.tempsTotal <= carte.nbSecondes)
					{
						if(!exploration[current_node][route.endPointIndex] && !exploration[route.endPointIndex][current_node])
							l += route.longueur;
						else
							continue;
						v.addToPath(route.endPointIndex);
						v.tempsTotal += route.temps;
						exploration[current_node][route.endPointIndex] = true;
						exploration[route.endPointIndex][current_node] = true;
						current_node = route.endPointIndex;
						break;
					}
				}
				if(e == 10)
					break;
			}
			System.out.println("temps de la voiture " + i + " : " + v.tempsTotal);
		}
		
		System.out.println("longueur : " + l);
		Algo.longueur = l;
		return res;
	}
	
	public static List<Vehicule> solve4(Carte carte)
	{
		List<Vehicule> res = initVehicules(carte.nbVehicules, carte.intersectInit);
		
		boolean[][] exploration = new boolean[carte.nbIntersections][carte.nbIntersections];
		
		for(int i = 0; i < carte.nbIntersections; i++)
			for(int j = 0; j < carte.nbIntersections; j++)
				exploration[i][j] = false;
		
		Vehicule v;
		List<Route> listeRoute;
		Random r = new Random();
		int current_node;
		Route route;
		int l = 0;
		int iter = 0;
		
		int iterThreshold;
		
		for(int i = 0; i < carte.nbVehicules; i++)
		{
			v = res.get(i);
			current_node = carte.intersectInit;
			
			iter = 0;
			if(i % 2 == 0)
				iterThreshold = 100;
			else
				iterThreshold = 50;
			while(v.tempsTotal < carte.nbSecondes)
			{
				iter ++;
				listeRoute = getRoutes(carte, current_node);
				if(listeRoute.size() == 0)
					break;
				int e = 0;
				for(; e < 15; e++)
				{
					int rand = r.nextInt(listeRoute.size());
					//System.out.println(rand);
					route = listeRoute.get(rand);
					if(route.temps + v.tempsTotal <= carte.nbSecondes)
						if(iter < iterThreshold && e < 5 && (i % 2 == 0) == est(carte.l.get(current_node), carte.l.get(route.endPointIndex)))
						{
							v.addToPath(route.endPointIndex);
							v.tempsTotal += route.temps;
							if(!exploration[current_node][route.endPointIndex] && !exploration[route.endPointIndex][current_node])
								l += route.longueur;
							exploration[current_node][route.endPointIndex] = true;
							exploration[route.endPointIndex][current_node] = true;
							current_node = route.endPointIndex;
							break;
						}
						else if(e >= 10 || iter >= iterThreshold)
						{
							v.addToPath(route.endPointIndex);
							v.tempsTotal += route.temps;
							if(!exploration[current_node][route.endPointIndex] && !exploration[route.endPointIndex][current_node])
								l += route.longueur;
							exploration[current_node][route.endPointIndex] = true;
							exploration[route.endPointIndex][current_node] = true;
							current_node = route.endPointIndex;
							break;
						}
				}
				if(e == 15)
					break;
			}
			System.out.println("temps de la voiture " + i + " : " + v.tempsTotal);
		}
		
		System.out.println("longueur : " + l);
		Algo.longueur = l;
		return res;
	}
	
	public static List<Route> getRoutes(Carte c, int noeud)
	{
		List<Route> res = new ArrayList<Route>();
		for(int i = 0; i < c.nbIntersections; i++)
		{
			if(c.matrice.getRoute(noeud, i) != null)
				res.add(c.matrice.getRoute(noeud, i));
		}
		return res;
	}
	
	public static boolean nord(Point a, Point b)
	{
		return a.x < b.x;
	}
	
	public static boolean est(Point a, Point b)
	{
		return a.y > b.y;
	}
	
	public static boolean correctdirection(Point a, Point b)
	{
		return a.y > b.y;
	}
	
	
	public static List<Vehicule> solve5(Carte carte)
	{
		List<Vehicule> res = initVehicules(carte.nbVehicules, carte.intersectInit);
		
		boolean[][] exploration = new boolean[carte.nbIntersections][carte.nbIntersections];
		int l = 0;
		
		List<Integer> path = new ArrayList<Integer>();
		Vehicule v;
		
		for(int i = 0; i < carte.nbVehicules; i++)
		{
			v = res.get(i);
			if(i >= carte.nbVehicules-3)
			{
				l += goToSouth(carte, v, exploration);
				l += glouton(carte, v, exploration, v.path.get(v.path.size()-1));
			}
			else
				l += glouton(carte, v, exploration);
			
		}
		
		System.out.println("longueur : " + l);
		Algo.longueur = l;
		return res;
	}
	
	public static int goToSouth(Carte c, Vehicule v, boolean[][] exploration)
	{
		int current_node = c.intersectInit;
		Route bestRoute = null;
		List<Route> routes;
		Random random = new Random();
		int l = 0;
		
		for(int j = 0; j < 400; j++)
		{
			bestRoute = null;
			routes = getRoutes(c, current_node);
			for(Route r : routes)
			{
				if(!nord(c.l.get(current_node), c.l.get(r.endPointIndex)))
				{
					bestRoute = r;
				}
			}
			if(bestRoute == null)
			{
				for(int e = 0; e < 7; e++)
				{
					Route r = routes.get(random.nextInt(routes.size()));
					if(r.temps + v.tempsTotal < c.nbSecondes)
					{
						bestRoute = r;
						break;
					}
				}
				if(bestRoute == null)
					break;
				else
				{
					v.addToPath(bestRoute.endPointIndex);
					if(!exploration[current_node][bestRoute.endPointIndex] 
							&& !exploration[bestRoute.endPointIndex][current_node])
						l += bestRoute.longueur;
					exploration[current_node][bestRoute.endPointIndex] = true;
					exploration[bestRoute.endPointIndex][current_node] = true;
					current_node = bestRoute.endPointIndex;
					v.tempsTotal += bestRoute.temps;
				}
			}
			else
			{
				v.addToPath(bestRoute.endPointIndex);
				if(!exploration[current_node][bestRoute.endPointIndex] 
						&& !exploration[bestRoute.endPointIndex][current_node])
					l += bestRoute.longueur;
				exploration[current_node][bestRoute.endPointIndex] = true;
				exploration[bestRoute.endPointIndex][current_node] = true;
				current_node = bestRoute.endPointIndex;
				v.tempsTotal += bestRoute.temps;
			}
		}
		
		return l;
	}
	
	public static int glouton(Carte c, Vehicule v, boolean[][] exploration)
	{
		return glouton(c, v, exploration, c.intersectInit);
	}
	
	public static int glouton(Carte c, Vehicule v, boolean[][] exploration, int start)
	{
		int l = 0;
		List<Route> routes;
		int current_node = start;
		double bestRatio = 0;
		Route bestRoute;
		int minTemps;
		Random random = new Random();
		System.out.println("glouton");
		while(v.tempsTotal <= c.nbSecondes)
		{	
			routes = getRoutes(c, current_node);
			
			bestRatio = 0;
			bestRoute = null;
			
			if(routes.size() == 0)
			{
				v.path.remove(v.path.size()-1);
				bestRoute = c.matrice.getRoute(v.path.get(v.path.size()), current_node);
				current_node = bestRoute.startPointIndex;
				v.tempsTotal -= bestRoute.temps;
				routes = getRoutes(c, current_node);
				System.out.println("backtrack");
			}
			
			for(Route r : routes)
			{
				if(exploration[current_node][r.endPointIndex] || exploration[r.endPointIndex][current_node])
					continue;
				if((double)r.longueur / (double)r.temps > bestRatio && r.temps + v.tempsTotal <= c.nbSecondes)
				{
					bestRatio = (double)r.longueur / (double)r.temps;
					bestRoute = r;
				}
			}
			if(bestRoute != null)
			{
				v.addToPath(bestRoute.endPointIndex);
				if(!exploration[current_node][bestRoute.endPointIndex] 
						&& !exploration[bestRoute.endPointIndex][current_node])
					l += bestRoute.longueur;
				exploration[current_node][bestRoute.endPointIndex] = true;
				exploration[bestRoute.endPointIndex][current_node] = true;
				current_node = bestRoute.endPointIndex;
				v.tempsTotal += bestRoute.temps;
			}
			else
			{
				return goRandom(c, v, exploration, current_node);
			}
		}
		return l;
	}
	
	public static int goRandom(Carte c, Vehicule v, boolean[][] exploration, int start)
	{
		int l = 0;
		List<Route> routes;
		int current_node = start;
		double bestRatio = 0;
		Route bestRoute;
		int minTemps;
		Random random = new Random();
		System.out.println("random");
		int NB = 0;
		
		while(v.tempsTotal <= c.nbSecondes)
		{
			if(NB++ == 50)
				return glouton(c, v, exploration, current_node);
			
			routes = getRoutes(c, current_node);
			
			bestRatio = 0;
			bestRoute = null;
			
			if(routes.size() == 0)
			{
				break;
			}
			
				for(int e = 0; e < 7; e++)
				{
					Route r = routes.get(random.nextInt(routes.size()));
					if(r.temps + v.tempsTotal < c.nbSecondes)
					{
						bestRoute = r;
						break;
					}
				}
				if(bestRoute == null)
				{
					break;
				}
				else
				{
					v.addToPath(bestRoute.endPointIndex);
					if(!exploration[current_node][bestRoute.endPointIndex] 
							&& !exploration[bestRoute.endPointIndex][current_node])
						l += bestRoute.longueur;
					exploration[current_node][bestRoute.endPointIndex] = true;
					exploration[bestRoute.endPointIndex][current_node] = true;
					current_node = bestRoute.endPointIndex;
					v.tempsTotal += bestRoute.temps;
				}
			}
		
		return l;
	}
}

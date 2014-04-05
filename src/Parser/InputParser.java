package Parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Classes.Carte;
import Classes.Point;

public class InputParser {
	public int N;
	public int M;
	public int T;
	public int C;
	public int S;	
	public List<Point> intersections = new ArrayList<Point>();
	
	public Carte parseInput() throws FileNotFoundException{
		
		Scanner fis = new Scanner(new FileInputStream("paris_54000.txt"));
		String current_line = fis.nextLine();
		N = Integer.parseInt(current_line.split(" ")[0]);
		M = Integer.parseInt(current_line.split(" ")[1]);
		T = Integer.parseInt(current_line.split(" ")[2]);
		C = Integer.parseInt(current_line.split(" ")[3]);
		S = Integer.parseInt(current_line.split(" ")[4]);
		
		//intersections
		for (int i = 0; i < N; i++){
			current_line = fis.nextLine();
			intersections.add(new Point(Double.parseDouble(current_line.split(" ")[0]), Double.parseDouble(current_line.split(" ")[1])));
		}
		
		Carte res = new Carte(N, M, T, C, S, intersections);
		
		String[] split;
		
		// rues
		for(int j = 0; j < M; j++) {
			current_line = fis.nextLine();
			
			split = current_line.split(" ");
			
			res.matrice.AjouterRoute(
				Integer.parseInt(split[0]), 
				Integer.parseInt(split[1]), 
				Integer.parseInt(split[3]), 
				Integer.parseInt(split[4]), 
				Integer.parseInt(split[2])
			);
		}
			
		fis.close();
		
		return res;	
	}
}

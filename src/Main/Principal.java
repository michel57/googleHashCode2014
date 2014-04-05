package Main;


import java.io.FileNotFoundException;
import java.util.List;

import Parser.InputParser;
import Parser.OutputParser;
import Classes.Carte;
import Classes.Vehicule;
import Main.Algo;

public class Principal
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int limit = 1342709;
		if(args.length > 1)
			limit = Integer.parseInt(args[0]);
		System.out.println("hello");
		InputParser input = new InputParser();
		System.out.println("fin input");
		try
		{
			Carte c = input.parseInput();
			Integer longueur = 0;
			List<Vehicule> res = Algo.solve5(c);
			//if(Algo.longueur > limit)
				OutputParser.Output(res, "" + Algo.longueur);
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import Classes.Vehicule;


public class OutputParser {
	public static void Output(List<Vehicule> l, String outputfile) {
		System.out.println("debut output");
		if(  l == null || l.isEmpty() )
			return;
        String filename=outputfile + ".txt";
        File file = new File(filename);
        List<Integer> path;
        try {
            PrintStream printStream = new PrintStream(file);
            System.setOut(printStream);
            System.out.println(l.size());
    		while( ! l.isEmpty() ) {
    			Vehicule v = l.remove(0);
    			path = v.getPath();
    			System.out.println(path.size());
    			while( ! path.isEmpty() ){
    				System.out.println(path.remove(0));
    			}
    		}
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        //System.out.println("fin output");
	}
}

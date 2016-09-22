package algorithmes;

import java.util.ArrayList;
import java.util.Collections;

import Models.Ordonnancement;
import Models.Tache;
import Utils.MyFileReader;

public class RND {

	public static Ordonnancement run(Ordonnancement o) {
		ArrayList<Tache> sol = o.getLesTaches();
		Collections.shuffle(sol);
		return new Ordonnancement(sol);
	}
	
	public static void main(String[] args) {
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			
			ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
			for (int i = 0; i < lesOrdonnancements.size(); i++) {
				Ordonnancement rnd = run(lesOrdonnancements.get(i));
				System.out.println(i + " " + rnd.eval());
			}
		}
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_RND.jar <filename> <nombre de taches>");
		}
		
	}

}

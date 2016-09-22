package algorithmes;

import java.util.ArrayList;
import java.util.Collections;

import Models.Ordonnancement;
import Models.Tache;
import Utils.MyFileReader;

public class EDD {

	public static Ordonnancement run(Ordonnancement o) {
		ArrayList<Tache> sol = o.getLesTaches();
		Collections.sort(sol);
		return new Ordonnancement(sol);
	}
	
	public static void main(String[] args) {
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);

			ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
			for (int i = 0; i < lesOrdonnancements.size(); i++) {
				Ordonnancement edd = run(lesOrdonnancements.get(i));
				System.out.println(i + " " + edd.eval());
			}
		}
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_EDD.jar <filename> <nombre de taches>");
		}
	}

}

package algorithmes;

import java.util.ArrayList;

import Models.Ordonnancement;
import Models.Tache;
import Utils.MyFileReader;

public class MDD {

	public static Ordonnancement run(Ordonnancement o) {
		ArrayList<Tache> list = o.getLesTaches();
		ArrayList<Tache> sol = new ArrayList<Tache>();
		int c = 0;
		while (list.size() != 0) {
			Tache tache = list.get(0);
			int min = Math.max(c + tache.getP(), tache.getD());
			for (Tache t : list) {
				int current = Math.max(c + t.getP(), t.getD());
				if (current < min) {
					tache = t;
					min = current;
				}
			}
			sol.add(tache);
			list.remove(tache);
			c += tache.getP();
		}
		return new Ordonnancement(sol);
	}
	
	public static void main(String[] args) {
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			
			ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
			for (int i = 0; i < lesOrdonnancements.size(); i++) {
				Ordonnancement mdd = run(lesOrdonnancements.get(i));
				System.out.println(i + " " + mdd.eval());
			}
		}
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_MDD.jar <filename> <nombre de taches>");
		}
	}

}

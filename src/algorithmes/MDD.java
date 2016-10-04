package algorithmes;

import java.util.ArrayList;

import models.Ordonnancement;
import models.Tache;

public class MDD extends Heuristic {
		
	public MDD(Ordonnancement o) {
		super(o);
	}

	/**
	 * Lance l'algorithme
	 * @return un ordonnancement
	 */
	public Ordonnancement run() {
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

}

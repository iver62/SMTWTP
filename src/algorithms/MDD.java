package algorithms;

import java.util.ArrayList;

import models.Ordonnancement;
import models.Tache;

public class MDD extends Heuristic {
		
	public MDD(Ordonnancement o) {
		super(o);
	}

	/**
	 * Lance l'algorithme. Cet algorithme trie les taches par ordre croissant de leurs echeances modifiees
	 * mddj := max{C + pj , dj} ou C est la somme des temps d'exucution des jobs deja ordonnances.
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

	@Override
	public String toString() {
		return "mdd";
	}
	
}

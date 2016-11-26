package algorithms;

import java.util.ArrayList;
import java.util.List;

import models.Instance;
import models.Tache;

public class MDD extends Heuristic {

	/**
	 * Lance l'algorithme. Cet algorithme trie les taches par ordre croissant de leurs echeances modifiees
	 * mddj := max{C + pj , dj} ou C est la somme des temps d'execution des jobs deja ordonnances.
	 * @return une instance
	 */
	public Instance run(Instance i) {
		List<Tache> list = new ArrayList<Tache>(i.getLesTaches());
		List<Tache> sol = new ArrayList<Tache>();
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
		return new Instance(sol);
	}

	@Override
	public String toString() {
		return "MDD";
	}
	
}

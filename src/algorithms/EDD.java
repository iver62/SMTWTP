package algorithms;

import java.util.Collections;

import models.Instance;

public class EDD extends Heuristic {

	/**
	 * Lance l'algorithme. Cet algorithme trie les taches par ordre croissant.
	 * @return une instance
	 */
	public Instance run(Instance i) {
		Collections.sort(i.getLesTaches());
		return i;
	}
	
	@Override
	public String toString() {
		return "EDD";
	}

}

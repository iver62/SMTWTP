package algorithms;

import java.util.Collections;

import models.Instance;

public class RND extends Heuristic {
	
	public RND() {}

	/**
	 * Lance l'algorithme qui genere une solution aleatoire.
	 * @return une instance
	 */
	public Instance run(Instance i) {
		Collections.shuffle(i.getLesTaches());
		return i;
	}
	
	@Override
	public String toString() {
		return "RND";
	}

}

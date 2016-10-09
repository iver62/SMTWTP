package algorithmes;

import java.util.Collections;

import models.Ordonnancement;

public class EDD extends Heuristic {
		
	public EDD(Ordonnancement o) {
		super(o);
	}

	/**
	 * Lance l'algorithme. Cet algorithme trie les taches par ordre croissant.
	 * @return un ordonnancement
	 */
	public Ordonnancement run() {
		Collections.sort(o.getLesTaches());
		return o;
	}
	
	@Override
	public String toString() {
		return "edd";
	}

}

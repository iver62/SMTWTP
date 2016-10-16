package algorithms;

import java.util.Collections;

import models.Ordonnancement;

public class RND extends Heuristic {
	
	public RND(Ordonnancement o) {
		super(o);
	}

	/**
	 * Lance l'algorithme qui permet de generer une solution aleatoire.
	 * @return un ordonnancement
	 */
	public Ordonnancement run() {
		Collections.shuffle(o.getLesTaches());
		return o;
	}
	
	@Override
	public String toString() {
		return "rnd";
	}

}

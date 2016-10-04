package algorithmes;

import java.util.Collections;

import models.Ordonnancement;

public class RND extends Heuristic {
	
	public RND(Ordonnancement o) {
		super(o);
	}

	/**
	 * Lance l'algorithme
	 * @return un ordonnancement
	 */
	public Ordonnancement run() {
		Collections.shuffle(o.getLesTaches());
		return o;
	}

}

package algorithmes;

import java.util.Collections;

import models.Ordonnancement;

public class RND {
	
	private Ordonnancement o;

	public RND(Ordonnancement o) {
		this.o = o;
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

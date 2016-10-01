package algorithmes;

import java.util.Collections;

import models.Ordonnancement;

public class EDD {
	
	private Ordonnancement o;
	
	public EDD(Ordonnancement o) {
		this.o = o;
	}

	/**
	 * Lance l'algorithme
	 * @return un ordonnancement
	 */
	public Ordonnancement run() {
		Collections.sort(o.getLesTaches());
		return o;
	}

}

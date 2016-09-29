package algorithmes;

import java.util.Collections;

import Models.Ordonnancement;

public class EDD {
	
	private Ordonnancement o;
	
	public EDD(Ordonnancement o) {
		this.o = o;
	}

	public Ordonnancement run() {
		Collections.sort(o.getLesTaches());
		return o;
	}

}

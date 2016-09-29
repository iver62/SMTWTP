package algorithmes;

import java.util.Collections;

import Models.Ordonnancement;

public class RND {
	
	private Ordonnancement o;

	public RND(Ordonnancement o) {
		this.o = o;
	}

	public Ordonnancement run() {
		Collections.shuffle(o.getLesTaches());
		return o;
	}

}

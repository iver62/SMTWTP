package algorithmes;

import models.Ordonnancement;

public abstract class Heuristic {
	
	protected Ordonnancement o;

	public Heuristic(Ordonnancement o) {
		this.o = o;
	}
	
	public abstract Ordonnancement run();
	

}

package neighborhood;

import models.Ordonnancement;

public abstract class Neighborhood {
			
	public Neighborhood() {	}
	
	public abstract Ordonnancement run(String select, Ordonnancement o);

}

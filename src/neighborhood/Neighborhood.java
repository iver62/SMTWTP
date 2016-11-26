package neighborhood;

import models.Instance;
import utils.Strategie;

public abstract class Neighborhood {
	
	public abstract Instance run(Strategie str, Instance inst);

}

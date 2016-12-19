package algorithms;

import models.Instance;
import neighborhood.Neighborhood;
import utils.Strategie;

public class HillClimbing {
	
	private Strategie str; // strategie de selection du meilleur voisin
	private Neighborhood ngb; // type de voisinage
	private Heuristic h; // choix de la solution initiale
	
	public HillClimbing(Strategie str, Neighborhood ngb, Heuristic h) {
		this.str = str;
		this.ngb = ngb;
		this.h = h;
	}
	
	/**
	 * Genere la solution initiale
	 * @return une instance
	 */
	public Instance generateInitialSolution(Instance i) {
		return h.run(i);
	}
	
	/**
	 * Lance l'algorithme Hill-Climbing selon différents paramètres : la strategie de selection, le voisinage et la solution initiale.
	 * @return l'optimum local
	 */
	public Instance run(Instance i) {
		Instance sol = generateInitialSolution(i); // generation de la solution initiale
		
		boolean improve = true;
		
		while (improve) { // tant qu'on ameliore la solution

			Instance cand = ngb.run(str, sol); // le meilleur voisin de la solution courante selon la strategie
			if (cand.eval() >= sol.eval()) { // si la solution candidate n'est pas meilleure
				improve = false; // pas d'amelioration
			}
			else { // sinon
				sol = cand; // la solution candidate devient la solution
			}
			
		}
				
		return sol;
	}

}

package algorithms;

import models.Ordonnancement;
import neighborhood.Neighborhood;

public class HillClimbing {
	
	private String select; // strategie de selection
	private Neighborhood ngb; // voisinage
	private Heuristic h; // choix de la solution initiale
	
	public HillClimbing(String select, Neighborhood ngb, Heuristic h) {
		this.select = select;
		this.ngb = ngb;
		this.h = h;
	}
	
	/**
	 * Genere la solution initiale
	 * @return un ordonnancement
	 */
	public Ordonnancement generateInitialSolution() {
		return h.run();
	}
	
	/**
	 * Lance l'algorithme Hill-Climbing selon différents paramètres : la strategie de selection, le voisinage et la solution initiale 
	 */
	public Ordonnancement run() {
		Ordonnancement sol = generateInitialSolution(); // generation de la solution initiale
		
		boolean improve = true;
		
		while (improve) { // tant qu'on ameliore la solution

			Ordonnancement cand = ngb.run(select, sol); // le meilleur voisin de la solution courante selon la strategie
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

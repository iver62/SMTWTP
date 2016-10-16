package algorithms;

import java.util.List;

import models.Ordonnancement;
import neighborhood.Neighborhood;

public class VND {
	
	private List<Neighborhood> voisinages; // liste des voisinages
	private String select; // strategie de selection du meilleur voisin
	private Heuristic h; // solution initiale
	
	public VND(List<Neighborhood> voisinages, String select, Heuristic h) {
		this.voisinages = voisinages;
		this.select = select;
		this.h = h;
	}
	
	public Ordonnancement generateInitialSolution() {
		return h.run();
	}
	
	/**
	 * Lance l'algorithme
	 */
	public Ordonnancement run() {
		int k = 0, i = 0;
		Ordonnancement sol = generateInitialSolution(); // generation de la solution initiale
		long deb = System.currentTimeMillis();
		
		while (k < 10*sol.size()) { // on sort de la boucle si on a effectue 1000 iterations sans amelioration
			Neighborhood ngb = voisinages.get(i);
			Ordonnancement cand = ngb.run(select, sol); // le meilleur voisin de la solution courante dans le voisinage courant
			
			if (cand.eval() < sol.eval()) { // s'il est meilleur que la solution courante
				sol = cand; // nouvel optimum local dans le voisinage courant
				long time = System.currentTimeMillis() - deb;
				sol.setTime(time);
//				System.out.println(cand.eval());
				k = 0; // une meilleure solution a ete trouvee k est reinitialise
				i = 0; // on revient au premier voisinage
			}
			
			else {
				i = (++i < voisinages.size()) ? i : 0; // on passe au voisinage suivant
				k++; // pas d'amelioration k est incremente
			}
			
		}
		
		return sol;
	}

}

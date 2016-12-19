package algorithms;

import java.util.ArrayList;
import java.util.List;

import models.Instance;
import neighborhood.Neighborhood;
import utils.Strategie;

public class TabuSearch {
	
	private List<Instance> tabuList; // la liste tabou
	private Strategie str; // strategie de selection du meilleur voisin 
	private List<Neighborhood> voisinages; // liste des voisinages
	private Heuristic h; // solution initiale
	
	public TabuSearch(List<Neighborhood> voisinages, Strategie str, Heuristic h) {
		this.voisinages = voisinages;
		this.str = str;
		this.h = h;
		tabuList = new ArrayList<Instance>();
	}

	/**
	 * Genere la solution initiale
	 * @return une instance
	 */
	public Instance generateInitialSolution(Instance inst) {
		return h.run(inst);
	}
	
	/**
	 * Lance l'algorithme selon differents parametres : la strategie de selection, la liste des voisinages et la solution initiale 
	 * @param inst l'instance sur laquelle l'algorithme est lance
	 * @return l'optimum local respectivement aux k voisinages
	 */
	public Instance run(Instance inst) {
		int k = 0, i = 0;
		
		Instance sol = generateInitialSolution(inst); // la solution initiale
		long debut = System.currentTimeMillis();
		
		while (k < 10*sol.size()) { // on sort de la boucle si on a effectue 1000 iterations sans amelioration
			Neighborhood ngb = voisinages.get(i);
			Instance cand = ngb.run(str, sol); // le meilleur voisin de la solution courante dans le voisinage courant
			
			if (!tabuList.contains(cand) && cand.eval() < sol.eval()) { // s'il est meilleur que la solution courante et qu'il n'est pas dans la liste tabou
				sol = cand; // nouvel optimum local dans le voisinage courant
				long time = System.currentTimeMillis()-debut;
				sol.setTime(time);
				
				if (tabuList.size() == 10*sol.size()) { // si la liste tabou est pleine
					tabuList.remove(0); // on enleve le premier element
				}
				tabuList.add(cand); // on ajoute le meilleur voisin dans la liste
				
				k = 0; // une meilleure solution a ete trouvee k est reinitialise
				i = 0; // on revient dans le premier voisinage
			}
			
			else {
				i = (++i < voisinages.size()) ? i : (voisinages.size()-1); // on passe au voisinage suivant
				k++; // pas d'amelioration k est incremente
			}
			
		}
		
		return sol;
	}

}

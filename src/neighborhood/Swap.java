package neighborhood;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Instance;
import models.Tache;
import utils.Strategie;

public class Swap extends Neighborhood {
	
	/**
	 * Retourne le meilleur voisin d'un ordonnancement selon la methode des permutations. Si la strategie est
	 * FIRST_IMPROVEMENT, on retourne le premier meilleur voisin, si la strategie est BEST_IMPROVEMENT, on
	 * retourne le meilleur voisin parmi tous les voisins.
	 * @param str : FIRST_IMPROVEMENT ou BEST_IMPROVEMENT
	 * @param inst : une instance
	 * @return le meilleur voisin
	 */
	public Instance run(Strategie str, Instance inst) {
		int n = inst.size();
		int e = inst.eval(); // l'evaluation de la solution initiale
		Instance sol = new Instance(new ArrayList<Tache>(inst.getLesTaches())); // au debut le meilleur voisin correspond a la solution initiale
		
		List<Integer> listeI = new ArrayList<Integer>(n), listeJ = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++) {
			listeI.add(i); listeJ.add(i);
		}
		Collections.shuffle(listeI); Collections.shuffle(listeJ);
		
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				int li = listeI.get(i), lj = listeJ.get(j);
				inst.swap(li, lj); // permutation de la ieme et la jieme tache
				if (inst.eval() < e) {
					sol = new Instance(new ArrayList<Tache>(inst.getLesTaches()));
					e = sol.eval();
					if (str.equals(Strategie.FIRST_IMPROVEMENT)) { // si on a choisi first on a trouve la solution donc on sort de la boucle
						inst.swap(lj, li);
						return sol;
					}
				}
				inst.swap(lj, li);
			}
		}
		return sol;
	}

	@Override
	public String toString() {
		return "SWAP";
	}

}

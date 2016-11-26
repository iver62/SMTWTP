package neighborhood;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Instance;
import models.Tache;
import utils.Strategie;

public class Interchange extends Neighborhood {
	
	/**
	 * Retourne le meilleur voisin d'un ordonnancement selon la methode des echanges. Si la strategie est
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
		
		List<Integer> listeI = new ArrayList<Integer>(n);
		for (int i = 0; i < n-1; i++) {
			listeI.add(i);
		}
		Collections.shuffle(listeI);
		
		for (int i = 0; i < n-1; i++) {
			int li = listeI.get(i);
			inst.swap(li, li+1);
			if (inst.eval() < e) {
				sol = new Instance(new ArrayList<Tache>(inst.getLesTaches()));
				e = sol.eval();
				if (str.equals(Strategie.FIRST_IMPROVEMENT)) { // si on a choisi first on a trouve la solution donc on sort de la boucle
					inst.swap(li+1, li);
					return sol;
				}
			}
			inst.swap(li+1, li);
		}
		return sol;
	}

	@Override
	public String toString() {
		return "INTERCHANGE";
	}

}

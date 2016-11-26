package neighborhood;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Instance;
import models.Tache;
import utils.Strategie;

public class Insert extends Neighborhood {
	
	/**
	 * Retourne le meilleur voisin d'un ordonnancement selon la methode des insertions. Si la strategie est
	 * FIRST_IMPROVEMENT, on retourne le premier meilleur voisin, si la strategie est BEST_IMPROVEMENT, on
	 * retourne le meilleur voisin parmi tous les voisins.
	 * @param str : FIRST_IMPROVEMENT ou BEST_IMPROVEMENT
	 * @param inst : une instance
	 * @return le meilleur voisin
	 */
	public Instance run(Strategie str, Instance inst) {
		int n = inst.size(); // le nombre de taches de l'instance
		int e = inst.eval(); // l'evaluation de la solution initiale
		Instance sol = new Instance(new ArrayList<Tache>(inst.getLesTaches())); // au debut le meilleur voisin correspond a la solution initiale
		
		List<Integer> listeI = new ArrayList<Integer>(n), listeJ = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++) {
			listeI.add(i); listeJ.add(i);
		}
		Collections.shuffle(listeI); Collections.shuffle(listeJ);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int li = listeI.get(i), lj = listeJ.get(j);
				if (li != lj) {
					inst.insert(li, lj); // on insere la ieme tache a l'indice j
					if (inst.eval() < e) {
						sol = new Instance(new ArrayList<Tache>(inst.getLesTaches()));
						e = sol.eval();
						if (str.equals(Strategie.FIRST_IMPROVEMENT)) { // si on a choisi first on a trouve la solution donc on sort de la boucle
							inst.insert(lj, li);
							return sol;
						}
					}
					inst.insert(lj, li);
				}
			}
		}
		return sol;
	}

	@Override
	public String toString() {
		return "INSERT";
	}

}

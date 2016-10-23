package neighborhood;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Ordonnancement;
import models.Tache;

public class Swap extends Neighborhood {

	public Swap() {	
		super();
	}
	
	/**
	 * Retourne le meilleur voisin d'un ordonnancement selon la methode des permutations. Si select = 'first' on 
	 * retourne le premier meilleur voisin, si select = 'best', on retourne le meilleur voisin parmi tous les voisins.
	 * @param select : first ou best
	 * @param o : un ordonnancement
	 * @return le meilleur voisin
	 */
	public Ordonnancement run(String select, Ordonnancement o) {
		int n = o.size();
		int e = o.eval(); // l'evaluation de la solution initiale
		Ordonnancement sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches())); // au debut le meilleur voisin correspond a la solution initiale
		
		List<Integer> listeI = new ArrayList<Integer>(n), listeJ = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++) {
			listeI.add(i); listeJ.add(i);
		}
		Collections.shuffle(listeI); Collections.shuffle(listeJ);
		
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				int li = listeI.get(i), lj = listeJ.get(j);
				o.swap(li, lj); // permutation de la ieme et la jieme tache
				if (o.eval() < e) {
					sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches()));
					e = sol.eval();
					if (select.equals("first")) { // si on a choisi first on a trouve la solution donc on sort de la boucle
						o.swap(lj, li);
						return sol;
					}
				}
				o.swap(lj, li);
			}
		}
		return sol;
	}

	@Override
	public String toString() {
		return "swap";
	}

}

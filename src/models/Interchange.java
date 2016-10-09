package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Interchange extends Neighborhood {

	public Interchange() {
		super();
	}
	
	/**
	 * Retourne le meilleur voisin d'un ordonnancement selon la methode des echanges. Si select = 'first' on 
	 * retourne le premier meilleur voisin, si select = 'best', on retourne le meilleur voisin parmi
	 * tous les voisins.
	 * @param select : first ou best
	 * @param o : un ordonnancement
	 * @return le meilleur voisin
	 */
	public Ordonnancement run(String select, Ordonnancement o) {
		int n = o.getSize();
		int e = o.eval(); // l'evaluation de la solution initiale
		Ordonnancement sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches())); // au debut le meilleur voisin correspond a la solution initiale
		
		List<Integer> listeI = new ArrayList<Integer>(n);
		for (int i = 0; i < n-1; i++) {
			listeI.add(i);
		}
		Collections.shuffle(listeI);
		
		for (int i = 0; i < n-1; i++) {
			int li = listeI.get(i);
			o.swap(li, li+1);
			if (o.eval() < e) {
				sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches()));
				e = sol.eval();
				if (select.equals("first")) { // si on a choisi first on a trouve la solution donc on sort de la boucle
					o.swap(li+1, li);
					return sol;
				}
			}
			o.swap(li+1, li);
		}
		return sol;
	}

	@Override
	public String toString() {
		return "interchange";
	}

}

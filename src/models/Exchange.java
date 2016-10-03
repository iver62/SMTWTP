package models;

import java.util.ArrayList;

public class Exchange extends Neighborhood {

	public Exchange() {
		super();
	}
	
	/**
	 * Retourne le meilleur voisin d'un ordonnancement selon la methode des echanges. Si select = 'first' on 
	 * retourne le premier meilleur voisin, si select = 'best', on retourne l'evaluation du meilleur voisin parmi
	 * tous les voisins.
	 * @param select : first ou best
	 * @param o : un ordonnancement
	 * @return le meilleur voisin
	 */
	public Ordonnancement run(String select, Ordonnancement o) {
		int n = o.getSize();
		int e = o.eval(); // l'evaluation de la solution initiale
		Ordonnancement sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches())); // au debut le meilleur voisin correspond a la solution initiale
		for (int i = 0; i < n-1; i++) {
			o.swap(i, i+1);
			if (o.eval() < e) {
				sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches()));
				e = sol.eval();
				if (select.equals("first")) { // si on a choisi first on a trouve la solution donc on sort de la boucle
					o.swap(i+1, i);
					return sol;
				}
			}
			o.swap(i+1, i);
		}
		return sol;
	}

	@Override
	public String toString() {
		return "Exchange";
	}

}

package models;

import java.util.ArrayList;

public class Swap extends Neighborhood {

	public Swap() {	
		super();
	}
	
	/**
	 * Retourne le meilleur d'un ordonnancement voisin selon la methode des permutations. Si select = 'first' on 
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
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				o.swap(i, j); // on permute la ieme et la jieme tache
				if (o.eval() < e) {
					sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches()));
					e = sol.eval();
					if (select.equals("first")) { // si on a choisi first on a trouve la solution donc on sort de la boucle
						o.swap(j, i);
						return sol;
					}
				}
				o.swap(j, i);
			}
		}
		return sol;
	}

	@Override
	public String toString() {
		return "Swap";
	}

}

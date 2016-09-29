package Models;

import java.util.ArrayList;

public class Neighborhood {
	
	private Ordonnancement o;
	private int n;
	
	public Neighborhood(Ordonnancement o) {
		this.o = o;
		n = o.getSize();
	}
	
	/**
	 * Retourne le meilleur voisin selon la methode des insertions. Si select = 'first' on retourne le premier
	 * meilleur voisin, si select = 'best', on retourne l'evaluation du meilleur voisin parmi tous les voisins.
	 * @param select : first ou best
	 * @return le meilleur voisin
	 */
	public Ordonnancement insert(String select) {
		int e = o.eval(); // l'evaluation de la solution initiale
		Ordonnancement sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches())); // au debut le meilleur voisin correspond a la solution initiale
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					o.insert(i, j); // on insere la ieme tache a l'indice j
					if (o.eval() < e) {
//						sol = o.eval();
						sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches()));
						e = sol.eval();
						if (select.equals("first")) { // si on a choisi first on a trouve la solution donc on sort de la boucle
							o.insert(j, i);
							return sol;
						}
					}
					o.insert(j, i);
				}
			}
		}
		return sol;
	}
	
	/**
	 * Retourne le meilleur voisin selon la methode des permutations. Si select = 'first' on retourne le premier
	 * meilleur voisin, si select = 'best', on retourne l'evaluation du meilleur voisin parmi tous les voisins.
	 * @param select : first ou best
	 * @return le meilleur voisin
	 */
	public Ordonnancement swap(String select) {
		int e = o.eval(); // l'evaluation de la solution initiale
		Ordonnancement sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches())); // au debut le meilleur voisin correspond a la solution initiale
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				o.swap(i, j); // on permute la ieme et la jieme tache
				if (o.eval() < e) {
//					sol = o.eval();
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
	
	/**
	 * Retourne le meilleur voisin selon la methode des echanges. Si select = 'first' on retourne le premier
	 * meilleur voisin, si select = 'best', on retourne l'evaluation du meilleur voisin parmi tous les voisins.
	 * @param select : first ou best
	 * @return le meilleur voisin
	 */
	public Ordonnancement exchange(String select) {
		int e = o.eval(); // l'evaluation de la solution initiale
		Ordonnancement sol = new Ordonnancement(new ArrayList<Tache>(o.getLesTaches())); // au debut le meilleur voisin correspond a la solution initiale
		for (int i = 0; i < n-1; i++) {
			o.swap(i, i+1);
			if (o.eval() < e) {
//				sol = o.eval();
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

}

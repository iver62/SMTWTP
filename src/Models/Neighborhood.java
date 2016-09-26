package Models;

public class Neighborhood {
	
	private Ordonnancement o;
	private int n;
	
	public Neighborhood(Ordonnancement o) {
		this.o = o;
		n = o.getSize();
	}
	
	/**
	 * Retourne l'evaluation du meilleur voisin selon la methode des insertions. Si select = 'first' on retourne
	 * l'evaluation du premier meilleur voisin, si select = 'best', on retourne l'evaluation du meilleur voisin parmi
	 * tous les voisins.
	 * @param select first ou best
	 * @return l'evaluation du meilleur voisin
	 */
	public Ordonnancement insert(String select) {
		int e = o.eval(); // l'evaluation de la solution initiale
		Ordonnancement sol = new Ordonnancement(o.getLesTaches());
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j) {
					o.insert(i, j);
					if (o.eval() < e) {
//						sol = o.eval();
						sol = new Ordonnancement(o.getLesTaches());
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
	 * Retourne l'evaluation du meilleur voisin selon la methode des permutations. Si select = 'first' on retourne
	 * l'evaluation du premier meilleur voisin, si select = 'best', on retourne l'evaluation du meilleur voisin parmi
	 * tous les voisins.
	 * @param select first ou best
	 * @return l'evaluation du meilleur voisin
	 */
	public int swap(String select) {
		int sol = o.eval(); // l'evaluation de la solution initiale
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				o.swap(i, j);
				if (o.eval() < sol) {
					sol = o.eval();
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
	 * Retourne l'evaluation du meilleur voisin selon la methode des echanges. Si select = 'first' on retourne
	 * l'evaluation du premier meilleur voisin, si select = 'best', on retourne l'evaluation du meilleur voisin parmi
	 * tous les voisins.
	 * @param select first ou best
	 * @return l'evaluation du meilleur voisin
	 */
	public int exchange(String select) {
		int sol = o.eval(); // l'evaluation de la solution initiale
		for (int i = 0; i < n-1; i++) {
			o.swap(i, i+1);
			if (o.eval() < sol) {
				sol = o.eval();
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

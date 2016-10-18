package algorithms;

import java.util.List;

import models.Ordonnancement;
import neighborhood.Neighborhood;
import utils.MyFileWriter;

public class VND {
	
	private List<Neighborhood> voisinages; // liste des voisinages
	private String select; // strategie de selection du meilleur voisin
	private Heuristic h; // solution initiale
	private int n;
	
	public VND(List<Neighborhood> voisinages, String select, Heuristic h, int n) {
		this.voisinages = voisinages;
		this.select = select;
		this.h = h;
		this.n = n;
	}
	
	public Ordonnancement generateInitialSolution() {
		return h.run();
	}
	
	/**
	 * Lance l'algorithme
	 */
	public void run() {
		int k = 0, i = 0, j = 0;
		
		Ordonnancement sol = generateInitialSolution(); // generation de la solution initiale
		double[] devs = new double[100*sol.size()]; long[] times = new long[100*sol.size()];
		
		long deb = System.currentTimeMillis();
		
		while (k < 10*sol.size()) { // on sort de la boucle si on a effectue 1000 iterations sans amelioration
			Neighborhood ngb = voisinages.get(i);
			Ordonnancement cand = ngb.run(select, sol); // le meilleur voisin de la solution courante dans le voisinage courant
			
			if (cand.eval() < sol.eval()) { // s'il est meilleur que la solution courante
				sol = cand; // nouvel optimum local dans le voisinage courant
				long time = System.currentTimeMillis() - deb;
				devs[j] = sol.deviation(n);
				times[j] = time;
				j++;
//				sol.setTime(time);
				System.out.println(sol.eval());
				k = 0; // une meilleure solution a ete trouvee k est reinitialise
				i = 0; // on revient au premier voisinage
			}
			
			else {
				i = (++i < voisinages.size()) ? i : 0; // on passe au voisinage suivant
				k++; // pas d'amelioration k est incremente
			}
			
		}
		
		MyFileWriter.writeData("data/results/vnd/"+select+"_"+h.toString()+".dat", devs, times);
//		return sol;
	}

}

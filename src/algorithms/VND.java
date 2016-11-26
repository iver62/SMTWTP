package algorithms;

import java.util.List;

import models.Instance;
import neighborhood.Neighborhood;
import utils.MyFileWriter;
import utils.Strategie;

public class VND {
	
	private List<Neighborhood> voisinages; // liste des voisinages
	private Strategie str; // strategie de selection du meilleur voisin
	private Heuristic h; // solution initiale
	private int n; // nieme instance
	
	public VND(List<Neighborhood> voisinages, Strategie str, Heuristic h, int n) {
		this.voisinages = voisinages;
		this.str = str;
		this.h = h;
		this.n = n;
	}
	
	public Instance generateInitialSolution(Instance inst) {
		return h.run(inst);
	}
	
	/**
	 * Lance l'algorithme VND
	 */
	public void run(Instance inst) {
		int k = 0, i = 0, j = 0;
		
		Instance sol = generateInitialSolution(inst); // generation de la solution initiale
		double[] devs = new double[100*sol.size()]; long[] times = new long[100*sol.size()];
		
		long deb = System.currentTimeMillis();
		
		while (k < 10*sol.size()) { // on sort de la boucle si on a effectue 10*nombre_de_taches iterations sans amelioration
			Neighborhood ngb = voisinages.get(i); // le voisinage courant
			Instance cand = ngb.run(str, sol); // le meilleur voisin de la solution courante dans le voisinage courant
			
			if (cand.eval() < sol.eval()) { // s'il est meilleur que la solution courante
				sol = cand; // nouvel optimum local dans le voisinage courant
				long time = System.currentTimeMillis() - deb; // duree a laquelle on a trouve une meilleure solution
				devs[j] = sol.deviation(n);
				times[j] = time;
				j++;
//				sol.setTime(time);
				System.out.println(sol.eval() + " " + time + "ms");
				k = 0; // une meilleure solution a ete trouvee k est reinitialise
				i = 0; // on revient au premier voisinage
			}
			
			else {
				i = (++i < voisinages.size()) ? i : 0; // on passe au voisinage suivant
				k++; // pas d'amelioration k est incremente
			}
			
		}
		
//		MyFileWriter.writeData("data/results/vnd/"+str+"_"+h.toString()+".dat", devs, times);
//		return sol;
	}

}

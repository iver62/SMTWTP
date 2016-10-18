package algorithms;

import java.util.ArrayList;
import java.util.List;

import models.Ordonnancement;
import neighborhood.Neighborhood;
import utils.MyFileWriter;

public class TabuSearch {
	
	private List<Ordonnancement> tabuList; // la liste tabou
	private String select; // strategie de selection du meilleur voisin 
	private List<Neighborhood> voisinages; // liste des voisinages
	private Heuristic h; // solution initiale
	private int n;
	
	public TabuSearch(List<Neighborhood> voisinages, String select, Heuristic h, int n) {
		this.voisinages = voisinages;
		this.select = select;
		this.h = h;
		this.n = n;
		tabuList = new ArrayList<Ordonnancement>();
	}

	/**
	 * Genere la solution initiale
	 * @return un ordonnancement
	 */
	public Ordonnancement generateInitialSolution() {
		return h.run();
	}
	
	/**
	 * Lance l'algorithme selon diff�rents param�tres : la strategie de selection, la liste des voisinages et la solution initiale 
	 */
	public void run() {
		int k = 0, i = 0, j = 0;
		Ordonnancement sol = generateInitialSolution(); // la solution initiale
		double[] devs = new double[100*sol.size()]; long[] times = new long[100*sol.size()];
		long debut = System.currentTimeMillis();
		
		while (k < 10*sol.size()) { // on sort de la boucle si on a effectue 1000 iterations sans amelioration
			Neighborhood ngb = voisinages.get(i);
			Ordonnancement cand = ngb.run(select, sol); // le meilleur voisin de la solution courante dans le voisinage courant
			
			if (!tabuList.contains(cand) && cand.eval() < sol.eval()) { // s'il est meilleur que la solution courante et qu'il n'est pas dans la liste tabou
				long time = System.currentTimeMillis()-debut;
				sol = cand; // nouvel optimum local dans le voisinage courant
				System.out.println(sol.eval());
				devs[j] = sol.deviation(n);
				times[j] = time;
				j++;
//				sol.setTime(time);
				
				if (tabuList.size() == sol.size()) { // si la liste tabou est pleine
					tabuList.remove(0); // on enleve le premier element
				}
				tabuList.add(cand); // on ajoute le meilleur voisin dans la liste
				
//				MyFileWriter.writeData("data/results/ts/"+select+"_"+h.toString()+"_"+(n+1)+".dat", dev, d);
//				System.out.println(sol.eval() + " t=" + d + "ms" + " deviation=" + dev);
				k = 0; // une meilleure solution a ete trouvee k est reinitialise
				i = 0; // on revient dans le premier voisinage
			}
			
			else {
				i = (++i < voisinages.size()) ? i : (voisinages.size()-1); // on passe au voisinage suivant
				k++; // pas d'amelioration k est incremente
			}
			
		}
		
		MyFileWriter.writeData("data/results/ts/"+select+"_"+h.toString()+".dat", devs, times);
//		return sol;
	}

}

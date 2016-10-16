package algorithms;

import java.util.ArrayList;
import java.util.List;

import models.Ordonnancement;
import neighborhood.Neighborhood;

public class TabuSearch {
	
	private List<Ordonnancement> tabuList; // la liste tabou
	private String select; // strategie de selection du meilleur voisin 
	private List<Neighborhood> voisinages; // liste des voisinages
	private Heuristic h; // solution initiale
	
	public TabuSearch(List<Neighborhood> voisinages, String select, Heuristic h, int n) {
		this.voisinages = voisinages;
		this.select = select;
		this.h = h;
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
	 * Lance l'algorithme selon différents paramètres : la strategie de selection, la liste des voisinages et la solution initiale 
	 */
	public Ordonnancement run() {
		int k = 0, i = 0;
		Ordonnancement sol = generateInitialSolution(); // la solution initiale
//		double bestScore = MyFileReader.bestSolution(n); // l'evaluation de la meilleure solution connue
		long debut = System.currentTimeMillis();
		
		while (k < 10*sol.size()) { // on sort de la boucle si on a effectue 1000 iterations sans amelioration
			Neighborhood ngb = voisinages.get(i);
			Ordonnancement cand = ngb.run(select, sol); // le meilleur voisin de la solution courante dans le voisinage courant
			
			if (!tabuList.contains(cand) && cand.eval() < sol.eval()) { // s'il est meilleur que la solution courante et qu'il n'est pas dans la liste tabou
				long time = System.currentTimeMillis()-debut;
				sol = cand; // nouvel optimum local dans le voisinage courant
//				double dev = 100 * (sol.eval()-bestScore) / bestScore; // la deviation par rapport a la meilleure solution connue
				
//				System.out.println(cand.eval());
				sol.setTime(time);
				
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
		
		return sol;
	}

}

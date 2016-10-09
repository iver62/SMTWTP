package algorithmes;

import java.util.ArrayList;
import java.util.List;

import models.Neighborhood;
import models.Ordonnancement;
import utils.MyFileReader;
import utils.MyFileWriter;

public class TabuSearch {
	
	private int stoppingCondition = 1000; // nombre maximal d'iterations sans amelioration
	private List<Ordonnancement> tabuList; // la liste tabou
	private String select; // strategie de selection du meilleur voisin 
	private List<Neighborhood> voisinages; // liste des voisinages
	private Heuristic h; // solution initiale
	private int n; // la nieme instance
	
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
	 * Lance l'algorithme selon différents paramètres : la strategie de selection, la liste des voisinages et la solution initiale 
	 */
	public void run() {
		int k = 0, i = 0;
		Ordonnancement current = generateInitialSolution(); // la solution courante, au debut c'est la solution initiale
		double bestScore = MyFileReader.bestSolution(n); // l'evaluation de la meilleure solution connue
		long debut = System.currentTimeMillis();
		
		while (k < stoppingCondition) { // on sort de la boucle si on a effectue 1000 iterations sans amelioration
			Neighborhood ngb = voisinages.get(i);
			Ordonnancement best = ngb.run(select, current); // le meilleur voisin de la solution courante dans le voisinage courant
			
			if (/*best.eval() < current.eval() && */!tabuList.contains(best)) { // s'il est meilleur que la solution courante et qu'il n'est pas dans la liste tabou
				long d = System.currentTimeMillis()-debut;
				current = best; // nouvel optimum local dans le voisinage courant
				double dev = 100 * (current.eval()-bestScore) / bestScore; // la deviation par rapport a la meilleure solution connue
				
				if (tabuList.size() == current.getSize()) { // si la liste tabou est pleine
					tabuList.remove(0); // on enleve le premier element
				}
				tabuList.add(best); // on ajoute le meilleur voisin dans la liste
				
				MyFileWriter.writeData("data/results/ts/"+select+"_"+h.toString()+"_"+(n+1)+".dat", dev, d);
				System.out.println(current.eval() + " t=" + d + "ms" + " deviation=" + dev);
				k = 0; // une meilleure solution a ete trouvee k est reinitialise
				i = 0; // on revient dans le premier voisinage
			}
			
			else {
				i = (++i < voisinages.size()) ? i : (voisinages.size()-1); // on passe au voisinage suivant
				k++; // pas d'amelioration k est incremente
			}
			
		}
	}

}

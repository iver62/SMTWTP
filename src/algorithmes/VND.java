package algorithmes;

import java.util.List;

import models.Neighborhood;
import models.Ordonnancement;
import utils.MyFileReader;

public class VND {
	
	private int t = 1000; // nombre maximal d'iterations sans amelioration
	private List<Neighborhood> voisinages; // liste des voisinages
	private String select; // strategie de selection du meilleur voisin
	private Heuristic h; // solution initiale
	private int n; // la nieme instance
	
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
		int k = 0, i = 0;
		Ordonnancement current = generateInitialSolution();
		long time = 0;
		long debut = System.currentTimeMillis();
		System.out.println("Running...");
		
		while (k < t) { // on sort de la boucle si on a effectue 1000 iterations sans amelioration
			Neighborhood ngb = voisinages.get(i);
			Ordonnancement best = ngb.run(select, current); // le meilleur voisin de la solution courante dans le voisinage courant
			if (best.eval() < current.eval()) { // s'il est meilleur que la solution courante
				current = best; // nouvel optimum local dans le voisinage courant
				time = System.currentTimeMillis()-debut;
				k = 0; // une meilleure solution a ete trouvee k est reinitialise
			}
			else {
				i = (++i < voisinages.size()) ? i : 0; // on passe au voisinage suivant
				k++; // pas d'amelioration k est incremente
			}
		}
		
		double bestScore = MyFileReader.bestSolution(n); // l'evaluation de la meilleure solution connue
		double dev = 100 * (current.eval()-bestScore) / bestScore; // la deviation par rapport a la meilleure solution connue
		System.out.println("Done");
		System.out.println(current.eval() + " t=" + time + "ms" + " deviation=" + dev);
	}

}

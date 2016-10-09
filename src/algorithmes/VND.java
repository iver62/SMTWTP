package algorithmes;

import java.util.List;

import models.Neighborhood;
import models.Ordonnancement;
import utils.MyFileReader;
import utils.MyFileWriter;

public class VND {
	
	private int stoppingCondition = 1000; // nombre maximal d'iterations sans amelioration
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
		int sc = 0, i = 0;
		Ordonnancement current = generateInitialSolution();
		double bestScore = MyFileReader.bestSolution(n); // l'evaluation de la meilleure solution connue
		long debut = System.currentTimeMillis();
		
		while (sc < stoppingCondition) { // on sort de la boucle si on a effectue 1000 iterations sans amelioration
			Neighborhood ngb = voisinages.get(i);
			Ordonnancement best = ngb.run(select, current); // le meilleur voisin de la solution courante dans le voisinage courant
			
			if (best.eval() < current.eval()) { // s'il est meilleur que la solution courante
				long d = System.currentTimeMillis()-debut;
				current = best; // nouvel optimum local dans le voisinage courant
				double dev = 100 * (current.eval()-bestScore) / bestScore; // la deviation par rapport a la meilleure solution connue
				
				MyFileWriter.writeData("data/results/vnd/"+select+"_"+h.toString()+"_"+(n+1)+".dat", dev, d);
				System.out.println(current.eval() + " t=" + d + "ms" + " deviation=" + dev);
				sc = 0; // une meilleure solution a ete trouvee k est reinitialise
				i = 0; // on revient au premir voisinage
			}
			
			else {
				i = (++i < voisinages.size()) ? i : (voisinages.size()-1); // on passe au voisinage suivant
				sc++; // pas d'amelioration k est incremente
			}
			
		}
	}

}

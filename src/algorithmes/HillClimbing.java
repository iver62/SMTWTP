package algorithmes;

import models.Neighborhood;
import models.Ordonnancement;
import utils.MyFileReader;

public class HillClimbing {
	
	private int nbRuns = 30;
	private String select; // strategie de selection
	private Neighborhood ngb; // voisinage
	private Ordonnancement initSol; // la solution initiale
	private int n; // la nieme instance
	
	public HillClimbing(String select, Neighborhood ngb, Ordonnancement initSol, int n) {
		this.select = select;
		this.ngb = ngb;
		this.initSol = initSol;
		this.n = n;
	}
	
	/**
	 * Lance l'algorithme
	 */
	public void run() {
		long total = 0;
		Ordonnancement sol = null;
		
		for (int k = 0; k < nbRuns; k++) {
			long debut = System.currentTimeMillis();
			sol = ngb.run(select, initSol); // le meilleur voisin de la solution initiale selon la strategie
			total += System.currentTimeMillis()-debut;
		}
		
		double bestScore = MyFileReader.bestSolution(n); // la meilleure solution connue de l'ordonnancement n 
		long d = total / nbRuns;
		double dev = 100 * (sol.eval()-bestScore) / bestScore; // la deviation par rapport a la meilleure solution connue
		
		System.out.println(n + " " + d + "ms" + " " + sol.eval() + " " + dev);
	}

}

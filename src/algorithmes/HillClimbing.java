package algorithmes;

import models.Neighborhood;
import models.Ordonnancement;
import utils.MyFileReader;
import utils.MyFileWriter;

public class HillClimbing {
	
	private int nbRuns = 30;
	private String select; // strategie de selection
	private Neighborhood ngb; // voisinage
	private Heuristic h; // solution initiale
	private int n; // la nieme instance
	
	public HillClimbing(String select, Neighborhood ngb, Heuristic h, int n) {
		this.select = select;
		this.ngb = ngb;
		this.h = h;
		this.n = n;
	}
	
	/**
	 * Genere la solution initiale
	 * @return un ordonnancement
	 */
	public Ordonnancement generateInitialSolution() {
		return h.run();
	}
	
	/**
	 * Lance l'algorithme Hill-Climbing selon différents paramètres : la strategie de selection, le voisinage et la solution initiale 
	 */
	public void run() {
		long total = 0;
		int sum = 0;
		Ordonnancement initSol = generateInitialSolution();
		Ordonnancement sol = null;
				
		for (int k = 0; k < nbRuns; k++) {
			long debut = System.currentTimeMillis();
			sol = ngb.run(select, initSol); // le meilleur voisin de la solution initiale selon la strategie
			sum += sol.eval();
			total += System.currentTimeMillis()-debut;
		}
		
		int eval = sum/nbRuns; // l'évaluation moyenne d'un run
		double bestScore = MyFileReader.bestSolution(n); // la meilleure solution connue de la nieme instance
		long d = total/nbRuns; // la durée moyenne d'un run
		double dev = (eval == 0 && bestScore == 0) ? 0 : 100 * (eval-bestScore)/bestScore; // la deviation par rapport a la meilleure solution connue
		
		MyFileWriter.writeData("data/results/hc/"+select+"_"+ngb.toString()+"_"+h.toString()+".dat", dev, d);
		
		System.out.println(n + " " + d + "ms" + " " + eval + " " + dev);
	}

}

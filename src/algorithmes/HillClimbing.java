package algorithmes;

import Models.Neighborhood;
import Models.Ordonnancement;
import Utils.MyFileReader;

public class HillClimbing {
	
	private int nbRuns = 30;
	private String select; // strategie de selection
	private Neighborhood ngb; // voisinage
	private int n;
	
	public HillClimbing(String select, Neighborhood ngb, int n) {
		this.select = select;
		this.ngb = ngb;
		this.n = n;
	}
	
	public Ordonnancement run() {
		long total = 0;
		Ordonnancement sol = null;
		for (int k = 0; k < nbRuns; k++) {
			long debut = System.currentTimeMillis();
			sol = ngb.run(select);
			total += System.currentTimeMillis()-debut;
		}
		double best = MyFileReader.bestSolution(n); // la meilleure solution connue de l'ordonnancement n 
		long d = total / nbRuns;
		double dev = 100 * (sol.eval()-best) / best; // la deviation par rapport a la meilleure solution connue
		
		System.out.println(n + " " + d + "ms" + " " + sol.eval() + " " + dev);
		return sol;
	}

}

package algorithmes;

import java.util.List;

import models.Neighborhood;
import models.Ordonnancement;

public class TabuSearch {
	
	private Ordonnancement[] tabuList;
	private String select; // strategie de selection du meilleur voisin 
	private List<Neighborhood> voisinages; // liste des voisinages
	private Heuristic h;
	private int n; // la nieme instance
	
	public Ordonnancement generateInitialSolution() {
		return h.run();
	}
	
	public void run() {
		
	}

}

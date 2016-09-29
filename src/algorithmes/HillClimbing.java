package algorithmes;

import java.util.ArrayList;

import Models.Neighborhood;
import Models.Ordonnancement;
import Utils.MyFileReader;

public class HillClimbing {
	
	private static int nbRuns = 30;

	public static void main(String[] args) {
		ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load("data/wt100.txt", 100);
		
		if (args.length == 6) { // on verife qu'il y a bien 6 parametres
			String select = args[1];
			String voisinage = args[3];
			String init = args[5];
			
			if (checkParameters(select, voisinage, init)) { // on verifie si les parametres sont corrects
				System.out.println("Resulats " + select + " " + voisinage + " " + init);
				
				if (init.equals("rnd")) { // si la solution initiale est RND
					for (int n = 0; n < lesOrdonnancements.size(); n++) { // pour chaque ordonnancement
						long total = 0;
						Ordonnancement rnd_ord = RND.run(lesOrdonnancements.get(n)); // la solution initiale
						Neighborhood ngb = new Neighborhood(rnd_ord); // creation d'un voisinage par rapport a rnd_ord
						Ordonnancement sol = null; // l'evaluation de l'optimum local
						
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							for (int k = 0; k < nbRuns; k++) {
								long debut = System.currentTimeMillis();
								sol = ngb.insert(select); // l'evaluation du meilleur voisin selon first ou best
								total += System.currentTimeMillis()-debut;
							}
						}
						
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							for (int k = 0; k < nbRuns; k++) {
								long debut = System.currentTimeMillis();
								sol = ngb.swap(select);
								total += System.currentTimeMillis()-debut;
							}
						}
						
						else if (voisinage.equals("exchange")) { // si on a choisi l'echange
							for (int k = 0; k < nbRuns; k++) {
								long debut = System.currentTimeMillis();
								sol = ngb.exchange(select);
								total += System.currentTimeMillis()-debut;
							}
						}
						
						double best = MyFileReader.bestSolution(n);
						long d = total / 30;
						double dev = 100 * (sol.eval()-best) / best; // la deviation par rapport a la meilleure solution connue
						
						System.out.println("n=" + n + " " + d + "ms" + " " + rnd_ord.eval() + " " + sol.eval() + " " + dev);
					}
				}
				
				else if (init.equals("edd")) {
					for (int n = 0; n < lesOrdonnancements.size(); n++) {
						long total = 0;
						Ordonnancement edd_ord = EDD.run(lesOrdonnancements.get(n)); // algorithme edd
						Neighborhood ngb = new Neighborhood(edd_ord);
						Ordonnancement sol = null;
						
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							for (int k = 0; k < nbRuns; k++) {
								long debut = System.currentTimeMillis();
								sol = ngb.insert(select);
								total += System.currentTimeMillis()-debut;
							}
						}
						
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							for (int k = 0; k < nbRuns; k++) {
								long debut = System.currentTimeMillis();
								sol = ngb.swap(select);
								total += System.currentTimeMillis()-debut;
							}
						}
						
						else if (voisinage.equals("exchange")) { // si on a choisi l'echange
							for (int k = 0; k < nbRuns; k++) {
								long debut = System.currentTimeMillis();
								sol = ngb.exchange(select);
								total += System.currentTimeMillis()-debut;
							}
						}
						
						double best = MyFileReader.bestSolution(n);
						long d = total / 30;
						double dev = 100 * (sol.eval()-best) / best;
						
						System.out.println("n=" + n + " " + d + "ms" + " " + edd_ord.eval() + " " + sol.eval() + " " + dev);
					}
				}
				
				else if (init.equals("mdd")) {
					for (int n = 0; n < lesOrdonnancements.size(); n++) {
						long total = 0;
						Ordonnancement mdd_ord = MDD.run(lesOrdonnancements.get(n)); // solution initiale - algorithme mdd
						Neighborhood ngb = new Neighborhood(mdd_ord); // voisinage de la solution initiale
						Ordonnancement sol = null;
						
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							for (int k = 0; k < nbRuns; k++) {
								long debut = System.currentTimeMillis();
								sol = ngb.insert(select);
								total += System.currentTimeMillis()-debut;
							}
						}
						
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							for (int k = 0; k < nbRuns; k++) {
								long debut = System.currentTimeMillis();
								sol = ngb.swap(select);
								total += System.currentTimeMillis()-debut;
							}
						}
						
						else if (voisinage.equals("exchange")) { // si on a choisi l'echange
							for (int k = 0; k < nbRuns; k++) {
								long debut = System.currentTimeMillis();
								sol = ngb.exchange(select);
								total += System.currentTimeMillis()-debut;
							}
						}
						
						double best = MyFileReader.bestSolution(n);
						long d = total / 30;
						double dev = 100 * (sol.eval()-best) / best;
						
						System.out.println("n=" + n + " " + d + "ms" + " " + mdd_ord.eval() + " " + sol.eval() + " " + dev);
					}
				}
			}
			
			else {
				System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar -select [first,best] -voisinage [insert,swap,exchange] -init [rnd,edd,mdd]");
			}
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar -select [first,best] -voisinage [insert,swap,exchange] -init [rnd,edd,mdd]");
		}
	}

	private static boolean checkParameters(String select, String voisinage, String init) {
		return (select.equals("first") || select.equals("best")) && (voisinage.equals("insert") || voisinage.equals("swap") || voisinage.equals("exchange")) && (init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
	}
	
}

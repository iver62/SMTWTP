package tests;

import java.util.ArrayList;
import java.util.List;

import algorithms.EDD;
import algorithms.Heuristic;
import algorithms.MDD;
import algorithms.RND;
import algorithms.VND;
import models.Instance;
import neighborhood.Insert;
import neighborhood.Interchange;
import neighborhood.Neighborhood;
import neighborhood.Swap;
import utils.MyFileReader;
import utils.Strategie;

public class MainVND {
	
	public static void main(String[] args) {
		
		if (args.length == 6) { // on verifie qu'il y a bien 6 parametres
			
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			Strategie str = getStrategie(args[2]); // strategie de selection du meilleur voisin		
			Heuristic h = getHeuristic(args[3]); // choix de la  solution initiale
			int v = Integer.parseInt(args[4]); // choix de l'ordre des voisinages
			int n = Integer.parseInt(args[5]); // le numero de l'ordonnancement
			
			System.out.println("Running...");
			List<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
				
//			int size = lesOrdonnancements.size();
//			int[] evals = new int[size]; String[] devs = new String[size]; long[] times = new long[size];
				
//			for (int n = 0; n < lesOrdonnancements.size(); n++) { // pour chaque instance
			if (n <= lesInstances.size() && n > 0) { // si on a choisi un ordonnancement valide
					
				Instance inst = lesInstances.get(n-1); // l'ordonnancement choisi
					
				List<Neighborhood> voisinages = new ArrayList<Neighborhood>(); // la liste des voisinages
				voisinages.add(new Interchange());
				
				if (v == 1) { // le premier ensemble de voisinage
					voisinages.add(new Swap());
					voisinages.add(new Insert());
				}
				
				else if (v == 2) { // le deuxieme ensemble de voisinage
					voisinages.add(new Insert());
					voisinages.add(new Swap());
				}
				
				VND vnd = new VND(voisinages, str, h, n);
					
//					long totalTime = 0;
//					double totalEval = 0;
//					for (int k = 0; k < 30; k++) {
						/*Ordonnancement sol = */vnd.run(inst);
//						System.out.println(sol.eval());
//						totalTime += sol.getTime();
//						totalEval += sol.eval();
//					}
					
//					double eval = totalEval/30;
//					double bestScore = MyFileReader.bestSolution(n); // la meilleure solution connue de la nieme instance
//					double dev = (eval == 0 && bestScore == 0) ? 0 : 100 * (eval-bestScore)/bestScore; // la deviation par rapport a la meilleure solution connue
//
//					DecimalFormat df = new DecimalFormat("#.###");
//					
//					System.out.println(n + " " + eval + " " + df.format(dev) + "%" + " " + totalTime/30 + "ms");
					
//					double dev = sol.deviation(n);
//					DecimalFormat df = new DecimalFormat("#.###");
//					System.out.println(n + " " + sol.eval() + " " + df.format(dev) + "%" + " " + sol.getTime() + "ms");
//					evals[n] = sol.eval(); devs[n] = df.format(dev); times[n] = sol.getTime(); // enregistrement des donnees
					System.out.println("Done");
			}
//				
//				MyFileWriter.writeData("data/results/vnd/"+select+"_"+init+".dat", evals, devs, times);
				
			else {
				System.out.println("choisir un numero dans [1..." + (lesInstances.size()) + "]");
			}
				
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_VND.jar <filename> <nbTaches> [first,best] [rnd,edd,mdd] [1,2] {1 ... n}");
		}
			
	}
	
	private static Heuristic getHeuristic(String arg) {
		switch (arg) {
		case "rnd":
			return new RND();
		case "edd":
			return new EDD();
		case "mdd":
			return new MDD();
		default:
			System.out.println("Erreur solution initiale incorrecte, choisir [rnd,edd,mdd]");
			System.exit(0);
			break;
		}
		return null;
	}

	private static Strategie getStrategie(String arg) {
		switch (arg) {
		case "first":
			return Strategie.FIRST_IMPROVEMENT;
		case "best":
			return Strategie.BEST_IMPROVEMENT;
		default:
			System.out.println("Erreur strategie incorrect, choisir [first,best]");
			System.exit(0);
			break;
		}
		return null;
	}

}

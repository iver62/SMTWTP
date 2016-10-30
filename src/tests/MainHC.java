package tests;

import java.text.DecimalFormat;
import java.util.List;

import algorithms.EDD;
import algorithms.HillClimbing;
import algorithms.MDD;
import algorithms.RND;
import models.Ordonnancement;
import neighborhood.Insert;
import neighborhood.Interchange;
import neighborhood.Neighborhood;
import neighborhood.Swap;
import utils.MyFileReader;
import utils.MyFileWriter;

public class MainHC {

	public static void main(String[] args) {
		
		if (args.length == 5) { // on verife qu'il y a bien 6 parametres
			String filename = args[0]; // nom du fichier
			int nbTaches = Integer.parseInt(args[1]); // nombre de taches par instance
			String select = args[2]; // first ou best
			String voisinage = args[3]; // insert, swap ou exchange
			String init = args[4]; // choix de la solution initiale
			
			if (checkParameters(select, voisinage, init)) { // on verifie si les parametres sont corrects
				
				System.out.println("Running...");
				List<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
				
				HillClimbing hc;
				Neighborhood ngb;
				
				int size = lesOrdonnancements.size();
				int[] evals = new int[size]; String[] devs = new String[size]; long[] times = new long[size];
			
				for (int n = 0; n < lesOrdonnancements.size(); n++) { // pour chaque instance

					if (init.equals("rnd")) { // si la solution initiale est RND
						RND rnd = new RND(lesOrdonnancements.get(n));
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert();
						}
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap();
						}
						else { // si on a choisi l'echange
							ngb = new Interchange();
						}
						hc = new HillClimbing(select, ngb, rnd);				
					}
					
					else if (init.equals("edd")) { // si la solution initiale est EDD
						EDD edd = new EDD(lesOrdonnancements.get(n));
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert();
						}
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap();
						}
						else { // si on a choisi l'echange
							ngb = new Interchange();
						}
						hc = new HillClimbing(select, ngb,  edd);
					}
					
					else { // si la solution initiale est MDD
						MDD mdd = new MDD(lesOrdonnancements.get(n));
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert();
						}
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap();
						}
						else { // si on a choisi l'echange
							ngb = new Interchange();
						}
						hc = new HillClimbing(select, ngb,  mdd);
					}
					
//					long totalTime = 0;
//					double totalEval = 0;
					
//					for (int k = 0; k < 30; k++) {
						long deb = System.currentTimeMillis();
						Ordonnancement sol = hc.run();
						long time = System.currentTimeMillis() - deb;
//						System.out.println(sol.eval());
//						totalTime += time;
//						totalEval += sol.eval();
//					}
					
//					double eval = totalEval/30;
//					double bestScore = MyFileReader.bestSolution(n); // la meilleure solution connue de la nieme instance
//					double dev = (sol.eval() == 0 && bestScore == 0) ? 0 : 100 * (sol.eval()-bestScore)/bestScore; // la deviation par rapport a la meilleure solution connue

					DecimalFormat df = new DecimalFormat("#.###");
					
//					System.out.println(sol);
					System.out.println(n+1 + " " + sol.eval() + " " + df.format(sol.deviation(n)) + "%" + " " + time + "ms");
					System.out.println(sol);
					
//					double dev = sol.deviation(n);
//					DecimalFormat df = new DecimalFormat("#.###");
//					evals[n] = sol.eval(); devs[n] = df.format(dev); times[n] = time; // enregistrement des donnees
				
				}
				
				MyFileWriter.writeData("data/results/hc/"+select+"_"+voisinage+"_"+init+".dat", evals, devs, times);
				System.out.println("Done");

			}
			
			else {
				System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar <filename> <nbTaches> [first,best] [insert,swap,interchange] [rnd,edd,mdd]");
			}
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar <filename> <nbTaches> [first,best] [insert,swap,interchange] [rnd,edd,mdd]");
		}
	}

	private static boolean checkParameters(String select, String voisinage, String init) {
		return (select.equals("first") || select.equals("best")) && 
				(voisinage.equals("insert") || voisinage.equals("swap") || voisinage.equals("interchange")) && 
				(init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
	}

}

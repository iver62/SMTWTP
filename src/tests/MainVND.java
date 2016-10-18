package tests;

import java.util.ArrayList;
import java.util.List;

import algorithms.EDD;
import algorithms.MDD;
import algorithms.RND;
import algorithms.VND;
import models.Ordonnancement;
import neighborhood.Insert;
import neighborhood.Interchange;
import neighborhood.Neighborhood;
import neighborhood.Swap;
import utils.MyFileReader;

public class MainVND {
	
	public static void main(String[] args) {
		
		if (args.length == 6) { // on verifie qu'il y a bien 6 parametres
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			String select = args[2]; // first ou best
			String init = args[3]; // choix de la  solution initiale
			int v = Integer.parseInt(args[4]); // choix de l'ordre des voisinages
			int n = Integer.parseInt(args[5]); // le numero de l'ordonnancement
			
			if (checkParameters(select, init)) { // on verifie si les parametres sont corrects
				
				System.out.println("Running...");
				List<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
				
				VND vnd;
				
//				int size = lesOrdonnancements.size();
//				int[] evals = new int[size]; String[] devs = new String[size]; long[] times = new long[size];
				
//				for (int n = 0; n < lesOrdonnancements.size(); n++) { // pour chaque instance
				if (n <= lesOrdonnancements.size()) { // si on a choisi un ordonnancement valide
					
					Ordonnancement o = lesOrdonnancements.get(n); // l'ordonnancement choisi
					
					List<Neighborhood> voisinages = new ArrayList<Neighborhood>(); // la liste des voisinages
					voisinages.add(new Interchange());
					// le premier ensemble de voisinage
					if (v == 1) {
						voisinages.add(new Swap());
						voisinages.add(new Insert());
					}
					// le deuxieme ensemble de voisinage
					else if (v == 2) {
						voisinages.add(new Insert());
						voisinages.add(new Swap());
					}
					
					if (init.equals("rnd")) { // si la solution initiale est RND
						RND rnd = new RND(o);
						vnd = new VND(voisinages, select, rnd, n);
						
					}
					
					else if (init.equals("edd")) { // si la solution initiale est EDD
						EDD edd = new EDD(o);
						vnd = new VND(voisinages, select, edd, n);
					}
					
					else { // si la solution initiale est MDD
						MDD mdd = new MDD(o);
						vnd = new VND(voisinages, select, mdd, n);
					}
					
//					long totalTime = 0;
//					double totalEval = 0;
//					for (int k = 0; k < 30; k++) {
						/*Ordonnancement sol = */vnd.run();
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
					System.out.println("choisir un numero dans [0..." + (lesOrdonnancements.size()-1) + "]");
				}
				
			}
			
			else {
				System.out.println("Usage : \n\tjava -jar SMTWTP_VND.jar <filename> <nbTaches> [first,best] [rnd,edd,mdd] [1,2] {1 ... n}");
			}
			
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_VND.jar <filename> <nbTaches> [first,best] [rnd,edd,mdd] [1,2] {1 ... n}");
		}
		
	}
	
	private static boolean checkParameters(String select, String init) {
		return (select.equals("first") || select.equals("best")) && 
				(init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
	}

}

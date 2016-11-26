package tests;

import java.util.ArrayList;
import java.util.List;

import algorithms.EDD;
import algorithms.Heuristic;
import algorithms.MDD;
import algorithms.RND;
import algorithms.TabuSearch;
import models.Instance;
import neighborhood.Insert;
import neighborhood.Interchange;
import neighborhood.Neighborhood;
import neighborhood.Swap;
import utils.MyFileReader;
import utils.Strategie;

public class MainTS {

	public static void main(String[] args) {
		
		if (args.length == 6) { // on verifie qu'il y a bien 6 parametres
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			Strategie str = getStrategie(args[2]); // first ou best
			Heuristic h = getHeuristic(args[3]); // choix de la  solution initiale
			int v = Integer.parseInt(args[4]); // choix de l'ordre des voisinages
			int n = Integer.parseInt(args[5]); // le numero de l'ordonnancement
			
			System.out.println("Running...");
			ArrayList<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
				
//				int size = lesOrdonnancements.size();
//				int[] evals = new int[size]; String[] devs = new String[size]; long[] times = new long[size];
				
//				for (int n = 0; n < lesOrdonnancements.size(); n++) { // pour chaque instance
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
				
				TabuSearch ts = new TabuSearch(voisinages, str, h, n);
					
//					String name = select+"_"+init+"_"+n;
//					
//					File file = new File("data/results/ts/"+name+".dat");
//					file.delete(); // on ecrase le fichier precedent
					
//					System.out.println("Resulat " + init + " " + select + voisinages.toString() + " " + n);
					
				/*Ordonnancement sol = */ts.run(inst);
				System.out.println("Done");
//					double dev = sol.deviation(n);
//					DecimalFormat df = new DecimalFormat("#.###");
//					evals[n] = sol.eval(); devs[n] = df.format(dev); times[n] = sol.getTime(); // enregistrement des donnees
//					System.out.println(n + " " + sol.eval() + " " + df.format(dev) + "%" + " " + sol.getTime() + "ms");
					
			}
				
//				MyFileWriter.writeData("data/results/ts/"+select+"_"+init+".dat", evals, devs, times);
//				System.out.println("Done");
				
			else {
				System.out.println("choisir un numero dans [0..." + (lesInstances.size()) + "]");
			}
			
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_TS.jar <filename> <nbTaches> [first,best] [rnd,edd,mdd] [1,2] {1 ... n}");
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

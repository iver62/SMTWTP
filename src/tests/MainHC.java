package tests;

import java.text.DecimalFormat;
import java.util.List;

import algorithms.EDD;
import algorithms.Heuristic;
import algorithms.HillClimbing;
import algorithms.MDD;
import algorithms.RND;
import models.Instance;
import neighborhood.Insert;
import neighborhood.Interchange;
import neighborhood.Neighborhood;
import neighborhood.Swap;
import utils.MyFileReader;
import utils.MyFileWriter;
import utils.Strategie;

public class MainHC {

	public static int nbRuns = 30;
//	public static String filename = "data/wt100.txt";
//	public static int nbTaches = 100;
//	public static Strategie str = Strategie.BEST_IMPROVEMENT;
//	public static Neighborhood ngb = new Insert();
//	public static Heuristic h = new MDD();
			
	public static void main(String[] args) {
		
		if (args.length == 5) { // on verifie qu'il y a bien 5 parametres

			String filename = args[0]; // nom du fichier
			int nbTaches = Integer.parseInt(args[1]); // nombre de taches par instance			
			Strategie str = getStrategie(args[2]); // strategie de selection du meilleur voisin			
			Neighborhood ngb = getVoisinage(args[3]); // choix du voisinage	
			Heuristic h = getHeuristic(args[4]); // choix de la solution initiale

			System.out.println("Running...");
			List<Instance> lesInstances = MyFileReader.load(filename, nbTaches);	
			HillClimbing hc = new HillClimbing(str, ngb, h);

			int size = lesInstances.size();
			String[] devs = new String[size]; long[] times = new long[size]; // les tableaux ou les donnees seront enregistrees

			for (int n = 0; n < lesInstances.size(); n++) { // pour chaque instance

				long totalTime = 0;
				double totalEval = 0;

				Instance sol = null;
				Instance inst = lesInstances.get(n);
				
				for (int k = 0; k < nbRuns; k++) {
					long deb = System.currentTimeMillis();
					sol = hc.run(inst);
					long time = System.currentTimeMillis() - deb;
					totalTime += time;
					totalEval += sol.eval();
				}

				double eval = totalEval/nbRuns;
				long time = totalTime/nbRuns;
				double bestScore = MyFileReader.bestSolution(n); // la meilleure solution connue de la nieme instance
				double dev = (eval == 0 && bestScore == 0) ? 0 : 100 * (eval-bestScore)/bestScore; // la deviation par rapport a la meilleure solution connue

				DecimalFormat df = new DecimalFormat("#.###");

				System.out.println(n+1 + " " + eval + " " + df.format(dev) + "%" + " " + time + "ms");

				devs[n] = df.format(dev); times[n] = time; // enregistrement de la deviation et du temps de calcul

			}
				
			MyFileWriter.writeData("data/results/hc/"+str+"_"+ngb+"_"+h+".dat", devs, times);
			System.out.println("Done");

		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar <filename> <nbTaches> [first,best] [insert,swap,interchange] [rnd,edd,mdd]");
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

	private static Neighborhood getVoisinage(String arg) {
		switch (arg) {
		case "insert":
			return new Insert();
		case "swap":
			return new Swap();
		case "interchange":
			return new Interchange();
		default:
			System.out.println("Erreur voisinage incorrect, choisir [insert,swap,interchange]");
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

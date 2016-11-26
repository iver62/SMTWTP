package tests;

import java.text.DecimalFormat;
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
import utils.MyFileWriter;
import utils.Strategie;

public class MainTS {

	public static int nbRuns = 30;
	
	public static void main(String[] args) {
		
		if (args.length == 5) { // on verifie qu'il y a bien 5 parametres
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			Strategie str = getStrategie(args[2]); // first ou best
			Heuristic h = getHeuristic(args[3]); // choix de la  solution initiale
			int v = Integer.parseInt(args[4]); // choix de l'ordre des voisinages
//			int n = Integer.parseInt(args[5]); // le numero de l'instance
			
			ArrayList<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
			int size = lesInstances.size();
			String[] devs = new String[size]; long[] times = new long[size];

//			if (n <= lesInstances.size() && n > 0) { // si on a choisi une instance valide
			
			List<Neighborhood> voisinages = generateNeighborhoods(v);
			TabuSearch ts = new TabuSearch(voisinages, str, h);
			
			System.out.println("Running...");
			
			for (int n = 0; n < size; n++) { // pour chaque instance
				Instance inst = lesInstances.get(n); // l'instance courante
				double totalEval = 0;
				long totalTime = 0;
				
				for (int k = 0; k < nbRuns; k++) {
					Instance opt = ts.run(inst);
					System.out.println("k = " + k + " " + opt.eval() + " " + opt.getTime());
					totalEval += opt.eval();
					totalTime += opt.getTime();
				}
				
				double eval = totalEval/nbRuns;
				long time = totalTime/nbRuns;
				double bestScore = MyFileReader.bestSolution(n); // la meilleure solution connue de la nieme instance
				double dev = (eval == 0 && bestScore == 0) ? 0 : 100 * (eval-bestScore)/bestScore; // la deviation par rapport a la meilleure solution connue

				DecimalFormat df = new DecimalFormat("#.###");
				devs[n] = df.format(dev); times[n] = time; // enregistrement de la deviation et du temps de calcul moyens
				
				System.out.println(n+1 + " " + eval + " " + df.format(dev) + "%" + " " + time + "ms");
			
			}
				
			MyFileWriter.writeData("data/results/ts/"+str+"_"+h+".dat", devs, times);
			System.out.println("Done");
			
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_TS.jar <filename> <nbTaches> {first,best} {rnd,edd,mdd} {1,2}");
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
			System.out.println("Erreur solution initiale incorrecte, choisir {rnd,edd,mdd}");
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
			System.out.println("Erreur strategie incorrecte, choisir {first,best}");
			System.exit(0);
			break;
		}
		return null;
	}
	
	private static List<Neighborhood> generateNeighborhoods(int x) {
		List<Neighborhood> voisinages = new ArrayList<Neighborhood>();
		voisinages.add(new Interchange());
		switch (x) {
		case 1:
			voisinages.add(new Swap());
			voisinages.add(new Insert());
			break;
		case 2:
			voisinages.add(new Insert());
			voisinages.add(new Swap());
			break;
		default:
			System.out.println("Erreur choix des voisinages, choisir {1,2}");
			System.exit(0);
			break;
		}
		return voisinages;
	}

}

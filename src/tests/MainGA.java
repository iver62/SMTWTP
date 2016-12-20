package tests;

import java.text.DecimalFormat;
import java.util.List;

import algorithms.EDD;
import algorithms.GeneticAlgo;
import algorithms.Heuristic;
import algorithms.MDD;
import algorithms.RND;
import models.Instance;
import utils.MyFileReader;
import utils.MyFileWriter;

public class MainGA {

	public static void main(String[] args) {
		
		if (args.length == 5) {
		
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			int popNumber = Integer.parseInt(args[2]); // taille de la population
			int nbGen = Integer.parseInt(args[3]); // nombre de generations
			Heuristic h = getHeuristic(args[4]); // choix de la  solution initiale
			
			List<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
			int size = lesInstances.size();
			String[] devs = new String[size]; long[] times = new long[size]; // les tableaux ou les donnees seront enregistrees
			
			System.out.println("Running...");
			for (int n = 0; n < size; n++) {		
				GeneticAlgo ga = new GeneticAlgo(popNumber, nbGen);
				Instance inst = lesInstances.get(n); 
				long d = System.currentTimeMillis();
				Instance sol = ga.run(inst, h);
				long time = System.currentTimeMillis() - d;
				
				DecimalFormat df = new DecimalFormat("#.###");
				devs[n] = df.format(sol.deviation(n)); times[n] = time; // enregistrement de la deviation et du temps de calcul moyens
				System.out.println(n+1 + " " + sol.eval() + " " + sol.deviation(n));
			}
			
			System.out.println("Done");
			MyFileWriter.writeData("data/results/ga/"+popNumber+"_"+nbGen+"_"+h+".dat", devs, times);
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_GA.jar <filename> <nbTaches> <population> <generations> <heuristique>");
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

}

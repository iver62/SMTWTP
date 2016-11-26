package tests;

import java.util.List;

import algorithms.EDD;
import algorithms.GeneticAlgo;
import algorithms.Heuristic;
import algorithms.MDD;
import algorithms.RND;
import models.Instance;
import utils.MyFileReader;

public class MainGA {

	public static void main(String[] args) {
		
		if (args.length == 5) {
		
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			int popNumber = Integer.parseInt(args[2]); // taille de la population
			int nbGen = Integer.parseInt(args[3]); // nombre de generations
			Heuristic h = getHeuristic(args[4]); // choix de la  solution initiale
			int n = Integer.parseInt(args[5]); // le numero de l'instance
			
			List<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
			int size = lesInstances.size();
			
			if (n > 0 && n <= size) { // on verifie que l'instance choisie est valide 
						
				GeneticAlgo ga = new GeneticAlgo(popNumber, nbGen);
				Instance inst = lesInstances.get(n-1); 
				ga.initPopulation(inst, h);
				System.out.println(ga.toString());
				Instance sol = ga.run();
				
				System.out.println(sol.eval() + " " + sol.deviation(n-1));
				
			}
			
			else {
				System.out.println("Choisir une instance dans [1..." + size + "]");
			}
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_GA_ONE.jar <filename> <nbTaches> <population> <generations> <nieme instance>");
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

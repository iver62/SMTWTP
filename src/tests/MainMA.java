package tests;

import java.util.List;

import algorithms.EDD;
import algorithms.Heuristic;
import algorithms.MDD;
import algorithms.MemeticAlgo;
import algorithms.RND;
import models.Instance;
import utils.MyFileReader;

public class MainMA {

	public static void main(String[] args) {
		
		if (args.length == 8) { // on verifie qu'il y a 8 parametres
			
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			int popNumber = Integer.parseInt(args[2]); // taille de la population
			int nbGen = Integer.parseInt(args[3]); // nombre de generations
			Heuristic h = getHeuristic(args[4]); // choix de la  solution initiale
			double corate = Double.parseDouble(args[5]); // le taux de crossover
			double pmut = Double.parseDouble(args[6]); // la probabilite de mutation
			int n = Integer.parseInt(args[7]); // le numero de l'instance
			
			List<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
			int size = lesInstances.size();
			
			if (n > 0 && n <= size) { // on verifie que l'instance choisie est valide 
			
				MemeticAlgo ma = new MemeticAlgo(popNumber, nbGen, corate, pmut);
				Instance i = lesInstances.get(n-1); 
				ma.initPopulation(i, h);
				System.out.println(ma.toString());
				Instance sol = ma.run();
				System.out.println(sol.eval() + " " + sol.deviation(n-1));
			
			}
			
			else {
				System.out.println("Choisir une instance dans [1..." + size + "]");
			}
			
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_MA_ONE.jar <filename> <nbTaches> <population> <generations> <heuristique> <crossover rate> <probabilite mutation> <nieme instance>");
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

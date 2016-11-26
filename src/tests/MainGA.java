package tests;

import java.util.List;

import algorithms.GeneticAlgo;
import algorithms.MDD;
import models.Instance;
import utils.MyFileReader;

public class MainGA {

	public static void main(String[] args) {
		
		if (args.length == 5) {
		
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			int popNumber = Integer.parseInt(args[2]); 
			int nbGen = Integer.parseInt(args[3]);
			int n = Integer.parseInt(args[4]); // le numero de l'instance
			
			List<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
			int size = lesInstances.size();
			
			if (n > 0 && n <= size) {
						
				GeneticAlgo ga = new GeneticAlgo(popNumber, nbGen);
				Instance inst = lesInstances.get(n-1); 
				ga.initPopulation(inst, new MDD());
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

}

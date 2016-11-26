package tests;

import java.util.List;

import algorithms.GeneticAlgo;
import algorithms.MDD;
import models.Instance;
import utils.MyFileReader;

public class MainGA {
	
	public static String filename = "data/wt100.txt";
	public static int nbTaches = 100;
	public static int n = 1;

	public static void main(String[] args) {
		List<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
		GeneticAlgo ga = new GeneticAlgo(20, 2000);
		Instance i = lesInstances.get(n-1); 
		ga.initPopulation(i, new MDD());
		System.out.println(ga.toString());
		Instance sol = ga.run();
		System.out.println(sol.eval() + " " + sol.deviation(n-1));
	}

}

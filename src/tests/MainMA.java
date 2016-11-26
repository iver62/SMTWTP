package tests;

import java.util.List;

import algorithms.MDD;
import algorithms.MemeticAlgo;
import models.Instance;
import utils.MyFileReader;

public class MainMA {
	
	public static String filename = "data/wt100.txt";
	public static int nbTaches = 100;
	public static int n = 1;

	public static void main(String[] args) {
		List<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
		MemeticAlgo ma = new MemeticAlgo(20, 100, 0.8, 0.3);
		Instance i = lesInstances.get(n-1); 
		ma.initPopulation(i, new MDD());
		System.out.println(ma.toString());
		Instance sol = ma.run();
		System.out.println(sol.eval() + " " + sol.deviation(n-1));
	}

}

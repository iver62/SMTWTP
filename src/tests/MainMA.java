package tests;

import java.util.List;

import algorithms.MDD;
import algorithms.MemeticAlgo;
import models.Ordonnancement;
import utils.MyFileReader;

public class MainMA {
	
	public static String filename = "data/wt100.txt";
	public static int nbTaches = 100;
	public static int n = 86;

	public static void main(String[] args) {
		List<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
		MemeticAlgo ma = new MemeticAlgo(20, 100, 0.8, 0.3);
		Ordonnancement o = lesOrdonnancements.get(n-1); 
		ma.initPopulation(o, new MDD(o));
		System.out.println(ma.toString());
		Ordonnancement sol = ma.run();
		System.out.println(sol.eval() + " " + sol.deviation(n-1));
	}

}

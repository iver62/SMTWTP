package tests;

import java.util.List;

import algorithms.GeneticAlgo;
import algorithms.MDD;
import models.Ordonnancement;
import utils.MyFileReader;

public class MainGA {
	
	public static String filename = "data/wt100.txt";
	public static int nbTaches = 100;
	public static int n = 1;

	public static void main(String[] args) {
		List<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
		GeneticAlgo ga = new GeneticAlgo();
//		System.out.println(lesOrdonnancements.get(n-1));
		Ordonnancement o = lesOrdonnancements.get(n-1); 
		ga.initPopulation(o, new MDD(o), 20);
		System.out.println(ga.getPop());
		

	}

}

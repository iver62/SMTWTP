package tests;

import java.util.ArrayList;

import algorithms.RND;
import models.Instance;
import utils.MyFileReader;
import utils.MyFileWriter;

public class MainRND {

	public static int nbRuns = 30;
//	public static String filename = "data/wt100.txt";
//	public static int nbTaches = 100;
	
	public static void main(String[] args) {
		
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			
			ArrayList<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
			
			int[] dataEval = new int[lesInstances.size()]; // tableau ou seront stockees les evaluations de chaque instance
			
			for (int i = 0; i < lesInstances.size(); i++) {
				
				int total = 0;
				RND rnd = new RND();
				for (int k = 0; k < nbRuns; k++) {
					Instance res = rnd.run(lesInstances.get(i));
					int e = res.eval();
					total += e;
				}
				dataEval[i] = total/nbRuns;
				System.out.println(i+1 + " " + dataEval[i]);
				
			}
			MyFileWriter.writeData("data/results/rnd.dat", dataEval);
		}
		
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_RND.jar <filename> <nombre de taches>");
		}

	}

}

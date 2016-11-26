package tests;

import java.util.ArrayList;

import algorithms.MDD;
import models.Instance;
import utils.MyFileReader;
import utils.MyFileWriter;

public class MainMDD {

	public static void main(String[] args) {
		
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			
			ArrayList<Instance> lesInstances = MyFileReader.load(filename, nbTaches);
			
			int[] dataEval = new int[lesInstances.size()]; // tableau ou seront stockees les evaluations de chaque instance

			for (int i = 0; i < lesInstances.size(); i++) {
				MDD mdd = new MDD();
				Instance res = mdd.run(lesInstances.get(i));
				dataEval[i] = res.eval();
				System.out.println(i+1 + " " + res.eval());
			}
			MyFileWriter.writeData("data/results/mdd.dat", dataEval); // enregistrement de l'evaluation de chaque instance
		}
		
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_MDD.jar <filename> <nombre de taches>");
		}
		
	}

}

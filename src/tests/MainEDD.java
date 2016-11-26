package tests;

import java.util.List;

import algorithms.EDD;
import models.Instance;
import utils.MyFileReader;
import utils.MyFileWriter;

public class MainEDD {
	
	public static void main(String[] args) {
		
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);

			List<Instance> lesInstances = MyFileReader.load(filename, nbTaches); // tableau ou seront stockees les evaluations de chaque instance
			
			int[] dataEval = new int[lesInstances.size()];
			
			for (int i = 0; i < lesInstances.size(); i++) {
				EDD edd = new EDD();
				Instance res = edd.run(lesInstances.get(i));
				dataEval[i] = res.eval();
				System.out.println(i+1 + " " + res.eval());
			}
			MyFileWriter.writeData("data/results/edd.dat", dataEval); // enregistrement de l'evaluation de chaque instance
		}
		
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_EDD.jar <filename> <nombre de taches>");
		}
		
	}

}

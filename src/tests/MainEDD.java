package tests;

import java.util.ArrayList;

import algorithmes.EDD;
import models.Ordonnancement;
import utils.MyFileReader;
import utils.MyFileWriter;

public class MainEDD {
	
	public static void main(String[] args) {
		
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);

			ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
			
			int[] dataEval = new int[lesOrdonnancements.size()];
			
			for (int i = 0; i < lesOrdonnancements.size(); i++) {
				EDD edd = new EDD(lesOrdonnancements.get(i));
				Ordonnancement res = edd.run();
				dataEval[i] = res.eval();
				System.out.println(i + " " + res.eval());
			}
			MyFileWriter.writeData("data/results/edd.dat", dataEval);
		}
		
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_EDD.jar <filename> <nombre de taches>");
		}
		
	}

}

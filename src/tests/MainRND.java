package tests;

import java.util.ArrayList;

import algorithms.RND;
import models.Ordonnancement;
import utils.MyFileReader;
import utils.MyFileWriter;

public class MainRND {

	public static void main(String[] args) {
		
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			
			ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
			
			int[] dataEval = new int[lesOrdonnancements.size()];
			
			for (int i = 0; i < lesOrdonnancements.size(); i++) {
				RND rnd = new RND(lesOrdonnancements.get(i));
				Ordonnancement res = rnd.run();
				dataEval[i] = res.eval();
				System.out.println(i+1 + " " + res.eval());
			}
			MyFileWriter.writeData("data/results/rnd.dat", dataEval);
		}
		
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_RND.jar <filename> <nombre de taches>");
		}

	}

}

package tests;

import java.util.ArrayList;

import algorithmes.EDD;
import models.Ordonnancement;
import utils.MyFileReader;

public class MainEDD {
	
	public static void main(String[] args) {
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);

			ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
			EDD edd;
			for (int i = 0; i < lesOrdonnancements.size(); i++) {
				edd = new EDD(lesOrdonnancements.get(i));
				Ordonnancement res = edd.run();
				System.out.println(i + " " + res.eval());
			}
		}
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_EDD.jar <filename> <nombre de taches>");
		}
	}

}

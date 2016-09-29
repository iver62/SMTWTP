package tests;

import java.util.ArrayList;

import Models.Ordonnancement;
import Utils.MyFileReader;
import algorithmes.MDD;

public class MainMDD {

	public static void main(String[] args) {
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			
			ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
			MDD mdd;
			for (int i = 0; i < lesOrdonnancements.size(); i++) {
				mdd = new MDD(lesOrdonnancements.get(i));
				Ordonnancement res = mdd.run();
				System.out.println(i + " " + res.eval());
			}
		}
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_MDD.jar <filename> <nombre de taches>");
		}
	}

}

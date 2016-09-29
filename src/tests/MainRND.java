package tests;

import java.util.ArrayList;

import Models.Ordonnancement;
import Utils.MyFileReader;
import algorithmes.RND;

public class MainRND {

	public static void main(String[] args) {
		if (args.length == 2) {
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			
			ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
			RND rnd;
			for (int i = 0; i < lesOrdonnancements.size(); i++) {
				rnd = new RND(lesOrdonnancements.get(i));
				Ordonnancement res = rnd.run();
				System.out.println(i + " " + res.eval());
			}
		}
		else {
			System.out.println("Usage : \n\t java -jar SMTWTP_RND.jar <filename> <nombre de taches>");
		}

	}

}

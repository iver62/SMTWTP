package tests;

import java.util.ArrayList;

import algorithmes.EDD;
import algorithmes.HillClimbing;
import algorithmes.MDD;
import algorithmes.RND;
import models.Interchange;
import models.Insert;
import models.Neighborhood;
import models.Ordonnancement;
import models.Swap;
import utils.MyFileReader;

public class MainHillClimbing {

	public static void main(String[] args) {
		
		if (args.length == 5) { // on verife qu'il y a bien 6 parametres
			String filename = args[0]; // nom du fichier
			int nbTaches = Integer.parseInt(args[1]); // nombre de taches par instance
			String select = args[2]; // first ou best
			String voisinage = args[3]; // insert, swap ou exchange
			String init = args[4]; // choix de la solution initiale
			
			if (checkParameters(select, voisinage, init)) { // on verifie si les parametres sont corrects
				
				ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
				
				System.out.println("Resulats " + select + " " + voisinage + " " + init);
				HillClimbing hc;
				Neighborhood ngb = null;
					
				if (init.equals("rnd")) { // si la solution initiale est RND
					for (int n = 0; n < lesOrdonnancements.size(); n++) {
						RND rnd = new RND(lesOrdonnancements.get(n));
							
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert();
						}
						
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap();
						}
							
						else if (voisinage.equals("exchange")) { // si on a choisi l'echange
							ngb = new Interchange();
						}
							
						hc = new HillClimbing(select, ngb, rnd, n);
						hc.run();
					}
				}
					
				else if (init.equals("edd")) { // si la solution initiale est EDD
					for (int n = 0; n < lesOrdonnancements.size(); n++) {
						EDD edd = new EDD(lesOrdonnancements.get(n));
						
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert();
						}
							
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap();
						}
						
						else if (voisinage.equals("exchange")) { // si on a choisi l'echange
							ngb = new Interchange();
						}
							
						hc = new HillClimbing(select, ngb,  edd, n);
						hc.run();
					}
				}
					
				else { // si la solution initiale est MDD
					for (int n = 0; n < lesOrdonnancements.size(); n++) {
						MDD mdd = new MDD(lesOrdonnancements.get(n));
							
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert();
						}
							
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap();
						}
							
						else if (voisinage.equals("exchange")) { // si on a choisi l'echange
							ngb = new Interchange();
						}
							
						hc = new HillClimbing(select, ngb,  mdd, n);
						hc.run();
					}
				}

			}
			
			else {
				System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar <filename> <nbTaches> [first,best] [insert,swap,exchange] [rnd,edd,mdd]");
			}
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar <filename> <nbTaches> [first,best] [insert,swap,exchange] [rnd,edd,mdd]");
		}
	}

	private static boolean checkParameters(String select, String voisinage, String init) {
		return (select.equals("first") || select.equals("best")) && 
				(voisinage.equals("insert") || voisinage.equals("swap") || voisinage.equals("exchange")) && 
				(init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
	}

}

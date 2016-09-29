package tests;

import java.util.ArrayList;

import Models.Exchange;
import Models.Insert;
import Models.Neighborhood;
import Models.Ordonnancement;
import Models.Swap;
import Utils.MyFileReader;
import algorithmes.EDD;
import algorithmes.HillClimbing;
import algorithmes.MDD;
import algorithmes.RND;

public class MainHillClimbing {

	public static void main(String[] args) {
		ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load("data/wt100.txt", 100);
		
		if (args.length == 6) { // on verife qu'il y a bien 6 parametres
			String select = args[1];
			String voisinage = args[3];
			String init = args[5];
			
			if (checkParameters(select, voisinage, init)) { // on verifie si les parametres sont corrects
				System.out.println("Resulats " + select + " " + voisinage + " " + init);
				
				Ordonnancement initSol;
				HillClimbing hc;
				Neighborhood ngb = null;
				
				if (init.equals("rnd")) { // si la solution initiale est RND
					RND rnd;
					for (int n = 0; n < lesOrdonnancements.size(); n++) { // pour chaque ordonnancement
						rnd = new RND(lesOrdonnancements.get(n));
						initSol = rnd.run(); // la solution initiale					
						
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert(initSol);
						}
						
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap(initSol);
						}
						
						else if (voisinage.equals("exchange")) { // si on a choisi l'echange
							ngb = new Exchange(initSol);
						}
						
						hc = new HillClimbing(select, ngb, n);
						hc.run();
					}
				}
				
				else if (init.equals("edd")) { // si la solution initiale est EDD
					EDD edd;
					for (int n = 0; n < lesOrdonnancements.size(); n++) {
						edd = new EDD(lesOrdonnancements.get(n));
						initSol = edd.run();
						
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert(initSol);
						}
						
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap(initSol);
						}
						
						else if (voisinage.equals("exchange")) { // si on a choisi l'echange
							ngb = new Exchange(initSol);
						}
						
						hc = new HillClimbing(select, ngb, n);
						hc.run();
					}
				}
				
				else if (init.equals("mdd")) { // si la solution initiale est MDD
					MDD mdd;
					for (int n = 0; n < lesOrdonnancements.size(); n++) {
						mdd = new MDD(lesOrdonnancements.get(n));
						initSol = mdd.run();
						
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert(initSol);
						}
						
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap(initSol);
						}
						
						else if (voisinage.equals("exchange")) { // si on a choisi l'echange
							ngb = new Exchange(initSol);
						}
						
						hc = new HillClimbing(select, ngb, n);
						hc.run();
					}
				}

			}
			
			else {
				System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar -select [first,best] -voisinage [insert,swap,exchange] -init [rnd,edd,mdd]");
			}
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar -select [first,best] -voisinage [insert,swap,exchange] -init [rnd,edd,mdd]");
		}
	}

	private static boolean checkParameters(String select, String voisinage, String init) {
		return (select.equals("first") || select.equals("best")) && 
				(voisinage.equals("insert") || voisinage.equals("swap") || voisinage.equals("exchange")) && 
				(init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
	}

}

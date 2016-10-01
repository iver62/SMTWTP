package tests;

import java.util.ArrayList;
import java.util.List;

import algorithmes.EDD;
import algorithmes.MDD;
import algorithmes.RND;
import algorithmes.VND;
import models.Exchange;
import models.Insert;
import models.Neighborhood;
import models.Ordonnancement;
import models.Swap;
import utils.MyFileReader;

public class MainVND {
	
	private static boolean checkParameters(String select, String init) {
		return (select.equals("first") || select.equals("best")) && 
				(init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
	}
	
	public static void main(String[] args) {
		
		if (args.length == 6) { // on verifie qu'il y a bien 6 parametres
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			String select = args[2]; // first ou best
			String init = args[3]; // choix de la  solution initiale
			int v = Integer.parseInt(args[4]); // choix du voisinage
			int n = Integer.parseInt(args[5]); // le numero de l'ordonnancement
			
			if (checkParameters(select, init)) { // on verifie si les parametres sont corrects
				
				ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
				
				if (n <= lesOrdonnancements.size()) { // si on a choisi un ordonnancement valide
					
					Ordonnancement o = lesOrdonnancements.get(n-1); // l'ordonnancement choisi
					Ordonnancement initSol;
					
					List<Neighborhood> voisinages = new ArrayList<Neighborhood>(); // la liste des voisinages
					voisinages.add(new Exchange());
					// le premier voisinage
					if (v == 0) {
						voisinages.add(new Swap());
						voisinages.add(new Insert());
					}
					// le deuxieme voisinage
					else {
						voisinages.add(new Insert());
						voisinages.add(new Swap());
					}
					
					System.out.println("Resulat " + init + " " + select + " voisinages " + voisinages.toString() + " instance " + n);
					
					if (init.equals("rnd")) { // si la solution initiale est RND
						RND rnd = new RND(o);
						initSol = rnd.run();
						VND vnd = new VND(voisinages, select, initSol, n-1);
						vnd.run();
					}
					
					else if (init.equals("edd")) { // si la solution initiale est EDD
						EDD edd = new EDD(o);
						initSol = edd.run(); // solution initiale
						VND vnd = new VND(voisinages, select, initSol, n-1);
						vnd.run();
					}
					
					else if (init.equals("mdd")) { // si la solution initiale est MDD
						MDD mdd = new MDD(o);
						initSol = mdd.run(); // solution initiale
						VND vnd = new VND(voisinages, select, initSol, n-1);
						vnd.run();
					}
					
				}
				
				else {
					System.out.println("choisir un numero dans [0..." + (lesOrdonnancements.size()-1) + "]");
				}
				
			}
			
			else {
				System.out.println("Usage : \n\tjava -jar SMTWTP_VND.jar <filename> <nbTaches> [first,best] [rnd,edd,mdd] [0,1] {0 ... n-1}");
			}
			
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_VND.jar <filename> <nbTaches> [first,best] [rnd,edd,mdd] [0,1] {0 ... n-1}");
		}

	}

}

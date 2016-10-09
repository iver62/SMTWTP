package tests;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import algorithmes.EDD;
import algorithmes.MDD;
import algorithmes.RND;
import algorithmes.TabuSearch;
import models.Insert;
import models.Interchange;
import models.Neighborhood;
import models.Ordonnancement;
import models.Swap;
import utils.MyFileReader;

public class MainTS {

	public static void main(String[] args) {
		if (args.length == 6) { // on verifie qu'il y a bien 6 parametres
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			String select = args[2]; // first ou best
			String init = args[3]; // choix de la  solution initiale
			int v = Integer.parseInt(args[4]); // choix de l'ordre des voisinages
			int n = Integer.parseInt(args[5]); // le numero de l'ordonnancement
			
			if (checkParameters(select, init)) { // on verifie si les parametres sont corrects
				
				ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
				
				if (n <= lesOrdonnancements.size()) { // si on a choisi un ordonnancement valide
					
					Ordonnancement o = lesOrdonnancements.get(n-1); // l'ordonnancement choisi
					
					List<Neighborhood> voisinages = new ArrayList<Neighborhood>(); // la liste des voisinages
					voisinages.add(new Interchange());
					// le premier ensemble de voisinage
					if (v == 1) {
						voisinages.add(new Swap());
						voisinages.add(new Insert());
					}
					// le deuxieme ensemble de voisinage
					else if (v == 2) {
						voisinages.add(new Insert());
						voisinages.add(new Swap());
					}
					
					String name = select+"_"+init+"_"+n;
					
					File file = new File("data/results/ts/"+name+".dat");
					file.delete(); // on ecrase le fichier precedent
					
					System.out.println("Resulat " + init + " " + select + voisinages.toString() + " " + n);
					
					if (init.equals("rnd")) { // si la solution initiale est RND
						RND rnd = new RND(o);
						TabuSearch ts = new TabuSearch(voisinages, select, rnd, n-1);
						ts.run();
					}
					
					else if (init.equals("edd")) { // si la solution initiale est EDD
						EDD edd = new EDD(o);
						TabuSearch ts = new TabuSearch(voisinages, select, edd, n-1);
						ts.run();
					}
					
					else if (init.equals("mdd")) { // si la solution initiale est MDD
						MDD mdd = new MDD(o);
						TabuSearch ts = new TabuSearch(voisinages, select, mdd, n-1);
						ts.run();
					}
					
				}
				
				else {
					System.out.println("choisir un numero dans [0..." + (lesOrdonnancements.size()-1) + "]");
				}
				
			}
			
			else {
				System.out.println("Usage : \n\tjava -jar SMTWTP_TS.jar <filename> <nbTaches> [first,best] [rnd,edd,mdd] [1,2] {1 ... n}");
			}
			
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_TS.jar <filename> <nbTaches> [first,best] [rnd,edd,mdd] [1,2] {1 ... n}");
		}

	}
	
	private static boolean checkParameters(String select, String init) {
		return (select.equals("first") || select.equals("best")) && 
				(init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
	}

}

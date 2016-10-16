package tests;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import algorithms.EDD;
import algorithms.MDD;
import algorithms.RND;
import algorithms.TabuSearch;
import models.Ordonnancement;
import neighborhood.Insert;
import neighborhood.Interchange;
import neighborhood.Neighborhood;
import neighborhood.Swap;
import utils.MyFileReader;
import utils.MyFileWriter;

public class MainTS {

	public static void main(String[] args) {
		if (args.length == 5) { // on verifie qu'il y a bien 6 parametres
			String filename = args[0];
			int nbTaches = Integer.parseInt(args[1]);
			String select = args[2]; // first ou best
			String init = args[3]; // choix de la  solution initiale
			int v = Integer.parseInt(args[4]); // choix de l'ordre des voisinages
//			int n = Integer.parseInt(args[5]); // le numero de l'ordonnancement
			
			if (checkParameters(select, init)) { // on verifie si les parametres sont corrects
				
				System.out.println("Running...");
				ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
				
				TabuSearch ts;
				
				int size = lesOrdonnancements.size();
				int[] evals = new int[size]; String[] devs = new String[size]; long[] times = new long[size];
				
				for (int n = 0; n < lesOrdonnancements.size(); n++) { // pour chaque instance
//				if (n <= lesOrdonnancements.size()) { // si on a choisi un ordonnancement valide
					
					Ordonnancement o = lesOrdonnancements.get(n); // l'ordonnancement choisi
					
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
					
//					String name = select+"_"+init+"_"+n;
//					
//					File file = new File("data/results/ts/"+name+".dat");
//					file.delete(); // on ecrase le fichier precedent
					
//					System.out.println("Resulat " + init + " " + select + voisinages.toString() + " " + n);
					
					if (init.equals("rnd")) { // si la solution initiale est RND
						RND rnd = new RND(o);
						ts = new TabuSearch(voisinages, select, rnd, n-1);
					}
					
					else if (init.equals("edd")) { // si la solution initiale est EDD
						EDD edd = new EDD(o);
						ts = new TabuSearch(voisinages, select, edd, n-1);
					}
					
					else { // si la solution initiale est MDD
						MDD mdd = new MDD(o);
						ts = new TabuSearch(voisinages, select, mdd, n-1);
					}
					
					Ordonnancement sol = ts.run();
					double dev = sol.deviation(n);
					DecimalFormat df = new DecimalFormat("#.###");
					evals[n] = sol.eval(); devs[n] = df.format(dev); times[n] = sol.getTime(); // enregistrement des donnees
					System.out.println(n + " " + sol.eval() + " " + df.format(dev) + "%" + " " + sol.getTime() + "ms");
					
				}
				
				MyFileWriter.writeData("data/results/ts/"+select+"_"+init+".dat", evals, devs, times);
				System.out.println("Done");
				
//				else {
//					System.out.println("choisir un numero dans [0..." + (lesOrdonnancements.size()-1) + "]");
//				}
				
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

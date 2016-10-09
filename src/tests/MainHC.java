package tests;

import java.io.File;
import java.util.List;

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

public class MainHC {

	public static void main(String[] args) {
		
		if (args.length == 5) { // on verife qu'il y a bien 6 parametres
			String filename = args[0]; // nom du fichier
			int nbTaches = Integer.parseInt(args[1]); // nombre de taches par instance
			String select = args[2]; // first ou best
			String voisinage = args[3]; // insert, swap ou exchange
			String init = args[4]; // choix de la solution initiale
			
			if (checkParameters(select, voisinage, init)) { // on verifie si les parametres sont corrects
				
				List<Ordonnancement> lesOrdonnancements = MyFileReader.load(filename, nbTaches);
				
				System.out.println("Resulats " + select + " " + voisinage + " " + init);
				HillClimbing hc;
				Neighborhood ngb = null;
				String name = select+"_"+voisinage+"_"+init;
				
				File file = new File("data/results/hc/"+name+".dat");
				file.delete(); // on ecrase le fichier precedent
					
				if (init.equals("rnd")) { // si la solution initiale est RND
					for (int n = 0; n < lesOrdonnancements.size(); n++) { // pour chaque instance
						RND rnd = new RND(lesOrdonnancements.get(n));
							
						if (voisinage.equals("insert")) { // si on a choisi l'insertion
							ngb = new Insert();
						}
						
						else if (voisinage.equals("swap")) { // si on a choisi la permutation
							ngb = new Swap();
						}
							
						else { // si on a choisi l'echange
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
						
						else { // si on a choisi l'echange
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
							
						else { // si on a choisi l'echange
							ngb = new Interchange();
						}
							
						hc = new HillClimbing(select, ngb,  mdd, n);
						hc.run();
					}
				}
				
//				try {
//					BufferedWriter bw = new BufferedWriter(new FileWriter("data/results/hc/"+name+".plt")); // creation d'un script plt
//					bw.write("set term png\n");
//					bw.write("set output '"+name+".png'\n");
//					bw.write("set title 'deviation et temps d execution de chaque ordonnancement'\n");
//					bw.write("set xrange [0:"+lesOrdonnancements.size()+"]\n");
//					bw.write("set yrange [0:300]\n");
//					bw.write("set ylabel 'deviation/time'\n");
//					bw.write("set xlabel 'instances'\n");
//					bw.write("plot '"+name+".txt' using 1:2 with lines title 'deviation', '"+name+".txt' using 1:3 with lines title 'time'\n");
//					bw.close();
//				}
//				catch (IOException e) {
//					e.printStackTrace();
//				}

			}
			
			else {
				System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar <filename> <nbTaches> [first,best] [insert,swap,interchange] [rnd,edd,mdd]");
			}
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_HillClimbing.jar <filename> <nbTaches> [first,best] [insert,swap,interchange] [rnd,edd,mdd]");
		}
	}

	private static boolean checkParameters(String select, String voisinage, String init) {
		return (select.equals("first") || select.equals("best")) && 
				(voisinage.equals("insert") || voisinage.equals("swap") || voisinage.equals("interchange")) && 
				(init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
	}

}

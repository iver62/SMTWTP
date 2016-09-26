package algorithmes;

import java.util.ArrayList;

import Models.Neighborhood;
import Models.Ordonnancement;
import Utils.MyFileReader;

public class VND {
	
	static int t = 100000;

	private static boolean checkParameters(String param0, String param1, String param2, String select, String init) {
		return (param0.equals("-select")) && (param1.equals("-init")) && (param2.equals("-choose")) && (select.equals("first") || select.equals("best")) && (init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
	}
	
	public static void main(String[] args) {
		ArrayList<Ordonnancement> lesOrdonnancements = MyFileReader.load("data/wt100.txt", 100);
		
		if (args.length == 6) { // on verifie qu'il y a bien 6 parametres
			String p0 = args[0]; // -select
			String select = args[1]; // first ou best
			String p1 = args[2]; // -init
			String init = args[3]; // solution initiale
			String p2 = args[4]; //-choose
			int n = Integer.parseInt(args[5]); // le numero de l'ordonnancement
			
			if (checkParameters(p0, p1, p2, select, init)) { // on verifie si les parametres sont corrects
				System.out.println("Resulats " + init);
				
				if (n < lesOrdonnancements.size()) { // si on a choisi un ordonnancement valide
					Ordonnancement o = lesOrdonnancements.get(n); // l'ordonnancement choisi
//					int sol = o.eval();
//					Neighborhood ngbh = new Neighborhood(o);
//					String[] voisinages = {ngbh.exchange(select), ngbh.swap(select), ngbh.insert(select)};
//					String[] voisinages = {"exchange", "swap", "insert"};
					
					if (init.equals("rnd")) { // si la solution initiale est RND
						Ordonnancement rnd_ord = RND.run(o); // solution initiale
						Neighborhood ngbh = new Neighborhood(rnd_ord);
						int sol = rnd_ord.eval();
						int i = 0, k = 0;
						while (k < t) {
							if (i == 0) {
								if (ngbh.exchange(select) < sol) {
									sol = ngbh.exchange(select);
//									ngbh = new Neighborhood(sol);
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							else if (i == 1) {
								if (ngbh.swap(select) < sol) {
									sol = ngbh.swap(select);
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							else if (i == 2) {
								if (ngbh.insert(select) < sol) {
									sol = ngbh.insert(select);
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							k++;
						}
						
						double best = MyFileReader.bestSolution(n);
						double dev = 100 * (sol-best) / best; // la deviation par rapport a la meilleure solution connue
						System.out.println(rnd_ord.eval() + " " + sol + " " + dev);
					}
					
					else if (init.equals("mdd")) {
						Ordonnancement mdd_ord = MDD.run(o); // solution initiale
						Neighborhood ngbh = new Neighborhood(mdd_ord);
						int sol = mdd_ord.eval();
						int i = 0, k = 0;
						while (k < t) {
							if (i == 0) {
								if (ngbh.exchange(select) < sol) {
									sol = ngbh.exchange(select);
									i = 0;
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							else if (i == 1) {
								if (ngbh.swap(select) < sol) {
									sol = ngbh.swap(select);
									i = 0;
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							else if (i == 2) {
								if (ngbh.insert(select) < sol) {
									sol = ngbh.insert(select);
									i = 0;
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							k++;
						}
						
						double best = MyFileReader.bestSolution(n);
						double dev = 100 * (sol-best) / best; // la deviation par rapport a la meilleure solution connue
						System.out.println(mdd_ord.eval() + " " + sol + " " + dev);
					}
					
				}
				
				else {
					System.out.println("choisir un numero dans [0..." + (lesOrdonnancements.size()-1) + "]");
				}
				
			}
			
			else {
				System.out.println("Usage : \n\tjava -jar SMTWTP_VND.jar -select [first,best] -init [rnd,edd,mdd] -choose {0 ... n-1}");
			}
			
		}
		
		else {
			System.out.println("Usage : \n\tjava -jar SMTWTP_VND.jar -select [first,best] -init [rnd,edd,mdd] -choose {0 ... n-1}");
		}

	}

}

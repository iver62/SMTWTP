package algorithmes;

import java.util.ArrayList;

import Models.Exchange;
import Models.Insert;
import Models.Neighborhood;
import Models.Ordonnancement;
import Models.Swap;
import Utils.MyFileReader;

public class VND {
	
	static int t = 10000;

	private static boolean checkParameters(String param0, String param1, String param2, String select, String init) {
		return (param0.equals("-select")) && 
				(param1.equals("-init")) && 
				(param2.equals("-choose")) && 
				(select.equals("first") || select.equals("best")) && 
				(init.equals("rnd") || init.equals("edd") || init.equals("mdd"));
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
				
				if (n < lesOrdonnancements.size()) { // si on a choisi un ordonnancement valide
					System.out.println("Resulat " + init + " " + select + " instance " + n);
					Ordonnancement o = lesOrdonnancements.get(n); // l'ordonnancement choisi
//					String[] voisinages = {ngbh.exchange(select), ngbh.swap(select), ngbh.insert(select)};
//					String[] voisinages = {"exchange", "swap", "insert"};
					Neighborhood ngb;
					
					if (init.equals("rnd")) { // si la solution initiale est RND
						RND rnd = new RND(o);
						Ordonnancement sol = rnd.run(); // solution initiale
						System.out.println(sol.eval() + " t=0ms");
						int i = 0, k = 0;
						long debut = System.currentTimeMillis();
						
						while (k < t) {
														
							if (i == 0) { // voisinage exchange
								ngb = new Exchange(sol);
								if (ngb.run(select).eval() < sol.eval()) { // s'il y a un meilleur voisin dans le voisinage exchange
									sol = ngb.run(select); // nouvel optimum local dans le voisinage exchange
									System.out.println(sol.eval() + " t=" + (System.currentTimeMillis()-debut) +"ms");
								}
								else {
									i = (++i < 3) ? i : 0; // on passe au voisinage suivant
								}
							}
							
							else if (i == 1) { // voisinage swap
								ngb = new Swap(sol);
								if (ngb.run(select).eval() < sol.eval()) {
									sol = ngb.run(select);
									System.out.println(sol.eval() + " t=" + (System.currentTimeMillis()-debut) +"ms");
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							
							else if (i == 2) { // voisinage insert
								ngb = new Insert(sol);
								if (ngb.run(select).eval() < sol.eval()) {
									sol = ngb.run(select);
									System.out.println(sol.eval() + " t=" + (System.currentTimeMillis()-debut) +"ms");
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							
							k++;
						}
						
						double best = MyFileReader.bestSolution(n);
						double dev = 100 * (sol.eval()-best) / best; // la deviation par rapport a la meilleure solution connue
						System.out.println("deviation=" + dev);
					}
					
					else if (init.equals("mdd")) { // si la solution initiale est MDD
						MDD mdd = new MDD(o);
						Ordonnancement sol = mdd.run(); // solution initiale
						System.out.println(sol.eval() + " t=0ms");
						int i = 0, k = 0;
						long debut = System.currentTimeMillis();
						
						while (k < t) {
							
							if (i == 0) { // voisinage exchange
								ngb = new Exchange(sol);
								if (ngb.run(select).eval() < sol.eval()) {
									sol = ngb.run(select);
									System.out.println(sol.eval() + " t=" + (System.currentTimeMillis()-debut) +"ms");
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							
							else if (i == 1) { // voisinage swap
								ngb = new Swap(sol);
								if (ngb.run(select).eval() < sol.eval()) {
									sol = ngb.run(select);
									System.out.println(sol.eval() + " t=" + (System.currentTimeMillis()-debut) +"ms");
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							
							else if (i == 2) { // voisinage insert
								ngb = new Insert(sol);
								if (ngb.run(select).eval() < sol.eval()) {
									sol = ngb.run(select);
									System.out.println(sol.eval() + " t=" + (System.currentTimeMillis()-debut) +"ms");
								}
								else {
									i = (++i < 3) ? i : 0;
								}
							}
							
							k++;
						}
						
						double best = MyFileReader.bestSolution(n);
						double dev = 100 * (sol.eval()-best) / best; // la deviation par rapport a la meilleure solution connue
						System.out.println("deviation=" + dev);
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

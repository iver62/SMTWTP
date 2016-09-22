package Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Models.Ordonnancement;
import Models.Tache;

public class MyFileReader {
	
	public static ArrayList<Ordonnancement> load(String filename, int nbtaches) {	
		ArrayList<Ordonnancement> lesOrdonnancements = new ArrayList<Ordonnancement>();
		int tabP[] = new int[nbtaches];
		int tabW[] = new int[nbtaches];
		int tabD[] = new int[nbtaches];
		try {
			File f = new File (filename);
			FileReader fr = new FileReader(f);
			Scanner sc = new Scanner(fr);
			while (sc.hasNextInt()) {
				int nbP = 0, nbW = 0, nbD = 0;
				while (nbP < nbtaches) {	
					int i = sc.nextInt();
					tabP[nbP++] = i;
				}
				while (nbW < nbtaches) {	
					int i = sc.nextInt();
					tabW[nbW++] = i;
				}
				while (nbD < nbtaches) {	
					int i = sc.nextInt();
					tabD[nbD++] = i;
				}
				ArrayList<Tache> lesTaches = new ArrayList<Tache>();
				for (int i = 0; i < nbtaches; i++) {
					Tache j = new Tache(tabP[i], tabW[i], tabD[i]);
					lesTaches.add(j);
				}
				lesOrdonnancements.add(new Ordonnancement(lesTaches));
			}
			sc.close();
		}
		catch (FileNotFoundException exception) {
		    System.out.println("Le fichier n'a pas ete trouve");
		}
		return lesOrdonnancements;
	}
	
	public static int bestSolution(int n) {
		int res = 0;
		try {
			File f = new File ("data/wtbest100b.txt");
			FileReader fr = new FileReader(f);
			Scanner sc = new Scanner(fr);
			for (int i = 0; i <= n; i++) {
				res = sc.nextInt();
			}
			sc.close();
		}
		catch (FileNotFoundException exception) {
		    System.out.println("Le fichier n'a pas ete trouve");
		}
		return res;
	}
	
}

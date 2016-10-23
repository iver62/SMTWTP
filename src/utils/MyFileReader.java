package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import models.Ordonnancement;
import models.Tache;

public class MyFileReader {
	
	public static ArrayList<Ordonnancement> load(String filename, int nbTasks) {	
		ArrayList<Ordonnancement> lesOrdonnancements = new ArrayList<Ordonnancement>();
		int tabP[] = new int[nbTasks];
		int tabW[] = new int[nbTasks];
		int tabD[] = new int[nbTasks];
		
		try {
			File f = new File (filename);
			FileReader fr = new FileReader(f);
			Scanner sc = new Scanner(fr);
			
			while (sc.hasNextInt()) {
				int nbP = 0, nbW = 0, nbD = 0, taskNumber = 1;
				while (nbP < nbTasks) {	
					int i = sc.nextInt();
					tabP[nbP++] = i;
				}
				while (nbW < nbTasks) {	
					int i = sc.nextInt();
					tabW[nbW++] = i;
				}
				while (nbD < nbTasks) {	
					int i = sc.nextInt();
					tabD[nbD++] = i;
				}
				ArrayList<Tache> lesTaches = new ArrayList<Tache>();
				for (int i = 0; i < nbTasks; i++) {
					Tache t = new Tache(taskNumber++, tabP[i], tabW[i], tabD[i]);
					lesTaches.add(t);
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

package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import models.Ordonnancement;
import models.Tache;

public class GeneticAlgo {
	
	private List<Ordonnancement> pop;
//	private int n; // taille de la population
	
	public GeneticAlgo() {
		pop = new ArrayList<Ordonnancement>();
//		this.ord = ord;
//		this.n = n;
	}

	public void initPopulation(Ordonnancement ord, Heuristic h, int n) {
		for (int i = 0; i < n; i++) {
//			System.out.println(pop);
//			Ordonnancement o = h.run();
//			Collections.shuffle(ord.getLesTaches());
			pop.add(h.run());
//			o = ord;
		}
	}
	
	public void selection() {
		
	}
	
	public void reproduction() {
		
	}
	
	public void replace() {
		
	}
	
	public Ordonnancement crossover(Ordonnancement p1, Ordonnancement p2) {
		Random r = new Random();
		int debSeq = r.nextInt(p1.size()/2);
		int finSeq = debSeq + p1.size()/2;
		List<Tache> lesTaches = new ArrayList<Tache>();
		for (int i = 0; i < p1.size(); i++) {
			if (i >= debSeq && i < finSeq) {
				lesTaches.add(p1.getLesTaches().get(i));
			}
			else {
				lesTaches.add(p2.getLesTaches().get(i));
			}
		}
		return new Ordonnancement(lesTaches);
	}
	
	public void mutation() {
		
	}
	
	public void run() {
		
	}

	public List<Ordonnancement> getPop() {
		return pop;
	}
	
}

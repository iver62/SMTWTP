package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import models.Ordonnancement;
import models.Tache;

public class GeneticAlgo {
	
	private List<Ordonnancement> pop;
	private int populationNumber;
	private int nbGenerations;
	private Ordonnancement p1, p2; // les parents
	private Ordonnancement child; // l'enfant
	private List<Ordonnancement> offsprings;
	private List<Ordonnancement> mutations;
	private int[] ranks;
	private double[] proba; 
	
	public GeneticAlgo(int populationNumber, int nbGenerations) {
		pop = new ArrayList<Ordonnancement>(populationNumber);
//		this.ord = ord;
		this.populationNumber = populationNumber;
		this.nbGenerations = nbGenerations;
		ranks = new int[populationNumber];
		proba = new double[populationNumber];
	}

	public void initPopulation(Ordonnancement ord, Heuristic h) {
		Ordonnancement o = h.run();
		Random r = new Random();
		for (int n = 0; n < populationNumber; n++) {
			Ordonnancement c = new Ordonnancement(new ArrayList<>(o.getLesTaches()));
			int i = r.nextInt(o.size()), j = r.nextInt(o.size()); 
			c.swap(i, j);
//			System.out.println("permute " + i + " et " + j);
//			System.out.println(c);
			pop.add(c);
		}
//		Collections.sort(pop);
	}
	
	public void selection() {
		Collections.sort(pop);
		System.out.println(toString());
		rank();
		double r1 = Math.random(), r2 = Math.random();
		int i = 0, j = 0;
		while (proba[i] < r1) {
			i++;
		}
		while (proba[j] < r2) {
			j++;
		}
		p1 = pop.get(i);
		p2 = pop.get(j);
		if (p1.equals(p2)) {
			j = (j+1 == populationNumber) ? j-- : j++;
		}
//		System.out.println(p1 + "" + i + " " + p1.eval() + " " + r1);
//		System.out.println(p2 + "" + j + " " + p2.eval() + " " + r2);
	}
	
	private void rank() {
		int r = populationNumber;
		ranks[0] = r;
		int sumRanks = r;
		for (int i = 1; i < populationNumber; i++) {
			--r;
			if (pop.get(i).eval() == pop.get(i-1).eval()) {
				ranks[i] = ranks[i-1];
			}
			else {
				ranks[i] = r;
			}
			sumRanks += ranks[i];
		}
//		System.out.println(sumRanks);
//		printTable(ranks);
		
		double sumProba = 0;
		for (int i = 0; i < populationNumber; i++) {
			proba[i] = (double)ranks[i] / sumRanks + sumProba;
			sumProba = proba[i];
		}
//		printTable(proba);
	}
	
	public void replace() {
		if (child.eval() < pop.get(populationNumber-1).eval()) {
			pop.remove(populationNumber-1);
			pop.add(child);
		}
	}
	
	public void mutation(Ordonnancement p) {
		mutations = new ArrayList<Ordonnancement>();
		Ordonnancement mutated = new Ordonnancement(p.getLesTaches());
		Random r = new Random();
		int i = 0, j = 0;
		while (i == j) {
			i = r.nextInt(p.size());
			j = r.nextInt(p.size());
		}
		mutated.swap(i, j);
		mutations.add(mutated);
	}
	
	public void crossover(Ordonnancement p1, Ordonnancement p2) {
		offsprings = new ArrayList<Ordonnancement>();
		Random r = new Random();
		int debSeq = r.nextInt(p1.size()/2);
		int finSeq = debSeq + p1.size()/2;
		List<Tache> lesTaches = new ArrayList<Tache>();
		
		List<Tache> p1Tasks = new ArrayList<Tache>();
		for (int i = debSeq; i < finSeq; i++) {
			Tache t = p1.getLesTaches().get(i);
			p1Tasks.add(t);
		}
		
		List<Tache> p2Tasks = new ArrayList<Tache>();
		for (int i = 0; i < p2.size(); i++) {
			Tache t = p2.getLesTaches().get(i);
			if (!p1Tasks.contains(t)) {
				p2Tasks.add(t);
			}
		}
//		Collections.shuffle(p2Tasks);
		
		int cpt1 = 0, cpt2 = 0;
		for (int i = 0; i < p1.size(); i++) {
			if (i >= debSeq && i < finSeq) {
				lesTaches.add(i, p1Tasks.get(cpt1++));
			}
			else {
				lesTaches.add(i, p2Tasks.get(cpt2++));
			}
		}
		
//		int i = 0;
//		Tache t;
//		for (i = 0; i < debSeq; i++) {
//			t = p2Tasks.get(i);
//			lesTaches.add(i, t);
//		}
//		for (i = finSeq; i < p1.size(); i++) {
//			t = p2Tasks.get(i);
//			lesTaches.add(i, t);
//		}
		offsprings.add(new Ordonnancement(lesTaches));
	}
	
	public Ordonnancement run() {
		for (int k = 0; k < nbGenerations; k++) {
			System.out.println(k);
			for (int i = 0; i < populationNumber/2; i++) {
				selection(); // selection de 2 parents
				crossover(p1, p2); // reproduction
			}
			for (int i = 0; i < populationNumber; i++) {
				selection(); // selection de 2 parents
				mutation(p1); // reproduction
			}
			replace();
		}
		System.out.println(pop.get(0).toString());
		return pop.get(0);
	}

//	private void printTable(int[] tab) {
//		for (int i = 0; i < tab.length; i++) {
//			System.out.print(tab[i] + " ");
//		}
//		System.out.println();
//	}
//	
//	private void printTable(double[] tab) {
//		for (int i = 0; i < tab.length; i++) {
//			System.out.print(tab[i] + " ");
//		}
//		System.out.println();
//	}
	
	@Override
	public String toString() {
		String str = new String();
		for (int i = 0; i < populationNumber; i++) {
			str += pop.get(i).eval() + " ";
		}
		return str;
	}
	
}

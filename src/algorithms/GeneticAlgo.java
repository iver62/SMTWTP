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
//	private List<Ordonnancement> offsprings; // liste des enfants
//	private List<Ordonnancement> mutations; // liste des mutants
	private int[] ranks;
	private double[] proba; 
	
	public GeneticAlgo(int populationNumber, int nbGenerations) {
		pop = new ArrayList<Ordonnancement>(populationNumber);
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
			pop.add(c);
		}
		
	}
	
	/**
	 * Selectionne 2 parents tel que plus un ordonnancement est meilleur, plus il a de chances d'etre selectionne
	 * @return un tableau contenant 2 ordonnancements
	 */
	public Ordonnancement[] select() {
		rank();
		int i, j;
		
		do {
			i = 0; j = 0;
			double r1 = Math.random(), r2 = Math.random();
			while (proba[i] < r1) {
				i++;
			}
			while (proba[j] < r2) {
				j++;
			}
		} while (i == j);

		return new Ordonnancement[] {pop.get(i), pop.get(j)};
	}
	
	private void rank() {
		int r = populationNumber;
		ranks[0] = r;
		int sumRanks = r;
		for (int i = 1; i < populationNumber; i++) {
			r--;
			if (pop.get(i).eval() == pop.get(i-1).eval()) {
				ranks[i] = ranks[i-1];
			}
			else {
				ranks[i] = r;
			}
			sumRanks += ranks[i];
		}
		
		double sumProba = 0;
		for (int i = 0; i < populationNumber; i++) {
			proba[i] = (double)ranks[i] / sumRanks + sumProba;
			sumProba = proba[i];
		}
		
	}
	
	public void replace(List<Ordonnancement> offsprings, List<Ordonnancement> mutations) {
		
		for (Ordonnancement off : offsprings) {
			Ordonnancement worst = pop.get(populationNumber-1); 
			if (off.eval() < worst.eval()) {
				pop.remove(worst);
				pop.add(off);
				Collections.sort(pop);
			}
		}
		
		for (Ordonnancement mut : mutations) {
			Ordonnancement worst = pop.get(populationNumber-1); 
			if (mut.eval() < worst.eval()) {
				pop.remove(worst);
				pop.add(mut);
				Collections.sort(pop);
			}
		}
		
	}
	
	public Ordonnancement mutation(Ordonnancement p) {
		Ordonnancement mutated = new Ordonnancement(new ArrayList<>(p.getLesTaches()));
		Random r = new Random();
		int i = 0, j = 0;
		while (i == j) {
			i = r.nextInt(p.size());
			j = r.nextInt(p.size());
		}
		mutated.swap(i, j);
		return mutated;
	}
	
	public Ordonnancement[] crossover(Ordonnancement p1, Ordonnancement p2) {
		List<Integer> alea = randomPos(p1.size()); // liste de positions (entre 0 et nombreDeTaches) choisies aleatoirement
		
		List<Integer> remainingPos1 = new ArrayList<Integer>(); // les positions des taches manquantes du 1er parent
		List<Integer> remainingPos2 = new ArrayList<Integer>(); // les positions des taches manquantes du 2eme parent
		
		List<Tache> remainingJobs1 = new ArrayList<Tache>(); // les taches manquantes du 1er parent
		List<Tache> remainingJobs2 = new ArrayList<Tache>(); // les taches manquantes du 2eme parent
		
		List<Tache> l1 = new ArrayList<Tache>(p1.size()); // la liste des taches du 1er enfant
		List<Tache> l2 = new ArrayList<Tache>(p1.size()); // la liste des taches du 2eme enfant
		
		for (int i = 0; i < p1.size(); i++) { // liste des taches manquantes du 1er parent
			if (!alea.contains(i)) {
				remainingJobs1.add(p1.get(i));
			}
		}
		
		for (int i = 0; i < p2.size(); i++) { // positions des taches manquantes du 1er parent dans le 2eme parent
			if (remainingJobs1.contains(p2.get(i))) {
				remainingPos1.add(i);
			}
		}
		
		for (int i = 0; i < p2.size(); i++) { // liste des taches manquantes du 2eme parent
			if (!alea.contains(i)) {
				remainingJobs2.add(p2.get(i));
			}
		}
		
		for (int i = 0; i < p1.size(); i++) { // positions des taches manquantes du 2eme parent dans le 1er parent
			if (remainingJobs2.contains(p2.get(i))) {
				remainingPos2.add(i);
			}
		}
				
		int cpt1 = 0, cpt2 = 0;
		for (int i = 0; i < p1.size(); i++) {
			if (alea.contains(i)) {
				l1.add(p1.get(i));
				l2.add(p2.get(i));
			}
			else {
				l1.add(p2.get(remainingPos1.get(cpt1++)));
				l2.add(p2.get(remainingPos2.get(cpt2++)));
			}
		}

		return new Ordonnancement[] {new Ordonnancement(l1), new Ordonnancement(l2)};
//		o1 = new Ordonnancement(l1);
//		System.out.println(o1 + " " + o1.eval());
//		o2 = new Ordonnancement(l2);
//		System.out.println(o2 + " " + o2.eval());
		
	}
	
	/**
	 * Lance l'algorithme
	 * @return le meilleur ordonnancement trouve
	 */
	public Ordonnancement run() {
		List<Ordonnancement> offsprings = new ArrayList<Ordonnancement>(); // liste des enfants
		List<Ordonnancement> mutations = new ArrayList<Ordonnancement>(); // liste des mutants
		for (int k = 0; k < nbGenerations; k++) { // pour chaque generation
			offsprings.clear(); // reinitialisation de la liste des offsprings
			mutations.clear(); // reinitialisation de la liste des mutants
			System.out.println("generation " + k);
			Collections.sort(pop); // tri de la population dans l'ordre croissant, les meilleurs elements sont en debut de liste
			System.out.println("population triee " + toString());
			for (int i = 0; i < populationNumber/2; i++) { // a chaque tour de boucle creation de 2 offsprings
				Ordonnancement[] parents = select(); // selection de 2 parents
				Ordonnancement[] children = crossover(parents[0], parents[1]); // reproduction		
				offsprings.add(children[0]);
				offsprings.add(children[1]);
			}
			for (int i = 0; i < populationNumber; i++) {
				Ordonnancement[] parents = select(); // selection de 1 parent
				Ordonnancement m = mutation(parents[0]); // mutation
				mutations.add(m);
			}
//			for (Ordonnancement o : offsprings) {
//				System.out.print(o.eval() + " ");
//			}
//			for (Ordonnancement o : mutations) {
//				System.out.print(o.eval() + " ");
//			}
			replace(offsprings, mutations); // parmi la population courante, les offsprings et les mutants on selectionne les meilleurs
		}
//		System.out.println(pop.get(0).toString());
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
	
	private List<Integer> randomPos(int n) {
		List<Integer> alea = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			alea.add(i);
		}
		Collections.shuffle(alea);
		return alea.subList(0, n/2);
	}
	
}

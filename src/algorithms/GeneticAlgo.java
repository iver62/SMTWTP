package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Instance;
import models.Tache;

public class GeneticAlgo extends EvolutionaryAlgorithm {
	
	public GeneticAlgo(int populationNumber, int nbGenerations) {
		super(populationNumber, nbGenerations);
	}
	
	public void replace(List<Instance> offsprings, List<Instance> mutations) {
		
		for (Instance off : offsprings) {
			Instance worst = pop.get(populationNumber-1); 
			if (off.eval() < worst.eval()) {
				pop.remove(worst);
				pop.add(off);
				Collections.sort(pop);
			}
		}
		
		for (Instance mut : mutations) {
			Instance worst = pop.get(populationNumber-1); 
			if (mut.eval() < worst.eval()) {
				pop.remove(worst);
				pop.add(mut);
				Collections.sort(pop);
			}
		}
		
	}
	
	public Instance[] crossover(Instance p1, Instance p2) {
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

		return new Instance[] {new Instance(l1), new Instance(l2)};		
	}
	
	/**
	 * Lance l'algorithme
	 * @return le meilleur ordonnancement trouve
	 */
	public Instance run() {
		List<Instance> offsprings = new ArrayList<Instance>(); // liste des enfants
		List<Instance> mutations = new ArrayList<Instance>(); // liste des mutants
		
		for (int k = 0; k < nbGenerations; k++) { // pour chaque generation
			offsprings.clear(); // reinitialisation de la liste des offsprings
			mutations.clear(); // reinitialisation de la liste des mutants
			System.out.println("generation " + k);
			Collections.sort(pop); // tri de la population dans l'ordre croissant, les meilleurs elements sont en debut de liste
			System.out.println("population triee " + toString());
			
			for (int i = 0; i < populationNumber/2; i++) { // a chaque tour de boucle creation de 2 offsprings
				Instance[] parents = select(); // selection de 2 parents
				Instance[] children = crossover(parents[0], parents[1]); // reproduction		
				offsprings.add(children[0]);
				offsprings.add(children[1]);
			}
			
			for (int i = 0; i < populationNumber; i++) {
				Instance[] parents = select(); // selection de 1 parent
				Instance m = mutation(parents[0]); // mutation
				mutations.add(m);
			}

			replace(offsprings, mutations); // parmi la population courante, les offsprings et les mutants on selectionne les meilleurs
		}
		
		return pop.get(0);
	}
	
}

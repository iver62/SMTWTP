package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Ordonnancement;
import models.Tache;
import neighborhood.Insert;

public class MemeticAlgo extends EvolutionaryAlgorithm {
	
	private double coRate, pMut;

	public MemeticAlgo(int populationNumber, int nbGenerations, double coRate, double pMut) {
		super(populationNumber, nbGenerations);
		this.coRate = coRate;
		this.pMut = pMut;
	}
	
	public Ordonnancement crossover(Ordonnancement p1, Ordonnancement p2) {
		List<Integer> alea = randomPos(p1.size()); // liste de positions (entre 0 et nombreDeTaches) choisies aleatoirement
		List<Integer> remainingPos = new ArrayList<Integer>(); // les positions des taches manquantes du 1er parent		
		List<Tache> remainingJobs = new ArrayList<Tache>(); // les taches manquantes du 1er parent
		List<Tache> list = new ArrayList<Tache>(p1.size()); // la liste des taches du 1er enfant
		
		for (int i = 0; i < p1.size(); i++) { // liste des taches manquantes du 1er parent
			if (!alea.contains(i)) {
				remainingJobs.add(p1.get(i));
			}
		}
		
		for (int i = 0; i < p2.size(); i++) { // positions des taches manquantes du 1er parent dans le 2eme parent
			if (remainingJobs.contains(p2.get(i))) {
				remainingPos.add(i);
			}
		}
				
		int cpt = 0;
		for (int i = 0; i < p1.size(); i++) {
			if (alea.contains(i)) {
				list.add(p1.get(i));
			}
			else {
				list.add(p2.get(remainingPos.get(cpt++)));
			}
		}

		return new Ordonnancement(list);		
	}
	
	public void replace(List<Ordonnancement> offsprings) {
		for (Ordonnancement off : offsprings) {
			Ordonnancement worst = pop.get(populationNumber-1); 
			if (off.eval() < worst.eval()) {
				pop.remove(worst);
				pop.add(off);
				Collections.sort(pop);
			}
		}
	}
	
	public Ordonnancement run() {
		List<Ordonnancement> offsprings = new ArrayList<Ordonnancement>();
		for (int k = 0; k < nbGenerations; k++) { // pour chaque generation
			offsprings.clear(); // reinitialisation de la liste des offsprings
			System.out.println("generation " + k);
			Collections.sort(pop); // tri de la population dans l'ordre croissant, les meilleurs elements sont en debut de liste
			System.out.println("population triee " + toString());
			
			for (int i = 0; i < coRate*populationNumber; i++) { // a chaque tour de boucle creation de 1 offspring
				Ordonnancement[] parents = select(); // selection de 2 parents
				Ordonnancement child = crossover(parents[0], parents[1]); // reproduction
				if (Math.random() < pMut) {
					mutation(child);
				}
				HillClimbing hc = new HillClimbing("first", new Insert(), new MDD(child));
				offsprings.add(hc.run());
			}

			replace(offsprings); // parmi la population courante et les offsprings on selectionne les meilleurs
		}
		return pop.get(0);
	}
	
}

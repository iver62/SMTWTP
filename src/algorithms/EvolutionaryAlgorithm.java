package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import models.Instance;

public class EvolutionaryAlgorithm {
	
	protected List<Instance> pop; // la population est une liste d'instances 
	protected int populationNumber; // nombre d'instances dans la population
	protected int nbGenerations; // nombre de generations
	protected int[] ranks;
	protected double[] proba;
	
	public EvolutionaryAlgorithm(int populationNumber, int nbGenerations) {
		pop = new ArrayList<Instance>(populationNumber);
		this.populationNumber = populationNumber;
		this.nbGenerations = nbGenerations;
		ranks = new int[populationNumber];
		proba = new double[populationNumber];
	}
	
	/**
	 * Initialise la population. D'abord une instance est creee avec une heuristique, puis a partir de cette instance
	 * x nouveaux individus sont crees en permutant 2 taches.  
	 * @param inst l'instance a partir de laquelle la population est creee
	 * @param h l'heuristique a partir de laquelle la population est initialisee
	 */
	public void initPopulation(Instance inst, Heuristic h) {
		Instance p = h.run(inst);		
		Random r = new Random();
		for (int n = 0; n < populationNumber; n++) { // creation de x nouveaux individus
			Instance nw = new Instance(new ArrayList<>(p.getLesTaches()));
			int i = r.nextInt(p.size()), j = r.nextInt(p.size()); 
			nw.swap(i, j); // permutation de 2 taches au hasard
			pop.add(nw); // ajout de l'individu a la population
		}
	}
	
	/**
	 * Selectionne 2 parents tel que plus une instance est meilleure, plus elle a de chances d'etre selectionnee
	 * @return un tableau contenant 2 instances
	 */
	public Instance[] select() {
		rank();
		int i, j;
		
		do {
			i = 0; j = 0;
			double r1 = Math.random(), r2 = Math.random(); // choix de 2 parents au hasard
			while (proba[i] < r1) {
				i++;
			}
			while (proba[j] < r2) {
				j++;
			}
		} while (i == j); // il faut choisir 2 parents differents

		return new Instance[] {pop.get(i), pop.get(j)};
	}
	
	/**
	 * Construit le tableau des rangs et le tableau des probablilites associe.
	 * Plus une instance est bonne plus elle a de chances d'etre selectionnee.
	 */
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
	
	/**
	 * Cree une instance en permutant 2 taches du parent au hasard
	 * @param p le parent
	 * @return une instance "mutee"
	 */
	public Instance mutation(Instance p) {
		Instance mutated = new Instance(new ArrayList<>(p.getLesTaches()));
		Random r = new Random();
		int i = 0, j = 0;
		while (i == j) { // 2 positions differentes choisies au hasard
			i = r.nextInt(p.size());
			j = r.nextInt(p.size());
		}
		mutated.swap(i, j); // permutation des 2 taches
		return mutated;
	}
	
	protected List<Integer> randomPos(int n) {
		List<Integer> alea = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			alea.add(i);
		}
		Collections.shuffle(alea);
		return alea.subList(0, n/2);
	}
	
	@Override
	public String toString() {
		String str = new String();
		for (int i = 0; i < populationNumber; i++) {
			str += pop.get(i).eval() + " ";
		}
		return str;
	}

}

package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import models.Ordonnancement;

public class EvolutionaryAlgorithm {
	
	protected List<Ordonnancement> pop;
	protected int populationNumber;
	protected int nbGenerations;
	protected int[] ranks;
	protected double[] proba;
	
	public EvolutionaryAlgorithm(int populationNumber, int nbGenerations) {
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
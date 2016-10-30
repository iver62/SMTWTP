package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import models.Ordonnancement;
import models.Tache;
import neighborhood.Insert;

public class MemeticAlgo {
	
	private List<Ordonnancement> pop;
	private int populationNumber;
	private int nbGenerations;
	private double coRate, pMut;
	private int[] ranks;
	private double[] proba;

	public MemeticAlgo(int populationNumber, int nbGenerations, double coRate, double pMut) {
		pop = new ArrayList<Ordonnancement>(populationNumber);
		this.populationNumber = populationNumber;
		this.nbGenerations = nbGenerations;
		this.coRate = coRate;
		this.pMut = pMut;
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
	
	private List<Integer> randomPos(int n) {
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

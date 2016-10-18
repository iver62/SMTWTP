package models;
import java.util.List;

import utils.MyFileReader;

public class Ordonnancement {
	
	private List<Tache> lesTaches;
	private long time;

	public Ordonnancement(List<Tache> lesTaches) {
		this.lesTaches = lesTaches;
	}
	
	/**
	 * fonction d'evaluation de l'ordonnancement
	 * @return le resultat de l'evaluation
	 */
	public int eval() {
		int sum = 0;
		int c = 0;
		for (Tache t : lesTaches) {
			c += t.getP();
			sum += t.getW() * Math.max(c-t.getD(), 0);
		}
		return sum;
	}
	
	/**
	 * Calcule la deviation par rapport a la meilleure solution connue
	 * @param n la nieme instance
	 * @return un pourcentage
	 */
	public double deviation(int n) {
		double bestScore = MyFileReader.bestSolution(n); // la meilleure solution connue de la nieme instance
		double dev = (this.eval() == 0 && bestScore == 0) ? 0 : 100 * (this.eval()-bestScore)/bestScore; // la deviation par rapport a la meilleure solution connue
		return dev;
	}
	
	/**
	 * Insere la tache de la position i a la position j
	 * @param i : l'indice de la tache
	 * @param j : l'indice ou inserer la tache
	 */
	public void insert(int i, int j) {
		Tache t = lesTaches.get(i);
		lesTaches.remove(i);
		lesTaches.add(j, t);
	}
	
	/**
	 * Permute deux taches aux positions i et j
	 * @param i : l'indice de la 1ere tache
	 * @param j : l'indice de la 2eme tache
	 */
	public void swap(int i, int j) {
		Tache t1 = lesTaches.get(i);
		Tache t2 = lesTaches.get(j);
		lesTaches.remove(j);
		lesTaches.add(j, t1);
		lesTaches.remove(i);
		lesTaches.add(i, t2);
	}

	public List<Tache> getLesTaches() {
		return lesTaches;
	}
	
	public int size() {
		return lesTaches.size();
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String toString() {
		String str = new String("Ordonnancement [lesTaches= ");
		for (int i = 0; i < lesTaches.size(); i++) {
			str += "tache n°" + i + ":" + lesTaches.get(i).toString() + " ";
		}
		str += "]\n";
		return str;
	}
	
}

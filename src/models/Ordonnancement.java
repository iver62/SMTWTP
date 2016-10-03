package models;
import java.util.ArrayList;

public class Ordonnancement {
	
	private ArrayList<Tache> lesTaches;

	public Ordonnancement(ArrayList<Tache> lesTaches) {
		this.lesTaches = lesTaches;
	}
	
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
	 * Inter-echange deux taches aux positions i et j
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

	public ArrayList<Tache> getLesTaches() {
		return lesTaches;
	}
	
	public int getSize() {
		return lesTaches.size();
	}
	
	public String toString() {
		String str = new String("Ordonnancement [lesTaches= ");
		for (int i = 0; i < lesTaches.size(); i++) {
			str += "tache n°" + i + ":" + lesTaches.get(i).toString() + " ";
		}
		str += "]";
		return str;
	}
	
}

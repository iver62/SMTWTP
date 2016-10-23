package models;

public class Tache implements Comparable<Tache> {
	
	private int n;
	private int p;
	private int w;
	private int d;
	
		
	public Tache(int n, int p, int w, int d) {
		this.n = n;
		this.p = p;
		this.w = w;
		this.d = d;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	@Override
	public String toString() {
		return "Tache [n=" + n + ", p=" + p + ", w=" + w + ", d=" + d + "]";
	}
	
	public int compareTo(Tache t) {
		return d - t.getD();
	}

}

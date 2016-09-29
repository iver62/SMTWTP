package Models;

public abstract class Neighborhood {
	
	protected Ordonnancement o;
	protected int n;
	
	public Neighborhood(Ordonnancement o) {
		this.o = o;
		n = o.getSize();
	}
	
	public abstract Ordonnancement run(String select);

}

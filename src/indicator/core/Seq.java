package indicator.core;

//OK - so sequence has grown into something weird....
public interface Seq extends Iterable<Double> {
	public int size();
	public double add(double obj);
	public void set(int i,double obj);
	public double get(int i);
	public double get();
	public double[] toArray();	
	public int start(); 
	public int capacity(); 
	public void shift(long by);
    
	public int ex();
}

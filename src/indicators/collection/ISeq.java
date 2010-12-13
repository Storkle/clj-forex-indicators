package indicators.collection;

//OK - so sequence has grown into something weird....
public interface ISeq extends Iterable<Double>{ 
	public double add(double obj);
	public void set(int i,double obj);
	public void shift(long by);
	public int ex(); 
	
	public int size();
	public double get(int i);
	public double get();
	public double[] toArray();	
	public int start(); 
	public int capacity();  
}

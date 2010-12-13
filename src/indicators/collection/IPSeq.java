package indicators.collection;

public interface IPSeq {
	public int size();
	public double get(int i);
	public double get();
	public double[] toArray();	
	public int start(); 
	public int capacity(); 
}

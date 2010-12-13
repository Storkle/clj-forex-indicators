package indicators.collection;

//OK - so sequence has grown into something weird....
public interface ISeq extends Iterable<Double>,IPSeq{ 
	public double add(double obj);
	public void set(int i,double obj);
	public void shift(long by);
	public int ex();
}

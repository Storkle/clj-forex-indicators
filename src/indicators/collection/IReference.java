package indicators.collection;

public interface IReference {
	public void update(); 
	public long diff(long r);
	public long head();  //head is volatile
}
 
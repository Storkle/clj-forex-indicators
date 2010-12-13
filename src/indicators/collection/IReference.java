package indicators.collection;

public interface IReference {
	public void update();
	public long diff(long ref);
	public long head();
}
 
package indicator.core;

public interface Reference {
	public void update();
	public long diff(long ref);
	public long head();
}
 
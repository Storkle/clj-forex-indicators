package indicators.collection;
 

interface ILock {
	public void writeLock();
	public void writeUnlock();
	public void readLock();
	public void readUnlock();
}

public interface IPriceStream extends IReference,ILock{
	public void add(double open,double high,double low,double close);
	public void setHead(double open,double high,double low,double close);
	
	public int size ();
	public int capacity();
	//convenience methods  
	public double open(int i);
	public double open(); 
	public double low(int i);
	public double low();
	public double close(int i);
	public double close();
	public double high(int i);
	public double high();
}

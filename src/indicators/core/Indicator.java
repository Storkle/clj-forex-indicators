package indicators.core;
import indicators.collection.*;


import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.locks.*;

interface IIndicator  {
	void Destroy();
	int Execute(int rates_total,int prev);
} 
   
public abstract class Indicator implements IReference,IIndicator,ISeq {
    int prev; 
	  
	protected IPriceStream Bars;
	ReadWriteLock lock = new ReentrantReadWriteLock(); 
    public ISeq main = new RingBufferArray();
   
	public Indicator(IPriceStream Bars) {
		this.Bars = Bars; this.head = Bars.head();
	}
	
	//ADD a reference 
	private Vector<IReference> references = new Vector<IReference>();
	public void addRef (IReference ref) {
		references.add(ref);	
	}
	
	//IMPLEMENT Iterable
	@Override
	public Iterator<Double> iterator() {
		return main.iterator();
	}
	
	//IMPLEMENTS Seq
	public int capacity () {
		return main.capacity();
	}
	public int size() {
		return main.size();
	}
	public double add(double obj) {
		return main.add(obj);
	}
	public void set(int i,double obj) {
		main.set(i,obj);
	}
	public double get(int i) {
		return main.get(i);
	}
	public double get() {
		return main.get();
	}
	public double[] toArray() {
		return main.toArray();
	}
	
	public int start(int limit) {
		return main.start(limit);
	} 
	
	
	public int start () {
		return main.start();
	}
	public void shift (long by) {
		main.shift(by);
	}
	
	//IMPLEMENTS Reference
	long head = -1;
	@Override  
	//TODO: this is thread safe because all indicators are updated after the price stream is updated! so, update price stream, then update indicators (serially)
	public void update() {
		if (head!=-1) {
			long d = Bars.diff(head);	
			shift(d); 
			prev = Math.max((int)(prev-d),0);
		} 
		head = Bars.head(); 
		for (IReference r:references)    
			r.update(); 	
	} 
	
	public long diff(long ref){
		return Bars.diff(ref);	
	}
	public long head() {
		return head;
	}	
	//UPDATING indicator
	protected int call_execute(int size,int prev) {
		return Execute(size,prev);
	}
	boolean stopped = false;

	public int ex() {
		if (stopped==true) {
			throw new RuntimeException("indicator is deinitialized - cant use it!");
		}
		try {
			lock.writeLock().lock(); Bars.readLock();
			//TODO: uncomment
			update(); 
			prev = call_execute(rates_total(),prev);
			//if (prev>0) prev=prev-1;
		}   
		catch(Exception e) {
			stopped=true;Destroy();e.printStackTrace(); throw new RuntimeException(e.getMessage());
		}
		finally {
			lock.writeLock().unlock(); Bars.readUnlock();
		}
		return prev;
	}
	
	//IMPLEMENT IIndicator
	public void Destroy() {};
	
	//Convenience methods
	private int rates_total() {
		return Math.min(Bars.size(),capacity());
	}
	
	public int limit () {
		return Math.max((rates_total()-prev-1),0); //rates_total-prev-1
	}
	public Double open(int index) {
		return Bars.open(index);//Open.get(index);
	}

	public Double open() {
		return Bars.open(0);
	}

	public Double low(int index) {
		return Bars.low(index);
	}

	public Double low() {
		return Bars.low(0);
	}

	public Double close(int index) {
		return Bars.close(index);
	}

	public Double close() {
		return Bars.close(0);
	}

	public Double high(int index) {
		return Bars.high(index);
	}

	public Double high() {
		return Bars.high(0);
	}

}

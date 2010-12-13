package indicators.core;
import indicators.collection.*;


import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.locks.*;

interface IIndicator  {
	void Destroy();
	int Execute(int rates_total,int prev);
} 
   
//TODO:: implement in clojure, it really is getting quite verbose
public abstract class Indicator implements IReference,IIndicator,ISeq {
	protected PriceStream Bars;
	ReadWriteLock lock = new ReentrantReadWriteLock();
    int prev; 
    
    public ISeq main = new RingBufferArray();
   
	public Indicator(PriceStream Bars) {
		this.Bars = Bars;
	}
	
	@Override
	public Iterator<Double> iterator() {
		return main.iterator();
	}
	
	////////////////////////////////////////
	///////////////////////////////////////END OF PSeq for High,Low,Open,Close
	//ADD a reference
	private Vector<IReference> references = new Vector<IReference>();
	public void addRef (IReference ref) {
		references.add(ref);	
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
	public int start () {
		return main.start();
	}
	public void shift (long by) {
		main.shift(by);
	}
	
	//IMPLEMENTS Reference
	long head = -1;
	public void update() {
		if (head!=-1) {
		shift(Bars.head()-head); 
		}
		head = Bars.head(); 
		for (IReference ref:references) 
			ref.update();	
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
	private int rates_total() {
		return Math.min(Bars.size(),capacity());
	}
	public int ex() {
		if (stopped==true) {
			throw new RuntimeException("indicator is deinitialized - cant use it!");
		}
		try { //TODO: also lock PPriceStream????
			lock.writeLock().lock();
			update(); 
			prev = call_execute(rates_total(),prev);//call_execute? perhaps use something else?
			if (prev>0) prev=prev-1;
		}  
		catch(Exception e){
			stopped=true;Destroy();e.printStackTrace(); throw new RuntimeException(e.getMessage());
		}
		finally {
			lock.writeLock().unlock();
		}
		return prev;
	}
	
	//IMPLEMENT IIndicator
	public void Destroy() {};
	
	//Convenience methods
	public int limit () {
		return (rates_total()-prev-1); //rates_total-prev-1
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

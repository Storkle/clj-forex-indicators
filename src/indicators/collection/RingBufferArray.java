package indicators.collection;
import java.util.Iterator;

public class RingBufferArray implements  ISeq {
	public double[] elements;
	private int capacity; 
	private int head=0;  

	private int max = -1;
	private int min = -1;
	
	public RingBufferArray(int capacity,RingBufferArray a) {
		this(capacity);
		for (int i=a.size()-1;i>=0;i--) {
			add(a.get(i));
		}
	}
	
	//what to return?
	public int ex() {return -1;}
	
	public RingBufferArray() {
		this(1000);
	}
	public RingBufferArray(int capacity) {
		elements = new double[capacity];
		this.capacity=capacity; 
	} 
	
	//TODO: allow a capacity greather than length
	/*public static RingBufferArray create(double[]array,int begin,int length) {
		RingBufferArray a = new RingBufferArray(length);
		a.elements =Arrays.copyOfRange(array,begin,begin+length);//TODO: head points at last one - is this good? I think the first 0 is oldest!
		a.start = length-1; a.head = length-1;  
		return a;   
	}   
	 */
	
	public void shift (long by) {
		long shiftBy =0;
		if (by>capacity)
			shiftBy = capacity;
		else 
			shiftBy = by;
		for (int i=0;i<shiftBy;i++) {
			this.pad(0); //add_without_increasing_start?
		}
	}
	
	public int capacity() {
		return capacity;
	}
	
	public int size()  {
		return max+1; 
	}
	 
	//TODO: fix
	public double[] toArray() {
		int begin = start();
		double[]dest = new double[begin];
        for (int i=0;i<begin;i++) {
        	dest[begin-i-1] = get(i);
        }
        return dest;        
	}
	
	public double add(double obj) {
		++head;
		if (head>=capacity) head=0;
		double prev = elements[head];
		elements[head] = obj;	
		
		if (max<capacity-1) 
			++max; 
		return prev;
	}
	
	public int start () {
		return max;
	}
	public int start(int limit ) {
		return Math.min(max,limit);
	}
	  
	//pad with zeros usually - if min >max, reset indices!
	public double pad(double obj) {
		++head;
		if (head>=capacity) head=0;
		double prev = elements[head];
		elements[head] = obj;			
		if (max<capacity-1) 
			++max; 
		++min;
		if (min>max) {
			min=-1;max=-1; 
		}
		return prev;
	}
	
	
	public void set(int i,double obj) {
		if (i>=capacity || i<0)
			throw new IndexOutOfBoundsException();
		if (i>max) 
			max=i;
		if (i<min)
			min=i;
		int index= (head-i);
		if (index<0) index = capacity-Math.abs(index);
		elements[index]=obj; 
	}
	
	public double get() {
		return get(0);
	}
	public double get(int i) {
		if (i>max || i<0) 
			throw new IndexOutOfBoundsException();
		int index= (head-i);
		if (index<0) index = capacity-Math.abs(index);
		return elements[index]; 
	}

	 class RingIterator implements Iterator<Double> {
		RingBufferArray a;
		public RingIterator (RingBufferArray a) {
			this.a = a;
		}
		int index =0;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index<a.size();
		}

		@Override
		public Double next() {
			// TODO Auto-generated method stub
			double n = a.get(index); ++index;
			return n;
		}

		@Override
		public void remove() {
			//do nothing
		}	
		
	}

	@Override
	public Iterator<Double> iterator() {
		// TODO Auto-generated method stub
		return new RingIterator(this);
	}

}
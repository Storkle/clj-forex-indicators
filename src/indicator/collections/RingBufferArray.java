package indicator.collections;

import indicator.core.Seq;

import java.util.Arrays;
import java.util.Iterator;
 
public class RingBufferArray implements Seq {
	public double[] elements;
	private int size;
	private int head=0;
	private int start = -1; //keep track of number of elements
	
	//what to return?
	public int ex() {return -1;}
	
	public RingBufferArray() {
		this(300); 
	}
	public RingBufferArray(int size) {
		elements = new double[size];
		this.size=size; 
	} 
	
	//files in any zeros if begin+length-1 is greater than max array index;
	public static RingBufferArray create(double[]array,int begin,int length, int max) {
		RingBufferArray a = new RingBufferArray(length);
		a.elements =Arrays.copyOfRange(array,begin,begin+length);//TODO: head points at last one - is this good? I think the first 0 is oldest!
		a.start = max-1; a.head = max-1;  
		return a;   
	}  
	 
	public int start() {
		return start;
	} 
	
	public void shift (long by) {
		long shiftBy =0;
		if (by>size)
			shiftBy = size;
		else 
			shiftBy = by;
		for (int i=0;i<shiftBy;i++) {
			this.add(0);
		}
	}
	
	public int capacity() {
		return size;
	}
	
	public int size()  {
		return start+1; 
	}
	 
	public double[] toArray() {
		int start = start();
		double[]dest = new double[start];
        for (int i=0;i<start;i++) {
        	dest[start-i-1] = get(i);
        }
        return dest;        
	}
	
	public double add(double obj) {
		++head;
		if (head>=size) head=0;
		double prev = elements[head];
		elements[head] = obj;
		if (start<size-1) ++start; //TODO: need this?
		//if (head>max) max=head;
		return prev;
	}
	
	public void set(int i,double obj) {
		if (i>=size || i<0)
			throw new IndexOutOfBoundsException();
		if (i>start) 
			start=i;
		int index= (head-i);
		if (index<0) index = size-Math.abs(index);
		elements[index]=obj; 
	}
	
	public double get() {
		return get(0);
	}
	public double get(int i) {
		if (i>start || i<0)
			throw new IndexOutOfBoundsException();
		int index= (head-i);
		if (index<0) index = size-Math.abs(index);
		return elements[index]; 
	}

	 class RingIterator implements Iterator {
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
		public Object next() {
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
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new RingIterator(this);
	}

}
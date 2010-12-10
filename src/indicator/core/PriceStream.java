package indicator.core;

import indicator.collections.RingBufferArray;
//REQUIREMENTS:
//ability to freeze a price stream in a certain context
//ability to freeze an indicator in a certain context (for eas - only need to freeze price stream)

//ability to interop with TA-LIB (using primitive double arrays) i.e. wrappers for various indicators
//ability to use any stream as a option, even into TA-LIB

 
public class PriceStream  implements Reference{
	public RingBufferArray High,Low,Open,Close;
    public PriceStream(int size) {
    	High = new RingBufferArray(size);
    	Low = new RingBufferArray(size);
    	Open = new RingBufferArray(size);
    	Close = new RingBufferArray(size);
    }
    
	public PriceStream add(double highVal, double lowVal,
						   double openVal, double closeVal) { 		
		High.add(highVal);
		Open.add(openVal);
		Low.add(lowVal);
		Close.add(closeVal); 
		++head;
		return this;
	}
	/*
	public PriceStream set(int index, Double highVal, Double lowVal,
			Double openVal, Double closeVal) {
		High.set(index, highVal);
		Open.set(index, openVal);
		Low.set(index, lowVal);
		Close.set(index, closeVal);
		return this;
	}
	*/
	public int size() {
		return High.size();
	}
	//CONVENIENCE methods
	public Double open(int index) {
		return Open.get(index);
	}

	public Double open() {
		return Open.get(0);
	}

	public Double low(int index) {
		return Low.get(index);
	}

	public Double low() {
		return Low.get(0);
	}

	public Double close(int index) {
		return Close.get(index);
	}

	public Double close() {
		return Close.get(0);
	}

	public Double high(int index) {
		return High.get(index);
	}

	public Double high() {
		return High.get(0);
	}

	//IMPLEMENT Reference
	long head = 0;
	@Override
	public long diff(long ref) {
		return ref-head; //NEVER overflows (well, ill fix it later)
	}
	@Override
	public long head() {
		return head;
	}
	@Override
	public void update() {}

}

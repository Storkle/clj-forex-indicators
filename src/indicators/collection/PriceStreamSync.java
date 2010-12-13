package indicators.collection;

//TODO: should write this in clojure (not performance critical after all!)
//well, basic strategy is = lock it all! yay! this is the global price stream which everything acccesses in parallel
//TODO: we need to explore multithreading alternatives in java
//like using AKKA

public class PriceStreamSync extends PriceStream  {
	public void setHead(double open, double high, double low, double close) {
		try {
			writeLock();
			super.setHead(open, high, low, close);
		} finally {
			writeUnlock();
		}
	}
	
	public int capacity() {
		try {
			readLock();
		    return super.capacity();
		} finally {
			readUnlock();
		}
	}
	
	public long head() {
		try {
			readLock();
			return super.head();
		} finally {
			readUnlock();
		}
	}
	
	
	public long diff(long r) {
		try {readLock(); return super.diff(r);}
		finally {readUnlock();}
	}
	
	
	

	@Override
	public void add(double open, double high, double low, double close) {
		try {writeLock();super.add(open,high,low,close);} 
		finally{writeUnlock();}
	}

	// CONVENIENCE methods
	@Override
	public double close(int i) {
		try {readLock();return super.close(i);}
		finally{readUnlock();}
	}

	@Override
	public double close() {
		return super.close();
	}

	@Override
	public double high(int i) {
		try {readLock();return super.high(i);}
		finally{readUnlock();}
	}

	@Override
	public double high() {
		return super.high();
	}

	@Override
	public double low(int i) {
		try {readLock();return super.low(i);}
		finally{readUnlock();}
	}

	@Override
	public double low() {
		return super.low();
	}

	@Override
	public double open(int i) {
		try{readLock();return super.open(i);}
		finally{readUnlock();}
	}

	@Override
	public double open() {
		return super.open();
	}

	@Override
	public int size() {
		try{readLock();return super.size();}
		finally{readUnlock();}
	}
}

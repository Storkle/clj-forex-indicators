package indicators.collection;

import java.util.concurrent.locks.*;

//Used in the proper context, a PriceStream does not need a lock except when changing data.
 
public class PriceStream implements IPriceStream {
	public RingBufferArray Open, High, Low, Close;
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	// Lock
	public void readLock() {
		lock.readLock().lock();
	}

	public void readUnlock() {
		lock.readLock().unlock();
	}

	public void writeLock() {
		lock.writeLock().lock();
	}

	public void writeUnlock() {
		lock.writeLock().unlock();
	}

	// constructors
	public PriceStream() {
		this(1000);
	}

	public PriceStream(PriceStream p) {
		this(p.capacity(), p);
	}

	public PriceStream(int capacity, PriceStream p) {
		try {
			p.readLock();
			Close = new RingBufferArray(capacity, p.Close);
			Open = new RingBufferArray(capacity, p.Open);
			High = new RingBufferArray(capacity, p.High);
			Low = new RingBufferArray(capacity, p.Low);
			ref = p.head();
		} finally {
			p.readUnlock();
		}
	}

	public PriceStream(int size) {
		Open = new RingBufferArray(size);
		High = new RingBufferArray(size);
		Low = new RingBufferArray(size);
		Close = new RingBufferArray(size);
	}
	
	@Override
	public void add(double open, double high, double low, double close) {
		Open.add(open);
		High.add(high);
		Low.add(low);
		Close.add(close);
		++ref;
	}

	// CONVENIENCE methods
	@Override
	public double close(int i) {
		return Close.get(i);
	}

	@Override
	public double close() {
		return close(0);
	}

	@Override
	public double high(int i) {
		return High.get(i);
	}

	@Override
	public double high() {
		return high(0);
	}

	@Override
	public double low(int i) {
		return Low.get(i);
	}

	@Override
	public double low() {
		return low(0);
	}

	@Override
	public double open(int i) {
		return Open.get(i);
	}

	@Override
	public double open() {
		return open(0);
	}

	@Override
	public int size() {
		return High.size();
	}

	// IMPLEMENT Referece
	long ref;

	@Override
	public long diff(long r) {
		return ref - r;
	}

	@Override
	public long head() {
		return ref;
	}

	public void update(PriceStream p) {
		try {
			p.readLock();
			long a = p.diff(ref);
			if (a == 0) {
				setHead(p.open(), p.high(), p.low(), p.close());
			} else { 
				int capacity = capacity();
				Close = new RingBufferArray(capacity, p.Close);
				Open = new RingBufferArray(capacity, p.Open);
				High = new RingBufferArray(capacity, p.High);
				Low = new RingBufferArray(capacity, p.Low);
				ref = p.head();
			}
		} finally {
			p.readUnlock();
		}
	}

	@Override
	public int capacity() {
		return High.capacity();
	}

	@Override
	public void setHead(double open, double high, double low, double close) {
		// TODO Auto-generated method stub
		High.set(0, high);
		Low.set(0, low);
		Close.set(0, close);
		Open.set(0, open);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}

package indicators.collection;

//this is a mutable container for the immutable ippricestream so that we can simply pass this
//once into a group of indicators
//surely theres an easier way to 'redirect' the method calls to the IPPriceSTream?
public class PriceStream implements IReference {
	IPPriceStream stream;
	public PriceStream(IPPriceStream stream) {
		this.stream = stream;
	}
    public int size () {return stream.size();}
	public IPPriceStream clone() {return stream.clone();} 
	public IPPriceStream add(double open,double high,double low,double close) {
		return stream.add(open,high,low,close);
	}
	//convenience methods
	public Double open(int index) {
		return stream.open(index);
	}
	public Double open() {
		return stream.open(); 
	}
	public Double low(int index) {
		return stream.low(index); 
	}
	public Double low() {
		return stream.low();
	}
	public Double close(int index) {
		return stream.close(index);
	}
	public Double close() {
		return stream.close();
	}
	public Double high(int index) {
		return stream.high(index);
	}
	public Double high() {
		return stream.high();
	}
	@Override
	public long diff(long arg0) {
		// TODO Auto-generated method stub
		return stream.diff(arg0);
	}
	@Override
	public long head() {
		// TODO Auto-generated method stub
		return stream.head();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
	    stream.update();
	}
	
	
	
	
	
///////////////////////////////////////////////////////
	///////////////////////////////////////////////////////
	//CREATE a PSeq for High,Low,Open,and Close
	public IPSeq High =  new IPSeq () {
		@Override 
		public int capacity() {
			return size();
		}
		@Override
		public double get(int i) {
			return high(i);
		}
		@Override
		public double get() {
			return high();
		}  
		@Override
		public int size() {
			return PriceStream.this.size();
		}
		@Override
		public int start() {
			return 0;
		}
		@Override
		public double[] toArray() {
			return null;
		}};
		
		public IPSeq Open =  new IPSeq () {
 			@Override 
			public int capacity() {
				return size();
			}
			@Override
			public double get(int i) {
				return open(i);
			}
			@Override
			public double get() {
				return open();
			}
			@Override
			public int size() {
				return PriceStream.this.size();
			}
			@Override
			public int start() {
				return 0;  
			}
			@Override
			public double[] toArray() {
				return null;
			}};
			//close
			public IPSeq Close =  new IPSeq () {
				@Override 
				public int capacity() {
					return size();
				}
				@Override
				public double get(int i) {
					return close(i);
				}
				@Override
				public double get() {
					return close();
				}
				@Override
				public int size() {
					return PriceStream.this.size();
				}
				@Override
				public int start() {
					return 0;
				}
				@Override
				public double[] toArray() {
					return null;
				}};
				//low
				public IPSeq Low =  new IPSeq () {
					@Override 
					public int capacity() {
						return size();
					}
					@Override
					public double get(int i) {
						return low(i);
					}
					@Override
					public double get() {
						return low();
					}
					@Override
					public int size() {
						return PriceStream.this.size();
					}
					@Override
					public int start() {
						return 0;
					}
					@Override
					public double[] toArray() {
						return null;
					}};
}

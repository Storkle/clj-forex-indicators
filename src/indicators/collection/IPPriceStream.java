package indicators.collection;

//a persistent price stream for our indicators to use - actually implemented in clojure code 
public interface IPPriceStream extends IReference,Cloneable {
	public int size ();
	
	public IPPriceStream clone(); 
	public IPPriceStream add(double open,double high,double low,double close);
	//convenience methods
	public Double open(int index);
	public Double open(); 
	public Double low(int index);
	public Double low();
	public Double close(int index);
	public Double close();
	public Double high(int index);
	public Double high();
}

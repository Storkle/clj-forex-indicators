package indicators.collection;

//TODO: get rid of all locks, leave on clojure side!
 
public class ForexStream extends PriceStream {
	public String symbol = "";
	public int timeframe = 60; 
	public long headTime = 0;  
	
    public ForexStream(String symbol,int timeframe,long headTime) {
    	this.symbol = symbol;this.timeframe = timeframe;this.headTime = headTime;
    }
	public ForexStream(ForexStream p) {
		super(p.capacity(), p);
        this.symbol = p.symbol;
	    this.timeframe = p.timeframe;
		this.headTime = p.headTime;
	}
	
}

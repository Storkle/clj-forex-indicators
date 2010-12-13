package indicators.core;
import indicators.collection.*;
//reuse more by making protected?
public abstract class PriceIndicator  extends Indicator {
	IPSeq Price;  
	public PriceIndicator(PriceStream Bars,IPSeq price) {
		super(Bars);
		this.Price = price;
	} 
	protected int call_execute(int bars_total,int prev) {
		return Execute(rates_total(),prev);  
	} 
	public double price(int i) {
		return Price.get(i); 
	}
	public double price () {
		return price(0);
	}
	
	private int rates_total() {
		return Math.min(Price.size(),capacity());
	}
	
	public int limit () {
		return (rates_total()-prev-1); //rates_total-prev-1
	}
}

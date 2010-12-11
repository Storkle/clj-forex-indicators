package indicators.core;

//reuse more by making protected?

public abstract class PriceIndicator  extends Indicator {
    protected Seq Price;
	public PriceIndicator(PriceStream Bars,Seq price) {
		super(Bars);
		this.Price = price;
	}
	protected int call_execute(int bars_total,int prev) {
		return Execute(Price.size(),prev);
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

package indicator;
import indicator.core.*;

public class ATR extends Indicator {
	public ATR(PriceStream Bars,int period) {
		super(Bars);
		Check.is(period>0,"in ATR, period must be >0");
		this.period = period;
		main = new SMA(Bars,tr,period);
	} 
	Seq tr = new SeqVar(this); 
	@input
	int period = 14;
	//TODO
	@Override
	public int Execute(int rates_total,int prev) {
		if (rates_total<period) return 0;
		int limit = limit();
		limit--; // initialization starts at limit-1;
		// initialize true range
		for (int i = limit; i >= 0; i--) {
			double high = high(i);
			double val1 = Math.abs(high - low(i));
			double val2 = Math.abs(high - close(i + 1));
			double val3 = Math.abs(low(i) - close(i + 1));
			tr.set(i, Math.max(Math.max(val1, val2), val3));
		} 
		main.ex();
		return 0;
	}	
}

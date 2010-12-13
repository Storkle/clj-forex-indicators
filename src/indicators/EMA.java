package indicators;
import indicators.core.*;

import indicators.collection.*;
public class EMA extends PriceIndicator {
	public EMA(IPriceStream Bars, ISeq price,int period) {
		super(Bars, price);
		Check.is(period>0,"in EMA, invalid parameter period %s",period);
		this.period=period;
		weight = 2.0/(period+1);
	}
	double weight; 
	@input
	int period;
	@Override
	public int Execute(int rates_total, int prev) {
		if (rates_total<period) return 0; 
		int limit = limit();
	    double sum = 0;  
	    if (prev==0) { 
	    	//initialize with SMA
	    	for (int i=limit;i>limit-period;i--) {
	    		sum+=price(i);
	    	}
	    	set(limit-period+1,sum/period);
		    limit = limit-period;
	    }  
	    for (int i=limit;i>=0;i--) {
	    	set(i,price(i)*weight+get(i+1)*(1-weight)); 
	    }    
	    return rates_total;
	}
}

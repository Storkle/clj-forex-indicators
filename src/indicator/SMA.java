package indicator;
//import indicator.collections.RingBufferArray;
import indicator.core.*;

public class SMA extends PriceIndicator {
	public SMA(PriceStream Bars,Seq price, int period) {
		super(Bars,price); 
		Check.is(period>0,"in SMA, invalid parameter period %s",period);
		this.period = period; 
	} 	   
    @input   
	int period;   
	public int Execute(int rates_total,int prev) {
	    if (rates_total<period) return 0;
	    int limit = limit(); 
	    double sum = 0;  
	    if (prev==0) { 
	    	for (int i=limit;i>limit-period;i--) {
	    		sum+=price(i);
	    	}
	    	set(limit-period+1,sum/period);
		    limit = limit-period;
	    } else { 
	    	sum = get(limit+1)*period; 
	    }  
	    for (int i=limit;i>=0;i--) {
	    	sum = sum-price(i+period)+price(i);
	    	set(i,sum/period); 
	    }    
	    return rates_total;
	 }
}
 

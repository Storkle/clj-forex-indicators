package indicators.ta;
//NOTE: this works, but... some things have 'memory', which means they require previous calculations of themselves.
//i really didn't feel like sorting through the indicators which required memory, so.... And implementing all of the 'core'
//indicators is a great way to test out how the indicator interface works! And in the long run, i think this will pay off more.
//though maybe not.... maybe in the beginning...
 
/*package indicator.ta;
import indicator.collections.RingBufferArray;
import indicator.core.Indicator;
import indicator.core.PriceStream;
import indicator.core.Seq;
import indicator.core.SeqVar;
import static indicator.utils.Check.*;
import com.tictactec.ta.lib.MInteger ;
import com.tictactec.ta.lib.RetCode;
import com.tictactec.ta.lib.Core;
 
public class SMA extends Indicator {
	int period;  Seq price; Core core = new Core();
	
	public SMA(PriceStream Bars,Seq price, int period) {
		super(Bars); 
		is(period>0,"invalid parameter period %s",period);
		this.period = period; this.price = price;
	}

	public static boolean success(RetCode r) {
		if (RetCode.Success==r) 
			return true;
		return false;
	}
	
	public static void fail(RetCode r) {
		if (RetCode.BadParam==r) {
			throw new RuntimeException("failure in TAlib: bad parameter");
		} else {
			throw new RuntimeException("failure in TA LIB");
		}
	}
	 
	@Override 
	public int Execute() { W
		double[] p = price.toArray(); 
		double[] result = new double[p.length]; 
		MInteger outBegin = new MInteger(); 
		MInteger outN = new MInteger();
		//TODO: once it is done, experiment with only calculating new values! 
		RetCode r = core.sma(0, result.length-1, p, period, outBegin, outN, result);
		//indicator.utils.Print.out("ret length is %s, orig length is %s",result.length,price.start());
		if(success(r)) {     
			main = RingBufferArray.create(result,0,capacity(),outN.value);   
			return outN.value;       
		} 
		fail(r);	 
		return 0;  //a
	}
}
 
*/
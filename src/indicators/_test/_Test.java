package indicators._test;

import static indicators.utils.Print.milli;
import static indicators.utils.Print.out;
import indicators.*;
import indicators.collection.PriceStream;
import indicators.utils.*;
 
public class _Test {
	public static void runStream(String file) throws Exception {
		//PriceStream aud_nzd = Csv.get("/home/seth/Desktop/aud_nzd.csv");
		PriceStream stream= Csv.get("/home/seth/Desktop/aud_nzd.csv");
		VMA sma = new VMA(stream,2,2,1); 
		long time =milli();  
		sma.ex();
		out("time is %s",milli()-time);
		MPlotter.out(sma);  
	}   
	 
	public static void runIndicator(String file) throws Exception {
		runStream(file); 
	}

	public static void main(String[] args) throws Exception {
		runIndicator("/home/seth/Desktop/aud_nzd.csv");
	}
}

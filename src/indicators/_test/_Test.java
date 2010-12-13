package indicators._test;
import indicators.*;
import indicators.collection.*;
import static indicators.utils.Print.*;
 


public class _Test {	
	public static void runPriceStream (String file) {
	}     
	//TODO UNIT TESTS:
	//indicators handling less than max;
	public static void init () {
		
	}
	public static void runIndicator(String file) throws Exception {
	    PriceStream stream = Csv.get(file); 
	    out(stream.size()); 
	    //just to 'warm it up'
	    SMA sma3 = new SMA(stream,stream.Close,20);
	    sma3.ex();
	    // 
	    //Thread.sleep(3000);
	    SMA sma = new SMA(stream,stream.Close,20);
	    long time = milli();
	    sma.ex();
	    out("time: %s",milli()-time); 
	    out(sma.get(0));
	    
	    sma.ex();
	    out(sma.get(0));
		//VMA sma = new VMA(stream,2,2,1);     
		//sma.ex(); 
		//out("size is "+sma.size()+" "+sma.capacity()); 
		MPlotter.out(sma);
	}
	 
	public static void main(String[]args) throws Exception {
		//runRingBufferArray(); 	
		//runPriceStream("/home/seth/Desktop/aud_nzd.csv");	
		runIndicator("/home/seth/Desktop/aud_nzd.csv"); //ta lib does soonest to end, not opposite! (i think)
	}
}

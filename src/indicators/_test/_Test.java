package indicators._test;
import indicators.*;
import indicators.collections.RingBufferArray;
import indicators.core.*;
import static indicators.utils.Print.*;
 
public class _Test {
	public static void runRingBufferArray() {
		/*RingBufferArray a = new RingBufferArray(3);
		a.add(3); a.add(2);  a.add(4);
		  
		out("%s %s %s %s %s",a.get(0),a.get(1),a.get(2),a.start(),a.size());  
		
		double[] vec = {1.0,2.0,3.0}; //TODO: backwards.
		RingBufferArray a2 = RingBufferArray.create(vec,2,2);
		out("size is %s start is %s",a2.size(),a2.start());
		for (double d:a2) 
			out(d);*/
		
		/*out("%s %s %s",a.toArray()[3000],a.get(1),a.get(2));
		long t = milli();	
		out("time taken is %s",milli()-t);*/
	}
	
	public static void runPriceStream (String file) {
	}     
	//TODO UNIT TESTS:
	//indicators handling less than max;
	public static void runIndicator(String file) {
	    PriceStream stream = Csv.get(file);     
		VMA sma = new VMA(stream,2,2,1);     
		sma.ex(); out("size is "+sma.size()+" "+sma.capacity()); 
		 // stream.add(2,3,4,5);sma.ex(); out("size is "+sma.start());
		//out(sma.get()+" "+sma.get(1)+" "+sma.get(2)); //out(sma.get(sma.size()-4)); //out(sma.size()); out(sma.start()); 
		//stream.add(10.0, 3.0, 2.0, 3.0);
		//sma.ex(); 
		//out(sma.get()); out("1 %s, 2 %s",sma.get(1),sma.get(2));
		//sma.ex(); 
		//out("first %s",sma.get());
		//sma.get(sma.size()+1); sma.main
		MPlotter.out(sma);
		/*for (double d:sma) 
			out(d);*/
	}
	 
	public static void main(String[]args) {
		//runRingBufferArray(); 	
		//runPriceStream("/home/seth/Desktop/aud_nzd.csv");	
		runIndicator("/home/seth/Desktop/aud_nzd.csv"); //ta lib does soonest to end, not opposite! (i think)
	}
}

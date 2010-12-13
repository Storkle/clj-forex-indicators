package indicators._test;
import java.util.Vector;

import indicators.collection.*;

import clojure.lang.RT;
import clojure.lang.Var;

public class Csv {
	public static PriceStream newStream (Vector<Double> open,Vector<Double> high,Vector<Double>low,
			Vector<Double> close) throws Exception {
		RT.loadResourceScript("indicators/collection/stream.clj");
		Var foo = RT.var("indicators.collection.stream", "new-pstream"); 
		Object stream = foo.invoke(open,high,low,close);
		return new PriceStream((IPPriceStream)stream);
	}

	public static PriceStream get(String file) throws Exception {		
		TextFile f = new TextFile(file); 
		Vector<Double> open = new Vector<Double>();
		Vector<Double> high = new Vector<Double>();
		Vector<Double> low = new Vector<Double>();
		Vector<Double> close = new Vector<Double>();
		
		while (f.iterator().hasNext()) {
			String line = f.iterator().next(); 
			String[] dat = line.split(",");
			high.add(Double.parseDouble(dat[3])); 
			low.add(Double.parseDouble(dat[4]));
			open.add(Double.parseDouble(dat[2]));
			close.add(Double.parseDouble(dat[5]));
			//stream.add(high, low, open, close);
		}
		return newStream(open,high,low,close);
	}
}


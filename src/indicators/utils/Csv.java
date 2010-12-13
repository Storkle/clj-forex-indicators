package indicators.utils;
import indicators.collection.*;

public class Csv {
	public static PriceStream get(String file) throws Exception {		
		TextFile f = new TextFile(file); 
		PriceStream s = new PriceStream();
		while (f.iterator().hasNext()) {
			String line = f.iterator().next(); 
			String[] dat = line.split(",");
			double high = Double.parseDouble(dat[3]); 
			double low = Double.parseDouble(dat[4]);
			double open = Double.parseDouble(dat[2]);
			double close = Double.parseDouble(dat[5]);
			s.add(open, high, low, close);
		}
		return s;
	}
}


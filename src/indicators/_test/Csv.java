package indicators._test;

import indicators.core.PriceStream;

public class Csv {
	public static PriceStream get(String file) {
		PriceStream stream = new PriceStream(10000); // maybe a good size method, eh?
		TextFile f = new TextFile(file);
		while (f.iterator().hasNext()) {
			String line = f.iterator().next();
			String[] dat = line.split(",");
			Double high = Double.parseDouble(dat[3]), low = Double
					.parseDouble(dat[4]);
			Double open = Double.parseDouble(dat[2]), close = Double
					.parseDouble(dat[5]);
			stream.add(high, low, open, close);
		}
		return stream;
	}
}

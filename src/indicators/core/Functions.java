package indicators.core;
import indicators.collection.*;

public class Functions {
	public static double Highest (ISeq seq, int from, int length) {
		if (length<1) 
			throw new RuntimeException("Invalid length "+length+" passed to Highest");
		Double max = null;
		for (int i=from;i<from+length;i++) {
			double val = seq.get(i);
			if (max==null || val>max)
				max = val;
		}
		return max;
	}
	
	public static double Lowest (ISeq seq, int from, int length) {
		if (length<1) 
			throw new RuntimeException("Invalid length "+length+" passed to Lowest");
		Double min = null;
		for (int i=from;i<from+length;i++) {
			double val = seq.get(i);
			if (min==null || val<min)
				min = val;
		}
		return min;
	}	
}

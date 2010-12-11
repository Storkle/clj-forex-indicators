package indicators;
import indicators.core.*;

public class CCI extends Indicator {
	public CCI(PriceStream Bars, int period) {
		super(Bars);
		this.period = period; Init();
	}
	public void Init() {
		sma = new SMA(Bars, tp, period);
	}
	
	Indicator sma;
	SeqVar tp = new SeqVar(this);
	SeqVar dev = new SeqVar(this);
	@input
	int period = 14;

	public int Execute(int rates_total,int prev) {
		// calculate TP
		if (rates_total<period) return 0;
		int limit = limit();
		for (int i = 0; i <= limit; i++) 
			tp.set(i, (close(i) + high(i) + low(i)) / 3.0);
		// calculate simple moving average of typical price (SMATP)
		sma.ex(); 
		// calculate absolute value of different between SMATP and typical price
		for (int i=sma.start();i>=0;i--) {
			double currentSMA = sma.get(i);
			double sum = 0;
			for (int j = i; j < i+period; j++)
				sum+=Math.abs(currentSMA-tp.get(j));
			dev.set(i,sum*.015/period);
		}
		// calculate cci
		for (int i = dev.start(); i >= 0; i--)
			set(i, (tp.get(i)-sma.get(i)) /  dev.get(i)); //-154.91606714625223
		return rates_total;
	}
}

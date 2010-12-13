package indicators.collection;
import indicators.collection.RingBufferArray;
import indicators.core.*;
 
public class SeqVar extends RingBufferArray implements ISeq, IReference {
	long reference;
	Indicator ind;     
	public void update () { 
		this.shift(ind.diff(reference));   
	}	
	//IMPLEMENT Reference  
	@Override
	public long head() {
		return ind.head();
	}
	@Override
	public long diff(long r) {
		return ind.diff(r);
	}
	// 
	public SeqVar(Indicator ind) {
		super(ind.capacity());
		ind.addRef(this);
		this.ind = ind;
		reference = ind.head();		
	} 
}

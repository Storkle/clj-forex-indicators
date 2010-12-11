package indicators.core;
import indicators.collections.RingBufferArray;

public class SeqVar extends RingBufferArray implements Reference {
	long reference;
	Indicator ind; 
	//IMPLEMENT Reference 
	public void update () { 
		this.shift(ind.diff(reference));  
	}	
	public long diff(long ref) {
		return ref-reference;
	}
	public long head() {
		return reference;
	}
	// 
	public SeqVar(Indicator ind) {
		super(ind.capacity());
		ind.addRef(this);
		this.ind = ind;
		reference = ind.head();		
	} 

}

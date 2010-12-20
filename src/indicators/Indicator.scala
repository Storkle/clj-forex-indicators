package indicators
import scala.collection.mutable._
/*
trait TIndicator  {
  def Destroy():Int
  def Execute (rates_total:Int,prev:Long):Long
}


abstract class Indicator(val Bars:PriceStream,capacity:Int=1000) extends TIndicator with TLock with TReference with TSeq {
  var prev:Long=0
  var head:Long = Bars.head 
  var main:TSeq = new DoubleRingBuffer(capacity)
  
  //references
  var references = new ArrayBuffer[TReference]()
  def addRef (ref:TReference):Unit = references:+ ref 
  
  //implement TSeq
  def capacity() = main.capacity
  def size() = main.size
  def set(i:Int,obj:Double) = main.set(i,obj)
  def get(i:Int=0) = main.get(i)
  //start(int limit)
  //shift (long by)
  
  //implement TReference 
  def update():Unit = {
    if (head!= -1) {
      var d:Long = Bars.diff(head)
      shift(d)
      prev = (if ((prev-d)>0) (prev-d) else 0) 
    }
    head = Bars.head
    for (r <- references)
      r.update()
  }
  def diff(ref:Long) = Bars.diff(ref)
  
  //run indicator
  protected def _rates_total():Int = {
    Math.min(Bars.size,capacity);
  }
  
  //Implement TIndicator
  def Destroy():Int = 0
  private var stopped = false
  def ex():Long = {
    if (stopped==true)
      throw new RuntimeException("indicator is deinitialized - cant use again")
    try {
      writeLock()
      Bars.readLock()
      update()
      prev = Execute(_rates_total,prev)
    } catch {
      case e:Exception  => stopped=true;Destroy();e.printStackTrace();
      throw e
    } finally {
      writeUnlock(); Bars.readUnlock();
    }
    prev
  }
  
  //convenience methods
  final def open(i:Int=0) = Bars.Open(i)
  final def low(i:Int=0) = Bars.Close(i)
  final def close(i:Int=0) = Bars.Close(i)
  final def high(i:Int=0) = Bars.High(i)
}

*/

//NOTE: not thread safe - will synchronize in clojure when necessary
package indicators
import java.util.concurrent.locks.ReentrantReadWriteLock
 
//time reference
trait TReference {
  def diff(i:Long):Long
  def head():Long
  //def update():Unit
}

//perhaps useful for external lock coordination
trait TLock {
  val lock = new ReentrantReadWriteLock()
  def readLock ():Unit =  lock.readLock().lock()
  def readUnlock():Unit = lock.readLock().unlock()
  def writeLock():Unit = lock.writeLock().lock()
  def writeUnlock():Unit = lock.writeLock().unlock()
}
  
class PriceStream(val capacity:Int=1000) extends TReference with TLock {
  var Open = new DoubleRingBuffer(capacity)
  var Close = new DoubleRingBuffer(capacity)
  var High = new DoubleRingBuffer(capacity)
  var Low = new DoubleRingBuffer(capacity)
  var ref:Long = 0
  
  final def add(open:Double,high:Double,low:Double,close:Double):Unit = {
    Open.add(open)
    High.add(high)
    Low.add(low)
    Close.add(close)
    ref+=1
  }
  final def setHead(open:Double,high:Double,low:Double,close:Double):Unit = {
    High.set(0,high)
    Low.set(0,low)
    Close.set(0,close)
    Open.set(0,open)
  }

  final def reset(p:PriceStream):Unit = {
    if (p.size>capacity)
      throw new RuntimeException("size "+p.size+" is greater than the capacity of this price stream")
    val a = p.diff(ref)   
    Close = DoubleRingBuffer.create(p.Close,capacity)
    Open = DoubleRingBuffer.create(p.Open,capacity)
    High = DoubleRingBuffer.create(p.High,capacity)
    Low = DoubleRingBuffer.create(p.Low,capacity)
    ref = p.head   
  }
  
  final def update(p:PriceStream):Unit = {
    val a = p.diff(ref)
    if (a==0)
      setHead(p.Open(0),p.High(0),p.Low(0),p.Close(0))
    else { 
      reset(p)
    }
  }  
  //convenience methods for clojure???
  final def close(i:Int=0) = Close.get(i)
  final def high(i:Int=0) = High.get(i)
  final def open(i:Int=0) = Open.get(i)
  final def low(i:Int=0) = Low.get(i)
  final def size() = High.size
  //implement TReference
  def diff(r:Long) = ref-r
  def head() = ref
}




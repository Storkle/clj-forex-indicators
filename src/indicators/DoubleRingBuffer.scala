package indicators

object DoubleRingBuffer {
  def create (b:DoubleRingBuffer,capacity:Int):DoubleRingBuffer = {
    if (b.size>capacity) 
      throw new RuntimeException("source buffer size "+b.size+" is greater than requested capacity "+capacity)
    var buff = new DoubleRingBuffer(capacity)
    for (i<-b.size-1 to 0 by -1) {
      buff.add(b.get(i))
    }
    buff.max = b.max  
    buff  
  }
  def create(b:DoubleRingBuffer):DoubleRingBuffer = {
    create(b,b.capacity)
  }
}

trait TSeq {
  def size():Int
  def add(obj:Double):Double
  def set(i:Int,obj:Double):Double
  def get(i:Int):Double
  def capacity():Int
}

//NOTE: note thread safe
class DoubleRingBuffer(var capacity:Int=1000) extends TSeq {
  var elements = new Array[Double](capacity)
  var head=capacity-1
  var max= -1

  def apply(i:Int=0):Double = {
    get(i)
  }
  final def size ():Int = max+1
  final def add(obj:Double):Double = {
    head-=1
    if (head<0) head=capacity-1
    if (max<capacity-1) max+=1
    return set(0,obj) 
  }  
  final def set(i:Int,obj:Double):Double = {
    if (i>=capacity || i<0)
      throw new IndexOutOfBoundsException(i+" out of bounds")
    if (i>=max) max=i
    var index = (head+i)%capacity
    var prev = elements(index)
    elements(index)=obj
    return prev
  }  
  final def get(i:Int=0):Double = {
    if (i>=size() || i<0)
      throw new IndexOutOfBoundsException(i+" out of bounds")
    var index = (head+i)%capacity
    return elements(index)
  }    
}
 


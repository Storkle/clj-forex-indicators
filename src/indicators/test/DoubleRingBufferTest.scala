package indicators.test
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import indicators._

class DoubleRingBufferTest extends FunSuite with ShouldMatchers{ 
  test("Test DoubleRingBuffer set/exceptions") {
    val buff = new DoubleRingBuffer(4)
    evaluating {buff.get(0)} should produce [IndexOutOfBoundsException]
    buff.set(3,3)
    assert (buff.get(3)==3)
    assert (buff.size==4)
    assert (buff.capacity == 4)
    evaluating {buff.set(4,3)} should produce [IndexOutOfBoundsException]   
  }
  
  test("Test DoubleRingBuffer add/get/size/capacity") {
    val buff = new DoubleRingBuffer(4)
    buff.add(2)
    buff.add(4)
    buff.add(5)
    assert (buff.get()==5 && buff.get(0)==5 && buff.get(1)==4 && buff.get(2)==2)
    assert (buff.size==3)
    assert (buff.capacity == 4)
    buff.add(6)
    buff.add(7)
    assert (buff.get(0)==7 && buff.get(1)==6 && buff.get(2)==5)
    assert (buff.size==4 && buff.capacity==4)    
  }

  test("Testing Size") {
    val buff = new DoubleRingBuffer(2)
    assert(buff.size==0)
    buff.add(2)
    assert(buff.size==1)
    buff.add(4)
    assert(buff.size()==2)
    buff.add(5)
    assert(buff.size==2)
  }

  test("Static Creation") {
    val buff = new DoubleRingBuffer(2)
    buff.add(4)
    buff.add(2)
    buff.add(44) 
    assert (buff.size==2) 
    val buff2 = DoubleRingBuffer.create(buff,2)
    assert (buff2(0)==buff(0))
    assert (buff2(1)==buff(1))
    assert (buff2.max==buff.max)
    assert (buff2.size==2)
    evaluating {DoubleRingBuffer.create(buff,1)} should produce [RuntimeException]
    assert(DoubleRingBuffer.create(buff).size==buff.size)
  }
}

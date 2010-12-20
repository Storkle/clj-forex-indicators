package indicators.test
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import indicators._
  
class PriceStreamTest extends FunSuite with ShouldMatchers{
  var stream = Csv.get("/home/seth/Desktop/aud_nzd.csv")
  var stream_ref = stream.ref
  test("Testing PriceStream") {
    var (o,h,l,c) = (1,2,3,4)
    stream.add(o,h,l,c)
    assert (stream.open(0)==o && stream.high(0)==h && stream.low(0)==l &&
	    stream.close(0)==c)
    assert (stream.ref==stream_ref+1)
    stream.setHead(5,6,7,8)
    assert (stream.open(0)==5 && stream.high(0)==6 && stream.low(0)==7 &&
	    stream.close(0)==8)    
  }  
}

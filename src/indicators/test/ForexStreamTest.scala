package indicators.test
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import indicators._
 
class ForexStreamTest extends FunSuite with ShouldMatchers{
  var stream = Csv.get("/home/seth/Desktop/aud_nzd.csv")
  test("forex stream test") {
    var s = ForexStream.create(stream,"AUDNZD",60)
    var i=20
    assert (s.symbol=="AUDNZD" && s.timeframe==60)
    assert (s.high(i)==stream.high(i))
    assert (s.low(i)==stream.low(i))
    assert (s.close(i)==stream.close(i))
    assert (s.open(i)==stream.open(i))    
  }
}

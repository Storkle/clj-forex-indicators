package indicators
object ForexStream {
  def create(s:PriceStream,symbol:String,timeframe:Int) = {
    val b = new ForexStream(symbol,timeframe,s.size)
    b.reset(s)
    b
  }
  def create(s:ForexStream) = {
    val b = new ForexStream(s.symbol,s.timeframe,s.size)
    b.reset(s)
    b
  }
}

class ForexStream(val symbol:String,val timeframe:Int,the_capacity:Int=1000) extends PriceStream(the_capacity) {
  var headTime:Long = 0
}
 

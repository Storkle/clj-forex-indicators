package indicators

object Csv {  
  def get(file:String,capacity:Int=1000):PriceStream = {
    var s = new PriceStream(capacity)
    var lines = scala.io.Source.fromFile(file).getLines
    for (l<-lines) {
      var dat = l.split(",")
      var open = java.lang.Double.parseDouble(dat(2))
      var high = java.lang.Double.parseDouble(dat(3))
      var low = java.lang.Double.parseDouble(dat(4))
      var close = java.lang.Double.parseDouble(dat(5))
      s.add(open,high,low,close) 
    }
    s
  }
}  
 
object Test extends Application {
  var s = ForexStream.create(Csv.get("/home/seth/Desktop/aud_nzd.csv"),"AUDNZD",60)  
  println(s.close()+" "+s.high()+" "+s.low()+" "+s.open())
  println("size is "+s.size) 
}

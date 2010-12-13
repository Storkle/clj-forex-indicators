(ns indicators.test.SMA
  (:use [indicators.collection.stream]
	[clojure.test])
  (:import (indicators SMA VMA)
	   (indicators.collection PriceStream)
	   indicators._test.Csv))

(comment
  (let [stream (repeat 10000 1.0)]
    (def pstream (new-pstream stream stream stream stream)))

  (def stream (Csv/get "/home/seth/Desktop/aud_nzd.csv"))
 
  (def sequence (.Open stream)) 
  (def vma (VMA. stream 2 2 20)))

 
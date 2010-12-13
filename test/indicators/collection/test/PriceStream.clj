(ns indicators.collection.test.PriceStream
  (:use [indicators.collection.stream] [clojure.test])
  (:import (indicators.collection PriceStream)))
(deftest Price_Stream
  (let [pstream (new-pstream '(2.0 3.0 4.0) '(2 3.0 4.0)
			     '(5.0 3.0 2.0) '(4.0 3.0 2.0))
	stream (PriceStream. pstream)
	high (.High stream)]
    (is (= (.get high 0) 4))
    (are [x y] (= x y)
	 (.get high) 4
	 (.get high 1) 3
	 (.get high 2) 2)))



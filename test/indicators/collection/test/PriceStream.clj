(ns indicators.collection.test.PriceStream
  (:use  [clojure.test])
  (:import (indicators.collection PriceStream)
	   (indicators.utils Csv)))
 ;;TODO: make into small, functional tests
(let [parent (Csv/get "/home/seth/Desktop/aud_nzd.csv")
      child (PriceStream. parent)]
 (deftest Price_Stream
   (are [x y] (= x y)
	(.head parent) 472
	(.open parent) 1.31636
	(.high parent) 1.31708 
	(.low parent) 1.31407
	(.close parent) 1.31495
	
	(.size parent) 472
	(.head child) 472
	(.high child) 1.31708
	(.size child) 472)
   (.setHead parent 2 3 4 5)
   ;;open high low close
   (are [x y] (= x y)
	(.open parent) 2
	(.high parent) 3
	(.low parent) 4
	(.close parent) 5) 
   (.update child parent)
   (are [x y] (= x y)
	(.open child) 2
	(.high child) 3
	(.low child) 4
	(.close child) 5)
   (let [prev-head (.head parent)]
     (.add parent 10 11 12 13)
     (is (= (+ prev-head 1) (.head parent)))) ;;open high low close
   (are [x y] (= x y)
	(.open parent) 10
	(.high parent) 11
	(.low parent) 12
	(.close parent) 13)
   (.update child parent)
   (are [x y] (= x y)
	(.open child) 10
	(.high child) 11
	(.low child) 12
	(.close child) 13
	(.head child) (.head parent))))
  



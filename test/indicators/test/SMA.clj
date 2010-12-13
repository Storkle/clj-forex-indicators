(ns indicators.test.SMA
  (:use 
	[clojure.test])
  (:import (indicators SMA VMA)
	   (indicators.collection PriceStream)
	   indicators.utils.Csv))
 

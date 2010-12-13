(ns indicators.collection.stream
  (:use utils.general)
  (:import (indicators.collection IPPriceStream)))
 
;;newest to oldest
(defn- new-pstream*
  "construct a price stream from newest to oldest"
  ([open high low close] (new-pstream* open high low close 0))
  ([open high low close ref-num]
     (is (= (count open) (count high) (count low) (count close)) "in new-pstream, length of open, high, low, and close must be the same")
     (let [head (atom ref-num)
	   open (map double open)
	   low (map double low)
	   close (map double close)
	   high (map double high)]
       (reify IPPriceStream
	 (size [this] (.size open))
	 ;;call this on each second to create new price stream
	 (add [this o h l c]  
	      (new-pstream*  
	       (concat [o] (butlast open)) (concat [h] (butlast high))
	       (concat [l] (butlast low)) (concat [c] (butlast close)) 
	       (+ @head 1))) 
	 ;;IMPLEMENT Cloneable
	 (clone [this] (new-pstream* open high low close @head))
	 ;;convenience methods
	 (open [this] (nth open 0)) (open [this i] (nth open i))
	 (close [this] (nth close 0)) (close [this i] (nth close i))
	 (high [this] (nth high 0)) (high [this i] (nth high i))
	 (low [this] (nth low 0)) (low [this i] (nth low i))
	 ;;IMPLEMENT Reference 
	 (update [this] (swap! head inc))
	 (diff [this ref] (- @head ref)) 
	 (head [this] @head)))))      

;;TODO: we need a defn with defaults
(defn new-pstream
  "construct a price stream from oldest to newest"
  ([open high low close ref-num]
     (new-pstream* (reverse open) (reverse high) (reverse low) (reverse close) ref-num))
  ([open high low close]
     (new-pstream open high low close 0)))
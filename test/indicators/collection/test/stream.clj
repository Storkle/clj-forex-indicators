(ns indicators.collection.test.stream
  (:use [indicators.collection.stream] [clojure.test]))
;;TEST stream
(defn test-pstream-obj []
  (let [open '(2 3)  high '(3 4) low '(44 5) close '(2 3) ref-num 2]
    (new-pstream open high low close ref-num)))

;;TODO: how do we test exception throwing? this is important!!!!
(defn new-pstream-test 
  ([stream]
     ;;test ref-num and diff
     (are [x y] (= x y)
	  (.head stream) 2
	  (.diff stream 1) 1)
     ;;test update
     (.update stream)   
     (is (= (.head stream) (+ 2 1)))
     ;;test accessors
     (are [x y] (= x y)
	  (.open stream) 3 (.open stream 0) 3
	  (.high stream) 4 (.high stream 0) 4
	  (.low stream 1) 44 (.low stream 0) 5
	  (.close stream 1) 2 (.close stream 0) 3)
     ;;(is-error? (.close stream 2))
     ;;test add
     (let [old-stream stream stream (.add stream 2 3 4 5)] ;;open high low close
       (are [x y] (= x y)
	    (.open stream) 2
	    (.high stream) 3
	    (.low stream) 4 
	    (.close stream) 5
	    (.head stream) (+ (.head old-stream) 1)))
     ;;test clone
     ;;(if test-clone (is (new-pstream-test (.clone stream) false))) 
     (is (= (.size stream) 2))))

(deftest test-new-pstream (new-pstream-test (test-pstream-obj)))
(deftest test-new-pstream-clone (new-pstream-test (.clone (test-pstream-obj))))
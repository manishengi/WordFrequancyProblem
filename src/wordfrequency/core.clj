(ns wordfrequency.core 
(:gen-class))

(ns tokenize
 (:import (java.io BufferedWriter FileWriter)))

(defn split-words [text]
 "split text into a list of words"
 (re-seq #"\w+" text))
  
(defn printwords [words-hash]
 (map (fn [[a b]] (str b ": " a "\n")) words-hash))

(defn write-to-file [file-name lines]
 (with-open [wtr (BufferedWriter. (FileWriter. file-name))]
  (doseq [line lines] (.write wtr line))))

(prn (write-to-file "output.txt"
   (printwords
     (sort-by val
     (frequencies(split-words
      (clojure.string/lower-case (slurp "test.txt"))))))))
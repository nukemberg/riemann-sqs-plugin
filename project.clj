(defproject riemann-sqs-plugin "0.1.0-SNAPSHOT"
  :description "Riemann plugin to consume events from SQS"
  :url "https://github.com/avishai-ish-shalom/riemann-sqs-plugin"
  :license {:name "Apache v2"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies []
  :profiles {
    :dev {
      :dependencies [
	[org.clojure/clojure "1.6.0"]
        [midje "1.6.3"]
        [riemann "0.2.10"]
      ]
    }
  }
  :plugins [[lein-midje "3.0.0"]]
)

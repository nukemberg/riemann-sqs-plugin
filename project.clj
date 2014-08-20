(defproject riemann-sqs-plugin "0.1.0-SNAPSHOT"
  :description "Riemann plugin to consume events from SQS"
  :url "https://github.com/avishai-ish-shalom/riemann-sqs-plugin"
  :license {:name "Apache v2"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [
  					[org.clojure/clojure "1.6.0"]
  					[amazonica "0.2.24" :exclusions [com.amazonaws/aws-java-sdk]]
  				]
  :profiles {
    :dev {
      :dependencies [
        [midje "1.6.3"]
      ]
    }
  }
)

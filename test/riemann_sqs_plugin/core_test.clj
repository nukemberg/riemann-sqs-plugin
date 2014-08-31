(ns riemann-sqs-plugin.core-test
  (:require [midje.sweet :refer :all]
            [midje.util :refer [expose-testables]]
            [riemann.logging :as logging]
            [riemann-sqs-plugin.core :refer :all]))

(expose-testables riemann-sqs-plugin.core)

(logging/init {:console true})
(logging/set-level org.apache.log4j.Level/DEBUG)

(facts "about `consume`"
  (let [
        __good-msg__ {:body ...message... :message-id ...message-id...}
        __bad-msg__  {:body ...bad-message... :message-id ...bad-message-id...}
        ; for some reason, using background to describe the messages as metaconstants doesn't work. i would like to do this:
        ; ..bad-msg.. =contains=> {:body ..bad-message.. :message-id ..bad-message-id..}
        ; but it doesn't work. sigh.
       ]
    (against-background [
        (---parser-fn--- ...message...) => ...parsed-message...
        (---parser-fn--- ...bad-message...) =throws=> (Exception.)
        (---delete-message-batch--- anything) => nil
        (---receive-messages---) => {:messages [__good-msg__ __bad-msg__]}
        ]
    (fact "properly recieve message and stream to core"
      (consume (atom ...core...) {:parser-fn ---parser-fn---} ---receive-messages--- ---delete-message-batch---) => nil
      (provided
        (---receive-messages---) => {:messages [__good-msg__]}
        (#'riemann.core/stream! ...core... ...parsed-message...) => nil))
    (fact "continue even if unable to parse message but don't delete faulty messages"
      (consume (atom ...core...) {:parser-fn ---parser-fn---} ---receive-messages--- ---delete-message-batch---) => nil
      (provided
        (#'riemann.core/stream! ...core... ...parsed-message...) => nil
        (---delete-message-batch--- [__good-msg__]) => nil))
    (fact "continue even if unable to parse message and delete message if :delete-all is true"
      (consume (atom ...core...) {:parser-fn ---parser-fn--- :delete-all true} ---receive-messages--- ---delete-message-batch---) => nil
      (provided
        (#'riemann.core/stream! ...core... ...parsed-message...) => nil
        (---delete-message-batch--- [__good-msg__ __bad-msg__]) => nil)
      )
)))

# riemann-sqs-plugin

A [Riemann](http://riemann.io) plugin to consume events from AWS SQS queue.

## Usage

In your riemann.config

```clojure

(load-plugins) ; will load plugins from the classpath

(sqs/sqs-consumer {:queue-url "https://us-west-1.queue.amazonaws.com/22222313133/test"
                       :concurrency 1 ; number of consumer threads
                       :max-number-of-messages 10 ; max number of messages to fetch in one query
                       :wait-time-seconds 10 ; polling timeout
                       :parser-fn #(json/parse-string % true) ; message parsing function, the sample function here is the default
                       :delete-all true ; if true delete messages even if failed to handle them
                       })

```

## Installing

You will need to build this module for now and push it on riemann's classpath, for this
you will need a working JDK, JRE and [leiningen](http://leiningen.org).

First build the project:

```
lein uberjar
```

The resulting artifact will be in `target/riemann-sqs-input-standalone-0.1.0.jar`.
You will need to push that jar on the machine(s) where riemann runs, for instance, in
`/usr/lib/riemann/riemann-sqs-input.jar`.

If you have installed riemann from a stock package you will only need to tweak
`/etc/default/riemann` and change
the line `EXTRA_CLASSPATH` to read:

```
EXTRA_CLASSPATH=/usr/lib/riemann/riemann-sqs-input.jar
```

You can then use exposed functions, provided you have loaded the plugin in your configuration.

## License

Copyright © 2014 Avishai Ish-Shalom

Distributed under the Apache V2 License

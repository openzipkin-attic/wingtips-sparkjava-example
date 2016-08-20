### Wingtips tracing example application.

Work in Progress

This shows how to create client in Cujojs-rest zipkin instrumented library, server in Spark java and how to trace communication between them.

How to run: start Zipkin on localhost, start SparkServer.java, then run:
`node CujojsClient.js`

Navigate to: `http://localhost:9411` to see all the traces.

###Results:
![Screenshot](zipkin.png "Zipkin UI")


###Current Issues
1. [openzipkin/zipkin-js/issues/33](https://github.com/openzipkin/zipkin-js/issues/33)
2. [Nike-Inc/wingtips/issues/14](https://github.com/Nike-Inc/wingtips/issues/14)



### Wingtips tracing example application.

Work in Progress

This shows how to create client in Cujojs-rest zipkin instrumented library, server in Spark java and how to trace communication between them using [Wingtips](https://github.com/Nike-Inc/wingtips)

##How to run: 
1. Run `npm install` in project root directory
2. Start Zipkin on localhost - [Download](http://zipkin.io/pages/quickstart.html)
3. Compile and start SparkServer.java
4. Run from `/src` directory: `node CujojsClient.js`

Navigate to: `http://localhost:9411` to see all the traces.

###Results:
![Screenshot](zipkin.png "Zipkin UI")


###Current Issues
1. [openzipkin/zipkin-js/issues/33](https://github.com/openzipkin/zipkin-js/issues/33)

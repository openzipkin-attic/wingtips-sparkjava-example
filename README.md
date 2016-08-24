## Wingtips tracing example application.

Work in Progress

This shows how to create server in [Spark Web Framework](http://sparkjava.com/) and how to implement tracing using [Wingtips](https://github.com/Nike-Inc/wingtips)

##How to run: 
1. Compile and run `SparkServer.java`
2. Start Zipkin on localhost - [Download](http://zipkin.io/pages/quickstart.html)
3. Navigate to `http://localhost:9999/request`

Navigate to: `http://localhost:9411` to see all the traces in Zipkin.

###Results:
![Screenshot](zipkin.png "Zipkin UI")

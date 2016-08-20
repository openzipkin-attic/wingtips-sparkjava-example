import com.nike.wingtips.Span;
import com.nike.wingtips.Tracer;
import com.nike.wingtips.http.HttpRequestTracingUtils;
import com.nike.wingtips.zipkin.WingtipsToZipkinLifecycleListener;

import static spark.Spark.*;

public class SparkServer {
    public static void main(String[] args) {
        port(9999); //start server at port 9999
        Tracer.getInstance().addSpanLifecycleListener(new WingtipsToZipkinLifecycleListener("spark-server", "example-local-component-name", "http://localhost:9411"));

        //Spark before filter
        before((request, response) -> {
            RequestWithHeadersImpl incomingRequest = new RequestWithHeadersImpl(request);
            Span parentSpan = HttpRequestTracingUtils.fromRequestWithHeaders(incomingRequest, null);
            if (parentSpan == null)
                Tracer.getInstance().startRequestWithRootSpan("no-parent-id-server-span");
            else
                Tracer.getInstance().startRequestWithChildSpan(parentSpan, "server-span");
        });

        get("/request", (request, response) -> {
            //This demonstrates how to create subs-pans
            Tracer.getInstance().startSubSpan("server-first-sub-span", Span.SpanPurpose.LOCAL_ONLY);
            Thread.sleep(500);
            Tracer.getInstance().completeSubSpan();

            Thread.sleep(750);

            Tracer.getInstance().startSubSpan("server-second-sub-span", Span.SpanPurpose.LOCAL_ONLY);
            Thread.sleep(500);
            Tracer.getInstance().completeSubSpan();

            return "Hello world";
        });

        //Spark after filter
        after((request, response) -> {
            response.header("X-B3-TraceId", Tracer.getInstance().getCurrentSpan().getTraceId());
            response.header("X-B3-ParentSpanId", Tracer.getInstance().getCurrentSpan().getParentSpanId());
            response.header("X-B3-SpanId", Tracer.getInstance().getCurrentSpan().getSpanId());
            response.header("X-B3-Sampled", "1");

            Tracer.getInstance().completeRequestSpan();
        });

        exception(InterruptedException.class, (exception, request, response) -> {
            System.out.println("\n\n INTERRUPTED EXCEPTION\n PRINTING STACK TRACE \n\n");
            exception.printStackTrace();
        });
    }
}

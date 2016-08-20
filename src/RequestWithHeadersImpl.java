import com.nike.wingtips.http.RequestWithHeaders;
import spark.Request;

public class RequestWithHeadersImpl implements RequestWithHeaders {
    private Request sparkRequest;

    RequestWithHeadersImpl(Request sparkRequest){
        this.sparkRequest = sparkRequest;
    }

    @Override
    public String getHeader(String headerName) {
        return sparkRequest.headers(headerName);
    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }
}


package http;

import java.util.*;
import org.apache.http.*;

public class HTTPRequest {
    private Map<String, String> headers;
    private String body;
    private RequestLine requestLine;

    public HTTPRequest(Map<String, String> headers, String body, RequestLine requestLine) {
        this.headers = headers;
        this.body = body;
        this.requestLine = requestLine;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public String toString() {
        return "Request Line: " + getRequestLine() + '\n' +  "Headers: " + getHeaders();
    }

    public static HTTPRequest parse(List<String> lines) {
        String requestLineStr = lines.get(0);
        String[] requestLineArr = requestLineStr.split(" ");
        String method = requestLineArr[0];
        String URI = requestLineArr[1];
        String status = requestLineArr[2];

        RequestLine requestLine = new RequestLine(URI, method, status);


        List<String> headerList = lines.subList(1, lines.size()-1);
        Map<String, String> headersMap = new HashMap<>();

        for(int i = 0; i <headerList.size(); i++) {
            String line = headerList.get(i);
            String[] arr = line.split(" ");
            headersMap.put(arr[0], arr[1]);
        }


        return new HTTPRequest(headersMap, "", requestLine);
    }
}

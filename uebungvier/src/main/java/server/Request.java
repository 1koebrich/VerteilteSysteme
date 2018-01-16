package server;

import server.interfaces.HTTPRequest;

import java.util.List;
import java.util.Map;


public class Request implements HTTPRequest {

    String method;
    String pathInfo;
    Map<String, String> headers;
    Map<String, String> parameters;


    public Request(String method, String pathInfo, Map<String, String> headers, Map<String, String> parameters) {
        this.method = method;
        this.pathInfo = pathInfo;
        this.headers = headers;
        this.parameters = parameters;
    }

    public String getMethod() {
        return this.method;
    }

    public String getPathInfo() {
        return this.pathInfo;
    }

    public List<String> getHeaderNames() {
        List<String> headerKeys = null;
        for (String key: headers.keySet()) {
            headerKeys.add(key);
        }
        return headerKeys;
    }

    public String getHeader(String name) {
        return headers.get(name);
    }

    public List<String> getParameterNames() {
        List<String> parameterKeys = null;
        for (String key: parameters.keySet()) {
            parameterKeys.add(key);
        }
        return parameterKeys;
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }
}

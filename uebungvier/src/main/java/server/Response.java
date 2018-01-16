package server;

import server.interfaces.HTTPResponse;

import java.io.PrintWriter;


public class Response implements HTTPResponse {

    public PrintWriter writer;
    private String name;
    private String value;

    public Response(PrintWriter writer){
        this.writer = writer;
    }

    public void setHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public PrintWriter getWriter() {
        return null;
    }
}

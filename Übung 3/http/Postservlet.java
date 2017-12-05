
package http;


public class Postservlet extends HTTPServlet {
   @Override
   public void doPost(HTTPRequest request, HTTPResponse response){  
        System.out.println("HTTP/1.0 200 OK");
        System.out.println("Content-Type: text/html");
        System.out.println("");
        System.out.println("<p>POST REQUEST:</p><p>" + request + "</p>");
   }
}

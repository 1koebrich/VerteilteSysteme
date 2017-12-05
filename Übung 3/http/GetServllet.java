
package http;


public class GetServllet extends HTTPServlet {
   @Override
   public void doGet(HTTPRequest request, HTTPResponse response){  
        System.out.println("Body: "+request.getBody());
        System.out.println(request.toString());
   }
}
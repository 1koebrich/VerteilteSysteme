package server.interfaces;


public interface HTTPRequest {
         
     public  String getMethod() ;
           
     public String getPathInfo() ;
        
     public java.util.List< String > getHeaderNames() ;
    
     public  String getHeader(String name) ;
    
     public java.util.List< String > getParameterNames() ;
    
     public  String getParameter(String name) ;
}

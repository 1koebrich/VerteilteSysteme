
package http;

import java.util.*;


public interface HTTPResponse {
    
    public Map<String, String> getHeaders();
    public String getBody();
    public String getResponse();
}

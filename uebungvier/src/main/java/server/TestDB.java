package server;


import java.util.HashMap;
import java.util.Map;


public class TestDB {

    public static void main( String[] args ) {

        InsertDataBaseServlet db = new InsertDataBaseServlet();

        //------------------------Erster Request---------------------------------
        Map<String, String>  headers = new HashMap<String, String>();

        Map<String, String>  parameters = new HashMap<String, String>();

        parameters.put("name", "Max");
        parameters.put("msg", "Hallo Welt");

        Request req = new Request("POST", "/insert", headers, parameters);
        //Hier dann einen richtigen Writer übergeben
        Response res = new Response(null);

        db.doPost(req,res);

        //------------------------Zweiter Request---------------------------------
        Map<String, String>  headers2 = new HashMap<String, String>();

        Map<String, String>  parameters2 = new HashMap<String, String>();

        parameters2.put("name", "Tim");
        parameters2.put("msg", "Hallo ");

        Request req2 = new Request("POST", "/insert", headers2, parameters2);
        //Hier dann einen richtigen Writer übergeben
        Response res2 = new Response(null);

        db.doPost(req2,res2);

        //-------------------------Auslesen----------------------------------------
        FetchAllDataBaseServlet fetch = new FetchAllDataBaseServlet();
        Request fetchReq = new Request("GET", "/fetchAll", new HashMap<String, String>(), new HashMap<String, String>());
        Response fetchRes = new Response(null);
        fetch.doGet(fetchReq, fetchRes);


    }

}

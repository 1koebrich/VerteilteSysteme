
package http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class HTTP {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader bufferedReader;
    private static String inputLine;
    private static PrintWriter output;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(3000);

            while (true) {
                List<String> list = new ArrayList<String>();
                clientSocket = serverSocket.accept();
                bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                output = new PrintWriter(clientSocket.getOutputStream());

                inputLine = bufferedReader.readLine();
                while (inputLine.length() > 0) {
                    System.out.println(inputLine);
                    list.add(inputLine);
                    inputLine = bufferedReader.readLine();
                }
                bufferedReader.readLine();
                String bodyLine = bufferedReader.readLine();
                while(bodyLine != null && bodyLine.length() > 0){
                    System.out.println(bodyLine);
                    output.println(bodyLine);
                    bodyLine = bufferedReader.readLine();
                }

                HTTPRequest httprequest = HTTPRequest.parse(list);
                HTTPResponse httpresponse = new HTTPResponse();
                if (httprequest.getRequestLine().getMethod().equals("GET")){
                    GetServllet gs = new GetServllet();
                    gs.doGet(httprequest, httpresponse);
                }

                if (httprequest.getRequestLine().getMethod().equals("POST")){
                    Postservlet gs = new Postservlet();
                    gs.doPost(httprequest, httpresponse);
                }

                output.close();
                bufferedReader.close();
                clientSocket.close();

            }
        } catch (IOException error) {
            System.out.println(error);

        }

    }
}

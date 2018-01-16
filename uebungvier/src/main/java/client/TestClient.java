package client;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;


public class TestClient {

    public static void sendRequest(Socket client, String file) throws IOException {
        PrintWriter printWriter =
                new PrintWriter(
                        new OutputStreamWriter(
                                client.getOutputStream()));
        printWriter.print(file);
        printWriter.flush();
        System.out.println("Message send");
    }

    public static  void getResponse(Socket sock) throws IOException {
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        // receive file
        byte [] mybytearray  = new byte [6022386];
        InputStream is = sock.getInputStream();
        fos = new FileOutputStream("/opt/zcws/source/verteiltesysteme/src/main/resources/targetClient/downloadedFile.txt");
        bos = new BufferedOutputStream(fos);
        bytesRead = is.read(mybytearray,0,mybytearray.length);
        current = bytesRead;

        do {
            bytesRead =
                    is.read(mybytearray, current, (mybytearray.length-current));
            if(bytesRead >= 0) current += bytesRead;
        } while(bytesRead > -1);

        bos.write(mybytearray, 0 , current);
        bos.flush();
        System.out.println("File "
                + " downloaded (" + current + " bytes read)");


    }

    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File Download");
        System.out.println("-------------");
        System.out.println();

        System.out.println("Please insert a server address:");
        String host = scanner.nextLine();

        System.out.println();

        System.out.println("Please insert a file:");
        String file = scanner.nextLine();
        System.out.println();



        try{
            SocketAddress addr = new InetSocketAddress( host, 1080 );
            Socket socket = new Socket();
            socket.connect( addr,100);

            sendRequest(socket, file);
            getResponse(socket);


        }
        catch (Exception e) {
            System.out.println("No Server found with address " + host + " and port 1080");
        }




    }

}

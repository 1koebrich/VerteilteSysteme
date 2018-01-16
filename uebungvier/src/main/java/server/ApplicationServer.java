package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class ApplicationServer {

    public static void sendFile(Socket socket, String path){
        byte[] bytearray = new byte[1024*16];
        FileInputStream fis = null;
        try {

            fis = new FileInputStream(path);
            OutputStream output= socket.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(fis);

            int readLength = -1;
            while ((readLength = bis.read(bytearray)) > 0) {
                output.write(bytearray, 0, readLength);

            }
            bis.close();
            output.close();
        }
        catch(Exception ex ){

            ex.printStackTrace();
        }
    }

    public static String readMessage(Socket socket) throws IOException{
        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        char[] buffer = new char[200];
        int anzahlZeichen = bufferedReader.read(buffer, 0, 200); 

        String message = new String(buffer, 0, anzahlZeichen);

        return message;
    }

    private static void handleConnection( Socket client ) {
        try {
            String fileName = readMessage(client);


            String path = Configuration.ROOTPATH + new String(fileName);
            System.out.println("requested file:" + path);
            sendFile(client, path);
        }catch (Exception e){

        }
    }


    public static void main( String[] args ) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1080);
        System.out.println("Server is up");

        Socket client = serverSocket.accept();
        System.out.println("Client accepted");

        try{
            handleConnection(client);

        } catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            if ( client != null )
                try { client.close(); } catch ( IOException e ) { }
        }

        System.out.println("Client was closed");

    }

}

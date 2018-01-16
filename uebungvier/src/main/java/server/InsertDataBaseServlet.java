package server;

import server.interfaces.HTTPRequest;
import server.interfaces.HTTPResponse;
import server.interfaces.HTTPServlet;

import java.sql.*;
import java.util.Date;


public class InsertDataBaseServlet extends HTTPServlet {


    private String createInsertStatement(int id, String creation, String author, String msg, int version){
        String statement = "INSERT INTO messages VALUES(" + id + ", '" + creation +"', '" + author + "', '" + msg + "', " + version + ")";
        return statement;
    }



    @Override
    public void service(HTTPRequest req, HTTPResponse re) {
        //zu DB connecten
        Connection connection = null;

        try{
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException());
        }


        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:/home/Chief/Desktop/JavaProjekte/messages.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            //Get last index
            ResultSet lastIndexSet = statement.executeQuery("SELECT * FROM messages ORDER BY id DESC LIMIT 1");
            int newIndex;
            if(lastIndexSet.next()){
                 newIndex = Integer.parseInt(lastIndexSet.getString("id")) + 1;
            }
            else{
                newIndex = 1;
            }


            //Auslesen des Requests
            String name = req.getParameter("name");
            String message = req.getParameter("msg");
            int version = 1;
            Date date = new Date();
            String creation = date.toString();


            statement.executeUpdate(createInsertStatement(newIndex, creation, name, message, version));


            //Response formulieren

            re.setHeader("state","OK");
            connection.close();

        } catch (SQLException e)  {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println("Error:" + e.getMessage());
            re.setHeader("state","ERROR");
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e);
                re.setHeader("state","ERROR");
            }
        }

    }

    @Override
    public void doGet(HTTPRequest req, HTTPResponse res) {
        super.doGet(req, res);
    }

    @Override
    public void doPost(HTTPRequest req, HTTPResponse res) {
        super.doPost(req, res);
    }

    @Override
    public void doDelete(HTTPRequest req, HTTPResponse res) {
        super.doDelete(req, res);
    }

    @Override
    public void doHead(HTTPRequest req, HTTPResponse res) {
        super.doHead(req, res);
    }

    @Override
    public void doOptions(HTTPRequest req, HTTPResponse res) {
        super.doOptions(req, res);
    }

    @Override
    public void doPut(HTTPRequest req, HTTPResponse res) {
        super.doPut(req, res);
    }

    @Override
    public void doTrace(HTTPRequest req, HTTPResponse res) {
        super.doTrace(req, res);
    }
}

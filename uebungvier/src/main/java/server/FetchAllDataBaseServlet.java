package server;

import server.interfaces.HTTPRequest;
import server.interfaces.HTTPResponse;
import server.interfaces.HTTPServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FetchAllDataBaseServlet extends HTTPServlet {
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


            ResultSet messages = statement.executeQuery("SELECT * FROM messages");

            List<Message> messageList= new ArrayList<Message>();

            while(messages.next()){
                Message tmp = new Message();

                tmp.setId(messages.getInt("id"));
                tmp.setAuthor(messages.getString("author"));
                tmp.setMessage(messages.getString("msg"));
                tmp.setCreation(messages.getString("creation"));
                tmp.setVersion(messages.getInt("version"));

                messageList.add(tmp);
            }



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

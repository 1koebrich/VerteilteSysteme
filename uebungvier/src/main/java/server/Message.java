package server;


public class Message {
    public int id;
    public String author;
    public String creation;
    public int version;
    public String message;

    public Message() {

    }

    public Message(int id, String author, String creation, int version, String message){
        this.id = id;
        this.author = author;
        this.creation = creation;
        this.version = version;
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package init;

import db.Connector;


public class Database {
    public void main() {
        Connector z = newConnection("a","a","a");
    }

    public Connector newConnection(String url, String user, String password){
        return new Connector(url,user,password);



    }
}

package example;

import data.core.Action;
import data.core.Option;
import db.Connector;
import db.Db;

import java.sql.Connection;

public class Example {
    private static final Connector connector = new Connector("jdbc:hsqldb:mem:test", "SA", "");

    public static void main(String[] args) {

        // Initialize connection
        connector.withConnection(new Action<Connection>(){
            public void apply(Connection connection) {

                // Initialize Db
                Db database = new Db();

                database.executeUpdate(connection,"create table test ( version varchar(10), second varchar(255) )");
                database.updateObjects(connection, "insert into test values (?, ?)", 1, "foo");
                database.updateObjects(connection,"update test set version=?", 3);

                Boolean exists = database.queryExists(connection,"select * from test");
                System.out.println(exists);

                Option<Integer> version = database.queryInt(connection, "select * from test", 1);
                System.out.println( version.getOr(0) );

                Option<String> second = database.queryString(connection, "select second from test",1);
                System.out.println( second.getOrDie() );


            }
        });
    }
}

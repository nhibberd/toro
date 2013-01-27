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

                database.executeUpdate(connection,"create table test ( version varchar(10), foo varchar(255) )");
                database.executeUpdateObjects(connection,"insert into test values (?, ?)", 1, "stuff");

                Boolean exists = database.queryExists(connection,"select * from test");
                System.out.println(exists);

                //Option<Object> version = database.queryObject(connection,"select * from test",1);
                //System.out.println(version.);


            }
        });
    }
}

package java.example;

import java.data.core.Action;
import java.data.core.FromDb;
import java.data.core.Option;
import java.toro.*;

import java.sql.Connection;

import static java.toro.Getters.*;
import static java.toro.Convert.*;

public class Example {

    // Initialize Connector
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

                Option<Integer> version = database.query(connection, "select * from test", 1, getInteger);
                System.out.println( "version : " + version.getOr(0) );

                Option<String> second = database.query(connection, "select second from test", 1, getString);
                System.out.println( "second : " + second.getOrDie() );


                FromDb<String> z = getToFromDb(getString);
                Option<String> zz = database.queryObject(connection,"select second from test", z);
                System.out.println("zz.getOrDie() = " + zz.getOrDie());


                Option<String> s = database.query(connection,"select second from test", getString);
                System.out.println("s = " + s.getOrDie());

                Option<TestObject> tmp = database.queryObject(connection, "select * from test", new TestObject());
                System.out.println("tmp.getOrDie().version = " + tmp.getOrDie().version);
                System.out.println("tmp.getOrDie().second = " + tmp.getOrDie().second);



            }
        });
    }
}
package example;

import toro.Action;
import toro.FromDb;
import toro.Option;
import toro.*;

import java.sql.Connection;
import java.util.List;

import static toro.Getters.*;
import static toro.Convert.*;

public class Example {

    // Initialize Connector
    private static final Connector connector = new Connector("jdbc:hsqldb:mem:test", "SA", "");



    // Initialize Db
    static Db database = new Db();

    public static void main(String[] args) {

        // Initialize connection with Action - void return
        connector.withConnection(new Action<Connection>(){
            public void apply(Connection connection) {

                // Create table
                database.executeUpdate(connection,"create table test ( version varchar(10), second varchar(255) )");

                // Insert values
                database.updateObjects(connection, "insert into test values (?, ?)", 1, "foo");

                // Update values
                database.updateObjects(connection,"update test set version=?", 5);

                // Update values where X
                database.updateObjects(connection,"update test set version=? where second=?", 5, "foo");

                // More values for tests
                database.updateObjects(connection, "insert into test values (?, ?)", 1, "one");
                database.updateObjects(connection, "insert into test values (?, ?)", 2, "two");
                database.updateObjects(connection, "insert into test values (?, ?)", 3, "three");

                // Check if table has values
                Boolean exists = database.queryExists(connection,"select * from test");
                System.out.println("exists : " + exists);

                // Will return first result in resultSet's
                Option<Integer> version = database.query(connection, "select * from test", 1, getInteger);
                Option<String> second = database.query(connection, "select second from test", 1, getString);
                System.out.println( "version : " + version.getOr(0) );    // getOr - default to 0
                System.out.println( "second : " + second.getOrDie() );    // getOrDie - runtime exception

                // To return all results from resultSet's
                List<Integer> list = database.queryList(connection, "select * from test", 1, getInteger);
                for (Integer integer : list) {
                    System.out.println("integer = " + integer);
                }


                // Convert a object implementing Get<T> to FromDb<T>
                FromDb<String> from = getToFromDb(getString);
                Option<String> result = database.queryObject(connection,"select second from test", from);
                System.out.println("result : " + result.getOrDie());

                // Query using an object
                Option<String> s = database.queryFromObjects(connection, "select second from test where version=?", getString, 5);
                System.out.println("from single object = " + s.getOrDie());

                // Query using multiply objects
                Option<String> multiS = database.queryFromObjects(connection,"select second from test where version=? and second=?", getString, 5, "foo");
                System.out.println("from multiple objects = " + multiS.getOrDie());

                // TestObject implements FromDB<T>
                // Therefore it will return the TestObject using the implementation provided
                Option<TestObject> testObject = database.queryObject(connection, "select * from test", new TestObject());
                System.out.println("testObject version = " + testObject.getOrDie().version);
                System.out.println("testObject second = " + testObject.getOrDie().second);

            }
        });

        // Initialize connection with Function to return X
        String stringReturned = connector.withConnection(new Function<Connection, String>(){
            public String apply(Connection connection) {
                //return String from query
                return database.queryFromObjects(connection, "select second from test where version=?", getString, 5).getOr("error");
            }
        });

        System.out.println("stringReturned = " + stringReturned);
    }
}
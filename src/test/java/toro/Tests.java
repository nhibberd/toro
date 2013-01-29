package toro;

import junit.framework.TestCase;

import java.sql.Connection;

public class Tests extends TestCase {

    private static final Connector connector = new Connector("jdbc:hsqldb:mem:test", "SA", "");

    public void test() {

        connector.withConnection(new Action<Connection>() {
            public void apply(Connection connection) {


            }
        });
    }
}

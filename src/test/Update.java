import data.core.Action;
import db.Connector;
import junit.framework.TestCase;

import java.sql.Connection;
public class Update extends TestCase {
    private static final Connector connector = new Connector("jdbc:postgresql://localhost/test", "test", "test");


    public void test(){
        connector.withConnection(new Action<Connection>() {
            public void apply(Connection connection) {











            }
        });
    }



    void check() {
        System.out.println("");
    }


}

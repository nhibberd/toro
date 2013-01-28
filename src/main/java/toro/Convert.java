package java.toro;

import java.data.core.FromDb;
import java.data.core.Get;

public class Convert {
    public static <T> FromDb<T> getToFromDb(final Get<T> object){
        return new FromDb<T>() {
            public T from(EdgeResultSet resultSet) {
                return object.result(resultSet,1);
            }
        };
    }
}

package toro;

import data.core.FromDb;
import data.core.Get;

public class Convert {
    public <T> FromDb<T> getToFromDb(final Get<T> object){
        return new FromDb<T>() {
            public T from(EdgeResultSet resultSet) {
                return object.result(resultSet,1);
            }
        };
    }
}

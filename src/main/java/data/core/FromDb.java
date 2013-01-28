package java.data.core;

import java.toro.EdgeResultSet;

public interface FromDb<T> {
    T from(EdgeResultSet resultSet);
}

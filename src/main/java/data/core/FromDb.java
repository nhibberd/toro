package data.core;

import toro.EdgeResultSet;

public interface FromDb<T> {
    T from(EdgeResultSet resultSet);
}

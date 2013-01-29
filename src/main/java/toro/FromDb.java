package toro;

import toro.EdgeResultSet;

public interface FromDb<T> {
    T from(EdgeResultSet resultSet);
}

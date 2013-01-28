package toro;

import data.core.FromDb;

public class Tmp implements FromDb<Tmp> {
    public String data;
    public Integer version;

    public Tmp(String data, Integer version) {
        this.data = data;
        this.version = version;
    }

    public Tmp from(EdgeResultSet resultSet) {
        return new Tmp(resultSet.getString(1),resultSet.getInt(2));

    }
}

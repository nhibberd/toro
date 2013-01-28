package toro;

import data.core.FromDb;

public class Tmp implements FromDb<Tmp> {
    public Integer version;
    public String second;

    public Tmp(Integer version, String second) {
        this.version = version;
        this.second = second;
    }

    public Tmp() {

    }

    public Tmp from(EdgeResultSet resultSet) {
        return new Tmp(resultSet.getInt(1),resultSet.getString(2));
    }
}

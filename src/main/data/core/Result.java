package data.core;

import data.error.DatabaseException;

public class Result<T>{
    private final Boolean status = null;
    private final T value;

    public Result(Boolean status, T value) {
        this.value = value;
    }

    public static <T> Result<T> some(T t) { return new Result<T>(true, t); }
    public static <T> Result<T> none() { return new Result<T>(false, null); }

    public T value() {
        if (status)
            return value;
        throw new DatabaseException();
    }

    public Boolean statusOK(){
        return status;
    }}
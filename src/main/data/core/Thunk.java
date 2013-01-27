package data.core;

public interface Thunk<A> {
    A apply();
}

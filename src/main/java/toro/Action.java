package toro;

public interface Action<A> {
    void apply(A a);
}

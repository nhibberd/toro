package java.data.core;

public abstract class Option<A> {
    public abstract <X> X fold(
      Thunk<X> none,
      Function<A, X> some  
    );
    
    public A getOrDie() {
        return fold(
          new Thunk<A>() {
              public A apply() {
                  throw new RuntimeException("Called getOrDie on none");
              }
          },
          new Function<A, A>() {
              public A apply(A a) {
                  return a;
              }
          }
        );
    }
    
    public boolean isSome() {
        return fold(
                new Thunk<Boolean>() {
                    public Boolean apply() {
                        return false;
                    }
                },
                new Function<A, Boolean>() {
                    public Boolean apply(A a) {
                        return true;
                    }
                }
        );
    }
    
    public boolean isNone() {
        return !isSome();
    }
    public A getOr(final A dfault) {
        return fold(
                new Thunk<A>() {
                    public A apply() {
                        return dfault;
                    }
                },
                new Function<A, A>() {
                    public A apply(A a) {
                        return a;
                    }
                }
        );        
    }
    
    public <B> Option<B> bind(final Function<A, Option<B>> f) {
        return fold(
                new Thunk<Option<B>>() {
                    public Option<B> apply() {
                        return none();
                    }
                },
                f
        );   
    }
    
    public <B> Option<B> map(final Function<A, B> f) {
        return bind(new Function<A, Option<B>>() {
            public Option<B> apply(A a) {
                return some(f.apply(a));
            }
        });
    }

    public static <A> Option<A> some(final A a) {
      return new Option<A>() {
          public <X> X fold(Thunk<X> n, Function<A, X> s) {
              return s.apply(a);
          }
      };
    }
    
    public static <A> Option<A> none() {
        return new Option<A>() {
            public <X> X fold(Thunk<X> n, Function<A, X> s) {
                return n.apply();
            }
        };
    }
    
}

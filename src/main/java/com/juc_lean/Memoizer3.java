package com.juc_lean;

import java.util.concurrent.*;

public class Memoizer3<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
//java.util.concurrent.ExecutionException: java.lang.NumberFormatException: For input string: "xxxxx"
    public static void main(String[] args) {
        Memoizer3<String,Integer> memoizer3 = new Memoizer3(new ExpensiveFunction());
        try {
            String arg = "arg";
            Integer xxxxx = memoizer3.compute(arg);
            System.out.println(xxxxx);
            Integer xx = memoizer3.compute(arg);
            System.out.println(xx);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

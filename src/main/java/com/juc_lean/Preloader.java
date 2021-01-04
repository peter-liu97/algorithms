package com.juc_lean;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.zip.DataFormatException;

import static com.juc_lean.ProductInfo.loadProductInfo;

public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });
    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataFormatException {
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if(cause instanceof DataFormatException){
                throw (DataFormatException)cause;
            }else {
                throw launderThrowable( e);
            }
        }
        return null;
    }

    public static RuntimeException launderThrowable(Throwable e) {
        if (e instanceof RuntimeException){
            return (RuntimeException)e;
        }
        else if (e instanceof Error){
            throw (Error)e;
        }
        else {
            throw new IllegalStateException("Not unchecked" , e);
        }
    }


}

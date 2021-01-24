package com.juc_lean;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/*
    锁细粒度 膨胀 系统
 */
public class ServerStatus {
    public final Set<String> users;
    public final Set<String> queries;

    public ServerStatus(Set<String> users, Set<String> queries) {
        this.users = users;
        this.queries = queries;
    }

    public void addUser(String userName){
        synchronized (users){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            users.add(userName);
            System.out.println("add(userName)");
        }
    }

    public void addQueries(String querie){
        synchronized (queries){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queries.add(querie);
            System.out.println("queries.add(querie);");
        }
    }


    public void removeUser(String userName){
        synchronized (users){
            users.remove(userName);
        }
    }


    public void removeQueries(String querie){
        synchronized (queries){
            queries.remove(querie);
        }
    }

    public static void main(String[] args) {
        Set<String> users = new HashSet<>();
        Set<String> queries = new HashSet<>();
        ServerStatus serverStatus = new ServerStatus(users, queries);

        /*
        交替获取锁,不会发生死锁
         */
        new Thread(()->{
            serverStatus.addUser("zs");
            serverStatus.addQueries("zs");
        }).start();
        new Thread(()->{
            serverStatus.addQueries("zs");
            serverStatus.addUser("zs");
        }).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

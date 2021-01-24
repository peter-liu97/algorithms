package com.juc_lean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Account extends ReentrantLock {
    private  long id;
    private  long money;

    public Account(){

    }
    public Account(long id, long money) {
        this.id = id;
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public boolean transferMoney(Account fromAcct,
                                 Account toAcct,
                                 DollarAmount amount,
                                 long timeout,
                                 TimeUnit unit){





        return false;
    }


}

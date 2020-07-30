package cn.jakuxa.Algorithms.JavaLearn.Threads;

import java.util.concurrent.locks.ReentrantLock;

public class _Lock implements Runnable{
    //并发问题与锁

    private Integer ticketNumbers = 100;
    private Boolean flag = true;

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (flag) {
            //模拟延时
            try {
                lock.lock();
                Thread.sleep(100);
                if (ticketNumbers > 0)
                    buy();
                else
                    return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public void buy() {
        System.out.println(Thread.currentThread().getName() + "取得了第" + ticketNumbers-- + "张票");
    }

    public static void main(String[] args) {
        _Lock con = new _Lock();
        String[] peoples = {"SPAS-12", "OTs-14", "Linn"};

        for (int i = 0; i < 3; i++) {
            new Thread(con, peoples[i]).start();
        }
    }
}

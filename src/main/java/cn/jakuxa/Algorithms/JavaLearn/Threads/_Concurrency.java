package cn.jakuxa.Algorithms.JavaLearn.Threads;

public class _Concurrency implements Runnable {
    //并发问题与锁

    private int ticketNumbers = 30;

    @Override
    public void run() {
        while (ticketNumbers > 0) {
            //模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "取得了第" + ticketNumbers-- + "张票");
        }
    }

    public static void main(String[] args) {
        _Concurrency con = new _Concurrency();
        String[] peoples = {"SPAS-12", "OTs-14", "Linn"};

        for (int i = 0; i < 3; i++) {
            new Thread(con, peoples[i]).start();
        }
    }
}

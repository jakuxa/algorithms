package cn.jakuxa.Algorithms.JavaLearn.Threads;

public class _Concurrency implements Runnable {
    //并发问题与锁

    private Integer ticketNumbers = 50;
    private Boolean flag = true;

    @Override
    public void run() {
        synchronized (this) {
            while (flag) {
                //模拟延时
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (this.ticketNumbers > 0)
                    buy();
                else
                    return;
            }
        }
    }

    public void buy() {
        System.out.println(Thread.currentThread().getName() + "取得了第" + this.ticketNumbers-- + "张票");
    }

    public static void main(String[] args) {
        _Concurrency con = new _Concurrency();
        String[] peoples = {"SPAS-12", "OTs-14", "Linn"};

        for (int i = 0; i < 3; i++) {
            Thread aha = new Thread(con, peoples[i]);
            aha.setPriority(5+i);
            aha.start();
        }
    }

}

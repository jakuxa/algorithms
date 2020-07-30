package cn.jakuxa.Algorithms.JavaLearn.Threads;

import lombok.AllArgsConstructor;

public class _Wait {

    //线程协作--生产消费模式
    //wait与sleep的区别就在于，sleep是抱着锁睡觉，而wait会放开

    public static void main(String[] args) {
        BufferArea bufferArea = new BufferArea();

        new Thread(new Producer(bufferArea)).start();
        Thread consumer =  new Thread(new Consumer(bufferArea));
        consumer.setDaemon(true);
        consumer.start();

    }
}

@AllArgsConstructor
class Item{
    public int id;
}

//缓冲区
class BufferArea{
    public int MAX = 10;
    public Item[] items = new Item[MAX];
    Integer count = 0;

    public synchronized Item sell() {
        if(count <= 0){
            // 停止销售
            try {
                System.out.println("货没辣");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //仓库未满，可以生产,更新保持生产者的继续生产

        count--;
        notifyAll();
        return items[count];
    }

    public synchronized void produce(Integer i){
        if(count >= MAX){
            System.out.println("仓库满啦"+count);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //有货，可以销售,更新保持消费者的唤醒
        items[count++] = new Item(i);
        notifyAll();
    }

}

class Consumer implements Runnable{
    private BufferArea bufferArea;

    @Override
    public void run() {
        while (true){
            System.out.println("消费者消费了 "+bufferArea.sell().id+" 号商品");
        }
    }

    public Consumer(BufferArea bf){
        this.bufferArea = bf;
    }
}

class Producer implements Runnable{
    private BufferArea bufferArea;

    @Override
    public void run() {
        for (int i = 1; i < 101; i++) {
            bufferArea.produce(i);
            System.out.println("生产者生产了第 "+i+" 号商品");
        }
    }

    public Producer(BufferArea bf){
        this.bufferArea = bf;
    }
}


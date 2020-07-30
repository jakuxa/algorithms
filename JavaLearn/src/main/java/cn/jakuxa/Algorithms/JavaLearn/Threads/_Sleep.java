package cn.jakuxa.Algorithms.JavaLearn.Threads;

public class _Sleep {

    public static void main(String[] args) {
        try {
            countDown(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void countDown(int countsNum) throws InterruptedException {
        while (true){
            Thread.sleep(1000);
            System.out.println(countsNum--);
            if(countsNum < 0)
                break;
        }
    }

}

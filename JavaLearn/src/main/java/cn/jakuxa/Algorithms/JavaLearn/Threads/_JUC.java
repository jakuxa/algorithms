package cn.jakuxa.Algorithms.JavaLearn.Threads;

import java.util.concurrent.CopyOnWriteArrayList;

//JUC安全类型集合
public class _JUC {

    public static void main(String[] args) {

        //线程安全的arraylist，但不建议使用

        CopyOnWriteArrayList<String> cpl = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int j = i;
            new Thread(() -> cpl.add("add" + j)).start();
        }
        System.out.println(cpl.size());
    }


}
package cn.jakuxa.Algorithms.JavaLearn.Threads;

import cn.jakuxa.Algorithms.Tools.WebDownloader;

public class _Thread extends Thread {
    //学习通过继承thread的方法开启线程

    private static String url = "https://img.moegirl.org/common/a/a8/Gfspas12_1.png";
    private String path = "/Users/jakuxa/Downloads/";

    @Override
    public void run() {
        WebDownloader.download(url, path);
        System.out.println("文件"+path+"下载完毕");
    }

    public _Thread(String fname) {
        this.path = this.path + fname;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            _Thread td = new _Thread("SPAS" + i + ".png");
            //start开启线程，run会单独拉一条线程，线程开启不一定立即执行，由cpu调度执行
            td.start();
            //这里仅仅是调用方法，而非开启线程
//            td.run();
        }
    }
}

package cn.jakuxa.Algorithms.JavaLearn.Threads;

import cn.jakuxa.Algorithms.Tools.WebDownloader;

public class _Runnable implements Runnable{
    //学习实现runnable接口的方法开启线程

    private static String url = "https://img.moegirl.org/common/a/a8/Gfspas12_1.png";
    private String path = "/Users/jakuxa/Downloads/";

    @Override
    public void run() {
        WebDownloader.download(url, path);
        System.out.println("文件"+path+"下载完毕");
    }

    public _Runnable(String fname) {
        this.path = this.path + fname;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            _Runnable rbl = new _Runnable("SPAS" + i + ".png");
            //这里会比继承thread法，多一个用runnable构造thread的步骤
            new Thread(rbl).start();
        }
    }

}

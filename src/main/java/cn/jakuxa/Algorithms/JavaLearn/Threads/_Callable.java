package cn.jakuxa.Algorithms.JavaLearn.Threads;

import cn.jakuxa.Algorithms.Tools.WebDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//  Callable<>这里要添加泛型，规定返回值
public class _Callable implements Callable<String> {
    //学习通过实现callable接口的方法开启线程

    private static String url = "https://img.moegirl.org/common/a/a8/Gfspas12_1.png";
    private String path = "/Users/jakuxa/Downloads/";
    
    public static int total = 10;

    @Override
    //这里call方法的返回值要和上面的一致
    public String call() throws Exception {
        WebDownloader.download(url, path);
        System.out.println(path);
        return path;
    }

    public _Callable(String fname) {
        this.path = this.path + fname;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建执行服务
        ExecutorService exes = Executors.newFixedThreadPool(total);
        List<Future> callList = new ArrayList<Future>();
        for (int i = 0; i < total; i++) {
            //创建Call对象
            _Callable cbl = new _Callable("SPAS" + i + ".png");
            //提交执行
            Future<String> callFname = exes.submit(cbl);
            callList.add(callFname);
        }

        for (int i = 0;i < total;i++){
            //获取结果
            Future<String> callFname = callList.get(i);
            String fname = callFname.get();
            System.out.println(fname + "下载完毕");
        }
        //关闭服务
        exes.shutdownNow();
    }
}

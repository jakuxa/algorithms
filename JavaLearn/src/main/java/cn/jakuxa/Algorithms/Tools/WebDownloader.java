package cn.jakuxa.Algorithms.Tools;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class WebDownloader {

    public static void download(String url, String fname) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(fname));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("下载"+url+"到"+fname+"失败，IO异常" + e.getMessage());
        }
    }

}

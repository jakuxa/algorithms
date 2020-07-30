import org.apache.poi.ss.usermodel.Workbook;
import template.ExcelDataVO;
import utils.ExcelWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sql2Interface_xlsx {
    //将sql转换为xlsx接口文件
    //输入文件
    private static final String SOURCE_DIR = "F:\\sql\\国君-drs接口-sql";
    private static final String SOURCE_NAME = "tb_directed_customerinfo.sql";
    //输出文件
    private static final String TARGET_DIR = "F:\\sql\\国君-drs接口-excel";
    private static final String TARGET_NAME = "out.xlsx";

    public static void main(String[] args) {
        readSql(SOURCE_DIR + "\\" + SOURCE_NAME, TARGET_DIR + "\\" + TARGET_NAME);
    }

    static void readSql(String source, String target) {
        //开始读取
        File file = new File(source);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String tempString = null;
            //进入建表语句标志位
            Boolean creatingTable = false;
            //写行
            Integer row = 0;
            //写列
            Integer column = 0;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                writeExcel(tempString, row, column);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    //写进excel
    static void writeExcel(String content, Integer row, Integer column) {
        // 创建需要写入的数据列表
        List<ExcelDataVO> dataVOList = new ArrayList<>(2);
        ExcelDataVO dataVO = new ExcelDataVO();
        ExcelDataVO dataVO2 = new ExcelDataVO();
        dataVOList.add(dataVO);
        dataVOList.add(dataVO2);

        // 写入数据到工作簿对象内
        Workbook workbook = ExcelWriter.exportData(dataVOList);

        // 以文件的形式输出工作簿对象
        FileOutputStream fileOut = null;
        try {
            String exportFilePath = "/Users/Dreamer-1/Desktop/myBlog/java解析Excel/writeExample.xlsx";
            File exportFile = new File(exportFilePath);
            if (!exportFile.exists()) {
                exportFile.createNewFile();
            }

            fileOut = new FileOutputStream(exportFilePath);
            workbook.write(fileOut);
            fileOut.flush();
        } catch (Exception e) {
            System.out.println("输出Excel时发生错误，错误原因：" + e.getMessage());
        } finally {
            try {
                if (null != fileOut) {
                    fileOut.close();
                }
                if (null != workbook) {
                    workbook.close();
                }
            } catch (IOException e) {
                System.out.println("关闭输出流时发生错误，错误原因：" + e.getMessage());
            }
        }
    }
}
import org.pentaho.di.core.Const;
import org.pentaho.di.core.util.EnvUtil;
import template.DbInfo;
import template.FullArgFactory;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DecodeDBInfo {
    private static String WORK_DIR;
    //输入文件
    private static String SOURCE_FILE;
    //输出文件
    private static final String TARGET_FILE = "out.txt";

    public static void main(String[] args) {
        System.out.println("kettle数据库连接密码解密小工具\r\n请选择源文件,支持txt格式\r\n输入\"h\"，查看数据源格式\r\n输入\"q\"，退出");
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print("文件路径: ");
            SOURCE_FILE = sc.next();
            if("h".equals(SOURCE_FILE)) {
                help();
                continue;
            }if("q".equals(SOURCE_FILE)){
                break;
            }else{
                File source = new File(SOURCE_FILE);
                if(!source.exists()){
                    System.out.println("文件不存在!");
                    continue;
                }
                WORK_DIR = source.getParent();
                readTxt(source, WORK_DIR + File.separator + TARGET_FILE);
                System.out.println("\r\n完成，如要退出请输入q");
                continue;
            }
        }
        sc.close();
    }

    static void help(){
        System.out.println("\r\nsource.txt内容格式：");
        System.out.println("连接名称\thost主机\t端口\tSID\t用户名\t密码密文\r\n如：");
        System.out.println("DC-人行\t192.168.70.24\t1521\tora10g\tfcrm2\tEncrypted 2be98afc8\n" +
                "5.0\t192.168.1.1\t1521\tiii\t12\tEncrypted 2be98afc86\n" +
                "估值3.0\t10.20.29.60\t1521\torcl\tFD20190222J_TEST\tEncrypted 2be98af\r\n");
    }

    static void readTxt(File source, String target) {
        //开始读取
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(source), "UTF-8"));
            String tempString = null;
            List<DbInfo> dbInfoList = new ArrayList<>();
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                //System.out.println(tempString);
                DbInfo dbInfo = readTxt(tempString);
                dbInfoList.add(dbInfo);
            }
            writeTxt(dbInfoList, target);
        } catch (IOException e) {
            System.out.println("请输入有效的文件路径");
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

    //解析每一行记录
    static DbInfo readTxt(String record) {
        //空格分数组
        String[] recordArr = record.split("\\s+");
        String Encrypted = recordArr[6];
        String decoded = decryptPasswordInternal(recordArr[6]);
        DbInfo dbinfo = FullArgFactory.createDbInfo(recordArr[0], recordArr[1], Integer.parseInt(recordArr[2]), recordArr[3], recordArr[4], decoded);
        System.out.println(dbinfo.toString());
        return dbinfo;
    }

    //写入txt
    static void writeTxt(List<DbInfo> dbInfoList, String outPath) {
        if (dbInfoList == null) {
            return;
        }

        try {
            File outfile = new File(outPath);
            if (!outfile.exists())
                outfile.createNewFile();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile), "UTF-8"));
            for (DbInfo dbInfo : dbInfoList) {
//                System.out.println(dbInfo.toString());
                /** #数据中台DC中心   ：10.20.39.57/orcl   dct/hundsun */
                bw.write("# " + dbInfo.getName() + "   ：" + dbInfo.getHost() + "/" + dbInfo.getSid() + "" +
                        "   " + dbInfo.getUser() + "/" + dbInfo.getPwd() +"\r\n");
                /**
                 * data_center =
                 *  (DESCRIPTION=
                 *    (ADDRESS_LIST=
                 *    (ADDRESS=(PROTOCOL=TCP)(HOST=10.20.39.57)(PORT=1521))
                 *    )
                 *    (CONNECT_DATA=
                 *     (SID=orcl)(SERVER=DEDICATED)
                 *    )
                 *   )
                 */
                bw.write(" data_center =\r\n" +
                             "  (DESCRIPTION=\r\n" +
                             "    (ADDRESS_LIST=\r\n"+
                             "    (ADDRESS=(PROTOCOL=TCP)(HOST="+ dbInfo.getHost() +")(PORT="+ dbInfo.getPort() +"))\r\n" +
                             "    )\r\n" +
                             "    (CONNECT_DATA=\r\n" +
                             "     (SID="+ dbInfo.getSid() +")(SERVER=DEDICATED)\r\n" +
                             "    )\r\n" +
                             "   )");
                //连换三行
                bw.write("\r\n\r\n\r\n");
            }
            bw.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //打印数组
    static String array2str(String[] arr) {
        if (arr.length < 1) {
            return null;
        }
        StringBuffer l = new StringBuffer();
        l.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            l.append(",");
            l.append(arr[i]);
        }
        return "[" + l.toString() + "]";
    }

    /** 从 org.pentaho.di.core.encryption.Encr.decryptPasswordOptionallyEncrypted 追溯到的class中提取 */
    static String decryptPasswordInternal(String encrypted) {
        if (encrypted == null) {
            return "warnging: decode pwd failed";
        } else if (encrypted.length() == 0) {
            return "warnging: decode pwd failed";
        } else {
            BigInteger bi_confuse = new BigInteger(getSeed());
            try {
                BigInteger bi_r1 = new BigInteger(encrypted, 16);
                BigInteger bi_r0 = bi_r1.xor(bi_confuse);
                return new String(bi_r0.toByteArray());
            } catch (Exception var5) {
                return "warnging: decode pwd failed";
            }
        }
    }

    public static String getSeed() {
        String envSeed = Const.NVL(EnvUtil.getSystemProperty("KETTLE_TWO_WAY_PASSWORD_ENCODER_SEED"), "0933910847463829827159347601486730416058");
        return envSeed;
    }

}

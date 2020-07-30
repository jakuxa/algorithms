package template;

public class FullArgFactory {

    public static DbInfo createDbInfo(String name, String host,int port, String sid, String user, String pwd){
        DbInfo dbInfo = new DbInfo(name, host, port, sid, user, pwd);
        return dbInfo;
    }
}

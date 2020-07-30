package template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DbInfo {
    //连接名
    public String name;
    //主机名
    public String host;
    //端口
    public int port;
    //SID
    public String sid;
    //用户名
    public String user;
    //密码
    public String pwd;
}

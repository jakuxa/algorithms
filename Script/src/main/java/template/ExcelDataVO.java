package template;

/**
 * Author: Dreamer-1
 * Date: 2019-03-01
 * Time: 11:33
 * Description: 读取Excel时，封装读取的每一行的数据
 */
public class ExcelDataVO {

    /**
     * 字段名
     */
    private String name;

    /**
     * 是否为空，not null则为N, is null则省略
     */
    private Boolean notnull;

    /**
     * 数据类型
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNotnull() {
        return notnull;
    }

    public void setNotnull(Boolean notnull) {
        this.notnull = notnull;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ExcelDataVO(String name, Boolean notnull, String type, String desc) {
        this.name = name;
        this.notnull = notnull;
        this.type = type;
        this.desc = desc;
    }

    public ExcelDataVO(){

    }
}

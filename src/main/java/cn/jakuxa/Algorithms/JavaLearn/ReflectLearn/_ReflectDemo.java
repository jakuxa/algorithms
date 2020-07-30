package cn.jakuxa.Algorithms.JavaLearn.ReflectLearn;

import lombok.Data;

@Data
class Doll{
    public int uid;
    private String name;
    public String belong;
    private int stars;
}

class SF_Doll extends Doll{
    public SF_Doll(){
        this.belong = "铁血";
        this.setName("建筑师");
    }
}

class GF_Doll extends Doll{
    public GF_Doll(){
        this.belong = "格里芬";
        this.setName("SPAS-12");
    }
}

public class _ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        Doll d = new SF_Doll();

        //通过对象获得
        Class c1 = d.getClass();
        System.out.println(c1+" --- "+c1.hashCode());

        //通过包+类名获得
        Class c2 = Class.forName("cn.jakuxa.Algorithms.JavaLearn.ReflectLearn.SF_Doll");
        System.out.println(c2+" --- "+c2.hashCode());

        //通过类名获得
        Class c3 = SF_Doll.class;
        System.out.println(c3+" --- "+c3.hashCode());

        //大部分内置类都有个TYPE属性
        System.out.println(Integer.TYPE);
        System.out.println(Integer.class);

        //获得父类类型
        Class c4 = c3.getSuperclass();
        System.out.println(c4+" --- "+c4.hashCode());
    }

}
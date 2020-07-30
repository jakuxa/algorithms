package cn.jakuxa.Algorithms.JavaLearn.ReflectLearn;

import java.lang.annotation.ElementType;

public class _ClassElement {
    public static void main(String[] args) {

        Class c1 = Object.class;    //类
        Class c2 = Override.class;  //注解
        Class c3 = int[].class;     //数组
        Class c31 = int[][].class;
        Class c4 = ElementType.class;   //枚举类
        Class c5 = Comparable.class;    //接口
        Class c6 = void.class;      //void
        Class c7 = Class.class;     //Class本身

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c31);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
    }
}

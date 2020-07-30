package cn.jakuxa.Algorithms.Bean.BeanFactory;

//public class CarFactory {
//
//    public static Car Produce(String name){
//        if("五菱宏光".equals(name)){
//            return new WuLing();
//        }else if ("特斯拉".equals(name)){
//            return new Tesla();
//        }else {
//            return null;
//        }
//    }
//}

public interface CarFactory{
    Car getCar();
}

class TeslaFactory implements CarFactory{

    @Override
    public Car getCar() {
        return new Tesla();
    }
}

class WuLingFactory implements CarFactory{

    @Override
    public Car getCar() {
        return new WuLing();
    }
}
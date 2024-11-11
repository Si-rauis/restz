package edu.java.oop.abst;

class Product{
    String name;
    int price;
}

class TV extends Product{ }
class Monitor extends Product{ }
class Audio extends Product{ }

class Buyer {
    //TV, Monitor, Audio를 매개변수로 받아서
    //각각 다음과 같이 출력하고 반환값은 없는 buy()메서드 작성
    //TV를 구매합니다.
    public void buy(TV tv){ System.out.println(tv.name + "을 구매합니다."); }
    //Monitor를 구매합니다.
    public void buy(Monitor mon){
        System.out.println(mon.name + "을 구매합니다.");
    }
    //Audio를 구매합니다.
    public void buy(Audio aud){
        System.out.println(aud.name + "을 구매합니다.");
    }

    ////매개변수의 다양성
    public void buy(Product p){
        System.out.println(p.getClass().getSimpleName() + "를 구매합니다.");
    }
}


public class PolyProductMain {
    public static void main(String[] args) {
        //Buyer 클래스의 참조변수 b 생성
        Buyer b = new Buyer();

        TV t1 = new TV();
        b.buy(t1);

        Monitor mon1 = new Monitor();
        b.buy(mon1);

        Audio aud1 = new Audio();
        b.buy(aud1);

        System.out.println("----------------------------");
        Product p = new Product();
        p = t1;     b.buy(p);
        p = mon1;   b.buy(p);
        p = aud1;   b.buy(p);
    }
}

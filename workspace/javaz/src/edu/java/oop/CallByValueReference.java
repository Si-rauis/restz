package edu.java.oop;

public class CallByValueReference {

    public void printPizza(Pizza[] piz){
        System.out.println("----- PIZZA ORDER LIST -----");
        System.out.println("no.1 \t 피자 이름 \t 크기");
        for(int i = 0; i < piz.length; i++) {
            System.out.println((i + 1) + " \t " + piz[i].name + " \t " + piz[i].getRadius());
        }
        System.out.println("----------------------------");
        System.out.println("총 주문 수량 : " + piz.length);
    }

    public static void main(String[] args) {
        int medium = 15;
        int large = 20;

        //임의의 피자 객체 3개를 Pizza 배열에 저장하고
        //피자의 내용을 출력하는 메서드 호출
        Pizza[] piz = new Pizza[3];
        piz[0] = new Pizza(medium, "새우 피자");
        piz[1] = new Pizza(large, "감자 피자");
        piz[2] = new Pizza(medium, "치즈 피자");

        CallByValueReference call = new CallByValueReference();
        call.printPizza(piz);

        /*
        Pizza p1 = new Pizza(medium, "새우");
        Pizza p2 = new Pizza(large, "감자");

        System.out.println("더 큰 피자는 : " + Pizza.getLargePizza(p1,p2).name);

        Pizza.makeLargePizza(p1,30);
        System.out.println(p1.getRadius());

        p2.changeRadius(25);
        System.out.println(p2.getRadius());
        */
    }
}

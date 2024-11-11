package edu.java.oop;

//single pattern
//- 하나의 프로그램에서 인스턴스가 하나만 존재해야 하는 경우 사용

//현재 클래스의 객체를 한 개만 생성하여 모든 객체가 공유하여 사용할 수 있도록 구현
public class Singleton {

  private static Singleton instance = new Singleton(); //참조 변수 생성

  private Singleton(){ } //생성자 사용 불가

  public static Singleton getInstance(){
      if(instance == null) {
          return instance = new Singleton();
      } else{
          return instance;
      }
  } //참조 변수 값 리턴

}

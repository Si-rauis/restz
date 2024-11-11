package edu.java.lang;

class SamChoException extends Exception{
    public SamChoException(String message) {
        super(message);
    }
}

public class ExceptionMain {
    //2. 예외 간접 처리 - 예외를 호출한 쪽으로 던짐
    public static void countdown(int count) throws InterruptedException, SamChoException {
        if(count < 3){
            throw new SamChoException("카운트다운은 3초 이상으로 지정해주세요");
        }
        for(int i = count; i > 0;i--){
            System.out.println(i);
            Thread.sleep(1000);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        try{
            countdown(5);       //카운트다운 메서드 호출
                                //호출된 쪽에서 예외를 던진 경우
                                //이 부분에서 예외 처리 필요
        } catch (SamChoException e){
            System.out.println("사용자 정의 예외 발생");
            //System.err.println("예외 메시지 : " + e.);
        }

        //예외 직접 처리 -----------------------------------
        try { //예외 발생 가능 부분-------------------------
            System.out.println("1. 예외 발생 전");
//          System.out.println(args[2]);
            System.out.println(5/0);
            System.out.println("2. 예외 발생 후");
        } catch (NullPointerException e){ //발생된 예외 객체 받기
            System.out.println("3. NullPointerException 객체");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("4. ArrayIndexOutOfBoundsException 객체로 예외 잡기 성공");
        } catch (Exception e) {
            System.out.println("5. 최상위 예외 객체로 예외 처리!!");
            e.printStackTrace();
            System.out.println("예외 메세지: " + e.getMessage());
        } finally {
            System.out.println("6. 예외 발생 여부와 관계없이 항상 실행");
        }
    }
}

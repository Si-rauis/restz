package edu.java.basic;

public class Array2DEx {
    public static void main(String[] args) {

        int[][] studentGrade = {
                {100, 95, 98},
                {27, 48, 22},
                {69, 77, 80}
        };
        String[] subject = { "번호", "국어", "영어", "수학", "총점", "평균" };

        float avg = 0;
        int sum = 0;
        System.out.println("------------------------------------");
        for(String sub : subject){
            System.out.print(sub + "\t");
        }
        System.out.println();
        for(int i = 0; i < studentGrade.length; i++){
            System.out.print((i + 1) + "번\t");
            for (int j = 0; j < studentGrade[i].length; j++){
                System.out.print(studentGrade[i][j] + "\t");
                sum += studentGrade[i][j];
            }
            avg = (float)sum / studentGrade[i].length;
            System.out.print(sum + "\t" + avg + "\n");
            avg = 0;
            sum= 0;
        }
        System.out.println("------------------------------------");
        System.out.print("총점\t");
        for(int i = 0; i < studentGrade[0].length; i++){
            for(int j = 0; j < studentGrade.length; j++){
                sum += studentGrade[j][i];
            }
            System.out.print(sum + "\t");
            avg = (float)sum / studentGrade[0].length;
            sum = 0;
        }

        //print( )      - 괄호 안의 내용을 표시
        //println( )    - 괄호 안의 내용을 표시 한 후 줄바꿈
        //printf( )     - 형식을 지정해서 출력


    }
}

package AlgorithmStudy;
import java.util.*;
/**
 * @title  : 입국심사
 * @author : SI YOUNG
 */
public class 입국심사 {
    class Solution {
        public static int size;
        public static int people;
        public static int [] time;
        public long solution(int n, int[] times) {
            size = times.length;
            people = n;
            time = Arrays.copyOf(times, size);

            long answer = 0;
            long left = 1L; // 최소 걸리는 시간
            long right = (long) Arrays.stream(times).max().getAsInt() * n; // 최대 걸리는 시간

            while(left < right){
                long mid = (left + right) / 2;
                long count = check(mid);
                if(n <= count){
                    right = mid;
                }
                else left = mid + 1;
            }

            return left;
        }


        //입국 심사관들이 처리 가능한 사람의 숫자
        public static long check(long check){
            long sum = 0;
            for(int i = 0; i < size; i++){
                sum = sum + check / time[i];
            }
            return sum;
        }
    }
}

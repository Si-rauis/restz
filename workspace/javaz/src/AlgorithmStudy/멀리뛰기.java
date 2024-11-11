package AlgorithmStudy;
import java.util.*;
/**
 * @title  : 멀리뛰기
 * @author : SI YOUNG
 */

public class 멀리뛰기 {
        public long solution(int n) {
            long answer = 0;
            List<Long> num = new ArrayList<>();
            num.add(1L);
            num.add(2L);
            for(int i = 2; i < n; i++){
                num.add(num.get(i - 1) + num.get(i - 2)% 1234567);
            }
            answer = num.get(n - 1);
            return answer;


        }
}

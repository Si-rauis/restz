package AlgorithmStudy;
/**
 * @title  : 3 X N 타일링
 * @author : SI YOUNG
 */


public class 삼XN타일링 {

        public int solution(int n) {
            //f(n) = 3f(n-2) + 2(f(n-4) + f(n-6) + ... + f(2)) + 2
            //f(n-2) - f(n-4) = 2(f(n-4) + ... + f(2)) + 2 (식 2)
            //f(n) = 4f(n-2) - f(n-4)

            long[] num = new long[n + 1];

            num[2] = 3;
            num[4] = 11;

            //홀수면 0
            if(n % 2 == 1){
                return 0;
            } else {
                for(int i = 6; i <= n; i += 2){
                    //나머지가 음수가 되는 부분 고려
                    //예를들어 mod를 99라고 했을 때 원래 나누지 않았다면 100 - 10 로 계산되어야 하는 값이
                    // 1 - 10 로 계산되면서 음수가 나오게 됩니다.
                    // 음수가 나온다면 나온값에 +mod를 해줘서 해결 할 수 있습니다.
                    num[i] = (4 * num[i - 2] - num[i - 4] + 1000000007) % 1000000007;
                }
            }

            return (int)num[n];
        }
}

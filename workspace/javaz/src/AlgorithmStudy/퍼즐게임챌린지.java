package AlgorithmStudy;

/**
 * @title  : 퍼즐 게임 챌린지 Lv_2
 * @author : SI YOUNG
 */

public class 퍼즐게임챌린지 {
    class Solution {
        private static int max;
        private static int min;
        private static int result;

        public int solution(int[] diffs, int[] times, long limit) {

            max = 0;
            min = 1; // 최소값을 1로 설정하여 result가 0이 되는 것을 방지
            result = 1; // result의 초기값을 1로 설정

            // diffs에서 가장 큰 값 max로 초기화
            for(int i : diffs){
                if(max < i){
                    max = i;
                }
            }

            // 이진 탐색
            while(min <= max) {
                int mid = (max + min) / 2;

                // mid가 0일 때 유효성 체크
                if (mid == 0) {
                    // mid가 0일 때는 result가 최소 1 이상이 되도록 처리
                    if (possible(diffs, times, limit, 1)) {
                        result = 1;
                    }
                    break;
                }

                if (possible(diffs, times, limit, mid)) {
                    result = mid;  // 가능한 값 중 가장 큰 값을 저장
                    max = mid - 1; // 탐색 범위를 왼쪽으로 좁힘
                } else {
                    min = mid + 1; // 탐색 범위를 오른쪽으로 좁힘
                }
            }
            return result;
        }

        // possible 메서드가 boolean을 반환
        public static boolean possible(int[] diffs, int[] times, long limit, int mid) {
            long count = 0;

            for (int i = 0; i < diffs.length; i++) {
                if (mid >= diffs[i]) {
                    count += times[i];
                } else {
                    if (i - 1 >= 0) {
                        count += (diffs[i] - mid) * (times[i] + times[i - 1]) + times[i];
                    } else {
                        count += (diffs[i] - mid) * times[i];
                    }
                }
            }

            // limit을 넘지 않는 경우 true 반환
            return limit >= count;
        }
    }
}

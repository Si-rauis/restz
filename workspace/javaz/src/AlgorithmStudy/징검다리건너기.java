package AlgorithmStudy;
import java.util.*;

/**
 * @title  : 징검다리 건너기
 * @author : SI YOUNG
 */

public class 징검다리건너기 {
    class Solution {
        public int solution(int[] stones, int k) {
            int left = 1; // 최소 건널 수 있는 사람 수
            int right = Arrays.stream(stones).max().getAsInt(); // 최대 돌의 값(최대 건널 수 있는 사람 수)
            int answer = 0;

            while (left <= right) {
                int mid = (left + right) / 2;

                // 중간값(mid) 명이 건널 수 있는지 확인
                if (canCross(stones, k, mid)) {
                    answer = mid;  // 건널 수 있으면, 이 값을 일단 저장
                    left = mid + 1; // 더 많은 사람이 건널 수 있는지 확인
                } else {
                    right = mid - 1; // 건널 수 없으면, 사람 수를 줄여서 탐색
                }
            }

            return answer;
        }

        public static boolean canCross(int[] stones, int k, int mid) {
            int count = 0;

            for (int stone : stones) {
                if (stone - mid < 0) {
                    count++;  // 돌의 값이 중간값보다 작은 경우 카운트 증가
                    if (count == k) {
                        return false;  // 연속된 k개의 돌이 0보다 작아지면 건널 수 없음
                    }
                } else {
                    count = 0; // 연속된 0이 아니면 카운트 초기화
                }
            }

            return true;
        }
    }
}
